package com.prodinv.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "image",
        uniqueConstraints={@UniqueConstraint(columnNames={"file_name"})})
public class ImageFile
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "file_name")
    @NotBlank(message = "Files must have a file name.")
    private String fileName;
    @Pattern(regexp = "^image/.+", message = "Must submit a valid image format.")
    @NotBlank(message = "File must have a content type.")
    private String type;
    @Column(name = "image_bytes", columnDefinition = "bytea")
    @NotEmpty(message = "Files must not be empty.")
    private byte[] imgBytes;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("photos")
    private Product product;
    
    public ImageFile() {}

    public ImageFile(String fileName, String contentType, byte[] imgBytes)
    {
        this.fileName = fileName;
        this.type = contentType;
        this.imgBytes = imgBytes;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public byte[] getImgBytes()
    {
        return imgBytes;
    }

    public void setImgBytes(byte[] imgBytes)
    {
        this.imgBytes = imgBytes;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
