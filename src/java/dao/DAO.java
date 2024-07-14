/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Brand;
import model.Cart;
import model.Category;
import model.Customer;
import model.Image;
import model.Item;
import model.Product;
import model.Status;

/**
 *
 * @author Hoa
 */
public class DAO extends DBContext {

    public List<Product> searchByPrice() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Products where price between 1500000 and 3000000";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Brand b = new Brand(rs.getInt(13), rs.getString(14));
                Status s = new Status(rs.getInt(6), rs.getString(7));
                Category c = new Category(rs.getInt(11), rs.getString(12));
                Product p = new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        s,
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        c,
                        b);
                list.add(p);

            }
        } catch (SQLException e) {
        }
        return list;
    }

    public Customer getAdmin(String user, String pass) {
        String sql = "select * from Customers where username=? and passwordCustom=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getString("customer_id"),
                        rs.getString("firstName"),
                        rs.getString("middleName"),
                        rs.getString("lastName"),
                        rs.getString("username"),
                        rs.getInt("mobile"),
                        rs.getString("email"),
                        rs.getString("passwordCustom"),
                        rs.getInt("active"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Product> getAll() {

        List<Product> list = new ArrayList<>();
        String sql = "select p.product_id,p.productName,p.price,p.imageUrl,\n"
                + "p.productInfo,s.statusId,s.status,p.quantity,p.createdAt,p.updatedAt,c.category_id,c.category_name,b.brand_id,b.brand_name from Products p join Brands b on p.brand_id=b.brand_id\n"
                + "join Categories c on c.category_id=p.category_id\n"
                + "join Status s on s.statusId=p.statusId";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Brand b = new Brand(rs.getInt(13), rs.getString(14));
                Status s = new Status(rs.getInt(6), rs.getString(7));
                Category c = new Category(rs.getInt(11), rs.getString(12));
                Product p = new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        s,
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        c,
                        b);
                list.add(p);

            }
        } catch (SQLException e) {
        }
        return list;
    }

    public Product getProductByID(String id) {

        String sql = "select p.product_id,p.productName,p.price,p.imageUrl,\n"
                + "p.productInfo,s.statusId,s.status,p.quantity,p.createdAt,p.updatedAt,c.category_id,c.category_name,b.brand_id,b.brand_name from Products p join Brands b on p.brand_id=b.brand_id\n"
                + "join Categories c on c.category_id=p.category_id\n"
                + "join Status s on s.statusId=p.statusId where p.product_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Brand b = new Brand(rs.getInt(13), rs.getString(14));
                Status s = new Status(rs.getInt(6), rs.getString(7));
                Category c = new Category(rs.getInt(11), rs.getString(12));
                return new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        s,
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        c,
                        b);

            }
        } catch (SQLException e) {
        }
        return null;
    }

    public List<Product> checkBoxProduct(int[] id) {

        List<Product> list = new ArrayList<>();
        String sql = "select p.product_id,p.productName,p.price,p.imageUrl,\n"
                + "p.productInfo,s.statusId,s.status,p.createdAt,p.updatedAt,c.category_id,c.category_name,b.brand_id,b.brand_name from Products p join Brands b on p.brand_id=b.brand_id\n"
                + "join Categories c on c.category_id=p.category_id\n"
                + "join Status s on s.statusId=p.statusId where 1=1";
        if (id != null) {
            sql += " and c.category_id in(";
            for (int i = 0; i < id.length; i++) {
                sql += id[i] + ",";

            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);
                sql += ")";
            }
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Brand b = new Brand(rs.getInt(13), rs.getString(14));
                Status s = new Status(rs.getInt(6), rs.getString(7));
                Category c = new Category(rs.getInt(11), rs.getString(12));
                Product p = new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        s,
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        c,
                        b);
                list.add(p);

            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Product> getByIDCategory(int id) {
        List<Product> list = new ArrayList<>();
        String sql = "select p.product_id,p.productName,p.price,p.imageUrl,\n"
                + "p.productInfo,s.statusId,s.status,p.quantity,p.createdAt,p.updatedAt,c.category_id,c.category_name,b.brand_id,b.brand_name from Products p join Brands b on p.brand_id=b.brand_id\n"
                + "join Categories c on c.category_id=p.category_id\n"
                + "join Status s on s.statusId=p.statusId where c.category_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Brand b = new Brand(rs.getInt(13), rs.getString(14));
                Status s = new Status(rs.getInt(6), rs.getString(7));
                Category c = new Category(rs.getInt(11), rs.getString(12));
                Product p = new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        s,
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        c,
                        b);
                list.add(p);

            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Product> getByIDBrand(int id) {

        List<Product> list = new ArrayList<>();
        String sql = "select p.product_id,p.productName,p.price,p.imageUrl,\n"
                + "                p.productInfo,s.statusId,s.status,p.quantity,p.createdAt,p.updatedAt,\n"
                + "                c.category_id,c.category_name,b.brand_id,b.brand_name from Products p \n"
                + "                join Brands b on p.brand_id=b.brand_id \n"
                + "              join Categories c on c.category_id=p.category_id \n"
                + "                join Status s on s.statusId=p.statusId where b.brand_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Brand b = new Brand(rs.getInt(13), rs.getString(14));
                Status s = new Status(rs.getInt(6), rs.getString(7));
                Category c = new Category(rs.getInt(11), rs.getString(12));
                Product p = new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        s,
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        c,
                        b);
                list.add(p);

            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Product> searchByName(String key) {

        List<Product> list = new ArrayList<>();
        String sql = "select p.product_id,p.productName,p.price,p.imageUrl,\n"
                + "p.productInfo,s.statusId,s.status,p.quantity,p.createdAt,p.updatedAt,c.category_id,c.category_name,b.brand_id,b.brand_name from Products p join Brands b on p.brand_id=b.brand_id\n"
                + "join Categories c on c.category_id=p.category_id\n"
                + "join Status s on s.statusId=p.statusId where p.productName like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + key + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Brand b = new Brand(rs.getInt(13), rs.getString(14));
                Status s = new Status(rs.getInt(6), rs.getString(7));
                Category c = new Category(rs.getInt(11), rs.getString(12));
                Product p = new Product(rs.getString(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        s,
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getString(10),
                        c,
                        b);
                list.add(p);

            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Category> getAllCate() {
        List<Category> list = new ArrayList<>();
        String sql = "select * from Categories";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt("category_id"),
                        rs.getString("category_name"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;

    }

    public List<Brand> getAllBrand() {
        List<Brand> list = new ArrayList<>();
        String sql = "select * from Brands";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Brand c = new Brand(rs.getInt("brand_id"),
                        rs.getString("brand_name"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;

    }

    public List<Image> getAllImage(String id) {
        List<Image> list = new ArrayList<>();
        String sql = "select * from Image where product_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Image c = new Image(rs.getInt("image_id"),
                        rs.getString("product_id"),
                        rs.getString("imageUrl"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;

    }

    public List<Product> getListByPage(List<Product> list, int start, int end) {
        ArrayList<Product> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public void addOrder(Customer u, Cart c) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            String sql = "insert into Orders values(?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u.getId());
            st.setString(2, date);
            st.setString(3, date);
            st.setDouble(4, c.getTotalMoney());
            st.executeUpdate();
            //Lay ra id cua Order vua add
            String sql1 = "select top 1 order_id from Orders order by order_id desc";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();
            //add vaof bang orderitem
            if (rs.next()) {
                int oid = rs.getInt(1);
                for (Item i : c.getItems()) {
                    String sql2 = "insert into OrderItems values(?,?,?,?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setInt(1, oid);
                    st2.setString(2, i.getProduct().getId());
                    st2.setInt(3, i.getQuantity());
                    st2.setDouble(4, i.getPrice());
                    st2.executeUpdate();

                }
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        DAO d = new DAO();
        List<Product> list = d.searchByPrice();
        for (Product p : list) {
            System.out.println(p.getName());
        }

    }
}
