package Models;

/**
 * The type Login (model).
 */
public class Login {
    private String url, username;

    /**
     * Instantiates a new Login.
     *
     * @param url      the url
     * @param username the username
     */
    public Login(String url, String username) {
        this.url = url;
        this.username = username;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    
}
