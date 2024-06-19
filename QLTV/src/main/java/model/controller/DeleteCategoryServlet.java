package model.controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.User;
import model.bo.CategoryBO;


/**
 * Servlet implementation class DeleteCategory
 */
@WebServlet("/DeleteCategory")
public class DeleteCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryBO categoryBO = new CategoryBO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCategoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String errorString = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userSession");
		if (user == null) {
			errorString = "You need login first!";
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} else {
		int idCategory = Integer.parseInt(request.getParameter("idCategory"));
		boolean result;
		try {
			result = categoryBO.deleteCategory(idCategory);
			System.out.println("Ket qua"+result);
			if (result == true) {
				request.setAttribute("errorString", "Đã xóa thành công");
			} else {
				System.out.println("DeleteCategoryServlet - doGet() called");
				request.setAttribute("errorString", "Lỗi cơ sở dữ liệu");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/ManageCategory");
//		dispatcher.forward(request, response);
		response.sendRedirect("ManageCategory");
		}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
