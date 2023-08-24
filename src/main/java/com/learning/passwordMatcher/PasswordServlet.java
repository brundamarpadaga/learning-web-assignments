package com.learning.passwordMatcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/PasswordServlet")
public class PasswordServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		if(password == null) {
			response.getWriter().write("Enter a password!");
			return;
		}
		
		if(isPasswordBanned(password)) {
			response.getWriter().write("Password is not allowed because it's already taken");
			
		}else {
			response.getWriter().write("Password succesfully set!");
		}
	}
	
	private boolean isPasswordBanned(String Password)throws IOException {
		Set<String> bannedPasswords = new HashSet<>();
		
		try(BufferedReader reader = new BufferedReader(new FileReader("/home/brunda/eclipse-workspace/learning-web/src/main/java/com/learning/passwordMatcher/bannedPasswords.txt"))) {
			String line;
			while((line = reader.readLine())!= null){
				bannedPasswords.add(line.trim());	
			}
		}
		return bannedPasswords.contains(Password);
		
		
				
		
	}

}

