package com.xt.jiguo.entity;

import com.xt.jiguo.valid.CoolItemAddValidation;
import com.xt.jiguo.valid.CoolItemModifyValidation;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class CoolItem implements Serializable {
    @NotNull(groups = {CoolItemModifyValidation.class})
    private Integer id;
    @NotNull(groups = {CoolItemModifyValidation.class, CoolItemAddValidation.class})
    private String name;
    @NotNull(groups = {CoolItemModifyValidation.class, CoolItemAddValidation.class})
    private String title;

    private Double price;

    private String image;

    private Date pubDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }
}