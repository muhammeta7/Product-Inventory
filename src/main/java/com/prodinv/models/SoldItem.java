package com.prodinv.models;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SoldItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Item soldItem;
    private String purchaser;
    @CreationTimestamp
    private LocalDateTime sellDate;
    private LocalDateTime shipDate;
    private Integer qty;

    public SoldItem()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Item getSoldItem()
    {
        return soldItem;
    }

    public void setSoldItem(Item soldItem)
    {
        this.soldItem = soldItem;
    }

    public String getPurchaser()
    {
        return purchaser;
    }

    public void setPurchaser(String purchaser)
    {
        this.purchaser = purchaser;
    }

    public LocalDateTime getSellDate()
    {
        return sellDate;
    }

    public void setSellDate(LocalDateTime sellDate)
    {
        this.sellDate = sellDate;
    }

    public LocalDateTime getShipDate()
    {
        return shipDate;
    }

    public void setShipDate(LocalDateTime shipDate)
    {
        this.shipDate = shipDate;
    }

    public Integer getQty()
    {
        return qty;
    }

    public void setQty(Integer qty)
    {
        this.qty = qty;
    }
}
