package main.java.com.pointsmallsystem.www.dao;

import main.java.com.pointsmallsystem.www.po.User;

import java.util.List;

public interface UserDao {

    // 根据ID查找用户
    User findById(Integer id);

    // 查找所有用户
    List<User> findAll();

    // 保存用户（新增或更新）
    void save(User user);

    // 更新用户信息
    void update(User user);

    // 删除用户
    void delete(Integer id);

    // 根据用户名查找用户
    User findByUsername(String username);

    // 其他可能的方法，比如根据邮箱查找用户等
    // User findByEmail(String email);

    // ... 可以根据需要添加更多方法
}