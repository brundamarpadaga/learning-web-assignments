package com.learning.controller;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.web.IWebExchange;
public interface IController {
	
	 void processGet(IWebExchange exchange, TemplateEngine templateEngine);
	 void processPost(IWebExchange exchange, TemplateEngine templateEngine);

}
