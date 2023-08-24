package com.learning.mangatha;

import java.io.IOException;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import com.learning.Mangatha.Card;
import com.learning.Mangatha.Players;
import com.learning.controller.mangathaGame;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import org.thymeleaf.web.IWebExchange;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/manga")
public class MangathaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	  private Players p1;
	  private Players p2;
	  private mangathaGame MangathaGame;
	  
	  private JakartaServletWebApplication application;
	  private TemplateEngine templateEngine;
	  
	  boolean gameState = false;
	  int gameRound = 1;
	  
	  @Override
	  public void init(ServletConfig config) throws ServletException {
	    super.init(config);
	    
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
		  final IWebExchange webExchange = 
			        this.application.buildExchange(req, resp);
		  final WebContext ctx = new WebContext(webExchange);
		  String name1="";
		  String name2="";
		  String btnId = req.getParameter("btn");
		  System.out.println(btnId);
		  if(btnId != null) {
			  if(btnId.equals("p1")) {
			  
				  name1 = req.getParameter("Player1Name");
				  String bet1 = req.getParameter("Player1BetAmount");
				  System.out.println(req.getParameter("Player1CardPosition"));
				  Card cardType1 = new Card(req.getParameter("Player1Card"));
				  int cardPosition1 = req.getParameter("Player1CardPosition").equals("in") ? 0 : 1;
				  p1 = new Players(name1, Integer.parseInt(bet1), cardType1, cardPosition1);
				  System.out.println("name -"+p1.getName());
				  System.out.println("bet -"+p1.getBet());
				  System.out.println("type -"+p1.getCardPosition());
				  System.out.println("card -"+p1.getCardType());

			  }
			  if(btnId.equals("p2")) {
				  
				  name2 = req.getParameter("Player2Name");
				  String bet2 = req.getParameter("Player2BetAmount");
				  Card cardType2 = new Card(req.getParameter("Player2Card"));
				  int cardPosition2 = req.getParameter("Player2CardPosition").equals("in") ? 0 : 1;
				  p2 = new Players(name2, Integer.parseInt(bet2), cardType2, cardPosition2);
				  System.out.println("name -"+p2.getName());
				  System.out.println("bet -"+p2.getBet());
				  System.out.println("type -"+p2.getCardPosition());
				  System.out.println("card -"+p2.getCardType());
				  
			  }
			  
			  MangathaGame = new mangathaGame(p1, p2);
		  }
		  String dealer = req.getParameter("Dealer");
		  if(dealer != null) {
			  gameState = MangathaGame.gameOver();
			  ctx.setVariable("reading", MangathaGame.getTopCard());
			  ctx.setVariable("round", gameRound);
			  //p1 details
			  ctx.setVariable("p1Name", p1.getName());
			  ctx.setVariable("p1BetAmmount", p1.getBet());
			  ctx.setVariable("p1CardType", p1.getCardType());
			  ctx.setVariable("p1CardPosition", p1.getCardPosition());
			  
			  //p2 details
			  ctx.setVariable("p2Name", p2.getName());
			  ctx.setVariable("p2BetAmmount", p2.getBet());
			  ctx.setVariable("p2CardType", p2.getCardType());
			  ctx.setVariable("p2CardPosition", p2.getCardPosition());
			  
			  gameRound +=1;
		  }
		  if(gameState) {
			  if(p1.getWin()) {
				  ctx.setVariable("winner", p1.getName());
			  }
			  if(p2.getWin()) {
					  ctx.setVariable("winner", p2.getName());
			  }
		  }
		var out = resp.getWriter();
			    
		templateEngine.process("mangatha", ctx, out);

		 // doGet(req, resp);
		  }
		  




	  
	  @Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    final IWebExchange webExchange = this.application.buildExchange(req, resp);
	    final WebContext ctx = new WebContext(webExchange);
	    //ctx.setVariable("reading", oc.getReading());
	    templateEngine.process("mangatha", ctx, resp.getWriter());
	    
	   
	    //System.out.println(oc.getReading());
	  }

}