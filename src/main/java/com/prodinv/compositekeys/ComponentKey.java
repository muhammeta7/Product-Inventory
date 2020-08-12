package com.prodinv.compositekeys;

import com.prodinv.models.Product;
import com.prodinv.models.Bundle;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;

@Embeddable
public class ComponentKey implements Serializable
{
    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne
    @MapsId("bundle_id")
    @JoinColumn(name = "bundle_id")
    Bundle bundle;

    public ComponentKey() { }

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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComponentKey that = (ComponentKey) o;

        if (!product.equals(that.product)) return false;
        return bundle.equals(that.bundle);
    }

    @Override
    public int hashCode()
    {
        int result = product.hashCode();
        result = 31 * result + bundle.hashCode();
        return result;
    }
}
