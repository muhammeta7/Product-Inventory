package com.prodinv.dtos;

public class ProductQuantityOnly {
    private Long id;
    private Integer qty;

    public ProductQuantityOnly()
    {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
