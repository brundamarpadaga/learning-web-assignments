package com.learning.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FibonacciServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 try {
			 if(req.getParameter("limit")== null) {
				 PrintWriter out = resp.getWriter();
				 out.println("this is not the way to access this resource.");
				 return;
			 }
			 else {
				 String limit = req.getParameter("limit");
				 PrintWriter out = resp.getWriter();
				 List<Integer> lst = fibonacci(Integer.parseInt(limit));
				 for(int i = 0 ; i < lst.size() ; i++ ) {
					 out.println(String.format("<p> %d <p>", lst.get(i)));
				 
				 }
			 
			 }
		 }catch(IOException e ){
			 e.printStackTrace();
		 }
		 
	}	
	
	public List<Integer> fibonacci(int n) {
		List<Integer> fib = new ArrayList<>();
		fib.add(0);
		fib.add(1);
		for(int i=2; i<n ; i++) {
			fib.add(fib.get(i-1)+fib.get(i-2));
		}
		return fib;
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			 if(request.getParameter("formLimit")== null) {
				 PrintWriter out = response.getWriter();
				 out.println("this is not the way to access this resource. P");
				 return;
			 }
			 else {
				 String limit = request.getParameter("formLimit");
				 PrintWriter out = response.getWriter();
				 List<Integer> lst = fibonacci(Integer.parseInt(limit));
				 for(int i = 0 ; i < lst.size() ; i++ ) {
					 out.println(String.format("<p> %d <p>", lst.get(i)));
				 
				 }
			 
			 }
		 }catch(IOException e ){
			 e.printStackTrace();
		 }
	}
}
