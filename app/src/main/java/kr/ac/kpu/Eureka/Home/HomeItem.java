package kr.ac.kpu.Eureka.Home;

import android.graphics.drawable.Drawable;

/**
 * Created by KKS on 2017-02-02.
 */

public class HomeItem {

    public Drawable icont;
    public String title;
    public String desc;
    public String id;

    //(Drawable icon, String title, String desc, String id
    public HomeItem(Drawable market, String address, String numOfperson, String areaOfperson) {
        this.icont = market;
        this.title = address;
        this.desc = numOfperson;
        this.id = areaOfperson;
    }
}
