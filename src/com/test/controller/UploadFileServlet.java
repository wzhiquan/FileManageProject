package com.test.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import stringutil.StringUtils;

public class UploadFileServlet extends HttpServlet
{

	/**
	 * ���л�
	 */
	private static final long serialVersionUID = 1978317209774049279L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		try
		{
			String savePath = "D:/Develop/Eclipse_WorkSpace_1/FileManageProject/WebContent/WEB-INF/attachment";
			File file = new File(savePath);
			if(!file.isDirectory())
			{
				// mkdirs()���Խ����༶�ļ��У� mkdir()ֻ�Ὠ��һ�����ļ���
				if(!file.mkdirs())
				{
					throw new Exception("�����ļ�Ŀ¼ʧ��");
				}
			}
			
			// Apache�ļ��ϴ����
			
			// 1.��������������
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			// 2.�����ļ��ϴ�������
			ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
			// 3.���ñ����ʽ
			fileUpload.setHeaderEncoding("UTF-8");
			
			if(!fileUpload.isMultipartContent(req))
			{
				throw new Exception("����multipart/form-data��������");
			}
			else
			{
				List<FileItem> fileItem = fileUpload.parseRequest(req);
				
				for(FileItem tempItem: fileItem)
				{
					// ��Ҫ�ж�һ���ǲ�����ͨ�ı����ֶΣ�
					// ����API:
					// boolean isFormField()���������ж�FileItem������װ��������һ����ͨ�ı����ֶΣ�����һ���ļ����ֶΣ��������ͨ���ֶ��򷵻�true�����򷵻�false��
					// String getFieldName()���ڷ��ر���ǩname���Ե�ֵ��
					// String getName()���ڻ���ļ��ϴ��ֶ��е��ļ�����ע��IE��FireFox�л�ȡ���ļ����ǲ�һ���ģ�IE���Ǿ���·����FireFox��ֻ���ļ�����
					if(tempItem.isFormField())
					{
						System.out.println(tempItem.getFieldName());
					}
					else
					{
						String fileName = tempItem.getName();
						if(null != fileName && !"".equals(fileName))
						{
							// ��ȡ�ļ�������
							InputStream inputStream = tempItem.getInputStream();
							
							FileOutputStream fileOutputStream = new FileOutputStream(savePath + File.separator + fileName);
							
							byte[] bytes1 = new byte[1024];
							int length = 0;
							while((length = inputStream.read(bytes1)) > 0)
							{
								fileOutputStream.write(bytes1);
							}
							
							IOUtils.closeQuietly(inputStream);
							IOUtils.closeQuietly(fileOutputStream);
						}
					}
				}
				
				req.setAttribute("message", "�ɹ��ϴ��ļ�");
				req.getRequestDispatcher("/message.jsp").forward(req, resp);
			}
		}
		catch(Exception e)
		{
			req.setAttribute("message", "�����쳣");
			req.getRequestDispatcher("/message.jsp").forward(req, resp);
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}
}
		
		
		
		