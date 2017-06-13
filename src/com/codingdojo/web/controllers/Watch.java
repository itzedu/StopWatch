package com.codingdojo.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.codingdojo.web.models.Timer;

public class Watch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Watch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String status = request.getParameter("status");
		
		if(session.getAttribute("time") == null) {
			session.setAttribute("time", new Timer());
		}
		
		if(session.getAttribute("allTimes") == null) {
			List<Timer> allTimes = new ArrayList<Timer>();
			session.setAttribute("allTimes", allTimes);
		}
		
		if(status != null) {
			Timer t = (Timer) session.getAttribute("time");
			
			if(status.equals("start")){
				t.setStart();
			} else if(status.equals("stop")) {
				// getting the List from session
				List<?> currentTimes = (List<?>) session.getAttribute("allTimes");
				
				// empty temp list
				List<Timer> tempTimes = new ArrayList<Timer>();
				
				for(Object object : currentTimes) {
					if(object instanceof Timer) {
						// adding each one to the empty temp list
						tempTimes.add((Timer) object);
					}
				}
				t.setStop();
				tempTimes.add(t);
				session.setAttribute("allTimes", tempTimes);
				session.setAttribute("time", new Timer());
			} else {
				session.setAttribute("time", new Timer());
				List<Timer> allTimes = new ArrayList<Timer>();
				session.setAttribute("allTimes", allTimes);
			}
		}
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
		view.forward(request, response);
	}


}
