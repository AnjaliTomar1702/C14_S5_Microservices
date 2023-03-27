package com.bej.userproductservice.domain;
import org.springframework.data.annotation.Id;
public class Product {
    @Id
    private String productCode;
    private String productName;
    private String productDescription;
    private String isInStock;

    public Product() {
    }

    public Product(String productCode, String productName, String productDescription, String isInStock) {
        this.productCode = productCode;
        this.productName = productName;
        this.productDescription = productDescription;
        this.isInStock = isInStock;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getIsInStock() {
        return isInStock;
    }

    public void setIsInStock(String isInStock) {
        this.isInStock = isInStock;
    }
}
