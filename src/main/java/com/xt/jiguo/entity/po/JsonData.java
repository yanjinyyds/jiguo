package com.xt.jiguo.entity.po;
import lombok.Data;
@Data

public class JsonData {
    private String message;
    private Integer errorCode;
    private Object data;

    public static JsonData success(Object data){
       JsonData jsonData=new JsonData();
       jsonData.setData(data);
       jsonData.setErrorCode(0);
       return jsonData;
    }

    public static JsonData fail(Integer error,String message){
        JsonData tmp=new JsonData();
        tmp.setData(null);
        tmp.setErrorCode(error);
        tmp.setMessage(message);
        return  tmp;
    }




}
