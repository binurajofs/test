package com;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.google.gson.Gson;

public class FetchTrialList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("In doGet.................");
		StudyInfoBO businessObj = new StudyInfoBO();
        List<Student> triallist = businessObj.fetchTrialsList();
        
        System.out.println("trial list: " + triallist);
        Gson gson = new Gson();
        String json = gson.toJson(triallist);
        System.out.println("json:" + json);
        response.setContentType("application/json");
        response.getWriter().write(json);
	}

}
