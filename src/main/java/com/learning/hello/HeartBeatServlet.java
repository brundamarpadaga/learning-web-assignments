package com.learning.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;

public class HeartBeatServlet extends GenericServlet{

	  @Override
	  public void init() {
	    System.out.println("This line is printed when a servlet inits");
	  }
	  
	  
	  @Override
	  public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
	    System.out.println("I am alive!");
	    
	    //if arg0.isHttpGet() return doGet(arg0, arg1);
	  }
	  
	  @Override
	  public void destroy() {
	    System.out.println("This is called when the servlet is destroyed");
	  }

	}