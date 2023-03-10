package Models;

import java.util.Comparator;

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


  


    public String getUrl(){return this.url;}
    public String getTitle(){return this.title;}
    public String getVisitTime(){return this.visitTime;}
    public int getVisitCount(){return this.visitCount;}
    public String getUserProfile(){return this.userProfile;}
}
