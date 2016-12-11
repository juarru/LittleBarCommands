package com.juan_arillo.littlebarcommands.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.juan_arillo.littlebarcommands.R;
import com.juan_arillo.littlebarcommands.fragments.DishFragment;
import com.juan_arillo.littlebarcommands.models.Dish;
import com.juan_arillo.littlebarcommands.models.Order;
import com.juan_arillo.littlebarcommands.models.Table;

public class DishActivity extends AppCompatActivity implements DishFragment.OnAddDishSelectedListener {

    private Order mOrder;
    public static final String TABLE_INDEX = "TABLE_INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);
    }

    @Override
    public void onAddDishSelected(Dish dish, String extras, Table table) {
        table.getOrder().addDish(dish, extras);
        finish();
    }
}
