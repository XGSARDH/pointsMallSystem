package main.java.com.pointsmallsystem.www.dao;

import main.java.com.pointsmallsystem.www.po.Product;

import java.util.List;

public interface ProductDao {
    Product findById(Integer id);
    void save(Product product);
    void update(Product product);
    List<Product> findAll();
    void delete(Integer id);
}
