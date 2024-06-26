package main.java.com.pointsmallsystem.www.po;

public class Merchant {
    private Integer id;
    private Integer userId;
    private String name;
    private String address;
    private String business;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", business='" + business + '\'' +
                '}';
    }
}
