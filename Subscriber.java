import java.io.Serializable;

public class Subscriber implements Serializable {
    private String fname, lname, city;
    private int phone;

    public Subscriber(String fname,  String city, String lname,int phone) {
        this.fname = fname;
        this.city = city;
        this.lname = lname;
        this.phone = phone;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
