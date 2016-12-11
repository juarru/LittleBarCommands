package com.juan_arillo.littlebarcommands.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juan_arillo.littlebarcommands.R;
import com.juan_arillo.littlebarcommands.activities.DishActivity;
import com.juan_arillo.littlebarcommands.adapters.DishReciclerAdapter;
import com.juan_arillo.littlebarcommands.models.Dish;
import com.juan_arillo.littlebarcommands.models.Menu;
import com.juan_arillo.littlebarcommands.models.Table;
import com.juan_arillo.littlebarcommands.models.Tables;

public class DishFragment extends Fragment {


    private OnAddDishSelectedListener mOnDishSelectedListener;
    private static final String TABLE_INDEX="TABLE_INDEX";
    private int mTableIndex;
    private Tables mTables;
    private Table mTable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(false);

        if (getArguments() != null) {
            mTableIndex = getArguments().getInt(TABLE_INDEX);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_dish, container);

        mTables = Tables.getSTables();
        mTableIndex = getActivity().getIntent().getIntExtra(DishActivity.TABLE_INDEX, 0);
        mTable = mTables.getTable(mTableIndex);

        RecyclerView list = (RecyclerView) root.findViewById(R.id.menu_list);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.setItemAnimator(new DefaultItemAnimator());
        list.setAdapter(new DishReciclerAdapter(Menu.getMenu(), new DishReciclerAdapter.OnDishSelectedListener() {
            @Override
            public void onDishSelected(final Dish dish) {
                mOnDishSelectedListener.onAddDishSelected(dish,mTable);
                double amount = mTable.getTotal() + dish.getPrice();
                mTable.setTotal(amount);
            }

            @Override
            public void onDishLongSelected(Dish dish) {
            }
        }
        ));

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attachToActivity(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        attachToActivity(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnDishSelectedListener = null;
    }

    private void attachToActivity(Context context) {
        if (!(context instanceof OnAddDishSelectedListener)) {
            throw new ClassCastException("Exception OnAddDishSelectedListener");
        }

        mOnDishSelectedListener = (OnAddDishSelectedListener) context;
    }

    public interface OnAddDishSelectedListener {
        void onAddDishSelected(Dish dish, Table table);
    }

}
