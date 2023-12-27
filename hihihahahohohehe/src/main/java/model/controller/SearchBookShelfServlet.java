package model.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bean.BookShelf;
import model.bo.BookShelfBO;

@WebServlet("/SearchBookShelf")
public class SearchBookShelfServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookShelfBO bookShelfBO = new BookShelfBO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to your search_book_shelf.jsp page or handle the GET request as needed
        // (for example, showing a search form for book shelves)
        request.getRequestDispatcher("danh_sach_ke_sach.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("searchInput");
        ArrayList<BookShelf> danhsach = null;

        try {
            // Thực hiện tìm kiếm và lưu kết quả vào danh sách
            danhsach = bookShelfBO.getSearchBookShelf(name);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu có
        }

        request.setAttribute("ketqua", danhsach);
        request.getRequestDispatcher("SearchBookShelf.jsp").forward(request, response);
    }
}
