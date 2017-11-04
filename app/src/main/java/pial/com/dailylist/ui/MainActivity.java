package pial.com.dailylist.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import pial.com.dailylist.Model.Month;
import pial.com.dailylist.R;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.shoppingCardView)
    CardView shoppingCardView;
    @BindView(R.id.homeCardView)
    CardView homeCardView;
    @BindView(R.id.pocketCardView)
    CardView pocketCardView;
    @BindView(R.id.houseShiftingCardView)
    CardView houseShiftingCardView;
    @BindView(R.id.mainSalary)
    TextView mainSalaryTV;
    @BindView(R.id.mainExpense)
    TextView mainExpenseTV;
    @BindView(R.id.mainSaving)
    TextView mainSavingTV;
    @BindView(R.id.shoppingBudget)
    TextView shoppingBudgetTV;
    @BindView(R.id.shoppingExpense)
    TextView shoppingExpenseTV;
    @BindView(R.id.shoppingRemaining)
    TextView shoppingRemainingTV;
    @BindView(R.id.homeBudget)
    TextView homeBudgetTV;
    @BindView(R.id.homeExpense)
    TextView homeExpenseTV;
    @BindView(R.id.homeRemaining)
    TextView homeRemainingTV;
    @BindView(R.id.pocketBudget)
    TextView pocketBudgetTV;
    @BindView(R.id.pocketExpense)
    TextView pocketExpenseTV;
    @BindView(R.id.pocketRemaining)
    TextView pocketRemainingTV;
    @BindView(R.id.pocketPerDayCost)
    TextView pocketPerDayCostTV;
    @BindView(R.id.houseShiftingBudget)
    TextView houseShiftingBudgetTV;
    @BindView(R.id.houseShiftingExpense)
    TextView houseShiftingExpenseTV;
    @BindView(R.id.houseShiftingRemaining)
    TextView houseShiftingRemainingTV;
    private Realm realm;
    private long diffDays=0;
    private String dateFromServer = "2017-11-30";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        Log.e("error",""+realm.where(Month.class).findAll());
        shoppingCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ShoppingDetailsActivity.class);
                startActivity(intent);
            }
        });
        pocketCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PocketDetailsActivity.class);
                startActivity(intent);
            }
        });
        homeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HomeDeatilsActivity.class);
                startActivity(intent);
            }
        });
        houseShiftingCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HouseShiftingDetailsActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Month month = realm.where(Month.class).findFirst();
        mainSalaryTV.setText("Salary: "+String.valueOf(month.getSalary()));
        mainExpenseTV.setText("Total Expense: "+String.valueOf(month.getExpense()));
        mainSavingTV.setText("Saving: "+String.valueOf(month.getSaving()));

        shoppingBudgetTV.setText("Budget: "+String.valueOf(month.getShoppingRealmList().get(0).getBudget()));
        shoppingExpenseTV.setText("Expense: "+String.valueOf(month.getShoppingRealmList().get(0).getExpense()));
        shoppingRemainingTV.setText("Remaining: "+String.valueOf(month.getShoppingRealmList().get(0).getRemaining()));

        homeBudgetTV.setText("Budget: "+String.valueOf(month.getHomeRealmList().get(0).getBudget()));
        homeExpenseTV.setText("Expense: "+String.valueOf(month.getHomeRealmList().get(0).getExpense()));
        homeRemainingTV.setText("Remaining: "+String.valueOf(month.getHomeRealmList().get(0).getRemaining()));

        pocketBudgetTV.setText("Budget: "+String.valueOf(month.getPocketRealmList().get(0).getBudget()));
        pocketExpenseTV.setText("Expense: "+String.valueOf(month.getPocketRealmList().get(0).getExpense()));
        pocketRemainingTV.setText("Remaining: "+String.valueOf(month.getPocketRealmList().get(0).getRemaining()));

        String dateCurrent = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date1=dateFormat.parse(dateFromServer);
            Date date2=dateFormat.parse(dateCurrent);
            long diff = Math.abs(date2.getTime() - date1.getTime());
            diffDays = (diff / (24 * 60 * 60 * 1000));
            pocketPerDayCostTV.setText("Per Day Cost: "+String.valueOf(Math.round((month.getPocketRealmList().get(0).getRemaining()/(diffDays+1.00)))));

        }catch (Exception e)
        {
            Log.e("Error",e.getMessage());
        }

        houseShiftingBudgetTV.setText("Budget: "+String.valueOf(month.getHouseShiftingRealmList().get(0).getBudget()));
        houseShiftingExpenseTV.setText("Expense: "+String.valueOf(month.getHouseShiftingRealmList().get(0).getExpense()));
        houseShiftingRemainingTV.setText("Remaining: "+String.valueOf(month.getHouseShiftingRealmList().get(0).getRemaining()));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
