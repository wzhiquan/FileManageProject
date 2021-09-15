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
	 * 序列化
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
				// mkdirs()可以建立多级文件夹， mkdir()只会建立一级的文件夹
				if(!file.mkdirs())
				{
					throw new Exception("创建文件目录失败");
				}
			}
			
			// Apache文件上传组件
			
			// 1.创建解析器工厂
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
			// 2.创建文件上传解析器
			ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
			// 3.设置编码格式
			fileUpload.setHeaderEncoding("UTF-8");
			
			if(!fileUpload.isMultipartContent(req))
			{
				throw new Exception("不是multipart/form-data数据类型");
			}
			else
			{
				List<FileItem> fileItem = fileUpload.parseRequest(req);
				
				for(FileItem tempItem: fileItem)
				{
					// 需要判断一下是不是普通文本表单字段，
					// 核心API:
					// boolean isFormField()方法用于判断FileItem类对象封装的数据是一个普通文本表单字段，还是一个文件表单字段，如果是普通表单字段则返回true，否则返回false。
					// String getFieldName()用于返回表单标签name属性的值。
					// String getName()用于获得文件上传字段中的文件名。注意IE或FireFox中获取的文件名是不一样的，IE中是绝对路径，FireFox中只是文件名。
					if(tempItem.isFormField())
					{
						System.out.println(tempItem.getFieldName());
					}
					else
					{
						String fileName = tempItem.getName();
						if(null != fileName && !"".equals(fileName))
						{
							// 读取文件输入流
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
				
				req.setAttribute("message", "成功上传文件");
				req.getRequestDispatcher("/message.jsp").forward(req, resp);
			}
		}
		catch(Exception e)
		{
			req.setAttribute("message", "操作异常");
			req.getRequestDispatcher("/message.jsp").forward(req, resp);
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}
}
		
		
		
		