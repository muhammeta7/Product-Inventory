package com.prodinv.models;

//DTO
public class ProductCount {

    private Long productId;

    private Integer qty;

    public ProductCount() {}

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
