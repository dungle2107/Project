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
public class OrderItems {

    private int oid;
    private String pid;
    private int quantity;
    private double price;
    private double discount;

    public OrderItems() {
    }

    public OrderItems(int oid, String pid, int quantity, double price, double discount) {

        this.oid = oid;
        this.pid = pid;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

}
