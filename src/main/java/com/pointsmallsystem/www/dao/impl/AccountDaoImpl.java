package main.java.com.pointsmallsystem.www.dao.impl;

import main.java.com.pointsmallsystem.www.dao.AccountDao;
import main.java.com.pointsmallsystem.www.po.Account;
import main.java.com.pointsmallsystem.www.po.User;
import main.java.com.pointsmallsystem.www.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    @Override
    public Account findById(Integer id) {
        String sql = "SELECT * FROM account WHERE id = ?"; // 假设表名为account
        Account account = null;

        // 使用try-with-resources确保资源自动关闭
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) { // 使用try-with-resources确保ResultSet也自动关闭
                if (rs.next()) {
                    // 创建一个Account对象，从结果集中获取id、name和money
                    int accountId = rs.getInt("id");
                    String accountName = rs.getString("name");
                    int accountMoney = rs.getInt("money");
                    account = new Account(accountId, accountName, accountMoney);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // 在生产环境中，建议使用日志记录而不是打印堆栈跟踪
        }
        return account;
    }

    @Override
    public void save(Account account) {
        String sql = "INSERT INTO account (id, name, money) VALUES (?, ?, ?)"; // 假设表名为account
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, account.getId());
            pstmt.setString(2, account.getName());
            pstmt.setInt(3, account.getMoney());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Account account) {
        String sql = "UPDATE account SET name = ?, money = ? WHERE id = ?"; // 假设表名为account
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, account.getName());
            pstmt.setInt(2, account.getMoney());
            pstmt.setInt(3, account.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Account> findAll() {
        String sql = "SELECT * FROM account"; // Assuming the table name is account
        List<Account> users = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Account user = new Account(0,"",0);
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name")); // Assuming User uses username and DB has name
                // Set other properties...
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM account WHERE id = ?"; // Assuming the table name is account
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account findByname(String name) {
        String sql = "SELECT * FROM account WHERE name = ?"; // Assuming the table uses name for username
        Account user = null;
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new Account(0,"",0);
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setMoney(rs.getInt("money"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<Account> findByNameSubstring(String substring) {
        List<Account> matchingAccounts = new ArrayList<>();

        // 连接数据库，并执行查询
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM account WHERE name LIKE ?")) {
            pstmt.setString(1, "%" + substring + "%");
            ResultSet rs = pstmt.executeQuery();

            // 遍历结果集并将匹配的账户添加到列表中
            while (rs.next()) {
                Account account = new Account(rs.getInt("id"), rs.getString("name"), rs.getInt("money"));
                matchingAccounts.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return matchingAccounts;
    }

    public List<Account> findByNameSubstringStrict(String substring) {
        List<Account> matchingAccounts = new ArrayList<>();

        // 连接数据库，并执行查询（严格按大小写匹配）
        try (Connection conn = JdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM account WHERE name LIKE BINARY ?")) {
            pstmt.setString(1, "%" + substring + "%");
            ResultSet rs = pstmt.executeQuery();

            // 遍历结果集并将匹配的账户添加到列表中
            while (rs.next()) {
                Account account = new Account(rs.getInt("id"), rs.getString("name"), rs.getInt("money"));
                matchingAccounts.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return matchingAccounts;
    }

}
