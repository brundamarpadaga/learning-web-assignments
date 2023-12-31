package com.learning.notices;

import java.io.IOException;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import org.thymeleaf.web.IWebExchange;

import com.learning.controller.ControllerFactory;
import com.learning.controller.IController;
import com.learning.controller.NoticesController;
import com.learning.controller.OdometerController;
import com.learning.exception.UnsupportedActionException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/notice")
public class NoticesServlet extends HttpServlet{
	
	private static final long serialVersionUID = 956140707118987401L;
	 private NoticesController controller;
	 private JakartaServletWebApplication application;
	 private TemplateEngine templateEngine;
	 
	 @Override
	 public void init(ServletConfig config) throws ServletException {
		 super.init(config);
		 controller = new  NoticesController();
		 application = JakartaServletWebApplication.buildApplication(getServletContext());
		 application = JakartaServletWebApplication.buildApplication(getServletContext());
		 final WebApplicationTemplateResolver templateResolver = 
		        new WebApplicationTemplateResolver(application);
		 templateResolver.setTemplateMode(TemplateMode.HTML);
		 templateResolver.setPrefix("/WEB-INF/templates/");
		 templateResolver.setSuffix(".html");
		 templateEngine = new TemplateEngine();
		 templateEngine.setTemplateResolver(templateResolver);
	 }
	 
	 @Override
	 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		 String btnId = req.getParameter("btn");
		 int id =0 ;
		 String name="";
		 String phone ="";
		 int noticeId = 0;
		 String title = "";
		 String content = "";
		 int contactId = 0;
		 
		  
		 if(btnId != null) 
		 {
			  if(btnId.equals("addEntry")) {
				  id = Integer.valueOf(req.getParameter("contactId"));
				  name = req.getParameter("contactName");
				  phone = req.getParameter("contactPhone");
				  
			 
				  noticeId = Integer.valueOf(req.getParameter("noticeId"));
				  title = req.getParameter("noticeTitle");
				  content = req.getParameter("noticeContent");
				  contactId = Integer.valueOf(req.getParameter("contactId"));  
			  }	
			  
			  controller.addEntry(id, name, phone, noticeId, title, content,contactId);
		  }
		 
		 
		 doGet(req,resp);
		  }
	  
	 @Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 final IWebExchange webExchange = this.application.buildExchange(req, resp);
		 final WebContext ctx = new WebContext(webExchange);
		 
		 templateEngine.process("Notices", ctx, resp.getWriter());
	  }
}
