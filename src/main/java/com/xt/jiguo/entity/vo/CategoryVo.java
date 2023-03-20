package com.xt.jiguo.entity.vo;

import com.xt.jiguo.entity.CoolCategory;
import lombok.Data;

import java.util.List;
@Data
public class CategoryVo {
    private String title;
    private List<CoolCategory> images;
    private List<CoolCategory> noImages;

}
