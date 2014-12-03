package com.action;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.inspur.logicaldoc.DocumentManagerService;
import com.logicaldoc.core.document.Document;
import com.logicaldoc.core.security.dao.FolderDAO;
import com.logicaldoc.util.Context;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport
{

    /**
     * serialVersionUID:TODO����һ�仰�������������ʾʲô��
     *
     * @since Ver 1.1
     */
    private static final long serialVersionUID = 1L;
    
    private List<File>image;
    private List<String>imageContentType;
    private List<String>imageFileName;
    

	@Override
    public String execute() throws Exception
    {
        String path=ServletActionContext.getServletContext().getRealPath("/images");
        System.out.println("����·��Ϊ"+path);
        
        if (image.size()>0)
        {
            File savedir=new File(path);
            if(!savedir.exists()) savedir.mkdirs(); 
            for (int i = 0; i < image.size(); i++)
            {
                System.out.println("datas�ĸ���"+image.size());
                File saveFile=new File(savedir, imageFileName.get(i));
                FileUtils.copyFile(image.get(i), saveFile);
                
                DocumentManagerService mgr = new DocumentManagerService();
                Document doc = mgr.saveFiles(saveFile);
            }
            
            
            
        }else {
            System.out.println("datasΪ��");
        }
        return SUCCESS;
    }

    public List<File> getImage()
    {
        return image;
    }

    public void setImage(List<File> image)
    {
        this.image = image;
    }

    public List<String> getImageContentType()
    {
        return imageContentType;
    }

    public void setImageContentType(List<String> imageContentType)
    {
        this.imageContentType = imageContentType;
    }

    public List<String> getImageFileName()
    {
        return imageFileName;
    }

    public void setImageFileName(List<String> imageFileName)
    {
        this.imageFileName = imageFileName;
    }
    
    
    

}
