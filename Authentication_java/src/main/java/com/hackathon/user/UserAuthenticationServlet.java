package com.hackathon.user;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



@WebServlet("/UserAuthenticationServlet")
public class UserAuthenticationServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8975749459432642679L;
	private UserDbUtill userDbUtill;
	@Resource(name = "jdbc/userdb")
	private DataSource dataSource;


	@Override
	public void init() throws ServletException {
		super.init();
		try {
			userDbUtill = new UserDbUtill(dataSource);
		} catch (Exception exc) {
			throw new ServletException();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			// if the command is missing the default to listing the Users...
			if (theCommand == null) {
				theCommand = "LIST";
			}
			// route the apppropriate method
			switch (theCommand) {
			case "LIST":
				listUsers(request, response);
				break;
			case "ADD":
				addUser(request, response);
				break;
			case "LOAD":
				loadUsers(request, response);
				break;
			case "EDIT":
				editUsers(request, response);
				break;
			case "DELETE":
				deleteUsers(request, response);
				break;
			default:
				listUsers(request, response);
			}

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void deleteUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read userid from form data
		String theUserId = request.getParameter("userId");

		// delete user from database
		userDbUtill.deleteUsers(theUserId);

		listUsers(request, response);
	}

	private void editUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read user info from form data
		int userId = Integer.parseInt(request.getParameter("userId"));
		String userName = request.getParameter("userName");
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		String roleName = request.getParameter("roleName");
		String updatedBy = request.getParameter("updatedBy");
		// create a new users object
		User theUser = new User(userId,  userName, roleId, roleName,updatedBy);
		// perform update on database
		userDbUtill.editUsers(theUser);
		// send them back to users
		listUsers(request, response);

	}

	private void loadUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read user id from form data
				String theUserId = request.getParameter("userId");
				// get user from database (db util)
				User theUser = userDbUtill.getUser(theUserId);
				// place user in the request attribute
				request.setAttribute("THE_USER", theUser);
				// send to jsp page: update-user.jsp
				RequestDispatcher dispatcher = request.getRequestDispatcher("edit-user.jsp");
				dispatcher.forward(request, response);

	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read User info from the form
		String userName = request.getParameter("userName");
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		String roleName = request.getParameter("roleName");
		String updatedBy = request.getParameter("updatedBy");
				// creating new user object
				User theUser = new User(userName, roleId, roleName,updatedBy);
				// add the user to the database
				userDbUtill.addUser(theUser);
				// send back to main page (the user list)
				listUsers(request, response);

	}

	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<User> users = userDbUtill.getUsers();
		request.setAttribute("USER_LIST", users);
		// send back to homepage
			RequestDispatcher dispatcher = request.getRequestDispatcher("display-users.jsp");
			dispatcher.forward(request, response); 
		
	}

	

}
