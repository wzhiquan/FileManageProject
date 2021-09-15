package com.test.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownLoadFileServlet extends HttpServlet
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
			
			String filePath = req.getParameter("filePath");
			File file = new File(filePath);
			if(!file.exists())
			{
				req.setAttribute("message", "操作异常");
				req.getRequestDispatcher("/message.jsp").forward(req, resp);
			}
			String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length());
			// 设置响应头
			resp.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			
			FileInputStream fis = new FileInputStream(file);
			OutputStream os = resp.getOutputStream();
			byte[] buffer = new byte[1024];
			int length = 0;
			while((length = fis.read(buffer)) > 0)
			{
				os.write(buffer);
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
		
		
		
		