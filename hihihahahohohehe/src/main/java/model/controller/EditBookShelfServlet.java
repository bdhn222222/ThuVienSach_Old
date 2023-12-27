package model.controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.bean.BookShelf;
import model.bo.BookShelfBO;

@WebServlet("/EditBookShelf")
public class EditBookShelfServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idBookShelfStr = request.getParameter("idBookShelf");
        
        if (idBookShelfStr != null && !idBookShelfStr.isEmpty()) {
            try {
                int idBookShelf = Integer.parseInt(idBookShelfStr);
                BookShelfBO bookShelfBO = new BookShelfBO();
                BookShelf bookshelf = bookShelfBO.findBookShelf(idBookShelf);
                
                if (bookshelf != null) {
                    request.setAttribute("bookshelf", bookshelf);
                    request.getRequestDispatcher("/edit_bookshelf.jsp").forward(request, response);
                } else {
                    response.getWriter().println("BookShelf not found!");
                }
            } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                response.getWriter().println("An error occurred while processing your request.");
            }
        } else {
            response.getWriter().println("Invalid BookShelf ID!");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String idBookShelfStr = request.getParameter("idBookShelf");
        String nameBookShelf = request.getParameter("nameBookShelf");

        if (idBookShelfStr != null && !idBookShelfStr.isEmpty()) {
            try {
                int idBookShelf = Integer.parseInt(idBookShelfStr);
                BookShelf bookshelf = new BookShelf();
                bookshelf.setIdBookShelf(idBookShelf);
                bookshelf.setNameBookShelf(nameBookShelf);

                BookShelfBO bookShelfBO = new BookShelfBO();
                int result = bookShelfBO.updateBookShelf(bookshelf);

                if (result != 0) {
                    response.sendRedirect(request.getContextPath() + "/ManageBookShelf");
                } else {
                    response.getWriter().println("Update failed!");
                }
            } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                response.getWriter().println("An error occurred while processing your request.");
            }
        } else {
            response.getWriter().println("Invalid BookShelf ID!");
        }
    }
}
