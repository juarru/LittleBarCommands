package com.juan_arillo.littlebarcommands.models;

import java.util.LinkedList;

/**
 * Created by juan_arillo on 06/12/16.
 */

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

    // Utility for adding dishes to the Order
    public void addDish(Dish dish, String wishList) {
        Dish newDish = dish;
        newDish.setWishList(wishList);
        mDishLinkedList.push(newDish);
    }

    // Utility for removing dishes from the order
    public void removeDish(Dish dish){
        mDishLinkedList.removeFirstOccurrence(dish);
    }

}
