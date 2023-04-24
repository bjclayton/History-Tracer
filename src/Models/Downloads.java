package Models;

/**
 * The type Downloads (model).
 */
public class Downloads {
    private String referrer, current_path, total_bytes;

    /**
     * Instantiates a new Downloads.
     *
     * @param referrer     the referrer
     * @param current_path the current path
     * @param total_bytes  the total bytes
     */
    public Downloads(String referrer, String current_path, String total_bytes) {
        this.referrer = referrer;
        this.current_path = current_path;
        this.total_bytes = total_bytes;
    }

    /**
     * Gets referrer.
     *
     * @return the referrer
     */
    public String getReferrer() {
        return referrer;
    }

    /**
     * Sets referrer.
     *
     * @param referrer the referrer
     */
    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    /**
     * Gets current path.
     *
     * @return the current path
     */
    public String getCurrent_path() {
        return current_path;
    }

    /**
     * Sets current path.
     *
     * @param current_path the current path
     */
    public void setCurrent_path(String current_path) {
        this.current_path = current_path;
    }

    /**
     * Gets total bytes.
     *
     * @return the total bytes
     */
    public String getTotal_bytes() {
        return total_bytes;
    }

    /**
     * Sets total bytes.
     *
     * @param total_bytes the total bytes
     */
    public void setTotal_bytes(String total_bytes) {
        this.total_bytes = total_bytes;
    }

    
}
