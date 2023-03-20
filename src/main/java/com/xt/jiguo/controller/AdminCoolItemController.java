package com.xt.jiguo.controller;

import com.github.pagehelper.PageInfo;
import com.xt.jiguo.entity.CoolItem;
import com.xt.jiguo.entity.po.JsonData;
import com.xt.jiguo.service.CoolItemService;
import com.xt.jiguo.valid.CoolItemAddValidation;
import com.xt.jiguo.valid.CoolItemModifyValidation;
import com.xt.jiguo.utils.FtpUtil;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("admin/cool")
public class AdminCoolItemController {
    private  static  final List<String> allowTypes= Arrays.asList("image/jpeg","image/gif","image/png");
    private  static  final long maAllowSize=50*1024;
    @Resource
    private CoolItemService service;
    @RequestMapping(path = "list",method = RequestMethod.GET)
    public JsonData getByPage(Integer pno,Integer psize){
        List<CoolItem> items=service.findByPage(pno,psize);
        if (items.size()==0){
            return JsonData.fail(400,"查无数据");

        }
        PageInfo<CoolItem> info =new PageInfo<>(items);
        return JsonData.success(info);
    }
    @RequestMapping(path = "add",method = RequestMethod.POST)
    public JsonData add(@Validated(CoolItemAddValidation.class) CoolItem item, Errors errors, MultipartFile file, HttpServletRequest request){
        if (errors.hasErrors()){
            return JsonData.fail(400,errors.getAllErrors().get(0).getDefaultMessage());
        }
        if (file.isEmpty()){
            return JsonData.fail(400,"必须提供图片");
        }
        if (!allowTypes.contains(file.getContentType())){
            return JsonData.fail(400,"文件格式错误，只能是图片");
        }
        if (file.getSize()>maAllowSize){
            return JsonData.fail(400,"文件大小超出限制（50k）");
        }
        //3.修改文件保存的名称
        //a.使用原始名称：同名文件会覆盖
        //b.使用自定义名称（唯一值）：时间戳（适合单机模式）、uuid适合集群模式
        String originalFileName= file.getOriginalFilename();
        int dotIndex=originalFileName.lastIndexOf(".");
        String suffix=originalFileName.substring(dotIndex);
        String newFileName= UUID.randomUUID().toString()+suffix;
        try {
            /*String realPath=request.getServletContext().getRealPath("img");
            File path=new File(realPath);
            if (!path.exists()){
                path.mkdirs();
            }
            System.out.println("真实路径:"+realPath);
            File target=new File(realPath,newFileName);
            System.out.println("转存到:"+target.getAbsolutePath());
            file.transferTo(target);*/
            boolean rt= FtpUtil.upload(file.getInputStream(), newFileName);
            if (rt){
                item.setImage(FtpUtil.BaseURL+newFileName);
            }else {
                return JsonData.fail(408,"文件服务器上传失败");
            }

            service.add(item);
//            return JsonData.success("酷玩商品添加");
            return JsonData.success(item);



        } catch (IOException e) {
            return JsonData.fail(400,e.getMessage());
        }
    }
    @RequestMapping(path = "modify",method = RequestMethod.POST)
    public JsonData modify(@Validated(CoolItemModifyValidation.class) CoolItem item, Errors errors, MultipartFile file, HttpServletRequest request){
        if (errors.hasErrors()){
            return JsonData.fail(400,errors.getAllErrors().get(0).getDefaultMessage());
        }
        if (file!=null&&!file.isEmpty()){
            if (!allowTypes.contains(file.getContentType())){
                return JsonData.fail(400,"文件格式错误，只能是图片");
            }
            if (file.getSize()>maAllowSize){
                return JsonData.fail(400,"文件大小超出限制（50k）");
            }
            //3.修改文件保存的名称
            //a.使用原始名称：同名文件会覆盖
            //b.使用自定义名称（唯一值）：时间戳（适合单机模式）、uuid适合集群模式
            String originalFileName= file.getOriginalFilename();
            int dotIndex=originalFileName.lastIndexOf(".");
            String suffix=originalFileName.substring(dotIndex);
            String newFileName= UUID.randomUUID().toString()+suffix;
            try {
               /* String realPath=request.getServletContext().getRealPath("img");
                File path=new File(realPath);
                if (!path.exists()){
                    path.mkdirs();
                }
                File target=new File(realPath,newFileName);
                file.transferTo(target);
                item.setImage(newFileName);*/
                boolean rt= FtpUtil.upload(file.getInputStream(), newFileName);
                if (rt){
                    item.setImage(FtpUtil.BaseURL+newFileName);
                }else {
                    return JsonData.fail(408,"文件服务器上传失败");
                }

            } catch (IOException e) {
                return JsonData.fail(400,e.getMessage());
            }
        }
        int ret=service.modify(item);
        if (ret>0){
            return JsonData.success(item);
        }

        return JsonData.fail(400,"修改失败");
    }




}
