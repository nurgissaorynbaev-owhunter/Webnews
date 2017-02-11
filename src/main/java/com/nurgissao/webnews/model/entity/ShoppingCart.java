package com.nurgissao.webnews.model.entity;

public class ShoppingCart {

    private int id;
    private int productId;
    private String cookieId;
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCookieId() {
        return cookieId;
    }

    public void setCookieId(String cookieId) {
        this.cookieId = cookieId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", productId=" + productId +
                ", cookieId='" + cookieId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
