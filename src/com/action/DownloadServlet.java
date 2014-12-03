package com.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    
    private String contentType;
    private String enc="UTF-8";
    private String fileRoot;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        contentType = config.getInitParameter("contentType");
        fileRoot = config.getInitParameter("fileRoot");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String fileName=req.getParameter("fileName");
        String filePath=fileRoot+File.separator+fileName;
        
        File downloadFile=new File(filePath);
        if (downloadFile.exists()) {
            resp.setContentType(contentType);
            Long length=downloadFile.length();
            resp.setContentLength(length.intValue());
            fileName = URLEncoder.encode(downloadFile.getName(), enc);
            resp.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            
            ServletOutputStream servletOutputStream=resp.getOutputStream();
            FileInputStream fileInputStream=new FileInputStream(downloadFile);
            BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);
            int size=0;
            byte[] b=new byte[4096];
            while ((size=bufferedInputStream.read(b))!=-1) {
                servletOutputStream.write(b, 0, size);
            }
            servletOutputStream.flush();
            servletOutputStream.close();
            bufferedInputStream.close();
        }else {
        }
    }

}
