package pial.com.dailylist.root;

import android.app.Application;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import pial.com.dailylist.Model.Home;
import pial.com.dailylist.Model.HomeDetails;
import pial.com.dailylist.Model.HouseShifting;
import pial.com.dailylist.Model.Month;
import pial.com.dailylist.Model.Pocket;
import pial.com.dailylist.Model.PocketDetails;
import pial.com.dailylist.Model.Shopping;
import pial.com.dailylist.Model.ShoppingDetails;

/**
 * Created by pial on 11/3/17.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        //RealmConfiguration config = new RealmConfiguration.Builder().name("dailyList.realm").build();
        RealmConfiguration config = new RealmConfiguration.Builder()
                .initialData(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Month month = realm.createObject(Month.class);
                        month.setId(1);
                        month.setMonthName("November");
                        month.setSalary(15000);
                        month.setExpense(0);
                        month.setSaving(15000);

                        Shopping shopping = realm.createObject(Shopping.class);
                        shopping.setBudget(1500);
                        shopping.setExpense(0);
                        shopping.setId(1);
                        shopping.setRemaining(1500);
                        month.setShoppingRealmList(shopping);

                        Home home = realm.createObject(Home.class);
                        home.setBudget(1800);
                        home.setExpense(0);
                        home.setRemaining(1800);
                        home.setId(2);
                        month.setHomeRealmList(home);

                        Pocket pocket = realm.createObject(Pocket.class);
                        pocket.setBudget(4000);
                        pocket.setExpense(0);
                        pocket.setId(3);
                        pocket.setRemaining(4000);
                        pocket.setPerDayCost(133);
                        month.setPocketRealmList(pocket);

                        HouseShifting houseShifting = realm.createObject(HouseShifting.class);
                        houseShifting.setBudget(5700);
                        houseShifting.setExpense(0);
                        houseShifting.setId(4);
                        houseShifting.setRemaining(5700);
                        month.setHouseShiftingRealmList(houseShifting);

                        realm.close();

                    }
                }).name("LLLLfjhghjasgisthggjhgVsihjgon789.realm")
                .schemaVersion(5)
                .build();
        Realm.setDefaultConfiguration(config);

    }
}
