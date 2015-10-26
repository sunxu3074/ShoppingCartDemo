package io.github.sunxu3074.shoppoingdemo.Entity;

/**
 * Created by zhangyan on 2015/10/21.
 */
public class CategoryEntity {

    private String name;
    private String imgUrl;
    private String details;
    private String id ;

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", details='" + details + '\'' +
                ", id='" + id + '\'' +
                ", price=" + price +
                ", number=" + number +
                '}';
    }

    private int price;

    public CategoryEntity() {

    }

    private int number;

    public CategoryEntity(String name, String imgUrl, String details, String id, int price, int
            number) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.details = details;
        this.id = id;
        this.price = price;
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getDetails() {
        return details;
    }

    public String getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }
}
