package Models;

import java.util.Comparator;

/**
 * The type Site history (model).
 */
public class SiteHistory {
    private String url, title, visitTime, userProfile;
    private int visitCount;

    /**
     * Instantiates a new Site history.
     *
     * @param Url         the url
     * @param Title       the title
     * @param VisitTime   the visit time
     * @param VisitCount  the visit count
     * @param UserProfile the user profile
     */
    public SiteHistory(String Url, String Title, String VisitTime, int VisitCount, String UserProfile){
        this.url = Url;
        this.title = Title;
        this.visitTime = VisitTime;
        this.visitCount = VisitCount;
        this.userProfile = UserProfile;
    }


    /**
     * Instantiates a new Site history.
     *
     * @param url        the url
     * @param title      the title
     * @param visitCount the visit count
     */
    public SiteHistory(String url, String title, int visitCount) {
        this.url = url;
        this.title = title;
        this.visitCount = visitCount;
    }

    @Override
    public boolean equals(Object obj){   
        // checking if both the object references are referring to the same object.
        if(this == obj){
            return true;
        }
            
        // it checks if the argument is of the type SiteHistory by comparing the classes 
        // of the passed argument and this object.
        if(obj == null || obj.getClass()!= this.getClass())
            return false;
        
        // type casting of the argument. 
        SiteHistory site = (SiteHistory) obj;
        
        // comparing the state of argument with 
        // the state of 'this' Object.
        return (site.url == this.url && site.title == this.title && site.visitCount == this.visitCount );
    }


    /**
     * The type Comparator visit count.
     */
    public static class ComparatorVisitCount implements Comparator<SiteHistory> {
        // Sorting in ascending order of visit_count
        @Override
        public int compare(SiteHistory a, SiteHistory b){
            if (a.visitCount > b.visitCount)
                return 1;
            else if (a.visitCount < b.visitCount)
                return -1;
            return 0; 
        }
    }


    /**
     * Get url string.
     *
     * @return the string
     */
    public String getUrl(){return this.url;}

    /**
     * Get title string.
     *
     * @return the string
     */
    public String getTitle(){return this.title;}

    /**
     * Get visit time string.
     *
     * @return the string
     */
    public String getVisitTime(){return this.visitTime;}

    /**
     * Get visit count int.
     *
     * @return the int
     */
    public int getVisitCount(){return this.visitCount;}

    /**
     * Get user profile string.
     *
     * @return the string
     */
    public String getUserProfile(){return this.userProfile;}
}
