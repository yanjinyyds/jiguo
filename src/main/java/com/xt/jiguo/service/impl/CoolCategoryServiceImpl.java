package com.xt.jiguo.service.impl;

import com.xt.jiguo.dao.CoolCategoryDao;
import com.xt.jiguo.entity.CoolCategory;
import com.xt.jiguo.entity.vo.CategoryVo;
import com.xt.jiguo.service.CoolCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CoolCategoryServiceImpl implements CoolCategoryService {
    @Resource
    private CoolCategoryDao dao;
    @Override
    public List<CategoryVo> getCateories() {
        List<CoolCategory> cts=dao.selectCategories();
//        List<CategoryVo> vos=new ArrayList<>();
        Map<String,CategoryVo> map=new HashMap<>();
        for (CoolCategory ct : cts) {
            String title=ct.getCategory();
            CategoryVo cvo=map.get(title);
            if (cvo==null){
                cvo=new CategoryVo();
                cvo.setTitle(title);
                cvo.setImages(new ArrayList<>());
                cvo.setNoImages(new ArrayList<>());
                map.put(title,cvo);
            }
            if (ct.getImage()==null){
                cvo.getNoImages().add(ct);
            }else {
                cvo.getImages().add(ct);
            }
        }
        return new ArrayList<>(map.values());
    }
}
