package com.xt.jiguo.entity.vo;

import com.xt.jiguo.entity.TryItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TryItemVo extends TryItem {
    public static final  String END="end";
    public static final  String APPLY="apply";
    public static final  String TRY="try";
    private Integer applyCount;
    private Integer reportCount;
    private Integer remainDays;
    private String status;


}
