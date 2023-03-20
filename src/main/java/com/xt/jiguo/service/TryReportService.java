package com.xt.jiguo.service;

import com.xt.jiguo.entity.vo.TryReportVo;

import java.util.List;

public interface TryReportService {

    List<TryReportVo> findByPage(Integer pageNo, Integer pageSize,String order);
}
