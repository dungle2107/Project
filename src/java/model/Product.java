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
public class Product {
    private String id,name;
    private double price;
    private String imageUrl,info;
    private Status statusId;
    private int quantity;
    private String create,update;
    private Category cate;
    private Brand brand;

    public Product() {
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", imageUrl=" + imageUrl + ", info=" + info + ", statusId=" + statusId + ", quantity=" + quantity + ", create=" + create + ", update=" + update + ", cate=" + cate + ", brand=" + brand + '}';
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product(String id, String name, double price, String imageUrl, String info, Status statusId, int quantity, String create, String update, Category cate, Brand brand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.info = info;
        this.statusId = statusId;
        this.quantity = quantity;
        this.create = create;
        this.update = update;
        this.cate = cate;
        this.brand = brand;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public Category getCate() {
        return cate;
    }

    public void setCate(Category cate) {
        this.cate = cate;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    

    
}
