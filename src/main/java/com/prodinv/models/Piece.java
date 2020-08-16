package com.prodinv.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "piece",
        uniqueConstraints = @UniqueConstraint( columnNames = { "product_id", "bundle_id"} ))
public class Piece
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "bundle_id")
    private Bundle bundle;

    @Min(0)
    private Integer defaultQty;

    public Piece() { }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
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

    public Bundle getBundle()
    {
        return bundle;
    }

    public void setBundle(Bundle bundle)
    {
        this.bundle = bundle;
    }

    public Integer getDefaultQty()
    {
        return defaultQty;
    }

    public void setDefaultQty(Integer defaultQty)
    {
        this.defaultQty = defaultQty;
    }
}
