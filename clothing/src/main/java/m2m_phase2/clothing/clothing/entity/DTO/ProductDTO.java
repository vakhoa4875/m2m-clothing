package m2m_phase2.clothing.clothing.entity.DTO;

import jakarta.persistence.*;
import m2m_phase2.clothing.clothing.entity.Category;

public class ProductDTO {
    private int productId;

    private String productName ;

    private float price;

    private int quantity;

    private String description;

    private float averageRate;

    private int rateCount;

    private int sold;

    private String pictures;

    private String videos;

    private Category category;

    public ProductDTO(int productId, String productName, float price, int quantity, String description, float averageRate, int rateCount, int sold, String pictures, String videos, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.averageRate = averageRate;
        this.rateCount = rateCount;
        this.sold = sold;
        this.pictures = pictures;
        this.videos = videos;
        this.category = category;
    }

    public ProductDTO() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(float averageRate) {
        this.averageRate = averageRate;
    }

    public int getRateCount() {
        return rateCount;
    }

    public void setRateCount(int rateCount) {
        this.rateCount = rateCount;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
