package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudyInfoDAO {
	
	private final static String selectStudy = "SELECT * FROM study_cluster_association WHERE inform_studyid=?";
    private final static String updateClusterName = "UPDATE study_cluster_association set cluster_name=? where inform_studyid=?";
    private final static String insertClusterDetails = "INSERT INTO study_cluster_association (inform_studyid,cluster_name) VALUES (?,?)";
    private final static String FetchTrialList = "SELECT * from study_cluster_association";
    
    public List<Student> fetchTrialsList()
    {
    	List<Student> triallist = new ArrayList<Student>();
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");  
    		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","Liaison!23");
    		PreparedStatement selectStatement = con.prepareStatement(FetchTrialList);
            ResultSet rs = selectStatement.executeQuery();
            while(rs.next())
            {
            	Student a = new Student();
            	a.setStudyid(rs.getString(1));
            	a.setClustername(rs.getString(2));
            	triallist.add(a);
            }
            rs.close();
            selectStatement.close();
    	}catch(Exception e){
    		System.out.println(e);
    	}
		return triallist;
    	
    }
    
    public int updateClusterName(String studyid, String clustername)
    {
    	String[] studylist = studyid.split(",");
    	int count = 0;
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");  
    		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","Liaison!23");
    		PreparedStatement ps = null;
    	    
    		for(int i=0; i<studylist.length; i++) {
    			PreparedStatement selectStatement = con.prepareStatement(selectStudy);
    			selectStatement.setString(1, studylist[i]);
    			ResultSet rs = selectStatement.executeQuery();
    			if(rs.next())
    			{
    				ps = con.prepareStatement(updateClusterName);
    				ps.setString(1,clustername);
    				ps.setString(2, studylist[i]);
    				count = ps.executeUpdate();
    				ps.close();
    			}
    			else
    			{
    				ps = con.prepareStatement(insertClusterDetails);
    				ps.setString(1,studylist[i]);
    				ps.setString(2,clustername);
    				count = ps.executeUpdate();
    				ps.close();
    			}
    			rs.close();
    			selectStatement.close();
    		}
    	} catch (Exception e) {
    		System.out.println("Error in Trial Movement: "+ e);
		}
		return count;
    }
	
}
