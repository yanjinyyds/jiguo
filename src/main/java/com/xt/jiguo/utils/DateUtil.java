package com.xt.jiguo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    static  SimpleDateFormat fmt1=new SimpleDateFormat("yyyy-MM-dd");
    static  SimpleDateFormat fmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final long TIME_OF_ONE_DAY=24*60*60*1000;
    private static final long TIME_OF_GMT8=8*60*60*1000;
    public static void main(String[] args) throws ParseException {
        Date now=new Date();
        /*SimpleDateFormat fmt1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat fmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");*/
        Date d1=fmt2.parse("2023-01-23");
        Date d2=fmt1.parse("2023-01-04 1:23:45");
        int days=getDaysBetweenDates(d1,d2);
        System.out.println(days);/*
        Date d=getCurrentDate();
        Date d=getDate(d1);
        System.out.println(fmt1.format(d));*/

        System.out.println(before(d1,d2));

    }




    public static int getDaysBetweenDates(Date from,Date to){
        long t1=from.getTime();
        long t2=to.getTime();
        t1+=TIME_OF_GMT8;
        t2+=TIME_OF_GMT8;
        t1=t1-t1%TIME_OF_ONE_DAY;
        t2=t2-t2%TIME_OF_ONE_DAY;
        long diff=t1-t2;
        int days=(int)(diff/TIME_OF_ONE_DAY);
        return days;
    }
    public static boolean before(Date from,Date to){
        long t1=from.getTime();
        long t2=to.getTime();
        t1+=TIME_OF_GMT8;
        t2+=TIME_OF_GMT8;
        t1=t1-t1%TIME_OF_ONE_DAY;
        t2=t2-t2%TIME_OF_ONE_DAY;
        long diff=t2-t1;
        return diff>0;
    }
    public static Date getCurrentDate(){
        long ts=System.currentTimeMillis();
        ts=ts-ts%TIME_OF_GMT8;
        ts=ts-ts%TIME_OF_ONE_DAY;
        return new Date(ts);
    }
    public static Date getDate(){
        long ts=System.currentTimeMillis();
        ts=ts-ts%TIME_OF_GMT8;
        ts=ts-ts%TIME_OF_ONE_DAY;


        return new Date(ts);
    }


}
