package Models;


public class SiteHistory {

    private String url, title, visitTime, userProfile;
    private int visitCount;

    public SiteHistory(String Url, String Title, String VisitTime, int VisitCount, String UserProfile){
        this.url = Url;
        this.title = Title;
        this.visitTime = VisitTime;
        this.visitCount = VisitCount;
        this.userProfile = UserProfile;
    }

    public String getUrl(){return this.url;}
    public String getTitle(){return this.title;}
    public String getVisitTime(){return this.visitTime;}
    public int getVisitCount(){return this.visitCount;}
    public String getUserProfile(){return this.userProfile;}
}
