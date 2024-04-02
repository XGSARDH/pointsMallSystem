package main.java.com.pointsmallsystem.www.dao;

import main.java.com.pointsmallsystem.www.po.Account;
import main.java.com.pointsmallsystem.www.po.User;

import java.util.List;

public interface AccountDao {

    // 根据ID查找用户
    Account findById(Integer id);

    // 查找所有用户
    List<Account> findAll();

    // 保存用户（新增或更新）
    void save(Account account);

    // 更新用户信息
    void update(Account account);

    // 删除用户
    void delete(Integer id);

    // 根据用户名查找用户
    Account findByname(String name);

    // 用名字的子串来寻找名字
    List<Account> findByNameSubstring(String substring);

    // 用名字的子串来寻找名字,严格版
    List<Account> findByNameSubstringStrict(String substring);

    // 其他可能的方法，比如根据邮箱查找用户等
    // User findByEmail(String email);

    // ... 可以根据需要添加更多方法
}
