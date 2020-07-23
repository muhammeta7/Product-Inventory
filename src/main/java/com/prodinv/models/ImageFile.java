package com.prodinv.models;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class ImageFile
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fileName;
    private String type;
    @Lob
    @Column(columnDefinition = "bytea")
    private byte[] photo;

    // It's on one line.  Are you happy? :P
    public ImageFile() {}

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

    public byte[] getPhoto()
    {
        return photo;
    }

    public void setPhoto(byte[] photo)
    {
        this.photo = photo;
    }
}
