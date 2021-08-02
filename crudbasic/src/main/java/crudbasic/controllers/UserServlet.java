package crudbasic.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crudbasic.dao.userDAO;
import crudbasic.model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private userDAO UserrDAO;
    
       /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        this.UserrDAO = new userDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		try {
		switch(action) {
		case "/new":
			showNewform(request,response);
			break;
		case "/insert":
				insertuser(request,response);
			break;
		case "/delete":
				deleteUser(request,response);
			
			break;
		case "/edit":
				showEditform(request,response);
			    break;
		case "/update":
				updateUser(request,response);
			
			    break;
		default :
			//handle list
				listUser(request,response);
			    break;
		}
	   }catch(SQLException ex) {
			throw new ServletException(ex);
		   
	   }
	}
	
	private void listUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<User> listUser = UserrDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private void showNewform(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showEditform(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException, SQLException {
				int id = Integer.parseInt(request.getParameter("id"));
		        User existingUser = UserrDAO.selectUser(id);
		
		        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		        request.setAttribute("user", existingUser);
				dispatcher.forward(request, response);
			}
	
	private void insertuser(HttpServletRequest request,HttpServletResponse response)
			throws SQLException,IOException {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String batch = request.getParameter("batch");
            String courses = request.getParameter("courses");
            
            User newUser = new User(name,email,batch,courses);
            UserrDAO.insertUser(newUser);
            response.sendRedirect("list");
	}
	
	private void updateUser(HttpServletRequest request,HttpServletResponse response)
			throws SQLException,IOException {
		    int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String batch = request.getParameter("batch");
            String courses = request.getParameter("courses");
            
            User book = new User(id,name,email,batch,courses);
            UserrDAO.updateUser(book);
            response.sendRedirect("list");
	}

	
	
	private void deleteUser(HttpServletRequest request,HttpServletResponse response)
			throws SQLException,IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            UserrDAO.deleteUser(id);
            response.sendRedirect("list");
	}
	
	
}
