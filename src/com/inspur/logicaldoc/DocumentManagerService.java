package com.inspur.logicaldoc;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;

import org.junit.Test;

import com.google.gwt.user.client.Random;
import com.logicaldoc.core.document.Document;
import com.logicaldoc.core.document.DocumentManager;
import com.logicaldoc.core.document.History;
import com.logicaldoc.core.document.Version;
import com.logicaldoc.core.document.dao.DocumentDAO;
import com.logicaldoc.core.document.dao.VersionDAO;
import com.logicaldoc.core.searchengine.SearchEngine;
import com.logicaldoc.core.security.User;
import com.logicaldoc.core.security.dao.FolderDAO;
import com.logicaldoc.core.security.dao.UserDAO;
import com.logicaldoc.core.store.Storer;
import com.logicaldoc.util.Context;

public class DocumentManagerService extends AbstractCoreTCase
{
	private DocumentDAO docDao;

	private VersionDAO verDao;

	private UserDAO userDao;

	private FolderDAO folderDao;

	// Instance under test
	private DocumentManager documentManager;

	boolean isSetup = false;

	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		docDao = (DocumentDAO) context.getBean("DocumentDAO");
		verDao = (VersionDAO) context.getBean("VersionDAO");
		userDao = (UserDAO) context.getBean("UserDAO");
		folderDao = (FolderDAO) context.getBean("FolderDAO");

		// Make sure that this is a DocumentManagerImpl instance
		documentManager = (DocumentManager) context.getBean("DocumentManager");
		documentManager.setStorer(new MockStorer());

		isSetup = true;
	}

	@Test
	public void uploadTest() throws Exception
	{
		File file = new File("E:/1.txt");
		this.saveFiles(file);
	}

	@Test
	public void downloadTest() throws Exception
	{
		byte[] bytes = this.downloadFile(5537792L);
		InputStream inputStream = new ByteArrayInputStream(bytes);

		String path = "E:\\logs";
		String fileName = "test";
		String exType = "xml";

		OutputStream outputStream = new FileOutputStream(path
				+ "\\"+fileName+"."+exType);

		int bytesWritten = 0;
		int byteCount = 0;

		while ((byteCount = inputStream.read(bytes)) != -1)
		{
			outputStream.write(bytes, bytesWritten, byteCount);
			bytesWritten += byteCount;
		}
		inputStream.close();
		outputStream.close();
	}

	public byte[] downloadFile(long docId) throws Exception
	{

		// SearchEngine indexer = (SearchEngine)
		// Context.getInstance().getBean(SearchEngine.class);
		//
		// String content = indexer.getHit(docId).getContent();
		//
		// byte[] bytes = content.getBytes(Charset.forName("UTF-8"));
		//
		// return bytes;
		if (!isSetup)
			this.setUp();
		Document doc = docDao.findById(docId);

		Storer storer = (Storer) Context.getInstance().getBean(Storer.class);
		String resourceName = storer.getResourceName(doc, "1.0", null);

		if (!storer.exists(doc.getId(), resourceName))
		{
			throw new FileNotFoundException(resourceName);
		}

		// Now we can append the 'document' attachment to the response
		byte[] bytes = storer.getBytes(doc.getId(), resourceName);

		return bytes;
		// String mime = "application/octet-stream";
		// try {
		// MagicMatch match = Magic.getMagicMatch(bytes, true);
		// mime = match.getMimeType();
		// } catch (Throwable t) {
		//
		// }
		//
		// DataHandler content = new DataHandler(new ByteArrayDataSource(bytes,
		// mime));
		// return content;
	}

	public Document saveFiles(File file) throws FileNotFoundException,
			Exception
	{
		if (!isSetup)
			this.setUp();
		User user = userDao.findByUserName("admin");
		Document doc = docDao.findById(6127616L);
		String filePath = file.getPath();

		docDao.initialize(doc);
		doc = (Document) doc.clone();
		long id = (long) (int) (Math.random() * 100);
		doc.setId(id);
		doc.setTitle(file.getName().substring(0, file.getName().lastIndexOf(".")));
		String extName = filePath.substring(filePath.lastIndexOf(".") + 1);// À©Õ¹Ãû
		doc.setType(extName);
		
		doc.setFileName(file.getName()); 

		History transaction = new History();
		transaction.setFolderId(103);
		transaction.setUser(user);
		transaction.setDocId(doc.getId());
		transaction.setUserId(1);
		transaction.setNotified(0);
		// transaction
		transaction.setComment("Test Comment Info");

		doc.setCustomId(UUID.randomUUID().toString());

		Document newDoc = documentManager.create(new FileInputStream(filePath),
				doc, transaction);

		newDoc = docDao.findById(newDoc.getId());

		return newDoc;
	}
	
	public Collection<Document> getAllDocument() throws Exception
	{
		if (!isSetup)
			this.setUp();
		return docDao.findAll();
	}
	
	public Document getDocument(long docId) throws Exception
	{
		if (!isSetup)
			this.setUp();
		return docDao.findById(docId);
	}
}
