package projects1.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import projects1.Projects1;
import projects1.dao.ProjectDao;
import projects1.exception.DbException;

public class Projects1Service extends Projects1 {
	//I called the db connection so I wouldn't have to change the project setup I am too new..
	private static ProjectDao projectDao = new ProjectDao();
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

public static Projects1 addProject(Projects1 project) {
	
	return project;
}

private BigDecimal getDecimalInput(String string) {
	// TODO Auto-generated method stub
	String input = getStringInput(string);
	if(Objects.isNull(input))	{
		return null;
	}
	try {
	return new BigDecimal(string).setScale(2);
	}//set scale will take the original value and return something that looks a bit different but is the same number essentially. The new number depends on the scale
	catch(NumberFormatException e)	{
		throw new DbException(input + "is not a valid decimal number");
	}
}
public static List<Projects1> fetchAllProjects() {
	
	return ProjectDao.fetchAllProjects();

}
public static Projects1 fethProjectById(Integer projectId) {
	return projectDao.fetchProjectById(projectId).orElseThrow(	() -> new NoSuchElementException("Project with project Id= " + projectId + " does not exist."));
		
		
	

}

//private  final String SCHEMA_FILE = "projects_schema.sql";//this allows us to pick up the specific file passed in
//private ProjectDao projectDao1 = new ProjectDao();//instance variable so we can access dao from the service layer

//public void createAndPopulateTables()	{
//}//

//private void loadFromFile(String fileName) {
	//String content = readFileContent(fileName);//this will read the file content and convert it to sql statments
	//List<String> sqlStatements = convertContenttoSqlStatments(content);
	//ProjectDao projectDao2 = new ProjectDao();
	//sqlStatements.forEach(line -> System.out.println(line));
	///projectDao2.executeBatch(sqlStatements);
	//
//}

//private List<String> convertContenttoSqlStatments(String content) {
	//content = removeComments(content);
	//content = replaceWhitespaceSequencesWithSingleSpace(content);
	//return extractLinesFromContent(content);
//}

//private List<String> extractLinesFromContent(String content) {
	// TODO Auto-generated method stub
	//List<String> lines = new LinkedList<>();
	//while(!content.isEmpty())	{
		//int semicolon = content.indexOf(";");
		//if(semicolon == -1)	{
		//	if(!content.isBlank())	{
		///		lines.add(content);
		///	}
			
		//	content = "";
			//this will cause us to exit the loop
		//}
		//else	{//.substring takes content starting at index 0 to the first semicolon
			//lines.add(content.substring(0, semicolon).trim());
	//		//content = content.substring( semicolon + 1);
	//	}
//	}
	//return lines;
	

	
//}
//private String replaceWhitespaceSequencesWithSingleSpace(String content) {
	//return content.replaceAll("\\s+", " ");
	// (\s+) means look for while space since its a string use (\\s+), it will replace white space with ""
//}
//private String removeComments(String content) {
	//StringBuilder builder = new StringBuilder(content);
	////int commentPos = 0;//Returns the index within this string of the first occurrence of the specified substring("-- "), starting at the specified index. 
	//while((commentPos = builder.indexOf("-- ", commentPos))!= -1) {//if the substring is not found -1 is returned
		//will look for "-- " if the line finds it return the position of the string if not found print -1
		
		//int eolPos = builder.indexOf("\n", commentPos + 1);
		//("-- ", this is the start of the index  "\n" this will represent the last index of the line
		//everything in this line will be removed including the line feed
		//if(eolPos == -1)	{
			//builder.replace(commentPos, builder.length(),"");
		//}
		//else {
			//builder.replace(commentPos, eolPos + 1, "");
		//}// add the plus one so that the line feed is also included
	//}//this will effectively pull the comment out git rid of it and the line feed
	//return builder.toString();
//}//
//private String readFileContent(String fileName) {
	//Path and Files is a class in the java version java.uitl/nio
	// every object has a get class method that returns the class object aka Projects1Service class
	//that has the getClassLoader method can load resources aswell as class files for us
	//the get method can take a uri 
	//try {
		//Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
		
		//return Files.readString(path);////will find the passed in file and load it up as  a string
	//} catch (Exception e) {//the class loader loads resources as well as class files
		//throw new DbException(e);//all the unfamiliar methods are being called because of the Path Format
		//We made a Path called the . get method on t and made an object of our class and used the 
		//class loader to get access to the resources and files in the class
	//}
//}
//public static void main(String[] args) {
	//new Projects1Service().createAndPopulateTables();
//}
}

