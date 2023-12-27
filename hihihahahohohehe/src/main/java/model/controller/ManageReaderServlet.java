package model.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bean.Reader;
import model.bo.ReaderBO;

/**
 * Servlet implementation class ManageReader
 */
@WebServlet("/ManageReader")
public class ManageReaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReaderBO ReaderBO = new ReaderBO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageReaderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*if (request.getSession().getAttribute("User") == null) {
			String errorString = "Bạn cần đăng nhập trước";
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} else {*/
			String errorString = null;
			ArrayList<Reader> list = null;

			try {
				list = ReaderBO.getAllReader();
			} catch (Exception e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
			if (request.getAttribute("errorString") != null) {
				errorString = (String) request.getAttribute("errorString");
			}
			// Lưu thông tin vào request attribute trước khi forward sang views.
			request.setAttribute("errorString", errorString);
			request.setAttribute("readerList", list);
			request.getSession().setAttribute("Check", "ManageReader");
			// Forward  sang /WEB-INF/views/productListView.jsp
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/manage_reader.jsp");
			dispatcher.forward(request, response);
		}
	//}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String nameReader = request.getParameter("nameReader");
	    String identity = request.getParameter("identity"); // Lưu ý tên tham số
	    String telReader = request.getParameter("telReader");

	    Reader reader = new Reader();
	    reader.setNameReader(nameReader);
	    reader.setIdentity(identity); // Đặt giá trị cho thuộc tính identity
	    reader.setTelReader(telReader);

	    ReaderBO readerBO = new ReaderBO();
	    try {
	        readerBO.insertReader(reader); // Thêm người đọc mới
	        response.sendRedirect(request.getContextPath() + "/ManageReader"); // Chuyển hướng về trang quản lý người đọc
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        response.getWriter().println("An error occurred while processing your request.");
	    }
	}


}
