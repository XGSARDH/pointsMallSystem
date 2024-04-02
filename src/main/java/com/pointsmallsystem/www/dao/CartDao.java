package main.java.com.pointsmallsystem.www.dao;

import main.java.com.pointsmallsystem.www.po.Address;
import main.java.com.pointsmallsystem.www.po.Cart;

import java.util.List;

public interface CartDao {
    Cart findById(Integer id);
    void save(Cart cart);
    void update(Cart cart);
    List<Cart> findAll();
    void delete(Integer id);
}
