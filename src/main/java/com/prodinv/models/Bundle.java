package com.prodinv.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "bundle")
public class Bundle
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bundle_id")
    private Long id;

    @Column(unique = true)
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long.")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bundle")
    @JsonIgnoreProperties( { "product", "bundle", "piece" } )
    private Set<Piece> pieces;

    public Bundle() { }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<Piece> getPiece()
    {
        return pieces;
    }

    public void setPieces(Set<Piece> pieces)
    {
        this.pieces = pieces;
    }
}
