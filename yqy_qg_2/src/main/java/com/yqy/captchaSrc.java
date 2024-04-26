package com.yqy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class captchaSrc {

    static String[] strs = {
            "2","3","4","5","6","7","8","9",
            "a","b","c","d","e","f","g","h","i","j","k","m","n","p","q"
            ,"r","s","t","u","v","w","x","y","z"

    };



    public static BufferedImage  getImage() throws IOException {

        int w = 150;

        int h = 50;

        int imageType = BufferedImage.TYPE_INT_RGB;

        BufferedImage image = new BufferedImage(w,h,imageType);

        Graphics graphics = image.getGraphics();

        graphics.setColor(Color.YELLOW);
        graphics.fillRect(0,0,w,h);

        Random r = new Random();

        graphics.setColor(Color.red);
        graphics.setFont(new Font("楷体",Font.BOLD,25));
        int x = 30;

        int y = 30;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 4; i++) {

            int num = r.nextInt(strs.length);
            stringBuilder.append(num);

            String str = strs[num];

            graphics.drawString(str,x,y);
            x+=25;
        }
       String value = stringBuilder.toString();

        graphics.setColor(Color.black);
        graphics.setFont(new Font("楷体",Font.PLAIN,5));
        for (int i = 0; i < 6; i++) {
            int x1 = r.nextInt(130);
            int y1 = r.nextInt(45);
            int x2 = r.nextInt(130);
            int y2 = r.nextInt(45);
            graphics.drawLine(x1,y1,x2,y2);


        }
        for (int i = 0; i < 100; i++) {
            ImageIO.write(image,"jpg",new File("C:\\Users\\yanzhaonan\\IdeaProjects\\yqy_qg_2\\src\\main\\webapp\\jpg\\a"+i+".jpg"));
        }



        return image;

    }


}










