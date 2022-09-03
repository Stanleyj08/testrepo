package projects1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import projects1.Projects1;
import projects1.exception.DbException;

public class ProjectDao extends DaoBase {
	private static final String CATEGORY_TABLE = "category";
	private static final String MATERIAL_TABLE = "category";
	private static final String PROJECT_TABLE = "category";
	private static final String PROJECT_CATEGORY_TABLE = "category";
	private static final String STEP_TABLE = "category";
	
	public Projects1 inserProject(Projects1 project) throws SQLException	{
		String sql = ""
				+ "INSERT INTO" + PROJECT_TABLE + ""
				+ "(project_name, estimated_hours, actual_hours, difficulty, notes)"
				+ "VALUES "
				+ "(?, ?, ?, ?, ?)";
		try(Connection conn = DbConnection.getConnection()){
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql))	{
				setParameter(stmt, 1, project.getProjectname(), String.class);
				setParameter(stmt, 2, project.getEstimatedHours(), String.class);
				setParameter(stmt, 3, project.getActualHours(), String.class);
				setParameter(stmt, 4, project.getDifficulty(), String.class);
				setParameter(stmt, 5, project.getNotes(), String.class);
				
				stmt.executeUpdate();
				
				Integer projectId = getLastInsertId(conn, PROJECT_TABLE);
				commitTransaction(conn);
				
				project.setProjectId(projectId);
					return project;
			} catch(Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		}
	}

	public ProjectDao insertProject(ProjectDao project) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
