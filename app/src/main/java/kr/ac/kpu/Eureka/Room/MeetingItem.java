package kr.ac.kpu.Eureka.Room;

/**
 * Created by KKS on 2017-02-02.
 */

public class MeetingItem {


    String market;
    String address;
    String numOfperson;
    String areaOfperson;
    String date;
    String _id;

    public MeetingItem(String market, String address, String numOfperson, String areaOfperson, String date,String e) {
        this.market = market;
        this.address = address;
        this.numOfperson = numOfperson;
        this.areaOfperson = areaOfperson;
        this.date = date;
        this._id = e;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumOfperson() {
        return numOfperson;
    }

    public void setNumOfperson(String numOfperson) {
        this.numOfperson = numOfperson;
    }

    public String getAreaOfperson() {
        return areaOfperson;
    }

    public void setAreaOfperson(String areaOfperson) {
        this.areaOfperson = areaOfperson;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
