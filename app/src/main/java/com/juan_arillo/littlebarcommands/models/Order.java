package com.juan_arillo.littlebarcommands.models;

import java.util.LinkedList;

public class Order {

    private int mIdOrder;
    private int mIdTable;
    private LinkedList<Dish> mDishLinkedList;

    public Order(int idOrder, int idTable, LinkedList<Dish> dishLinkedList) {
        mIdOrder = idOrder;
        mIdTable = idTable;
        mDishLinkedList = new LinkedList<Dish>();
    }

    public Order() {
        mDishLinkedList = new LinkedList<Dish>();
    }

    public int getIdOrder() {
        return mIdOrder;
    }

    public void setIdOrder(int idOrder) {
        mIdOrder = idOrder;
    }

    public int getIdTable() {
        return mIdTable;
    }

    public void setIdTable(int idTable) {
        mIdTable = idTable;
    }

    public LinkedList<Dish> getDishLinkedList() {
        return mDishLinkedList;
    }

    public void setDishLinkedList(LinkedList<Dish> dishLinkedList) {
        mDishLinkedList = dishLinkedList;
    }

    public void addDish(Dish dish){
        Dish newDish = dish;
        mDishLinkedList.push(newDish);
    }

    // Utility for removing dishes from the order
    public void removeDish(Dish dish){
        mDishLinkedList.removeFirstOccurrence(dish);
    }

}
