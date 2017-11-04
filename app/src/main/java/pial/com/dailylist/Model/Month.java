package pial.com.dailylist.Model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by pial on 11/3/17.
 */

public class Month extends RealmObject {
    private int id;
    private String monthName;
    private int salary;
    private int expense;
    private int saving;
    private RealmList<Shopping> shoppingRealmList = new RealmList<>();
    private RealmList<Home> homeRealmList = new RealmList<>();
    private RealmList<Pocket> pocketRealmList = new RealmList<>();
    private RealmList<HouseShifting> houseShiftingRealmList = new RealmList<>();

    public RealmList<HouseShifting> getHouseShiftingRealmList() {
        return houseShiftingRealmList;
    }

    public void setHouseShiftingRealmList(HouseShifting houseShifting) {
        this.houseShiftingRealmList.add(houseShifting);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<Shopping> getShoppingRealmList() {
        return shoppingRealmList;
    }

    public void setShoppingRealmList(Shopping shop) {
        this.shoppingRealmList.add(shop);
    }

    public RealmList<Home> getHomeRealmList() {
        return homeRealmList;
    }

    public void setHomeRealmList(Home home) {
        this.homeRealmList.add(home);
    }

    public RealmList<Pocket> getPocketRealmList() {
        return pocketRealmList;
    }

    public void setPocketRealmList(Pocket pocket) {
        this.pocketRealmList.add(pocket);
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public int getSaving() {
        return saving;
    }

    public void setSaving(int saving) {
        this.saving = saving;
    }
}
