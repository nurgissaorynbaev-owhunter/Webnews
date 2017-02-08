package com.nurgissao.webnews.model.entity;


public class ProductOrder {
    private int id;
    private int customerId;
    private int productId;
    private int productQuantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", productId=" + productId +
                ", productQuantity=" + productQuantity +
                '}';
    }
}
