<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.BookShelf" %>
<%@ page import="model.bean.Category" %>
<%@ page import="model.bean.Authors" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add book</title>
</head>
<body style="background-color: #343541;">
    <section class="content">
        <div id="wrapper">
            <div class="container">
                <div class="row justify-content-around">
                    <form action="AddBook" method="post"  class="bg-black text-light col-md-5 bg-light p-3 my-3">
                        <h1 class="tex-uppercase h3">Add Book</h1>
                        
                        <hr style="border-top: 1px solid white; margin-bottom: 20px;">
                        
                        <div class="form-group">
                            <label for="nameBook">Title</label>
                            <input type="text" name="nameBook" id="nameBook" class="form-control bg-dark" style="color:white;">
                        </div>
                        
                        <div class="form-group">
                            <label for="category">Category</label>
                            <select class="form-control bg-dark" style="color: white;" id="category" name="category" required>
                                <option value="" disabled selected>Choose option</option>
                                <% 
                                    List<Category> categoryList = (List<Category>) request.getAttribute("categoryList");
                                    if (categoryList != null) {
                                        for (Category category : categoryList) {
                                %>
                                <option value="<%= category.getIdCategory() %>"><%= category.getNameCategory() %></option>
                                <% 
                                        }
                                    }
                                %>
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label for="amount">Amount</label>
                            <input type="number" name="amount" id="amount" class="form-control bg-dark" style="color:white;" min="0">
                        </div>
                        
                        <div class="form-group">
                            <label for="bookShelf">Bookshelf's id</label>
                            <select class="form-control bg-dark" style="color: white;" id="bookShelf" name="bookShelf" required>
                                <option value="" disabled selected>Choose option</option>
                                <% 
                                    List<BookShelf> bookShelfList = (List<BookShelf>) request.getAttribute("bookShelfList");
                                    if (bookShelfList != null) {
                                        for (BookShelf bookshelf : bookShelfList) {
                                %>
                                <option value="<%= bookshelf.getIdBookShelf() %>"><%= bookshelf.getNameBookShelf() %></option>
                                <% 
                                        }
                                    }
                                %>
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label for="authors">Author's Name</label>
                           <select class="form-control bg-dark" style="color: white;" id="authors" name="authors" required>
                                <option value="" disabled selected>Choose option</option>
                                <% 
                                    List<Authors> authorsList = (List<Authors>) request.getAttribute("authorsList");
                                    if (authorsList != null) {
                                        for (Authors author : authorsList) {
                                %>
                                <option value="<%= author.getIdAuthors() %>"><%= author.getNameAuthors() %></option>
                                <% 
                                        }
                                    }
                                %>
                            </select>
                        </div>
                        
                        <hr style="border-top: 1px solid white; margin-bottom: 20px;">
                        
                        <div class="form-group">
                            <div class="d-grid gap-2">
                                <input type="submit" value="Submit" class="btn btn-primary mt-1">
                            </div>
                            <div class="d-grid gap-2">
                                <input type="button" value="Cancel" class="btn btn-secondary mt-1" onclick="location.href='ManageBook'">
                            </div>
                        </div>
                        
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
