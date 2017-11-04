package pial.com.dailylist.Model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by pial on 11/3/17.
 */

public class Shopping extends RealmObject {
    private int budget;
    private int expense;
    private int Remaining;
    private int id;
    private RealmList<ShoppingDetails> shoppingDetailses = new RealmList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<ShoppingDetails> getShoppingDetailses() {
        return shoppingDetailses;
    }

    public void setShoppingDetailses(ShoppingDetails shoppingDetailses) {
        this.shoppingDetailses.add(shoppingDetailses);
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public int getRemaining() {
        return Remaining;
    }

    public void setRemaining(int remaining) {
        Remaining = remaining;
    }
}
