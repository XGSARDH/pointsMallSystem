package main.java.com.pointsmallsystem.www.dao;

import main.java.com.pointsmallsystem.www.po.Cart;
import main.java.com.pointsmallsystem.www.po.Merchant;

import java.util.List;

public interface MerchantDao {
    Merchant findById(Integer id);
    void save(Merchant merchant);
    void update(Merchant merchant);
    List<Merchant> findAll();
    void delete(Integer id);
}
