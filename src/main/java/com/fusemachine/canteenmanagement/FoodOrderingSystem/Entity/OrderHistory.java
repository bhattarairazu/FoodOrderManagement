//package com.fusemachine.canteenmanagement.FoodOrderingSystem.Entity;
//
//import javax.persistence.*;
//import java.util.Date;
//import java.util.List;
//
//@Entity
//@Table(name = "order_history")
//public class OrderHistory {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
//    private List<Order> order;
//
//    @Column(name="created_at")
//    private Date createdAt;
//
//    @Column(name="updated_at")
//    private Date updatedAt;
//
//    @PrePersist
//    protected void onCreate(){
//        this.createdAt = new Date();
//    }
//
//    @PreUpdate
//    protected void onUpdate(){
//        this.updatedAt = new Date();
//    }
//
//    public OrderHistory() {
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public List<Order> getOrder() {
//        return order;
//    }
//
//    public void setOrder(List<Order> order) {
//        this.order = order;
//    }
//
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Date getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//}
