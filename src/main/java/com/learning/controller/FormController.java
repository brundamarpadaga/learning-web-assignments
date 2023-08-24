package com.learning.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;


import com.learning.exception.UnsupportedActionException;

import jakarta.servlet.http.HttpServletResponse;

public class FormController implements IController {
	private DataSource dataSource;

    public FormController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void processGet(IWebExchange exchange, TemplateEngine templateEngine) {
        // No GET processing required for this example
    }

    @Override
    public void processPost(IWebExchange exchange, TemplateEngine templateEngine) {
    	WebContext context = new WebContextBuilder(exchange.getRequest(), exchange.getResponse(), exchange.getServletContext(), templateEngine.getContext().getLocale()).build();

        String contactId = exchange.getRequest().getParameter("contactId");
        String contactName = exchange.getRequest().getParameter("contactName");
        String contactPhone = exchange.getRequest().getParameter("contactPhone");

        String noticeId = exchange.getRequest().getParameter("noticeId");
        String noticeTitle = exchange.getRequest().getParameter("noticeTitle");
        String noticeContent = exchange.getRequest().getParameter("noticeContent");
        String noticeContactId = exchange.getRequest().getParameter("noticeContactId");

        try (Connection connection = dataSource.getConnection()) {
            String insertContactQuery = "INSERT INTO contacts (id, name, phone) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertContactQuery)) {
                preparedStatement.setInt(1, Integer.parseInt(contactId));
                preparedStatement.setString(2, contactName);
                preparedStatement.setString(3, contactPhone);
                preparedStatement.executeUpdate();
            }

            String insertNoticeQuery = "INSERT INTO notices (id, title, content, contact_id) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertNoticeQuery)) {
                preparedStatement.setInt(1, Integer.parseInt(noticeId));
                preparedStatement.setString(2, noticeTitle);
                preparedStatement.setString(3, noticeContent);
                preparedStatement.setInt(4, Integer.parseInt(noticeContactId));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            // Handle the exception as needed
        }

        // Redirect back to the form or a success page
        exchange.getResponse().sendRedirect("/path-to-your-form-page");
    }   
}
