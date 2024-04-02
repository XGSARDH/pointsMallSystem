package main.java.com.pointsmallsystem.www.dao.impl;

import main.java.com.pointsmallsystem.www.dao.AddressDao;
import main.java.com.pointsmallsystem.www.po.Address;
import main.java.com.pointsmallsystem.www.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements AddressDao {
    @Override
    public Address findById(Integer id) {
        String sql = "SELECT * FROM address WHERE id = ?";
        Address address = null;
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    address = new Address();
                    address.setId(rs.getInt("id"));
                    address.setUserId(rs.getInt("user_id"));
                    address.setRecipient(rs.getString("recipient"));
                    address.setPhone(rs.getString("phone"));
                    address.setAddress(rs.getString("address"));
                    address.setDefault(rs.getBoolean("is_default"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }

    @Override
    public void save(Address address) {
        String sql = "INSERT INTO address (user_id, recipient, phone, address, is_default) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, address.getUserId());
            pstmt.setString(2, address.getRecipient());
            pstmt.setString(3, address.getPhone());
            pstmt.setString(4, address.getAddress());
            pstmt.setBoolean(5, address.getDefault());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Address address) {
        String sql = "UPDATE address SET user_id = ?, recipient = ?, phone = ?, address = ?, is_default = ? WHERE id = ?";
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, address.getUserId());
            pstmt.setString(2, address.getRecipient());
            pstmt.setString(3, address.getPhone());
            pstmt.setString(4, address.getAddress());
            pstmt.setBoolean(5, address.getDefault());
            pstmt.setInt(6, address.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Address> findAll() {
        String sql = "SELECT * FROM address";
        List<Address> addresses = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Address address = new Address();
                address.setId(rs.getInt("id"));
                address.setUserId(rs.getInt("user_id"));
                address.setRecipient(rs.getString("recipient"));
                address.setPhone(rs.getString("phone"));
                address.setAddress(rs.getString("address"));
                address.setDefault(rs.getBoolean("is_default"));
                addresses.add(address);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addresses;
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM address WHERE id = ?";
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Address findByRecipient(String recipient) {
        String sql = "SELECT * FROM address WHERE recipient = ?";
        Address address = null;
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, recipient);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    address = new Address();
                    address.setId(rs.getInt("id"));
                    address.setUserId(rs.getInt("user_id"));
                    address.setRecipient(rs.getString("recipient"));
                    address.setPhone(rs.getString("phone"));
                    address.setAddress(rs.getString("address"));
                    address.setDefault(rs.getBoolean("is_default"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }

    @Override
    public List<Address> findByAddressSubstring(String substring) {
        List<Address> matchingAddresses = new ArrayList<>();
        String sql = "SELECT * FROM address WHERE address LIKE ?";
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + substring + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Address address = new Address();
                    address.setId(rs.getInt("id"));
                    address.setUserId(rs.getInt("user_id"));
                    address.setRecipient(rs.getString("recipient"));
                    address.setPhone(rs.getString("phone"));
                    address.setAddress(rs.getString("address"));
                    address.setDefault(rs.getBoolean("is_default"));
                    matchingAddresses.add(address);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matchingAddresses;
    }

    @Override
    public List<Address> findByUserId(Integer userId) {
        // 定义SQL查询语句，根据userId来筛选地址记录
        String sql = "SELECT * FROM address WHERE user_id = ?";
        // 创建一个空的地址列表，用于存储查询结果
        List<Address> addresses = new ArrayList<>();
        // 使用try-with-resources语句确保数据库资源的自动关闭
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // 设置查询参数为传入的userId
            pstmt.setInt(1, userId);
            // 执行查询
            try (ResultSet rs = pstmt.executeQuery()) {
                // 遍历查询结果集
                while (rs.next()) {
                    // 对于每一行结果，创建一个新的Address对象，并设置其属性
                    Address address = new Address();
                    address.setId(rs.getInt("id"));
                    address.setUserId(rs.getInt("user_id"));
                    address.setRecipient(rs.getString("recipient"));
                    address.setPhone(rs.getString("phone"));
                    address.setAddress(rs.getString("address"));
                    address.setDefault(rs.getBoolean("is_default"));
                    // 将创建的Address对象添加到地址列表中
                    addresses.add(address);
                }
            }
        } catch (Exception e) {
            // 如果发生异常，打印异常堆栈信息（在生产环境中应该使用日志记录而非直接打印）
            e.printStackTrace();
        }
        // 返回填充好的地址列表
        return addresses;
    }

}
