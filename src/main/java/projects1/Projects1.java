package projects1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

import projects1.entity.Projecttable;
import projects1.exception.DbException;
import projects1.service.Projects1Service;

//import projects1.dao.DbConnection;

public class Projects1 extends Projecttable {
	private Projects1 curProject;
		// TODO Auto-generated method stub
		//DbConnection.getConnection();
		//@formatter:off
	
	//Set<Integer> intss = new HashSet<Integer>();
	List<String> operations = List.of(
				 
				 "1) Add a Project",
				 "2)List Projects",
				 "3)Select a project"
				
				);
		 
private static Scanner scanner = new Scanner(System.in);



	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//DbConnection.getConnection();
		
		
		//@formatter:off
	 List<String> operations = List.of(
				 
				 "1) Add a Project"
				
				 
				); 
	//@formatter:on
		new Projects1().processUserSelection();
		//created a instance off the app and calls the menu
		
	}
	
	private  void processUserSelection() {
		// TODO Auto-generated method stub
		boolean done = false;
	while(!done) {
		 //what this is saying is that a while loop will decide when 
		//the menu is displayed while(!done) in English is basically 
		//while (not done) run the loop till its true or is -1 
			try {
				int selection = getUserSelection();
		 switch (selection)	{
			case -1:
				done = exitMenu();
				break;
			case 1:
				 createProject();
				break;
			case 2:
				listProjects();
				break;
			case 3:
				selectProject();
				default:
					System.out.println("/n" + " " + selection + " " + "is not valid. Try again.");
			}
			} catch(Exception e) {
				System.out.println("error: " + e.toString() + " Try again.");
			}
			}//this is in a try catch so it wont print out the stack trace
	}
	

		
	

	private void selectProject() {
		// TODO Auto-generated method stub
		listProjects();
		Integer projectId = getIntInput("enter a project id ");
		 curProject = null;
		 curProject = Projects1Service.fethProjectById(projectId);
	}
	
	
	private void listProjects() {
List<Projects1> projects = Projects1Service.fetchAllProjects();
		
		System.out.println("\nProjects:");
		
		for(Projects1 project : projects)	{
			System.out.println(project);
		}
		//projects.forEach(project -> System.out.println("  " + project.getProjectId()+ ": " + project.getProjectName()));
			//	.println("  " + project.getProjectId()
				//	+ ": " + project.getProjectName()));
		
	}

	private void createProject() {
		// TODO Auto-generated method stub
		String projectName = getStringInput("Enter the project name");
		BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
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
		
		
	}//same as getintinput had to create this method since the first option of the menu uses 
	//big decimal datat type
	private BigDecimal getDecimalInput(String string) {
		// TODO Auto-generated method stub
		String input = getStringInput(string);
		if(Objects.isNull(input))	{
			return null;
		}
		try {
		return new BigDecimal(input).setScale(2);
		}
		catch(NumberFormatException e)	{
			throw new DbException(input + "is not a valid decimal number");
		}
	}
	private boolean exitMenu() {
System.out.println(   "exiting the menu." );
		return true;
	}//this exits the menu since it is returning true and the while loop will execute till true 

	private  int getUserSelection() {
		// TODO Auto-generated method stub
		printOperations();
		Integer op = getIntInput("Enter an operation number (Press enter to quit)");
		return Objects.isNull(op) ? -1 : op;//if the operation put in is null exit menu will operate because it is set to -1 in out switch
		// ternary statement- used to assign or return a value, conditional
		//kinda like an if this than else if this
		//check if object is null if so return -1 or return the variable op
	}
	public  void printOperations() {
		System.out.println();
		System.out.println("Here is what you can do:");
		//this is a lambda expression
		operations.forEach(op -> System.out.println(op ));//basically an enhanced for loop for the list at the top(operations)
	if(Objects.isNull(curProject))	{
		System.out.println("\n you are not working with a project.");
	} else {
		System.out.println("\nyou are working with Project:" + curProject);
	}
		
		//for each element of line print out that element// it will print out each instance in that list(operations)
	}
	protected  Integer getIntInput(String prompt) {
		String op = getStringInput(prompt);//since you have called this method on the string(prompt) you can now use
											// grab user input in order to create a method to convert strings to integer(objects) if possible
		if(Objects.isNull(op))	{			//if it cannot be parsed than it will catch the exception and throw a new one
			return null;					//TAKING A CHECKED exception and making it unchecked
		}
		try {// if the string cannot be parsed as an integer catch numberformatexception
			return Integer.valueOf(op);
		}
	catch(NumberFormatException e) {
		throw new DbException(op + " is not a valid decimal number.");
	}
	}
	protected  String getStringInput(String prompt)	{//using protected allows inhanced access to the method
														//that is why I am able to call it in the service class
		System.out.println(prompt + ": ");
		String line = scanner.nextLine();
		return line.isBlank() ?null : line.trim();
		//what this does is returns null if the line is blank
		// otherwise if it is a string with a bunch of spaces 
		//it will return that string with the spaces trimmed off
	}
	public Scanner getScanner() {
		return scanner;
	}
	public void setScanner(Scanner scanner) {
		Projects1.scanner = scanner;
	}

	public int getProjectId(Integer projectId) {
		return projectId;
		
	}
	@SuppressWarnings("unchecked")
	public void setProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		this.projectId = projectId;
	}
 
	public Object getProjectname() {
		// TODO Auto-generated method stub
		return projectName;
	}

	public BigDecimal getEstimatedHours() {
		// TODO Auto-generated method stub
		return estimatedHours;
	}

	public BigDecimal getActualHours() {
		// TODO Auto-generated method stub
		return actualHours;
	}

	public Integer getDifficulty() {
		// TODO Auto-generated method stub
		return difficulty;
	}


	@SuppressWarnings("unchecked")
	public void setProjectName(String projectName) {
		this.projectName = projectName;
		
	}

	@SuppressWarnings("unchecked")
	public void setEstimatedHours(BigDecimal estimatedHours) {
		// TODO Auto-generated method stub
		  
			    this.estimatedHours = estimatedHours;
	}

	@SuppressWarnings("unchecked")
	public void setActualHours(BigDecimal actualHours) {
		this.actualHours = actualHours;
		
	}

	@SuppressWarnings("unchecked")
	public void setDifficulty(Integer difficulty) {
		// TODO Auto-generated method stub
		this.difficulty = difficulty;
	}


	
}
