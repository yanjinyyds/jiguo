package com.xt.jiguo.entity.vo;

import com.xt.jiguo.entity.Guide;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GuideVo extends Guide {
    private Integer thumbCount=0;
    private Integer commentCount=0;

}
