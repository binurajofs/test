package com;

import java.util.List;

public class StudyInfoBO {

	public int updateClusterName(String studyid, String clustername) 
    {
		StudyInfoDAO studyInfoDAO = new StudyInfoDAO();
        return studyInfoDAO.updateClusterName(studyid, clustername);
    }
	
	public List<Student> fetchTrialsList() 
    {
		StudyInfoDAO studyInfoDAO = new StudyInfoDAO();
        return studyInfoDAO.fetchTrialsList();
    }
	
}
