package com.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.converter.OpenOfficePDFConverter;
import com.converter.SWFToolsSWFConverter;
import com.inspur.logicaldoc.DocumentManagerService;
import com.opensymphony.xwork2.ActionSupport;

public class DocumentViewAction extends ActionSupport
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fileId;

	public String getFileId()
	{
		return fileId;
	}

	public void setFileId(String fileId)
	{
		this.fileId = fileId;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getFullFileName()
	{
		return fullFileName;
	}

	public void setFullFileName(String fullFileName)
	{
		this.fullFileName = fullFileName;
	}

	private String fileName;
	private String type;
	private String fullFileName;

	public String execute() throws NumberFormatException, Exception
	{
		System.out.println("DocumentViewExecute");
		String path = this.getClass().getResource("/").getPath();// �õ�d:/tomcat/webapps/������WEB-INF/classes/·��
		path = path.substring(1, path.indexOf("WEB-INF/classes"));// ��·���ַ�����ȡ������·��
		// ��ȡ�ļ�����������Ȼ�󽫶�������ת��Ϊ�ļ���Ȼ���ļ�ת��Ϊswf�����浽����·��
		String docPath = path + "DocumentViewSources/doc/";
		String pdfPath = path + "DocumentViewSources/pdf/";
		String swfPath = path + "DocumentViewSources/swf/";

		fullFileName = fileName + "." + type;
		System.out.println("�ļ����ƣ�" + fileName + "  �ļ�ȫ�ƣ�" + fullFileName);
		System.out.println("ID:" + fileId);
		DocumentManagerService service = new DocumentManagerService();
		byte[] bytes = service.downloadFile(Long.parseLong(fileId));
		InputStream is = new ByteArrayInputStream(bytes);
		String docFullName;
		if (type.equals("pdf"))
			docFullName = pdfPath + fullFileName;
		else
			docFullName = docPath + fullFileName;

		this.inputstreamtofile(is, new File(docFullName));

		String pdfFullName;
		if (!type.equals("pdf"))
		{
			OpenOfficePDFConverter pdfConverter = new OpenOfficePDFConverter();
			pdfFullName = pdfPath + fileName + ".pdf";
			pdfConverter.convert2PDF(docFullName, pdfFullName);
		} else
			pdfFullName = docFullName;

		SWFToolsSWFConverter swfConverter = new SWFToolsSWFConverter();
		swfConverter.convert2SWF(pdfFullName, swfPath + fileName + ".swf");

		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("fileName", fileName);
		return SUCCESS;
	}

	public void inputstreamtofile(InputStream ins, File file)
			throws IOException
	{
		if (!file.exists())
		{
			file.createNewFile();
		}
		OutputStream os = new FileOutputStream(file);
		int ch = 0;
		while ((ch = ins.read()) != -1)
		{
			os.write(ch);
		}
		os.close();
		ins.close();
	}

}
