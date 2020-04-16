package dataAccessLayer;
import java.sql.*;


/**
 * @author Leonties Dan Alexandru
 *	This class creates the connection to the database
 *
 */


public class AccessDB {

	private static String url = "jdbc:mysql://localhost:3306/schooldb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String user = "root";
	private static String pass = "root";
	
	private String sel;
	
	private Connection conn;
	private Statement stmt;
	private ResultSet res;
	
	
	/**
	 *
	 * 	This is a simple constructor
	 * 
	 */
	
	public AccessDB()
	{
		
	}
	
	/**
	 *	Creates the "Select * from table" command 
	 *	@param sel SQL Query which will contain the phrase "Select * from ..."
	 */
	
	public void setSel(String sel)
	{	
		this.sel = sel;
	}
	
	/**
	 * 	Creates the connection to the DataBase with the url, username and password
	 * 	@throws SQLException If connection to the DB is not possible
	 */
	
	public Connection connectToDB()
	{
		try {
			Connection newConn = DriverManager.getConnection(url, user, pass);
			return newConn;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 	Updates the connection parameter of the AccessDB object 
	 */
	
	public void updateConn()
	{
		this.conn = connectToDB();
	}
	

	/**
	 * 	 Closes the connection to the DB
	 * 	 @param conn Connection that needs to be closed
	 * 	 @throws SQLException If there is no connection to close
	 */
	
	public void closeConnection(Connection conn)
	{
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	 Creates the statement after connecting to the DB
	 * 	 @param conn Connection that needs to be used to create the Statement
	 * 	 @throws SQLException If the statement cannot be created
	 */
	
	public Statement createStmt(Connection conn)
	{
		try {
			Statement newStmt = conn.createStatement();
			return newStmt;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	/**
	 * 	 Updates the statement parameter of the AccessDB object
	 */
	
	public void updateStmt()
	{
		this.stmt = createStmt(this.conn);
	}
	
	/**
	 * 	 Closes the statement
	 * 	 @param stmt Statement that needs to be closed
	 * 	 @throws SQLException If there is no statement to close
	 */
	
	public void closeStmt(Statement stmt)
	{
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	 Creates the ResultSet after creating the statement
	 * 	 @param stmt Statement that has already been created is used 
	 * 	 @param sel SQL Query used for creating the ResultSet
	 * 	 @throws SQLException 
	 */
	public ResultSet createResSel(Statement stmt, String sel)
	{
		try {
			ResultSet newRes = stmt.executeQuery(sel);
			return newRes;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 	Executes the SQL Query, used to change information in the table : insert, delete, edit
	 * 	@param sql The SQL Query that will be used
	 * 	@throws SQLException In case the SQL Query cannot be executed 
	 */
	public void execUpd(String sql)
	{
		try {
			this.stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	Updates the SQL Query String
	 *	@param sel String used to update the SQL Query 	
	 */
	
	public void updateResSel(String sel)
	{
		this.res = createResSel(this.stmt, sel);
	}
	
	/**
	 * 	Closes the ResultSet
	 * 	@param res The ResultSet that will be closed
	 * 	@throws SQLException In case there is no ResultSet to close
	 */
	
	public void closeRes(ResultSet res)
	{
		try {
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	Getter for the Connection
	 */
	public Connection getConn()
	{
		return this.conn;
	}
	
	/**
	 * 	Getter for the Statement
	 */
	public Statement getStmt()
	{
		return this.stmt;
	}
	
	/**
	 * 	Getter for the ResultSet
	 */
	public ResultSet getRes()
	{
		return this.res;
	}
	
}
