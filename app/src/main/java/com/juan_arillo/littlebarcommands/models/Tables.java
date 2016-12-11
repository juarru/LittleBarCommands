package com.juan_arillo.littlebarcommands.models;

import java.util.LinkedList;

public class Tables {

    private LinkedList<Table> mTables;
    private static Tables sTables;
    private static final int NUMBER_OF_TABLES = 5;

    // Loading Tables if doesn´t exists
    public static Tables getSTables() {

        if (sTables == null) {
            Tables.sTables = new Tables();
        }
        return Tables.sTables;
    }

    // Utility
    private Tables() {
        this.clear();
    }

    // Adding tables
    private void clear() {
        mTables = new LinkedList<Table>();

        for (int i = 0; i < NUMBER_OF_TABLES; i++) {
            mTables.add(new Table((i+1), new Order(), false, 0.0));
        }
    }

    public LinkedList<Table> getTables() {
        return mTables;
    }

    public Table getTable(int position) {
        return mTables.get(position);
    }

}
