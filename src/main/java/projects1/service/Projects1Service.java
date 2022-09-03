package projects1.service;

import java.math.BigDecimal;
import java.util.Objects;

import projects1.Projects1;
import projects1.dao.DbConnection;
import projects1.dao.ProjectDao;
import projects1.exception.DbException;

public class Projects1Service extends Projects1 {
	//I called the db connection so I wouldn't have to change the project setup I am too new..
	private ProjectDao projectDao = new ProjectDao();
	public ProjectDao addProject(ProjectDao project) {
		
		return projectDao.insertProject(project);
		
	}
private void createProject() {
	String projectName = getStringInput("Enter the project name");
	BigDecimal estimatedHours = getDecimalInput("enter the estimated hours");
	BigDecimal actualHours = getDecimalInput("Enter the actual hours");
	Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
	String notes = getStringInput("Enter the project notes");
	
	Projects1 project = new Projects1();
	
	project.setProjectName(projectName);
	project.setEstimatedHours(estimatedHours);
	project.setActualHours(actualHours);
	project.setDifficulty(difficulty);
	project.setNotes(notes);
	
	Projects1 dbProject = Projects1Service.addProject(project);
	System.out.println("You have successfully created project:" +  dbProject);
	
}

private static Projects1 addProject(Projects1 project) {
	// TODO Auto-generated method stub
	return null;
}

private BigDecimal getDecimalInput(String string) {
	// TODO Auto-generated method stub
	String input = getStringInput(string);
	if(Objects.isNull(input))	{
		return null;
	}
	try {
	return new BigDecimal(string).setScale(2);
	}
	catch(NumberFormatException e)	{
		throw new DbException(input + "is not a valid decimal number");
	}
}

}
