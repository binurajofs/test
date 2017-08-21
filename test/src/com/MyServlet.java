package com;
 
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONObject;
 
public class MyServlet extends HttpServlet {
 
    private static final long serialVersionUID = 1L;
    private Logger log = Logger.getLogger(this.getClass().getName());
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	try {
    		System.out.println("In doPost...............");
    		
    		StringBuilder sb = new StringBuilder();
    		BufferedReader br = request.getReader();
    		String str = null;
    		
    		while ((str = br.readLine()) != null) {
            sb.append(str);
            System.out.println("string: " + str);
    		}
    		
    		JSONObject jObj = new JSONObject(sb.toString());
			String studyid = jObj.getString("studyid");
			String clustername = jObj.getString("clustername");
			System.out.println("studyid: " + studyid);
			System.out.println("clustername: " + clustername);
			
			StudyInfoBO businessObj = new StudyInfoBO();
	        int success = businessObj.updateClusterName(studyid, clustername);
			
	        String message = null;
	        if(success>0)
	        {
	        	message = "Successfully moved "+ studyid + " to " + clustername;
	        	log.debug(message);
	        	response.setContentType("text/plain");
		        response.setCharacterEncoding("UTF-8");
		        response.getWriter().write("Successfully moved trial " + studyid + " to " + clustername);
	        }
	        else
	        {
	        	message = "Unable to move trial "+ studyid + " to " + clustername;
	            log.debug(message);
	            response.setContentType("text/plain");
		        response.setCharacterEncoding("UTF-8");
		        response.getWriter().write("Error in moving trial " + studyid + " to " + clustername);
	        }
	        
		} catch (Exception e) {
				System.out.println(e);
				response.setContentType("text/plain");
		        response.setCharacterEncoding("UTF-8");
		        response.getWriter().write("Exception: " + e);				
		}
 
    }
 
}