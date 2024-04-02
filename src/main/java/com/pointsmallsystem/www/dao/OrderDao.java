package main.java.com.pointsmallsystem.www.dao;

import main.java.com.pointsmallsystem.www.po.Merchant;
import main.java.com.pointsmallsystem.www.po.Order;

import java.util.List;

public interface OrderDao {
    List<Order> findByUserId(Integer userId);
    Order findById(Integer id);
    void save(Order order);
    void update(Order order);
    List<Order> findAll();
    void delete(Integer id);
}
