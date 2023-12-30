package model.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.Authors;
import model.bean.Book;
import model.bean.BookShelf;
import model.bean.Category;
import model.bean.User;
import model.bo.AuthorsBO;
import model.bo.BookBO;
import model.bo.BookShelfBO;
import model.bo.CategoryBO;
@WebServlet("/EditBook")
public class EditBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryBO categoryBO = new CategoryBO();
    private AuthorsBO authorsBO = new AuthorsBO();
    private BookShelfBO bookshelfBO = new BookShelfBO();
    private BookBO bookBO = new BookBO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String errorString = null;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userSession");
		if (user == null) {
			errorString = "You need login first!";
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		} else {
			String idBook = request.getParameter("idBook");
	        List<Category> categoryList = null;
	        List<BookShelf> bookShelfList = null;
	        List<Authors> authorsList = null;
	        ArrayList<Book> list = null;

	        if (idBook != null && !idBook.isEmpty()) {
	            try {
	                Integer bookID = Integer.parseInt(idBook);
	                Book book = bookBO.findBook(bookID);
	                list = bookBO.getAllBook();
	                categoryList = categoryBO.list();
	                bookShelfList = bookshelfBO.getAllBookShelf();
	                authorsList = authorsBO.list();
	
	                if (book != null) {
	                    request.setAttribute("book", book);
	                    request.setAttribute("errorString", errorString);
	                    request.setAttribute("categoryList", categoryList);
	                    request.setAttribute("bookShelfList", bookShelfList);
	                    request.setAttribute("authorsList", authorsList);
	                    request.setAttribute("bookList", list);
	                    RequestDispatcher dispatcher = request.getRequestDispatcher("/edit_book.jsp");
	                    dispatcher.forward(request, response);
	                } else {
	                    errorString = "Book not found!";
	                    request.setAttribute("errorString", errorString);
	                    RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
	                    dispatcher.forward(request, response);
	                }
	            } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
	                e.printStackTrace();
	                errorString = "An error occurred while processing your request.";
	                request.setAttribute("errorString", errorString);
	                RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
	                dispatcher.forward(request, response);
            }
        }
    }
}
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String idBook = request.getParameter("idBook");
        String nameBook = request.getParameter("nameBook");
        String idCategory = request.getParameter("category");
        String idBookShelf = request.getParameter("bookShelf");
        String idAuthors = request.getParameter("authors");
        Integer amount = Integer.parseInt(request.getParameter("amount"));

        if (idBook != null && !idBook.isEmpty()) {
            try {
                Integer bookID = Integer.parseInt(idBook);
                Book book = new Book();
                Category category = new Category();
                BookShelf bookShelf = new BookShelf();
                Authors authors = new Authors();

                try {
                    int categoryId = Integer.parseInt(idCategory);
                    category = categoryBO.findCategory(categoryId);
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                    request.setAttribute("errorMessage", "Failed to find category.");
                    doGet(request, response);
                    return;
                }

                try {
                    int bookShelfId = Integer.parseInt(idBookShelf);
                    bookShelf = bookshelfBO.findBookShelf(bookShelfId);
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                    request.setAttribute("errorMessage", "Failed to find book shelf.");
                    doGet(request, response);
                    return;
                }

                try {
                    int authorsId = Integer.parseInt(idAuthors);
                    authors = authorsBO.findAuthors(authorsId);
                } catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                    request.setAttribute("errorMessage", "Failed to find authors.");
                    doGet(request, response);
                    return;
                }

                book.setIdBook(bookID);
                book.setNameBook(nameBook);
                book.setAmount(amount);
                book.setCategory(category);
                book.setAuthors(authors);
                book.setBookShelf(bookShelf);

                BookBO bookBO = new BookBO();
                   int result = bookBO.updateBook(book);
                   
                   if (result != 0) {
                       response.sendRedirect(request.getContextPath() + "/ManageBook");
                   } else {
                       response.getWriter().println("Update failed!");
                   }
               } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
                   e.printStackTrace();
                   response.getWriter().println("An error occurred while processing your request.");
               }
           } else {
               response.getWriter().println("Invalid reader ID!");
           }
       }
   }
