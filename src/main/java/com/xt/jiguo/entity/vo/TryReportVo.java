package com.xt.jiguo.entity.vo;

import com.xt.jiguo.entity.TryReport;
import com.xt.jiguo.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TryReportVo extends TryReport {
    private User author;
    private Integer thumbCount;
    private Integer commentCount;
    private String itemName;
    private Integer reportCount;

}
