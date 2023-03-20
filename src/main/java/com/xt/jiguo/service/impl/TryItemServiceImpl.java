package com.xt.jiguo.service.impl;

import com.github.pagehelper.PageHelper;
import com.xt.jiguo.dao.TryItemDao;
import com.xt.jiguo.dao.TryReportDao;
import com.xt.jiguo.entity.TryItem;
import com.xt.jiguo.entity.vo.TryItemVo;
import com.xt.jiguo.service.TryItemService;
import com.xt.jiguo.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)
public class TryItemServiceImpl implements TryItemService {
    @Resource
    private TryItemDao dao;
    @Resource
    private TryReportDao reportDao;
    @Override
    public List<TryItemVo> getByPage(Integer pageNo, Integer pageSize, String category, String status) {
        PageHelper.startPage(pageNo,pageSize);
        List<TryItemVo> vos=dao.selectByCategoryAndStatus(category,status);
        Date now=new Date();
        for (TryItemVo vo:vos){
            if (DateUtil.before(vo.getEndDate(),now)){
                vo.setStatus(TryItemVo.END);
                vo.setReportCount(reportDao.selectCountById(vo.getId()));
            }else if(vo.getBeginDate().after(now)){
                vo.setRemainDays(DateUtil.getDaysBetweenDates(vo.getBeginDate(),now));
                vo.setStatus(TryItemVo.APPLY);
            }else {
                vo.setStatus(TryItemVo.TRY);
            }

        }
        return vos;
    }

    @Override
    public List<TryItem> findByPage(Integer pageNo, Integer pageSize) {


        return null;
    }

    @Override
    public int add(TryItem tryItem) {
        return dao.insert(tryItem);
    }

    @Override
    public int modify(TryItem tryItem) {
        return dao.updateByPrimaryKeySelective(tryItem);
    }
}
