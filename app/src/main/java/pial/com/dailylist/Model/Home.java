package pial.com.dailylist.Model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by pial on 11/3/17.
 */

public class Home extends RealmObject {
    private int id;
    private int budget;
    private int expense;
    private int Remaining;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private RealmList<HomeDetails> homeDetailses = new RealmList<>();

    public RealmList<HomeDetails> getHomeDetailses() {
        return homeDetailses;
    }

    public void setHomeDetailses(RealmList<HomeDetails> homeDetailses) {
        this.homeDetailses = homeDetailses;
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
