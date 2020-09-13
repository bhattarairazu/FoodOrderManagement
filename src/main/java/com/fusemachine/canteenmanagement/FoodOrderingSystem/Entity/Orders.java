package com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="order_table")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "status")
    private String status;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "is_order_schedule")
    private boolean isOrderSchedule;

    @Column(name = "order_schedule_date")
    private Date orderScheduleDate;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String username;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderstable")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Orderitems> orderitems;

    @Column(name="created_at",updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "grand_total")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private float grandTotal;

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    public Orders() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public boolean isOrderSchedule() {
        return isOrderSchedule;
    }

    public void setOrderSchedule(boolean orderSchedule) {
        isOrderSchedule = orderSchedule;
    }

    public Date getOrderScheduleDate() {
        return orderScheduleDate;
    }

    public void setOrderScheduleDate(Date orderScheduleDate) {
        this.orderScheduleDate = orderScheduleDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Orderitems> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(List<Orderitems> orderitems) {
        this.orderitems = orderitems;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(float grandTotal) {
        this.grandTotal = grandTotal;
    }
}