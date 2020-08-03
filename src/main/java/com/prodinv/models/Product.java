package com.prodinv.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "product",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "abbreviation"})}
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;
    @NotEmpty(message = "Name can not be empty")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long.")
    private String name;
    @NotEmpty(message = "Name can not be empty")
    @Pattern(regexp = "\\S+")
    @Size(min = 2, max = 5, message = "Abbreviation must be between 2 and 5 characters long.")
    private String abbreviation;
    @Pattern(regexp = "^[A-K]\\d[TB]$")
    private String location;
    @DecimalMax("240.0")
    @DecimalMin("0.0")
    private Double length;
    @DecimalMax("120.0")
    @DecimalMin("0.0")
    private Double width;
    @DecimalMax("120.0")
    @DecimalMin("0.0")
    private Double depth;
    @NotNull
    @Min(1)
    private Integer qty;
    @NotEmpty(message = "Description can not be empty")
    @Size(min = 2, max = 300, message = "Description must be between 2 and 5 characters long.")
    private String description;
    @NotEmpty(message = "Category can not be empty")
    @Size(min = 2, max = 32, message = "Category must be between 2 and 32 characters long.")
    private String category;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("product")
    private Set<ImageFile> photos;

    public Product() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer quantity) {
        this.qty = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<ImageFile> getPhotos()
    {
        return photos;
    }

    public void setPhotos(Set<ImageFile> photos)
    {
        this.photos = photos;
    }
}
