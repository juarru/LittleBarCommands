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
import com.juan_arillo.littlebarcommands.models.Menu;
import com.juan_arillo.littlebarcommands.models.Table;

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

        // Cargamos a mano el fragment
        FragmentManager fm = getFragmentManager();

        // Preguntamos a ver si existe el hueco para TableList
        if (findViewById(R.id.table_list_fragment) != null) {
            // Existe hueco, y habiendo hueco metemos el fragment de TableList
            if (fm.findFragmentById(R.id.table_list_fragment) == null) {
                fm.beginTransaction()
                        .add(R.id.table_list_fragment, new TableListFragment())
                        .commit();
            }
        }

        //this.downloadMenu();

    }

    /*protected void downloadMenu() {
        if (Menu.getMenu().size() == 0) {
            DownloadMenu downloadMenu = new DownloadMenu((ViewGroup) findViewById(R.id.activity_main), this);
            downloadMenu.execute();
        }
    }*/

    @Override
    public void onTableSelected(Table table, int position) {
        /*Intent intent = new Intent(this, TableDetailActivity.class);
        intent.putExtra(TableDetailActivity.TABLE_INDEX, position);
        startActivity(intent);*/

    }
}
