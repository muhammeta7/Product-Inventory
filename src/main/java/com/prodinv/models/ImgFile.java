package com.prodinv.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="files")
public class ImgFile {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;

    private String name;
    private String type;

    @Lob
    private byte[] imgData;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;




}
