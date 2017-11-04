package pial.com.dailylist.Model.AdpaterModel;

/**
 * Created by pial on 11/3/17.
 */

public class ContentList extends TotalList {
    private String cause;
    private int price;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String getCause() {
        return cause;
    }

    @Override
    public void setCause(String cause) {
        this.cause = cause;
    }
}
