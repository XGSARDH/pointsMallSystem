package main.java.com.pointsmallsystem.www.dao.impl;

import main.java.com.pointsmallsystem.www.dao.MerchantDao;
import main.java.com.pointsmallsystem.www.po.Merchant;
import main.java.com.pointsmallsystem.www.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MerchantDaoImpl implements MerchantDao {
    @Override
    public Merchant findById(Integer id) {
        String sql = "SELECT * FROM merchant WHERE id = ?";
        Merchant merchant = null;
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    merchant = new Merchant();
                    merchant.setId(rs.getInt("id"));
                    merchant.setUserId(rs.getInt("user_id"));
                    merchant.setName(rs.getString("name"));
                    merchant.setAddress(rs.getString("address"));
                    merchant.setBusiness(rs.getString("business"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return merchant;
    }

    @Override
    public void save(Merchant merchant) {
        String sql = "INSERT INTO merchant (user_id, name, address, business) VALUES (?, ?, ?, ?)";
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, merchant.getUserId());
            pstmt.setString(2, merchant.getName());
            pstmt.setString(3, merchant.getAddress());
            pstmt.setString(4, merchant.getBusiness());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Merchant merchant) {
        String sql = "UPDATE merchant SET user_id = ?, name = ?, address = ?, business = ? WHERE id = ?";
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, merchant.getUserId());
            pstmt.setString(2, merchant.getName());
            pstmt.setString(3, merchant.getAddress());
            pstmt.setString(4, merchant.getBusiness());
            pstmt.setInt(5, merchant.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Merchant> findAll() {
        String sql = "SELECT * FROM merchant";
        List<Merchant> merchants = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Merchant merchant = new Merchant();
                merchant.setId(rs.getInt("id"));
                merchant.setUserId(rs.getInt("user_id"));
                merchant.setName(rs.getString("name"));
                merchant.setAddress(rs.getString("address"));
                merchant.setBusiness(rs.getString("business"));
                merchants.add(merchant);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return merchants;
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM merchant WHERE id = ?";
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
