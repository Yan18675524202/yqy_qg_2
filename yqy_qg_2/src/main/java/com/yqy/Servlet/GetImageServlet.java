package com.yqy.Servlet;

import com.aliyuncs.exceptions.ClientException;
import com.yqy.Service.Impl.LessonServiceImpl;
import com.yqy.Utils.AliOSSUtils;
import com.yqy.captchaSrc;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/GetImage")
@MultipartConfig

public class GetImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("number"));
        // 接收文件
        Part filePart = req.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        System.out.println(fileName);

        InputStream inputStream = filePart.getInputStream();

        AliOSSUtils aliOSSUtils = new AliOSSUtils();
        String upload = null;
        try {
            upload = aliOSSUtils.upload(filePart);
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }

        LessonServiceImpl lessonService = new LessonServiceImpl();
        int i = lessonService.addImage(id, upload);

        if (i==1){
            resp.getWriter().write("Success");
        }else {
            resp.getWriter().write("Error");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

}
