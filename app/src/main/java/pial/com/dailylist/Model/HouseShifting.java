package pial.com.dailylist.Model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by pial on 11/4/17.
 */

public class HouseShifting extends RealmObject {
    private int id;
    private int budget;
    private int expense;
    private int Remaining;
    private RealmList<HouseShiftingDetails> houseShiftingDetailsRealmList = new RealmList<>();

    public RealmList<HouseShiftingDetails> getHouseShiftingDetailsRealmList() {
        return houseShiftingDetailsRealmList;
    }

    public void setHouseShiftingDetailsRealmList(HouseShiftingDetails houseShiftingDetails) {
        this.houseShiftingDetailsRealmList.add(houseShiftingDetails);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
