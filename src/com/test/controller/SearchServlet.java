package com.test.controller;

import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import stringutil.StringUtils;

public class SearchServlet extends HttpServlet
{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1978317209774049279L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException 
	{
		try
		{
			Vector<CTagSet> result = new Vector<CTagSet>();
			
			CTagSet tag = new CTagSet("3333","胜多负少的");
			CTagSet tag1 = new CTagSet("4444","是的水电费");
			result.add(tag);
			result.add(tag1);
			
			Vector<CTagSet> dicttag = new Vector<CTagSet>();
			CTagSet dict = new CTagSet("groupId","集团编码");
			CTagSet dict1 = new CTagSet("groupName","集团名称");
			dicttag.add(dict);
			dicttag.add(dict1);
			req.setAttribute("dicttag", dicttag);
			req.setAttribute("result", result);
			
			req.getRequestDispatcher("/NewFile.jsp").forward(req, resp);
		}
		catch(Exception e)
		{
			req.setAttribute("message", "操作异常");
		}
	}
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException
	{
		doPost(req, resp);
	}
}
		
		
		
		