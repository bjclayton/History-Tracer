package Models;

public class Downloads {
    private String referrer, current_path, total_bytes;

    public Downloads(String referrer, String current_path, String total_bytes) {
        this.referrer = referrer;
        this.current_path = current_path;
        this.total_bytes = total_bytes;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getCurrent_path() {
        return current_path;
    }

    public void setCurrent_path(String current_path) {
        this.current_path = current_path;
    }

    public String getTotal_bytes() {
        return total_bytes;
    }

    public void setTotal_bytes(String total_bytes) {
        this.total_bytes = total_bytes;
    }

    
}
