package pial.com.dailylist.Model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by pial on 11/3/17.
 */

public class Pocket extends RealmObject {
    private int id;
    private int budget;
    private int expense;
    private int Remaining;
    private int perDayCost;
    private RealmList<PocketDetails> pocketDetailses = new RealmList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<PocketDetails> getPocketDetailses() {
        return pocketDetailses;
    }

    public void setPocketDetailses(RealmList<PocketDetails> pocketDetailses) {
        this.pocketDetailses = pocketDetailses;
    }

    public int getPerDayCost() {
        return perDayCost;
    }

    public void setPerDayCost(int perDayCost) {
        this.perDayCost = perDayCost;
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
