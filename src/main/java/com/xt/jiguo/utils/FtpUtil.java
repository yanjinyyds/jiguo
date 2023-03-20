package com.xt.jiguo.utils;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;


public class FtpUtil {
    public static String ConfigFileName="ftp.properties";
    private static Logger logger= Logger.getRootLogger();
    private static String ftpServer=null;
    private static int ftpPort=21;
    private static String ftpUser=null;
    private static String ftpPass=null;
    private static String ftpPath=null;

    public static String BaseURL="http://slave3/images/";

    private static void init() {
        Properties props = new Properties();
        InputStream is = null;

        try {
            is = FtpUtil.class.getClassLoader().getResourceAsStream(ConfigFileName);
            props.load(is);
            logger.debug("Load FTP Config File " + ConfigFileName + " OK!");
            String value = null;

            try {
                value = props.getProperty("ftp.port");
                ftpPort = Integer.parseInt(value);
                logger.debug("ftp.port=" + ftpPort);
            } catch (Exception ex) {
                logger.debug("ftp.port is not set,using default 21.");
            }

            value = props.getProperty("ftp.baseUrl");
            if (value != null) {
                BaseURL = value;
            }
            logger.debug("ftp.baseUrl=" + BaseURL);

            value = props.getProperty("ftp.server");
            if (value != null) {
                ftpServer = value;
            }
            logger.debug("ftp.server=" + ftpServer);

            value = props.getProperty("ftp.user");
            if (value != null) {
                ftpUser = value;
            }
            logger.debug("ftp.user=" + ftpUser);


            value = props.getProperty("ftp.pass");
            if (value != null) {
                ftpPass = value;
            }

            logger.debug("ftp.pass=" + ftpPass);

            value = props.getProperty("ftp.path");
            if (value != null) {
                ftpPath = value;
            }

            logger.debug("ftp.path=" + ftpPath);


        } catch (Exception ex) {
            logger.warn(ex.getMessage());
        } finally {
            try {
                is.close();
            } catch (Exception ex) {
            }
        }
    }


    public static boolean upload(InputStream is,String fileName){
        if (ftpServer==null){
            init();
        }

        if(is==null){
            logger.getAdditivity();
            return false;
        }
        FTPClient ftp=new FTPClient();
        try {

//连接 ftp 服务器：IP 和端口
            ftp.connect(ftpServer,ftpPort);
//登陆账号：用户名和密码
            ftp.login(ftpUser,ftpPass);

//切换到指定的上传路径
            ftp.changeWorkingDirectory(ftpPath);
            String echo=ftp.getReplyString();
//如果上传路径不存在
            if("550".equals(echo.substring(0,3))){
                System.out.println("not found");
//创建上传路径
                boolean ret=ftp.makeDirectory(ftpPath);
                if(!ret){
                    logger.error("上传文件夹不存在");
                    return false;
                }
//再次切换到指定的上传路径
                ftp.changeWorkingDirectory(ftpPath);
            }
//指定 PASV 模式
            ftp.enterLocalPassiveMode();
//指定文件类型
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
//上传文件
            ftp.storeFile(fileName, is);
//退出登录
            ftp.logout();
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            } catch (Exception e) { }
        }
        return false;
    }
}
