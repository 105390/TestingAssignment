package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Marius
 *
 */
public class DatabaseHandler {
	/**
	 * Instance Variables
	 */
	private final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final String DB_URL = "jdbc:oracle:thin:@localhost:1521:flightDB";
	private final String USER = "prog";
	private final String PASS = "password";
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet result = null;
	private String sql;
	
	/**
	 * Establish DB connection
	 */
	public void connectToDB(){
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	/**
	 * Close DB connection
	 */
	public void closeDB(){
		try{
			if(conn!=null)
				conn.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	/**
	 * Insert new employee to database
	 * @param employeeIn
	 * @return true/false - depending if its inserted or not
	 */
	public boolean insertEmployee(Employee employeeIn){
		try{
			connectToDB();
			sql = "INSERT INTO employee(employeeID, first_name, last_name, salary) VALUES(?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, employeeIn.getEmployeeNo());
			stmt.setString(2, employeeIn.getFirstName());
			stmt.setString(3, employeeIn.getLastName());
			stmt.setBigDecimal(4, employeeIn.getSalary());
			stmt.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}	
		finally{
			closeDB();
		}
		return true;
	}
	/**
	 * @param employeeID
	 * @return an employee from database
	 */
	public Employee getEmployee(int employeeIDIn){
		Employee e1 = null;
		try{
			//connect to DB and execute SQL statement
			connectToDB();
			sql = "SELECT * FROM employee WHERE employeeID = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, employeeIDIn);
			result = stmt.executeQuery();
			while(result.next()){
				e1 = new Employee();
				e1.setEmployeeNo(result.getInt("EmployeeID"));
				e1.setFirstName(result.getString("first_name"));
				e1.setLastName(result.getString("last_name"));
				e1.setSalary(result.getBigDecimal("salary"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			closeDB();
		}
		return e1;
	}
	/**
	 *  Update Employee
	 * @param employeeIn
	 * @return true/false - depending if its updated or not
	 */
	public boolean updateEmployee(Employee employeeIn){
		try{
			connectToDB();
			sql = "UPDATE employee SET employeeID = ? , first_name = ? , last_name = ? , salary = ? WHERE employeeID = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, employeeIn.getEmployeeNo());
			stmt.setString(2, employeeIn.getFirstName());
			stmt.setString(3, employeeIn.getLastName());
			stmt.setBigDecimal(4, employeeIn.getSalary());
			stmt.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}	
		finally{
			closeDB();
		}
		return true;
	}
}