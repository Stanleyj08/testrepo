package projects1.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;

import projects1.Projects1;
import projects1.entity.Categorytable.Category;
import projects1.entity.Materialtable.Material;
import projects1.entity.Steptable.Step;
import projects1.exception.DbException;

public class ProjectDao extends DaoBase {
	private static final String CATEGORY_TABLE = "category";
	private static final String MATERIAL_TABLE = "material";
	private static final String PROJECT_TABLE = "project";
	private static final String PROJECT_CATEGORY_TABLE = "project_category";
	private static final String STEP_TABLE = "step";
	
	public Projects1 insertProject(Projects1 project) 	{
		
		//formatter:off
		String sql = ""
				+ "INSERT INTO " + PROJECT_TABLE + " "
				+ "(project_name, estimated_hours, actual_hours, difficulty, notes) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?)";
		//formatter:on
		try(Connection conn = DbConnection.getConnection()){
			startTransaction(conn);
			//sql statments below
			//we are making a prepared statment and passing in the statment called sql above
			try(PreparedStatement stmt = conn.prepareStatement(sql))	{
				setParameter(stmt, 1, project.getProjectname(), String.class);
				setParameter(stmt, 2, project.getEstimatedHours(), BigDecimal.class);
				setParameter(stmt, 3, project.getActualHours(), BigDecimal.class);
				setParameter(stmt, 4, project.getDifficulty(), Integer.class);
				setParameter(stmt, 5, project.getNotes(), String.class);
				//execute update performs the insert
				stmt.executeUpdate();
				//counts when a row is added and adds the int of the row to the entity object
				Integer projectId = getLastInsertId(conn, PROJECT_TABLE);
				//to keep track of the id call the lastinsert method and pass in the connection
				commitTransaction(conn);
				//commit transactions writes all changes to the data base
				project.setProjectId(projectId);
					return project;
			} catch(Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		}
		catch(SQLException e)	{
			throw new DbException(e);
		}
	}
//this is not being used becuase insert project was created above
	//public ProjectDao insertProject(ProjectDao project) {
		// TODO Auto-generated method stub
		//return project;
	//}
public  void executeBatch(List<String> sqlBatch) {
	try(Connection conn = DbConnection.getConnection())	{
		startTransaction(conn);
		//try(Statement stmt = conn.createStatement())	{
			//for(String sql : sqlBatch);
			//String sql = null;//all of this was form something else(executeBatch)
			//stmt.addBatch(sql);
		//}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		throw new DbException(e);
	}
	}


public static List<Projects1> fetchAllProjects() {
	// TODO Auto-generated method stub
	String sql = "SELECT * FROM " + PROJECT_TABLE + " ORDER BY project_name";
	
	try(Connection conn = DbConnection.getConnection()) {
		startTransaction(conn);
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			try(ResultSet rs = stmt.executeQuery()) {
				List<Projects1> projects = new LinkedList<>();
				
				while(rs.next()) {
					projects.add(extract(rs, Projects1.class));
				}
				return projects;
			}
		}
		catch(Exception e) {
			rollbackTransaction(conn);
			throw new DbException(e);
		}
	}
	catch(SQLException e) {
		throw new DbException(e);
	}
}
	public ProjectDao insertProject(ProjectDao project) {
		// TODO Auto-generated method stub
		return project;
	}
	
	public java.util.Optional<Projects1> fetchProjectById(Integer projectId) {
		String sql = "SELECT * FROM " + PROJECT_TABLE + " WHERE project_id = ?";
		
		try(Connection conn = DbConnection.getConnection())	{
			startTransaction(conn);
			
			try {
				Projects1 project = null;
				 try(PreparedStatement stmt = conn.prepareStatement(sql)){
					 setParameter(stmt, 1, projectId, Integer.class);
					 
					 try(ResultSet rs = stmt.executeQuery())	{
						//If the ResultSet has a row in it (rs.next()) set the Project variable to a new Project object and 
						 //set all fields from values in the ResultSet.  call the extract() method for this.
						 if(rs.next()) {
							 project = extract(rs, Projects1.class);
							 
						 }
					 }
				 }
				 
				 
				 if(Objects.nonNull(project)) {
					 project.getMaterials().addAll(fetchMaterialForProject(conn, projectId));
					 project.getSteps().addAll(fetchStepsForProject(conn, projectId));
					 project.getCategories().addAll(fetchCategoriesForProject(conn, projectId));
				 }
				 commitTransaction(conn);
				 return java.util.Optional.ofNullable(project);
			}
			catch(Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		}
		catch(SQLException e)	{
			throw new DbException(e);
			 
		
	

	
		}
		}
	private List<Material> fetchMaterialForProject(Connection conn, Integer projectId) throws SQLException {
		String sql= "" + "SELECT c.* FROM" + MATERIAL_TABLE + "JOIN" + PROJECT_TABLE + "pc USING (category_id) " + "WHERE project_id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql))	{
			setParameter(stmt, 1, projectId, Integer.class);
			try(ResultSet rs = stmt.executeQuery()){
				List<Material> materials = new LinkedList<>();
				
				while(rs.next())	{
					materials.add(extract(rs, Material.class));
				}
				return materials;
			}
			
		}
		
	}
	private List<Step> fetchStepsForProject(Connection conn, Integer projectId) throws SQLException {
		String sql= "" + "SELECT c.* FROM" + STEP_TABLE + "JOIN" + PROJECT_TABLE + "pc USING (category_id) " + "WHERE project_id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql))	{
			setParameter(stmt, 1, projectId, Integer.class);
			try(ResultSet rs = stmt.executeQuery()){
				List<Step> step = new LinkedList<>();
				
				while(rs.next())	{
					step.add(extract(rs, Step.class));
				}
				return step;
		
			}
		}
	}
	private List<Category>fetchCategoriesForProject(Connection conn, Integer projectId) throws SQLException {
		String sql= "" + "SELECT c.* FROM" + CATEGORY_TABLE + "JOIN" + PROJECT_CATEGORY_TABLE + "pc USING (category_id) " + "WHERE project_id = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql))	{
			setParameter(stmt, 1, projectId, Integer.class);
			try(ResultSet rs = stmt.executeQuery()){
				List<Category> categories = new LinkedList<>();
				
				while(rs.next())	{
					categories.add(extract(rs, Category.class));
				}
				return categories;
	}
	
	
}
	}
}
	
	

