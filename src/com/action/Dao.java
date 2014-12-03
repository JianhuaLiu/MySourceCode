package com.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inspur.logicaldoc.DocumentManagerService;
import com.logicaldoc.core.document.Document;

public class Dao
{
	DocumentManagerService service = new DocumentManagerService();

	// 获得文件下载列表
	public List<FileItem> getFileList() throws Exception
	{
		Map<String, FileItem> files = new HashMap<String, FileItem>();

		Collection<Document> collection = service.getAllDocument();
		for (Document doc : collection)
		{
			files.put(Long.toString(doc.getId()),
					new FileItem(Long.toString(doc.getId()), doc.getTitle(),
							doc.getType(), "", 21 * 1024, doc.getCreation()
									.toString()));
		}
		return new ArrayList<FileItem>(files.values());
	}

	// 根据id获得单个文件
	public byte[] getFile(String docId) throws NumberFormatException, Exception
	{
		return service.downloadFile(Long.parseLong(docId));
	}

	public FileItem getFileItem(String docId) throws NumberFormatException,
			Exception
	{
		Document doc = service.getDocument(Long.parseLong(docId));
		return new FileItem(docId, doc.getTitle(), doc.getType(), "", 1000, doc
				.getCreation().toString());
	}
}
