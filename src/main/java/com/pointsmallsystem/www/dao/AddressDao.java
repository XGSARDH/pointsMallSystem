package main.java.com.pointsmallsystem.www.dao;

import main.java.com.pointsmallsystem.www.po.Account;
import main.java.com.pointsmallsystem.www.po.Address;

import java.util.List;

public interface AddressDao {
    Address findById(Integer id);
    void save(Address address);
    void update(Address address);
    List<Address> findAll();
    void delete(Integer id);
    Address findByRecipient(String recipient);
    List<Address> findByAddressSubstring(String substring);

    List<Address> findByUserId(Integer userId);

}
