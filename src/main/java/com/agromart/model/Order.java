package com.agromart.model;


public class Order {

    private int id;
    private double totalAmount;
    private String orderDate;
    private int statusId;

    // Getters & Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }

    public int getStatusId() { return statusId; }
    public void setStatusId(int statusId) { this.statusId = statusId; }
}
