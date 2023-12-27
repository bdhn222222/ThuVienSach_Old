package model.controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bo.BookShelfBO;


/**
 * Servlet implementation class DeleteBookShelf
 */
@WebServlet("/DeleteBookShelf")
public class DeleteBookShelfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookShelfBO BookShelfBO = new BookShelfBO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteBookShelfServlet() {
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
		int idBookShelf = Integer.parseInt(request.getParameter("idBookShelf"));
		boolean result;
		try {
			result = BookShelfBO.deleteBookShelf(idBookShelf);
			System.out.println("Ket qua"+result);
			if (result == true) {
				request.setAttribute("errorString", "Đã xóa thành công");
			} else {
				System.out.println("DeleteBookShelfServlet - doGet() called");
				request.setAttribute("errorString", "Lỗi cơ sở dữ liệu");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/ManageBookShelf");
//		dispatcher.forward(request, response);
		response.sendRedirect("ManageBookShelf");
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
