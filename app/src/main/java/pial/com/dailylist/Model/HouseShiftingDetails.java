package pial.com.dailylist.Model;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by pial on 11/4/17.
 */

public class HouseShiftingDetails extends RealmObject {
    private String cause;
    private int price;
    private int id;
    private int houseShiftingID;
    private Date date;

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHouseShiftingID() {
        return houseShiftingID;
    }

    public void setHouseShiftingID(int houseShiftingID) {
        this.houseShiftingID = houseShiftingID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
