package com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
public class Orderitems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
    @JoinColumn(name = "orders")
    @JsonIgnore
    private Orders orderstable;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
    @JoinColumn(name = "menuitem")
    @JsonIgnore
    private MenuItem menuitem;

    private int menuItemsId;

    private int ordersId;

    public Orderitems() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Orders getOrderstable() {
        return orderstable;
    }

    public void setOrderstable(Orders orderstable) {
        this.orderstable = orderstable;
    }

    public MenuItem getMenuitem() {
        return menuitem;
    }

    public void setMenuitem(MenuItem menuitem) {
        this.menuitem = menuitem;
    }

    public int getMenuItemsId() {
        return menuItemsId;
    }

    public void setMenuItemsId(int menuItemsId) {
        this.menuItemsId = menuItemsId;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }
}
