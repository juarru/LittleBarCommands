package com.juan_arillo.littlebarcommands.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.juan_arillo.littlebarcommands.R;
import com.juan_arillo.littlebarcommands.fragments.TableOrderFragment;
import com.juan_arillo.littlebarcommands.models.Tables;

public class TableOrderActivity extends AppCompatActivity{

    public static final String TABLE_INDEX = "TABLE_INDEX";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_order);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            Bundle b = getIntent().getExtras();
            if(b != null) {
                int position = b.getInt(TABLE_INDEX);
                actionBar.setTitle(Tables.getSTables().getTable(position).toString());
            }
        }

        FragmentManager fm = getFragmentManager();

        if (fm.findFragmentById(R.id.table_fragment) == null){

            int indexTable = getIntent().getIntExtra(TABLE_INDEX,0);

            fm.beginTransaction()
                    .add(R.id.table_fragment, TableOrderFragment.newInstance(indexTable))
                    .commit();
        }
    }

    public void onAddDishTapped(int tableIndex){
        Intent intent = new Intent(this, DishActivity.class);
        intent.putExtra(TableOrderActivity.TABLE_INDEX,tableIndex);
        startActivity(intent);
    }
}
