package com.prodinv.models;

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

    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long.")
    private String name;

    @OneToMany(mappedBy = "bundle")
    private Set<Component> components;

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

    public Set<Component> getComponents()
    {
        return components;
    }

    public void setComponents(Set<Component> components)
    {
        this.components = components;
    }
}
