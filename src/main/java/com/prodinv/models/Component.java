package com.prodinv.models;

import com.prodinv.compositekeys.ComponentKey;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class Component
{
    @EmbeddedId
    @Column(name = "component_id")
    private ComponentKey id;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("bundle_id")
    @JoinColumn(name = "bundle_id")
    private Bundle bundle;

    @Min(0)
    private Integer default_qty;

    public Component() { }

    public ComponentKey getId()
    {
        return id;
    }

    public void setId(ComponentKey id)
    {
        this.id = id;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public Bundle getProductSet()
    {
        return bundle;
    }

    public void setProductSet(Bundle bundle)
    {
        this.bundle = bundle;
    }

    public Integer getDefault_qty()
    {
        return default_qty;
    }

    public void setDefault_qty(Integer default_qty)
    {
        this.default_qty = default_qty;
    }
}
