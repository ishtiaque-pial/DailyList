package pial.com.dailylist.Model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by pial on 11/3/17.
 */

public class PocketDetails extends RealmObject {
    private String cause;
    private int price;
    private int id;
    private int pocketID;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPocketID() {
        return pocketID;
    }

    public void setPocketID(int pocketID) {
        this.pocketID = pocketID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
