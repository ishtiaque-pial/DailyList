package pial.com.dailylist.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import pial.com.dailylist.Adapter.ShoppingAdapter;
import pial.com.dailylist.Model.AdpaterModel.ContentList;
import pial.com.dailylist.Model.AdpaterModel.Header;
import pial.com.dailylist.Model.AdpaterModel.TotalList;
import pial.com.dailylist.Model.Home;
import pial.com.dailylist.Model.HomeDetails;
import pial.com.dailylist.Model.Month;
import pial.com.dailylist.Model.Shopping;
import pial.com.dailylist.Model.ShoppingDetails;
import pial.com.dailylist.R;

public class ShoppingDetailsActivity extends AppCompatActivity implements ItemClickListener {
    @BindView(R.id.recyclerViewShopping)
    RecyclerView recyclerView;
    @BindView(R.id.fabAddShopping)
    FloatingActionButton actionButton;
    private Realm realm;
    private Date date;
    private String newdate;
    private DateFormat df;
    private EditText causeET,priceET;
    private RadioButton todayRB,previousRB;
    private TextView yesTV,noTV;
    private Dialog dialog;
    private String dateCurrent;
    private SimpleDateFormat dateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_details);
        ButterKnife.bind(this);
        dateCurrent = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dialog = new Dialog(ShoppingDetailsActivity.this,R.style.Theme_Dialog);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Add Your Spend");
        causeET = dialog.findViewById(R.id.causeDialog);
        priceET = dialog.findViewById(R.id.priceDialog);
        todayRB = dialog.findViewById(R.id.todayRadio);
        previousRB = dialog.findViewById(R.id.previousRadio);
        yesTV = dialog.findViewById(R.id.dialogYes);
        noTV = dialog.findViewById(R.id.dialogNo);
        realm = Realm.getDefaultInstance();

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                setRecyclerView();
            }
        });


        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setRecyclerView();
    }

    private void setRecyclerView() {
        RealmResults<ShoppingDetails> shoppingDetailses = realm.where(ShoppingDetails.class).equalTo("shoppingID",1).findAll();
        shoppingDetailses = shoppingDetailses.sort("date");
        df = new SimpleDateFormat("dd-MMM-yyyy");
        ArrayList<TotalList> totalLists = new ArrayList<>();
        for (int i =0; i<shoppingDetailses.size();i++) {
            if (i == 0) {
                Header header = new Header();
                date = shoppingDetailses.get(i).getDate();
                header.setDate(df.format(shoppingDetailses.get(i).getDate()));
                totalLists.add(header);
                ContentList contentList = new ContentList();
                contentList.setCause(shoppingDetailses.get(i).getCause());
                contentList.setPrice(shoppingDetailses.get(i).getPrice());
                contentList.setPosition(shoppingDetailses.get(i).getId());
                totalLists.add(contentList);
            } else {
                if (date.compareTo(shoppingDetailses.get(i).getDate())==0) {
                    ContentList contentList = new ContentList();
                    contentList.setCause(shoppingDetailses.get(i).getCause());
                    contentList.setPrice(shoppingDetailses.get(i).getPrice());
                    contentList.setPosition(shoppingDetailses.get(i).getId());
                    totalLists.add(contentList);
                } else {
                    Header header = new Header();
                    date = shoppingDetailses.get(i).getDate();
                    header.setDate(df.format(shoppingDetailses.get(i).getDate()));
                    totalLists.add(header);
                    ContentList contentList = new ContentList();
                    contentList.setCause(shoppingDetailses.get(i).getCause());
                    contentList.setPrice(shoppingDetailses.get(i).getPrice());
                    contentList.setPosition(shoppingDetailses.get(i).getId());
                    totalLists.add(contentList);
                }
            }
        }
        ShoppingAdapter shoppingAdapter = new ShoppingAdapter(totalLists,this);
        recyclerView.setAdapter(shoppingAdapter);
    }

    private void openDialog() {
        causeET.setText("");
        priceET.setText("");
        todayRB.setChecked(true);
        todayRB.setFocusableInTouchMode(true);
        previousRB.setText("Previous Day");
        dialog.show();
        noTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        previousRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDatePicker();
            }
        });
        yesTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (causeET.getText().toString().isEmpty()) {
                    causeET.setError("Required");
                } else if (priceET.getText().toString().isEmpty()) {
                    priceET.setError("Required");
                } else {
                    updateShopping();
                }
            }
        });

    }

    private void callDatePicker() {

        final Calendar mCurrentDate = Calendar.getInstance();
        final int mYear = mCurrentDate.get(Calendar.YEAR);
        final int mMonth = mCurrentDate.get(Calendar.MONTH);
        final int mDay = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {


                mCurrentDate.set(Calendar.YEAR, selectedyear);
                mCurrentDate.set(Calendar.MONTH, selectedmonth);
                mCurrentDate.set(Calendar.DAY_OF_MONTH, selectedday);

                newdate=new java.text.SimpleDateFormat("yyyy-MM-dd").format(mCurrentDate.getTime());
                previousRB.setText(newdate);
            }
        }, mYear, mMonth, mDay);
        mDatePicker.show();

    }
    private void updateShopping() {

        if (todayRB.isChecked()) {
            try {
                date=dateFormat.parse(dateCurrent);
                executeRealm();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dialog.dismiss();
        } else {
            try {
                date=dateFormat.parse(previousRB.getText().toString());
                executeRealm();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dialog.dismiss();
        }

    }

    private void executeRealm() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Number maxId = realm.where(ShoppingDetails.class).equalTo("shoppingID",1).max("id");
                int nextId = (maxId == null) ? 1 : maxId.intValue() + 1;
                ShoppingDetails shoppingDetails = new ShoppingDetails();
                shoppingDetails.setDate(date);
                shoppingDetails.setShoppingID(1);
                shoppingDetails.setId(nextId);
                shoppingDetails.setCause(causeET.getText().toString());
                shoppingDetails.setPrice(Integer.parseInt(priceET.getText().toString()));
                realm.insert(shoppingDetails);
                RealmResults<ShoppingDetails> result = realm.where(ShoppingDetails.class).equalTo("shoppingID",1).findAll();
                final int sum = result.sum("price").intValue();
                final Shopping shopping = realm.where(Shopping.class).equalTo("id",1).findFirst();
                final int reamining = shopping.getBudget() - sum;
                shopping.setRemaining(reamining);
                shopping.setExpense(sum);
                realm.insertOrUpdate(shopping);
                Month month = realm.where(Month.class).equalTo("id",1).findFirst();
                int totalSum = month.getShoppingRealmList().get(0).getExpense()+
                        month.getHomeRealmList().get(0).getExpense()+
                        month.getPocketRealmList().get(0).getExpense()+
                        month.getHouseShiftingRealmList().get(0).getExpense();
                month.setExpense(totalSum);
                month.setSaving(month.getSalary() - totalSum);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void itemOnClickListener(int position) {
        dialogOfEdit(position);
    }

    private void dialogOfEdit(final int position) {
        dialog.show();
        ShoppingDetails results = realm.where(ShoppingDetails.class).equalTo("shoppingID",1).equalTo("id",1).findFirst();
        try {
            date = dateFormat.parse(dateCurrent);
            causeET.setText(results.getCause());
            priceET.setText(String.valueOf(results.getPrice()));
            if (results.getDate().compareTo(date)!=0) {
                previousRB.setChecked(true);
                newdate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(results.getDate());
                previousRB.setText(newdate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        noTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        previousRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDatePicker();
            }
        });

        yesTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (causeET.getText().toString().isEmpty()) {
                    causeET.setError("Required");
                } else if (priceET.getText().toString().isEmpty()) {
                    priceET.setError("Required");
                } else {
                    updateData(position);
                }
            }
        });
    }

    private void updateData(int position) {
        if (todayRB.isChecked()) {
            try {
                date = dateFormat.parse(dateCurrent);
                executeRealmUpdate(position);

            } catch (Exception e) {
                e.printStackTrace();
            }
            dialog.dismiss();
        } else {
            try {
                date = dateFormat.parse(previousRB.getText().toString());
                executeRealmUpdate(position);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dialog.dismiss();

        }
    }

    private void executeRealmUpdate(final int position) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ShoppingDetails resultss = realm.where(ShoppingDetails.class).equalTo("shoppingID",1).equalTo("id",position).findFirst();
                resultss.setDate(date);
                resultss.setPrice(Integer.parseInt(priceET.getText().toString()));
                resultss.setCause(causeET.getText().toString());
                realm.insertOrUpdate(resultss);
                RealmResults<ShoppingDetails> result = realm.where(ShoppingDetails.class).equalTo("shoppingID",1).findAll();
                final int sum = result.sum("price").intValue();
                final Shopping shopping = realm.where(Shopping.class).equalTo("id",1).findFirst();
                final int reamining = shopping.getBudget() - sum;
                shopping.setRemaining(reamining);
                shopping.setExpense(sum);
                realm.insertOrUpdate(shopping);
                Month month = realm.where(Month.class).equalTo("id",1).findFirst();
                int totalSum = month.getShoppingRealmList().get(0).getExpense()+
                        month.getHomeRealmList().get(0).getExpense()+
                        month.getPocketRealmList().get(0).getExpense()+
                        month.getHouseShiftingRealmList().get(0).getExpense();
                month.setExpense(totalSum);
                month.setSaving(month.getSalary() - totalSum);
            }
        });
    }

    @Override
    public void itemOnLongClickListener(final int position) {

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this,R.style.MyAlertDialogStyle);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        deleteItem(position);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(R.drawable.ic_warning_black_24dp)
                .show();
    }

    private void deleteItem(final int position) {
        final ShoppingDetails res =realm.where(ShoppingDetails.class).equalTo("shoppingID",1).equalTo("id",position).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //ShoppingDetails res = resultss.get(position);
                res.deleteFromRealm();
                RealmResults<ShoppingDetails> result = realm.where(ShoppingDetails.class).equalTo("shoppingID",1).findAll();
                final int sum = result.sum("price").intValue();
                final Shopping shopping = realm.where(Shopping.class).equalTo("id",1).findFirst();
                final int reamining = shopping.getBudget() - sum;
                shopping.setRemaining(reamining);
                shopping.setExpense(sum);
                realm.insertOrUpdate(shopping);
                Month month = realm.where(Month.class).equalTo("id",1).findFirst();
                int totalSum = month.getShoppingRealmList().get(0).getExpense()+
                        month.getHomeRealmList().get(0).getExpense()+
                        month.getPocketRealmList().get(0).getExpense()+
                        month.getHouseShiftingRealmList().get(0).getExpense();
                month.setExpense(totalSum);
                month.setSaving(month.getSalary() - totalSum);

            }
        });
    }
}
