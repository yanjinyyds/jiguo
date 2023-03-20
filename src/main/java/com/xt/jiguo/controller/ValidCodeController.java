package com.xt.jiguo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 图片验证码：4位数字
 * +字母
 * 讲验证码存入session
 * 将验证码转成图片：字节流
 *
 */
@Controller
public class ValidCodeController {
    @RequestMapping(path="validcode",method = RequestMethod.GET)
    public void valid(HttpSession session, OutputStream os) throws IOException {
        String code="";
        Random rnd=new Random();
        for (int i=0;i<4;i++){
            int n= rnd.nextInt(62);
            if (n<10){
                code+=n;
            } else if (n < 36) {
                code+=(char)(n-10+'a');

            }else {
                code+=(char)(n-36+'A');
            }
        }
        session.setAttribute("code",code);
        BufferedImage image=new BufferedImage(122,35,BufferedImage.TYPE_3BYTE_BGR);
        Graphics pen= image.getGraphics();
        pen.setColor(Color.WHITE);
        pen.fillRect(0,0,122,35);
        pen.setFont(new Font("Courier New",Font.BOLD|Font.ITALIC,20));
        for (int i=0;i<4;i++){
            String ch=code.substring(i,i+1);
            Color clr=new Color(rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256));
            pen.setColor(clr);
            int x=15+i*25;

            pen.drawString(ch,x,23);//beseline
        }
        ImageIO.write(image,"jpeg",os);
        os.close();
        System.out.println(code);
    }
}
