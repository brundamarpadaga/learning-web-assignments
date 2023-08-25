package com.learning;

import java.io.IOException;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import com.learning.exception.UnsupportedActionException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Game2584Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 956140707118987401L;
	
	private GameController GC;
	private JakartaServletWebApplication application;
	 private TemplateEngine templateEngine;
	 
	 @Override
	 public void init(ServletConfig config) throws ServletException {
		 super.init(config);
		 //controller = game.get();
		 GameController GC = new GameController();
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
	 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	 {
		 final IWebExchange webExchange = 
			        this.application.buildExchange(req, resp);
		  final WebContext ctx = new WebContext(webExchange);
		 String buttonID = req.getParameter("btnID");
		 
		 if(buttonID != null) {
			 if(buttonID.equals("up")) { 
				 GC.moveUp(); 
			 }
			 if(buttonID.equals("down")) {
				 GC.moveDown();
			 }
				/*
				 * if(buttonID.equals("left")) GC.moveLeft();
				 * 
				 * if(buttonID.equals("right")) GC.moveRight();
				 */
		 }
		 
		 if(!GC.isGameOver()) {
			 ctx.setVariable("result",GC.getScore());

		 }
		 
		 var out = resp.getWriter();
		 
		 templateEngine.process("game2584", ctx, out);
		 
		 
	 }
	 
	 
	 @Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 final IWebExchange webExchange = this.application.buildExchange(req, resp);
		 final WebContext ctx = new WebContext(webExchange);
		 //ctx.setVariable("reading", GC.getReading());
		 templateEngine.process("game2584", ctx, resp.getWriter());
	  }

}
