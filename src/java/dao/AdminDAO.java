/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Brand;
import model.Category;
import model.Customer;
import model.Order;
import model.OrderItems;
import model.Product;
import model.Status;

/**
 *
 * @author Hoa
 */
public class AdminDAO extends DBContext {

    public static void main(String[] args) {
        AdminDAO ad = new AdminDAO();
        String id = ad.getCustomerBuyMost();
        System.out.println(id);
    }

    public double getTotalFromMonth(int month) {
        double total = 0.0;
        String sql = "select SUM(total) from Orders where MONTH(shipped_date)=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, month);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (Exception e) {
        }
        return total;
    }

    public String getCustomerBuyMost() {
        String cuid = "";
        String sql = "select top 1 customer_id from Orders group by customer_id order by COUNT(customer_id) desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                cuid = rs.getString(1);
            }

        } catch (SQLException e) {
        }
        return cuid;
    }

    public String getProductBuyMost() {
        String cuid = "";
        String sql = "select top 1 product_id from OrderItems\n"
                + "group by product_id \n"
                + "order by SUM(quantity) desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                cuid = rs.getString(1);
            }
        } catch (SQLException e) {
        }
        return cuid;
    }

    public List<OrderItems> getOrderItems() {
        List<OrderItems> list = new ArrayList<>();

        String sql = "select * from OrderItems";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderItems o = new OrderItems(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4), 0.1);
                list.add(o);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Order> getOrder() {
        List<Order> list = new ArrayList<>();

        String sql = "select * from Orders";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order o = new Order(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5));
                list.add(o);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public Customer getCustomerByID(String id) {
        Customer c = null;
        String sql = "select * from Customers where customer_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                c = new Customer(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getLong(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9));
            }
        } catch (Exception e) {
        }
        return c;
    }

    public void getCustomer(Customer c) {
        String sql = "insert into Customers values(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getId());
            st.setString(2, c.getFname());
            st.setString(3, c.getMname());
            st.setString(4, c.getLname());
            st.setString(5, c.getUsername());
            st.setLong(6, c.getPhone());
            st.setString(7, c.getEmail());
            st.setString(8, c.getPass());
            st.setInt(9, c.getActive());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void insertCate(Category p) {
        String sql = "insert into Categories values(?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getCate_name());
            st.executeUpdate();
        } catch (SQLException e) {
        }

    }

    public Brand getBrandbyName(String p) {
        Brand b = null;
        String sql = "select * from Brands where brand_name like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                b = new Brand(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
        }
        return b;
    }

    public Category getCatebyName(String p) {
        Category b = null;
        String sql = "select * from Categories where cate_name like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                b = new Category(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
        }
        return b;
    }

    public void insertBrand(Brand p) {
        String sql = "insert into Brands values(?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, p.getBrand_id());
            st.setString(1, p.getBrand_name());
            st.executeUpdate();
        } catch (SQLException e) {
        }

    }

    public void insert(Product p) {
        try {
            String sql = "insert into Products values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getId());
            st.setString(2, p.getName());
            st.setDouble(3, p.getPrice());
            st.setString(4, p.getImageUrl());
            st.setString(5, p.getInfo());
            st.setInt(6, p.getStatusId().getStatus_id());
            st.setInt(7, p.getQuantity());
            st.setString(8, p.getCreate());
            st.setString(9, p.getUpdate());
            st.setInt(10, p.getCate().getCate_id());
            st.setInt(11, p.getBrand().getBrand_id());
            st.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public boolean existedCat(String id) {
        String sql = "select * from Products where product_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }

    public void delete(String id) {
        String sql = "delete from Products where product_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update(Product p) {
        String sql = "update Products set productName=?,price=?,imageUrl=?,"
                + "productInfo=?,statusId=?,quantity=?,createdAt=?,updatedAt=?,"
                + "category_id=?,brand_id=?"
                + " where product_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getName());
            st.setDouble(2, p.getPrice());
            st.setString(3, p.getImageUrl());
            st.setString(4, p.getInfo());
            st.setInt(5, p.getStatusId().getStatus_id());
            st.setInt(6, p.getQuantity());
            st.setString(7, p.getCreate());
            st.setString(8, p.getUpdate());
            st.setInt(9, p.getCate().getCate_id());
            st.setInt(10, p.getBrand().getBrand_id());
            st.setString(11, p.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
