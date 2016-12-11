package com.juan_arillo.littlebarcommands.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import com.juan_arillo.littlebarcommands.R;
import com.juan_arillo.littlebarcommands.fragments.TableListFragment;
import com.juan_arillo.littlebarcommands.fragments.TableOrderFragment;
import com.juan_arillo.littlebarcommands.models.Menu;
import com.juan_arillo.littlebarcommands.models.Order;
import com.juan_arillo.littlebarcommands.models.Table;
import com.juan_arillo.littlebarcommands.utilities.BackgroundMenuDownloading;

public class MainActivity extends AppCompatActivity implements TableListFragment.OnTableSelectedListener {

    public static final String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tools for device information
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        int dpWidth = (int) (width / metrics.density);
        int dpHeight = (int) (height / metrics.density);
        String model = Build.MODEL;
        int dpi = metrics.densityDpi;

        setContentView(R.layout.activity_main);
        // Toolbar access
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //toolbar.setLogo(R.mipmap.ic_launcher);
        // setting ActionBar
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.tablestitle);
        }

        // loading fragment
        FragmentManager fm = getFragmentManager();

        // Asking for a place to TableList
        if (findViewById(R.id.table_list_fragment) != null) {
            // There´s a place, we set TableList
            if (fm.findFragmentById(R.id.table_list_fragment) == null) {
                fm.beginTransaction()
                        .add(R.id.table_list_fragment, new TableListFragment())
                        .commit();
            }
        }

        // Asking for a place to Table Order
        if (findViewById(R.id.fragment_table_order) != null) {
            // There´s a place, we set Table Order
            if (fm.findFragmentById(R.id.fragment_table_order) == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_table_order, new TableOrderFragment())
                        .commit();
            }
        }

        this.downloadMenu();

    }

    protected void downloadMenu() {
        if (Menu.getMenu().size() == 0) {
            BackgroundMenuDownloading downloadMenu = new BackgroundMenuDownloading((ViewGroup) findViewById(R.id.activity_main), this);
            downloadMenu.execute();
        }
    }

    @Override
    public void onTableSelected(Table table, int position) {

        FragmentManager fm = getFragmentManager();
        TableOrderFragment tableOrderFragment = (TableOrderFragment) fm.findFragmentById(R.id.fragment_table_order);

        if (tableOrderFragment != null){
            // Move to another table
            tableOrderFragment.showOrderTable(position);

        } else {
            Intent intent = new Intent(this, TableOrderActivity.class);
            intent.putExtra(TableOrderActivity.TABLE_INDEX, position);
            startActivity(intent);
        }
    }
}
