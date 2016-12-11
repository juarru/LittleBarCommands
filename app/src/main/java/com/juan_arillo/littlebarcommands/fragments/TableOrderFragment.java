package com.juan_arillo.littlebarcommands.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.juan_arillo.littlebarcommands.R;
import com.juan_arillo.littlebarcommands.activities.TableOrderActivity;
import com.juan_arillo.littlebarcommands.adapters.DishReciclerAdapter;
import com.juan_arillo.littlebarcommands.models.Dish;
import com.juan_arillo.littlebarcommands.models.Table;
import com.juan_arillo.littlebarcommands.models.Tables;

public class TableOrderFragment extends Fragment {

    private static final String TABLE_INDEX = "TABLE_INDEX";
    private int mTableIndex;
    private DishReciclerAdapter mAdapter;
    Table mTable;

    public static TableOrderFragment newInstance(int position) {
        Bundle arguments = new Bundle();
        arguments.putInt(TABLE_INDEX, position);

        TableOrderFragment fragment = new TableOrderFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if (getArguments() != null){
            mTableIndex = getArguments().getInt(TABLE_INDEX);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_table_order, container);

        Tables tables = Tables.getSTables();
        mTableIndex = getActivity().getIntent().getIntExtra(TableOrderActivity.TABLE_INDEX, 0);
        mTable = tables.getTable(mTableIndex);

        RecyclerView listRecycle = (RecyclerView) root.findViewById(R.id.menu_list);
        listRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        listRecycle.setItemAnimator(new DefaultItemAnimator());


        mAdapter = new DishReciclerAdapter(mTable.getOrder().getDishLinkedList(), new DishReciclerAdapter.OnDishSelectedListener() {
            @Override
            public void onDishSelected(Dish dish) {

            }

            @Override
            public void onDishLongSelected(final Dish dish) {
                AlertDialog.Builder confirmDialog = new AlertDialog.Builder(getActivity());
                confirmDialog.setMessage(R.string.remove_dish);
                confirmDialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAdapter.notifyDataSetChanged();
                        mTable.getOrder().removeDish(dish);
                        double amount = mTable.getTotal() - dish.getPrice();
                        mTable.setTotal(amount);
                        Snackbar
                                .make(getView(), R.string.dish_removed, Snackbar.LENGTH_SHORT)
                                .show();
                    }
                });
                confirmDialog.setNegativeButton(android.R.string.cancel, null);
                confirmDialog.show();

            }
        });

        listRecycle.setAdapter(mAdapter);

        FloatingActionButton addButton = (FloatingActionButton) root.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TableOrderActivity myActivity = (TableOrderActivity) getActivity();
                myActivity.onAddDishTapped(mTableIndex);
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_table, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Tables tables = Tables.getSTables();
        if (item.getItemId() == R.id.order) {

            TableOrderActivity myActivity = (TableOrderActivity) getActivity();
            myActivity.onAddDishTapped(mTableIndex);

            return true;
        }
        else if (item.getItemId() == R.id.checkout) {
            AlertDialog.Builder confirmDialog = new AlertDialog.Builder(getActivity());
            confirmDialog.setMessage(getActivity().getString(R.string.total) + tables.getTable(mTableIndex).getTotal() + getActivity().getString(R.string.finish_command));
            confirmDialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mTable.clear();
                    mAdapter.notifyDataSetChanged();
                    Snackbar
                            .make(getView(), R.string.table_available, Snackbar.LENGTH_SHORT)
                            .show();
                }
            });
            confirmDialog.setNegativeButton(android.R.string.cancel, null);
            confirmDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
