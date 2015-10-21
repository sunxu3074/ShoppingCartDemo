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
                '}';
    }

    public CategoryEntity(){}

    public CategoryEntity(String name, String imgUrl, String details, String id) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.details = details;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
