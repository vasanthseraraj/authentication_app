package com.hackathon.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class UserDbUtill {
	private DataSource dataSource;

	public UserDbUtill(DataSource theDataSource) {
		this.dataSource = theDataSource;
	}

	public List<User> getUsers() throws Exception {

		List<User> users = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {

			myConn = dataSource.getConnection();
			String sql = " SELECT * FROM user ORDER BY user_id";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				int roleId = myRs.getInt("user_id");
				String userName = myRs.getString("user_name");
				int userId = myRs.getInt("role_id");
				String roleName = myRs.getString("role_name");
				String updatedBy = myRs.getString("updated_by");

				User tempUser = new User(userId, userName, roleId, roleName, updatedBy);
				users.add(tempUser);

			}
			return users;
		} finally {
			close(myConn, myStmt, myRs);

		}

	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if (myRs != null) {
				myRs.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}
			if (myConn != null) {
				myConn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void addUser(User theUser) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			// getting our Connection
			myConn = dataSource.getConnection();
			// create sql for insert
			String sql = "INSERT INTO user " + " (user_name, role_id, role_name, updated_by) " + "values (?, ?, ?, ?)";
			// set the param values for the user
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, theUser.getUserName());
			myStmt.setInt(2, theUser.getRoleId());
			myStmt.setString(3, theUser.getRoleName());
			myStmt.setString(4, theUser.getUpdatedBy());
			// excute the sql insert
			myStmt.execute();

		} finally {
			close(myConn, myStmt, null);
		}
	}

	public User getUser(String theUserId) throws Exception {
		User theUser = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int userId;
		try {
			// convert user id to int
			userId = Integer.parseInt(theUserId);
			// get connection
			myConn = dataSource.getConnection();
			// create sql query to get selected user
			String sql = "SELECT * FROM user WHERE user_id=?";
			// create prepared user
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1, userId);
			// execute statement
			myRs = myStmt.executeQuery();
			// retrieve data from resultset row
			if (myRs.next()) {
				
				String userName = myRs.getString("user_name");
				int roleId = myRs.getInt("role_id");
				String roleName = myRs.getString("role_name");
				String updatedBy = myRs.getString("updated_by");

				theUser = new User( userName, roleId, roleName, updatedBy);
			} 
			return theUser;
		} finally {
			close(myConn, myStmt, myRs);

		}
	}

	public void editUsers(User theUser) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		// get Db Connection
		try {
			myConn = dataSource.getConnection();
			// create Sql Update Statement
			String sql = "UPDATE user " 
						+ "set user_name=?, role_id=?, role_name=?, updated_by=? "
					    + "where user_id=?";
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			// set params
		
			myStmt.setString(1, theUser.getUserName());
			myStmt.setInt(2, theUser.getRoleId());
			myStmt.setString(3, theUser.getRoleName());
			myStmt.setString(4, theUser.getUpdatedBy());
			myStmt.setInt(5, theUser.getUserId());
			// execute SQl statement
			myStmt.executeUpdate();
		} finally {
			close(myConn, myStmt, null);
		}

	}

	public void deleteUsers(String theUserId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			// convert userId from String into Int
			int userId = Integer.parseInt(theUserId);
			// Getting the connetion to database
			myConn = dataSource.getConnection();
			// create sql to delete the user
			String sql = "Delete from user where user_id=?";
			// prepare Statement
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setInt(1, userId);
			// execute Sql Statement
			myStmt.execute();

		} finally {
			close(myConn, myStmt, null);

		}

	}
}
