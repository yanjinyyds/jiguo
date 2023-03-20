package com.xt.jiguo.entity.vo;

import com.xt.jiguo.entity.CoolItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class CoolItemVo extends CoolItem implements Serializable {
    private Integer thumbCount=0;
    private Integer commentCount=0;

}
