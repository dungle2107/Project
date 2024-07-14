/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Hoa
 */
public class Order {
    private int id;
    private String customerId;
    private String orderDate;
    private String shipDate;
    private double totalMoney;
    public Order() {
    }

    public Order(int id, String customerId, String orderDate, String shipDate, double totalMoney) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.totalMoney = totalMoney;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }


    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
    
}
