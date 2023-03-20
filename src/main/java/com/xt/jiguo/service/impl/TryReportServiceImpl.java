package com.xt.jiguo.service.impl;

import com.github.pagehelper.PageHelper;
import com.xt.jiguo.dao.TryReportDao;
import com.xt.jiguo.entity.vo.TryReportVo;
import com.xt.jiguo.service.TryReportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class TryReportServiceImpl implements TryReportService {
    @Resource
    private TryReportDao dao;
    @Override
    public List<TryReportVo> findByPage(Integer pageNo, Integer pageSize, String order) {
        PageHelper.startPage(pageNo,pageSize);
        return dao.selectByOrder(order);
    }
}
