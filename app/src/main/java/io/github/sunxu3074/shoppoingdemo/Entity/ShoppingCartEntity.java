package io.github.sunxu3074.shoppoingdemo.Entity;

/**
 * Created by zhangyan on 2015/10/29.
 */
public class ShoppingCartEntity {

    private String id ;
    private String name;
    private String category;
    private int price;
    private int number ;
    private int imgUrl;

    public ShoppingCartEntity(String id, String name, String category, int price, int number,
                              int imgUrl) {
        this.id = id;
        this.name = name;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }
}
