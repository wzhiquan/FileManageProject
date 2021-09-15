package com.test.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import stringutil.StringUtils;

public class QueryFileListServlet extends HttpServlet
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
			Map<String,String> map = new HashMap<String,String>();
			duigui(file, map);
			
			req.setAttribute("map", map);
			req.getRequestDispatcher("/list.jsp").forward(req, resp);
		}
		catch(Exception e)
		{
			req.setAttribute("message", "操作异常");
			req.getRequestDispatcher("/message.jsp").forward(req, resp);
		}
	}
	
	private void duigui(File file, Map<String,String> map)
	{
		if(file.isDirectory())
		{
			File[] listFile = file.listFiles();
			for(File temp:  listFile)
			{
				duigui(temp, map);
			}
		}
		else
		{
			String name = file.getName();
			name = name.substring(name.lastIndexOf("//") + 1, name.length());
			map.put(file.getPath(), name);
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}
}
		
		
		
		