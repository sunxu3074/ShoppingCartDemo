package io.github.sunxu3074.shoppoingdemo.Entity;

import java.io.Serializable;

/**
 * Created by zhangyan on 2015/10/27.
 */
public class HealthyEntity implements Serializable{
    private String id;
    private String name;
    private String details;
    private int price;
    private int number;
    private int imgUrl ;
    //TODO 加一个产品种类
    // private String category;

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    public HealthyEntity(String id, String name, String details, int price, int number, int
            imgUrl) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.price = price;
        this.number = number;
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
