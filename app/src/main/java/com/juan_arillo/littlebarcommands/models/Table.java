package com.juan_arillo.littlebarcommands.models;

/**
 * Created by juan_arillo on 06/12/16.
 */

public class Table {

    private int mIdTable = -1;
    private Order mOrder = null;
    private boolean mTakenOrder = false;
    private double mTotal = 0;

    public Table(int idTable, Order order, boolean takenOrder, double Total) {
        mIdTable = idTable;
        mOrder = order;
        mTakenOrder = takenOrder;
        mTotal = Total;
    }

    public int getIdTable() {
        return mIdTable;
    }

    public void setIdTable(int idTable) {
        mIdTable = idTable;
    }

    public Order getOrder() {
        return mOrder;
    }

    public void setOrder(Order order) {
        mOrder = order;
    }

    public boolean isTakenOrder() {
        return mTakenOrder;
    }

    public void setTakenOrder(boolean takenOrder) {
        mTakenOrder = takenOrder;
    }

    public double getTotal() {
        return mTotal;
    }

    public void setTotal(double total) {
        mTotal = total;
    }

    @Override
    public String toString() {
        return "Mesa " + this.getIdTable();
    }

    // Utility
    public void clear()  {
        this.setTotal(0);
        while (!mOrder.getDishLinkedList().isEmpty()) {
            mOrder.getDishLinkedList().removeFirst();
        }
    }

}
