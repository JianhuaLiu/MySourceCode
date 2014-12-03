package com.action;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.inspur.logicaldoc.DocumentManagerService;
import com.logicaldoc.core.document.Document;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.regexp.internal.REUtil;

public class DownloadAction extends ActionSupport
{

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    private static final long serialVersionUID = 1L;
    
    private Dao dao=new Dao();
    private List<FileItem>list;
    private String fileId;    
    private byte[] bytes;
    private FileItem fileItem;
    
    private String fileName;  
    
    public String getFileName()
	{
		return fileName;
	}

	public String getType()
	{
		return type;
	}


	private String type;
    
    public void setType(String type)
    {
    	this.type = type;
    }
    
    public void setFileName(String fileName) {  
        this.fileName = fileName;  
    } 
    
    //获得list
    public String list() throws Exception
    {
        list=dao.getFileList();
        return "list-success";
    }
//    //获得文件
//    public String get() throws NumberFormatException, Exception
//    {
//    	DocumentManagerService service = new DocumentManagerService();
//    	Document doc = service.getDocument(Long.parseLong(fileId));
//    	byte[] bytes = service.downloadFile(Long.parseLong(fileId));
//    	InputStream inputStream = new ByteArrayInputStream(bytes);
//    	
//		String path = "E:\\logs";
//		String fileName = doc.getTitle();
//		String exType = doc.getType();
//
//		OutputStream outputStream = new FileOutputStream(path
//				+ "\\"+fileName+"."+exType);
//
//		int bytesWritten = 0;
//		int byteCount = 0;
//
//		while ((byteCount = inputStream.read(bytes)) != -1)
//		{
//			outputStream.write(bytes, bytesWritten, byteCount);
//			bytesWritten += byteCount;
//		}
//		inputStream.close();
//		outputStream.close();
//        return SUCCESS;
//    }
    
    //获得输入流
    public InputStream getInputStream() throws NumberFormatException, Exception
    {
    	fileName=fileName+"."+type;
    	System.out.println("文件类型："+fileName);
    	DocumentManagerService service = new DocumentManagerService();
    	byte[] bytes = service.downloadFile(Long.parseLong(fileId));
    	return new ByteArrayInputStream(bytes);
//        try
//        {
//        	InputStream inputStream = new ByteArrayInputStream(bytes);
//
//    		String path = "E:\\logs";
//    		String fileName = "test";
//    		String exType = "xml";
//
//    		OutputStream outputStream = new FileOutputStream(path
//    				+ "\\"+fileName+"."+exType);
//
//    		int bytesWritten = 0;
//    		int byteCount = 0;
//
//    		while ((byteCount = inputStream.read(bytes)) != -1)
//    		{
//    			outputStream.write(bytes, bytesWritten, byteCount);
//    			bytesWritten += byteCount;
//    		}
//    		inputStream.close();
//    		outputStream.close();
////            String path=ServletActionContext.getServletContext().getRealPath("/");
////            String fileName=path+fileItem.getLocationPath();
////            FileInputStream fis=new FileInputStream(fileName);
////            return fis;
//        }
//        catch (FileNotFoundException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return null;
    }
    
    //获得文件类型
    public String getContentType()
    {
        return fileItem.getContentType();
    }
    
    //获得文件下载位置
    public String getContentDisposition()
    {
        try
        {
            return "attachment;filename="+URLEncoder.encode(fileItem.getFileName(),"utf-8");
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    //获得文件字节大小
    public int getContentLength()
    {
        return fileItem.getContentLength();
    }
    
    
    public List<FileItem> getList()
    {
        return list;
    }

    public void setList(List<FileItem> list)
    {
        this.list = list;
    }
    public String getFileId()
    {
        return fileId;
    }
    public void setFileId(String fileId)
    {
        this.fileId = fileId;
    }
    
    
    public String execute()throws Exception{  
        return SUCCESS;  
    }  

}
