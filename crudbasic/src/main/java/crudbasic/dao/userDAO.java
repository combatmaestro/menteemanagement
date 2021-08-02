package crudbasic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crudbasic.model.User;



//this dao class provides crud database operation for the table users in the database
public class userDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/crudbasic?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "adarsh@98";
	
	private static final String INSERT_USERS_SQL = "INSERT INTO users" + 
	"(name,email,courses,batch) VALUES" + " (?,?,?,?);";
	
	private static final String SELECT_USER_ID = "select id,name,email,courses,batch from users where id =?";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String DELETE_USERS_SQL ="delete from users where id=?";
	private static final String UPDATE_USERS_SQL = "update users set name =?,email=?,courses=?,batch=?, where id=?";
	
	
	protected Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
		    
		}catch(SQLException e) {
			//
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	//create or insert user method 
	
	 public void insertUser(User user) throws SQLException {
		 try(Connection connection = getConnection();
				 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
			          preparedStatement.setString(1, user.getName());
			          preparedStatement.setString(2, user.getEmail());
			          preparedStatement.setString(3, user.getBatch());
			          preparedStatement.setString(4, user.getCourses());
			          preparedStatement.executeUpdate();
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
				 
	 }
	
	
	
	//update user
	 public boolean updateUser(User user) throws SQLException {
		 boolean rowupdated = false;
		 try(Connection connection = getConnection();
				 PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL)){
			 statement.setString(1, user.getName());
			 statement.setString(2, user.getEmail());
			 statement.setString(3, user.getBatch());
			 statement.setString(4, user.getCourses());
			 statement.setInt(5, user.getId());
			          
			          rowupdated = statement.executeUpdate() > 0;
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		return rowupdated;
		
    }
	 
	 
	//select user by id
	 
	 public User selectUser(int id) throws SQLException {
		 User user = null;
		 //step 1 : Establishing a connection
		  try(Connection connection = getConnection();
		 //2. creating a statement using connection object
				  
	        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_ID);){
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			//3.execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			
			//4.process the resultset object
			while(rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String batch = rs.getString("batch");
				String courses = rs.getString("courses");
			    user = new User(id,name,email,batch,courses);
			}
			
		  }catch(SQLException e) {
			  e.printStackTrace();
		  }
		return user;
	 }
	//select users
	 
	 public List<User> selectAllUsers() throws SQLException {
		 List<User> users = new ArrayList<>();
		 //step 1 : Establishing a connection
		  try(Connection connection = getConnection();
		 //2. creating a statement using connection object
				  
	        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);){
            //preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			//3.execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			
			//4.process the resultset object
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String batch = rs.getString("batch");
				String courses = rs.getString("courses");
			    users.add(new User(id,name,email,batch,courses));
			}
			
		  }catch(SQLException e) {
			  e.printStackTrace();
		  }
		return users;
	 }
	//delete users
	
	public boolean deleteUser(int id) throws SQLException{
		boolean rowDeleted;
		
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);){
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate()>0;
		}
		return rowDeleted;
	}
	
}
