package app.awa.com.switchprofile;

/**
 * Created by malli on 8/1/2016.
 */
public class EmpInfo {
    String lati,longi;

    public EmpInfo() {

    }


    public String getLati() {
        return lati;
    }

    public void setLati(String lati) {
        this.lati = lati;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public EmpInfo(String lati, String longi) {
        this.lati = lati;
        this.longi = longi;
    }
}
