package pial.com.dailylist.Model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by pial on 11/3/17.
 */

public class ShoppingDetails extends RealmObject {
    private String cause;
    private int price;
    private Date date;
    private int id;
    private int shoppingID;

    public int getShoppingID() {
        return shoppingID;
    }

    public void setShoppingID(int shoppingID) {
        this.shoppingID = shoppingID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
