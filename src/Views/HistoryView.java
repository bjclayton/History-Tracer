package Views;

import Models.TreeNode;
import Models.Downloads;
import Models.Login;
import Models.SiteHistory;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import Helper.Constant;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;


/**
 * The type History view.
 * This abstract class implements the main application window that
 * displays browsing history data, downloaded files, and bookmarks for different browsers.
 */
public abstract class HistoryView extends JFrame {
    private JToolBar barre = new JToolBar();
    private JTextField searchView = new JTextField();
    private JScrollPane scrollTree;
    private static JTable table;
    private JScrollPane scrollTable;
    private JTree tree;
    private DefaultMutableTreeNode root;
    private static String  browserSelected = "";
    private static int row, column;
    private static ArrayList<SiteHistory> sites;
    private static ArrayList<Login> logins;
    private static ArrayList<Downloads> downloads;

    private final JButton copy = new JButton("Copy", new ImageIcon(Constant.getIcons().getIconCopy()));
    private final JButton refresh = new JButton("Refresh", new ImageIcon(Constant.getIcons().getIconRefresh()));
    private final JButton sellectAll = new JButton("Select All", new ImageIcon(Constant.getIcons().getIconSelectAll()));
    private final JButton sort = new JButton("Sort By", new ImageIcon(Constant.getIcons().getIconSort()));
    private final JButton more = new JButton("More", new ImageIcon(Constant.getIcons().getIconMore()));


    /**
     * The Col heads.
     */
    final String[] colHeads = {Constant.getTable().getTableSite().get(0),
                                Constant.getTable().getTableSite().get(1),
                                Constant.getTable().getTableSite().get(2),
                                Constant.getTable().getTableSite().get(3), 
                                Constant.getTable().getTableSite().get(4)};

    /**
     * The Col heads download.
     */
    final String[] colHeadsDownload = {"Url", "Current Path", "Total Bytes"};
    /**
     * The Col heads login.
     */
    final String[] colHeadsLogin = {"Url", "Username"};

    /**
     * The Data.
     */
    String[][] data = {{"", "", "", "", ""}};
    /**
     * The Login data.
     */
    String[][] loginData = {{"", ""}};
    /**
     * The Download data.
     */
    String[][] downloadData = {{"", "", ""}};
    private String OSName = System.getProperty("os.name"); // get the OS name

    private static final JPopupMenu sortList = new JPopupMenu("Popup Sort");
    private static final JRadioButton  Ascending = new JRadioButton ("Ascending");
    private static final JRadioButton  Descending = new JRadioButton ("Descending");
    private static final JPopupMenu morePop = new JPopupMenu("More");

    /**
     * Chrome's history.
     * Get Chrome browser browsing history data.
     *
     * @param choice the choice
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    public abstract void ChromeHistory(String choice) throws IOException, SQLException;

    /**
     * Microsoft edge's history.
     * Get Microsoft edge browsing history data.
     *
     * @param choice the choice
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    public abstract void microsoftEdgeHistory(String choice) throws IOException, SQLException;

    /**
     * Firefox's history.
     * Get Firefox browsing history data.
     *
     * @param choice the choice
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    public abstract void firefoxHistory(String choice) throws IOException, SQLException;

    /**
     * Opera's history.
     * Get Opera browsing history data.
     *
     * @param choice the choice
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    public abstract void operaHistory(String choice) throws IOException, SQLException;

    /**
     * Vivaldi's history.
     * Get Vivaldi browsing history data.
     *
     * @param choice the choice
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    public abstract void vivaldiHistory(String choice) throws IOException, SQLException;

    /**
     * Brave's history.
     * Get Brave browsing history data.
     *
     * @param choice the choice
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    public abstract void braveHistory(String choice) throws IOException, SQLException;

    /**
     * Browser download.
     *
     * @param name the name
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    public abstract void browserDownload(String name) throws IOException, SQLException;

    /**
     * Browser logins.
     *
     * @param name the name
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    public abstract void browserLogins(String name) throws IOException, SQLException;

    /**
     * Export data.
     *
     * @param data the data
     */
    public abstract void exportData(ArrayList<SiteHistory> data);

    /**
     * Instantiates a new History view.
     */
    public HistoryView() {
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("History Tracer");

        Image icon = Toolkit.getDefaultToolkit().getImage(Constant.getIcons().getIconApp());
        setIconImage(icon); // Add icon

        mainMenu();
        Menu(); // Menu
        listRoot(); // List all browsers
        Display(); // Display the Tree and JTable
        buttonToolbarAction(); // When user click a button on the toolbar
        popUpSort(); // Display a popup to sort
        popUpMore(); // Display a popup to more
        setVisible(true);
    }


    /**
     * Main menu.
     */
    public void mainMenu(){
        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();
        
        // Create the File menu and its items
        JMenu fileMenu = new JMenu("File");
        JMenuItem export = new JMenuItem("Export");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(export);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportData(sites);
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });

        /**
         * Create the Help menu and its items
         */
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PopupHelp();
            }
        });
         
        // Add the menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        
        // Set the menu bar for the frame
        setJMenuBar(menuBar);
    }


    /**
     * Menu and toolbar.
     */
    public void Menu() {
        barre.setFloatable(false);
        // put text on bottom of theses buttons
        copy.setVerticalTextPosition(JButton.BOTTOM);
        copy.setHorizontalTextPosition(JButton.CENTER);
        refresh.setVerticalTextPosition(JButton.BOTTOM);
        refresh.setHorizontalTextPosition(JButton.CENTER);

        sellectAll.setVerticalTextPosition(JButton.BOTTOM);
        sellectAll.setHorizontalTextPosition(JButton.CENTER);

        // add button to toolbar
        barre.add(refresh);
        barre.addSeparator(); // add a separator on the toolbar
        barre.add(copy);
        barre.addSeparator();
        barre.add(sellectAll);

        sort.setVerticalTextPosition(JButton.BOTTOM);
        sort.setHorizontalTextPosition(JButton.CENTER);

        more.setVerticalTextPosition(JButton.BOTTOM);
        more.setHorizontalTextPosition(JButton.CENTER);

        barre.addSeparator();
        barre.add(sort);

        barre.addSeparator();
        barre.add(more);

        barre.addSeparator();
        searchView.setMaximumSize(new Dimension(200, 20)); // size for the searchView
        searchView.setText("Search by title"); // a default placeholder on the searchView
        barre.add(searchView);

        add(barre, BorderLayout.NORTH);
    }


    /**
     * Display the data in the table
     */
    private void Display() {
        scrollTree = new JScrollPane(tree);

        final String[] colHeads = {Constant.getTable().getTableSite().get(0), 
                                    Constant.getTable().getTableSite().get(1),
                                    Constant.getTable().getTableSite().get(2),
                                    Constant.getTable().getTableSite().get(3), 
                                    Constant.getTable().getTableSite().get(4)};

        String[][] data = {{"", "", "", "", ""}};
        table = new JTable(data, colHeads);
        scrollTable = new JScrollPane(table);

        add(scrollTree, BorderLayout.WEST);
        add(scrollTable, BorderLayout.CENTER);


        /**
         * Click on tree
         */
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                // get the node selected
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();

                /**
                 * Get value of the node selected by the user
                 */
                if (node.getUserObject() instanceof TreeNode){
                    TreeNode nodeSelected = (TreeNode) node.getUserObject();

                    try {
                        // call method "doMouseClicked"
                        doMouseClicked(nodeSelected.getValue());
                    } catch (IOException | SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        /**
         * Clear placeholder searchView
         */
        searchView.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                searchView.setText("");
            }
        });


        /**
         * Search text in the database
         */
        searchView.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                // check when user press Enter
                if (key == KeyEvent.VK_ENTER) {
                    Toolkit.getDefaultToolkit().beep();

                    // check if searchView is empty
                    if (searchView.getText().equals("")) {
                        try {
                            decision(browserSelected, "Display");
                            searchView.setText("Search by title");
                        } catch (IOException | SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        try {
                            // search the word
                            decision(browserSelected, searchView.getText().toString());
                            searchView.setText("Search by title");
                        } catch (IOException | SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }
            }
        });
    }


    /**
     * Create a JTree
     * The nodes are: Chrome, Firefox, Vivaldi, Brave ...
     */
    private void listRoot() {
        tree = new JTree(this.root);
        getContentPane().add(new JScrollPane(tree)); // tree's JScrollPane

        /**
         * Root of the tree
         */
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("");

        /**
         * Create all node
         */
        DefaultMutableTreeNode dashboard = new DefaultMutableTreeNode(new TreeNode(Constant.getDashboard().getName(), Constant.getDashboard().getName(), Constant.getDashboard().getIconSrc()));
        DefaultMutableTreeNode chrome = new DefaultMutableTreeNode(new TreeNode(Constant.getChrome().getName(), Constant.getChrome().getName(), Constant.getChrome().getIconSrc()));
        DefaultMutableTreeNode microsoftEdge = new DefaultMutableTreeNode(new TreeNode(Constant.getMicrosoftEdge().getName(), Constant.getMicrosoftEdge().getName(), Constant.getMicrosoftEdge().getIconSrc()));
        DefaultMutableTreeNode firefox = new DefaultMutableTreeNode(new TreeNode(Constant.getFirefox().getName(), Constant.getFirefox().getName(), Constant.getFirefox().getIconSrc()));
        DefaultMutableTreeNode opera = new DefaultMutableTreeNode(new TreeNode(Constant.getOpera().getName(), Constant.getOpera().getName(), Constant.getOpera().getIconSrc()));
        DefaultMutableTreeNode vivaldi = new DefaultMutableTreeNode(new TreeNode(Constant.getVivaldi().getName(), Constant.getVivaldi().getName(), Constant.getVivaldi().getIconSrc()));
        DefaultMutableTreeNode brave = new DefaultMutableTreeNode(new TreeNode(Constant.getBrave().getName(), Constant.getBrave().getName(), Constant.getBrave().getIconSrc()));


        /**
         * Add node to the root
         */
        root.add(dashboard);
        root.add(chrome);
        root.add(microsoftEdge);
        root.add(firefox);
        root.add(opera);
        root.add(vivaldi);
        root.add(brave);

        DefaultTreeModel defaultTree = new DefaultTreeModel(root); // add root to DefaultTreeModel
        tree.setModel(defaultTree);
        tree.setCellRenderer(new nodeTreeCellRender());

        getContentPane().add(new JScrollPane(tree)); // tree's JScrollPane
    }


    /**
     * Do mouse clicked.
     *
     * @param nodeSelected the node selected
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    void doMouseClicked(String nodeSelected) throws IOException, SQLException {

        // Check click
        if (nodeSelected == null)
            return;

        browserSelected = nodeSelected;
        Constant.setBrowserSelected(browserSelected);
        decision(browserSelected, "Display");
    }

    /**
     * Decision .
     * Choice which the right browser
     *
     * @param browserSelected the browser selected
     * @param choice          the choice
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    public void decision(String browserSelected, String choice) throws IOException, SQLException {
        if (browserSelected.length() != 0) {
            try{
                switch (browserSelected.trim()) {
                    case "Chrome":
                        ChromeHistory(choice);
                        break;
                    case "Microsoft Edge":
                        microsoftEdgeHistory(choice);
                        break;
                    case "Firefox":
                        firefoxHistory(choice);
                        break;
                    case "Opera":
                        operaHistory(choice);
                        break;
                    case "Vivaldi":
                        vivaldiHistory(choice);
                        break;
                    case "Brave":
                        braveHistory(choice);
                        break;
                    default:
                        new DashboardView();
                        break;
                }
            }catch (Exception e){

            }

        }
    }

    /**
     * Mouse position.
     * Get position mouse click on JTable
     */
    void mousePosition(){
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                row = table.rowAtPoint(e.getPoint());
                column = table.columnAtPoint(e.getPoint());
            }
        });

    }


    /**
     * Show details.
     * Show data on JTable
     *
     * @param listInfo the list info
     * @throws SQLException the sql exception
     */
    protected void showDetails(ArrayList<SiteHistory> listInfo) throws SQLException {
        sites = listInfo; // Take a copy of listInfo

        String[][] data = {{"", "", "", "", ""}};
        remove(scrollTable);

        table = new JTable(data, colHeads) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

        table.setShowGrid(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);

        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(true);

        scrollTable = new JScrollPane(table);
        add(scrollTable, BorderLayout.CENTER);
        setVisible(true);

        int fileCounter = 0;
        data = new String[listInfo.size()][5];

        for (SiteHistory details : listInfo) {
            data[fileCounter][0] = details.getUrl();
            data[fileCounter][1] = details.getTitle();
            data[fileCounter][2] = convertTime(details.getVisitTime());
            data[fileCounter][3] = String.valueOf(details.getVisitCount());
            data[fileCounter][4] = details.getUserProfile();

            fileCounter++;
        }

        String[][] dataTemp = new String[fileCounter][5];
        System.arraycopy(data, 0, dataTemp, 0, fileCounter);
        data = dataTemp;

        remove(scrollTable);

        table = new JTable(data, colHeads) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

        /**
         * Add mouse click when use select a row to display more details
         */
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if(e.getClickCount() == 2){
                    new PopupDetail(); // Call popup detail
                    row = table.rowAtPoint(e.getPoint());
                    column = table.columnAtPoint(e.getPoint());

                    SiteHistory history = sites.get(row);
                    PopupDetail.setTxtTitle(history.getTitle());
                    PopupDetail.setTxtUrl(history.getUrl());
                    PopupDetail.setTxtTime(convertTime(history.getVisitTime()));
                    PopupDetail.setTxtVisitCount(String.valueOf(history.getVisitCount()));
                }
            }
        });


        table.setShowGrid(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setCellSelectionEnabled(true);

        scrollTable = new JScrollPane(table);
        add(scrollTable, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Method to convert timeStamp
     * Convert time string.
     *
     * @param date the date
     * @return the string
     */
    public String convertTime(String date){
        // Getting the current system time and passing it and passing the long value in the Date class
        if(date != null){
            Timestamp ts = new Timestamp(Long.parseLong(date));
            Date newDate = new Date(ts.getTime());
            return String.valueOf(newDate);
        }else {
            return "None";
        }
    }


    /**
     * Show downloads.
     *
     * @param listInfo the list info
     * @throws SQLException the sql exception
     */
    public void showDownloads(ArrayList<Downloads> listInfo) throws SQLException {
        downloads = listInfo; // Take a copy of listInfo

        String[][] downloadData = {{"", "", ""}};
        remove(scrollTable);

        table = new JTable(downloadData, colHeadsDownload) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

        table.setShowGrid(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);

        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(true);

        scrollTable = new JScrollPane(table);
        add(scrollTable, BorderLayout.CENTER);
        setVisible(true);

        int counter = 0;
        downloadData = new String[listInfo.size()][3];

        for (Downloads details : listInfo) {
            downloadData[counter][0] = details.getReferrer();
            downloadData[counter][1] = details.getCurrent_path();
            downloadData[counter][2] = details.getTotal_bytes();
            counter++;
        }

        String[][] dataTemp = new String[counter][3];
        System.arraycopy(downloadData, 0, dataTemp, 0, counter);
        downloadData = dataTemp;

        remove(scrollTable);

        table = new JTable(downloadData, colHeadsDownload) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };


        table.setShowGrid(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setCellSelectionEnabled(true);

        scrollTable = new JScrollPane(table);
        add(scrollTable, BorderLayout.CENTER);
        setVisible(true);
    }


    /**
     * Show credentials.
     *
     * @param listInfo the list info
     * @throws SQLException the sql exception
     */
    public void showCredentials(ArrayList<Login> listInfo) throws SQLException {
        logins = listInfo; // Take a copy of listInfo

        String[][] loginData = {{"", "", ""}};
        remove(scrollTable);

        table = new JTable(loginData, colHeadsLogin) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

        table.setShowGrid(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);

        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(true);

        scrollTable = new JScrollPane(table);
        add(scrollTable, BorderLayout.CENTER);
        setVisible(true);

        int counter = 0;
        loginData = new String[listInfo.size()][2];

        for (Login details : listInfo) {
            loginData[counter][0] = details.getUrl();
            loginData[counter][1] = details.getUsername();
            counter++;
        }

        String[][] dataTemp = new String[counter][2];
        System.arraycopy(loginData, 0, dataTemp, 0, counter);
        loginData = dataTemp;

        remove(scrollTable);

        table = new JTable(loginData, colHeadsLogin) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };


        table.setShowGrid(false);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setCellSelectionEnabled(true);

        scrollTable = new JScrollPane(table);
        add(scrollTable, BorderLayout.CENTER);
        setVisible(true);
    }


    /**
     * Copy database.
     * Copy the database (To avoid an error like "database is locked")
     *
     * @param path the path
     * @throws IOException  the io exception
     * @throws SQLException the sql exception
     */
    public void copyDatabase(String path) throws IOException, SQLException {
        File source = new File(path);
        File destination = null;

        if (OSName.contains(Constant.getWindows().getName())){
            destination = new File("windowsDatabase.sqlite");
            Files.deleteIfExists(Path.of("windowsDatabase.sqlite"));
            Files.copy(source.toPath(), destination.toPath());

        } else if (Objects.equals(OSName, Constant.getLinux().getName())) {
            Files.deleteIfExists(Path.of("linuxDatabase.sqlite"));
            destination = new File("linuxDatabase.sqlite");
            Files.copy(source.toPath(), destination.toPath());
        }else {
            System.out.println("Other OS");
        }
    }


    /**
     * Pop up sort by (title, date, and Visit count).
     */
    static void popUpSort() {
        JMenuItem Title = new JMenuItem("Title");
        JMenuItem Date = new JMenuItem("Date");
        JMenuItem vCount = new JMenuItem("Visit count");

        ButtonGroup group = new ButtonGroup();

        group.add(Ascending);
        group.add(Descending);

        /**
         * Sort by title
         */
        Title.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    sortBy("Title", Descending.isSelected());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


        /**
         *  Sort by date
         */
        Date.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    sortBy("Date", Descending.isSelected());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


        /**
         * Sort by Visit count
         */
        vCount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    sortBy("Visit count", Descending.isSelected());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });


        /**
         * add item to the popup
         */
        sortList.add(Title);
        sortList.add(Date);
        sortList.add(vCount);
        sortList.addSeparator();
        sortList.add(Ascending);
        sortList.add(Descending);
    }

    /**
     * Check if the user want to sort by title, date or Visit count
     *
     * @param choice1 the choice 1
     * @param choice2 the choice 2
     * @throws IOException the io exception
     */
    static void sortBy(String choice1, boolean choice2) throws IOException{
        int SortColNo = 0;


        switch(choice1) {
            case "Title":
                SortColNo = 1;
                break;
            case "Date":
                SortColNo = 2;
                break;
            case "Visit count":
                SortColNo = 3;
                break;
        }

        TableRowSorter<TableModel> ColSort = new TableRowSorter<>(table.getModel());
        table.setRowSorter(ColSort);
        ArrayList<RowSorter.SortKey> ColSortingKeys = new ArrayList<>();

        /**
         * check if user select ASCENDING or ASCENDING
         */
        if ("true".equals(String.valueOf(choice2))) {
            ColSortingKeys.add(new RowSorter.SortKey(SortColNo, SortOrder.DESCENDING));
        } else {
            ColSortingKeys.add(new RowSorter.SortKey(SortColNo, SortOrder.ASCENDING));
        }

        ColSort.setSortKeys(ColSortingKeys);
        ColSort.sort();

        Descending.setSelected(false);
        Ascending.setSelected(true);
    }


    /**
     * Pop up more.
     */
    public void popUpMore() {
        JMenuItem download = new JMenuItem("Downloads");
        JMenuItem login = new JMenuItem("Logins");

        download.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (browserSelected.length() != 0) {
                        browserDownload(browserSelected);
                    }
                } catch (IOException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });


        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (browserSelected.length() != 0) {
                        browserLogins(browserSelected);
                    }
                } catch (IOException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });


        // add item to the popup
        morePop.add(download);
        morePop.add(login);
    }

    /**
     * Button toolbar action (Sort, SelectAll, Copy, ...).
     * When user click button on the toolbar
     */
    void buttonToolbarAction(){

        /**
         * Button Sort action
         */
        sort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortList.show(sort, sort.getWidth()/2-26, sort.getHeight()/2+30); // show popup
            }
        });


        /**
         * Button More action
         */
        more.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            morePop.show(more, more.getWidth()/2-21, more.getHeight()/2+30); // show popup
            }
        });


        /**
         * Button SelectAll action
         */
        sellectAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                table.selectAll();
            }
        });


        /**
         * Button copy (copy text)
         */
        copy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

                mousePosition();

                String text = String.valueOf(table.getValueAt(row, column)); // get value at position mouse click
                StringSelection selection = new StringSelection(text); // copy the text
                clipboard.setContents(selection, null);
            }
        });


        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    decision(browserSelected, "Display");
                } catch (IOException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }


    private class nodeTreeCellRender extends DefaultTreeCellRenderer{
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            Component component = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            if (node.isLeaf()){
                TreeNode treeNode = (TreeNode) node.getUserObject();
                setText(treeNode.getValue());
                ImageIcon icon = new ImageIcon(new ImageIcon(treeNode.getIcon()).getImage()
                        .getScaledInstance(24, 24, Image.SCALE_DEFAULT));
                setIcon(icon);
            }else {
                setLeafIcon(null);
                setClosedIcon(null);
                setOpenIcon(null);
            }

            return component;
        }
    }
}
