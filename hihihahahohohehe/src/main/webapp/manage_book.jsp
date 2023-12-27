<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.Book" %>
<%@ page import="model.bean.Category" %>
<%@ page import="model.bean.BookShelf" %>
<%@ page import="model.bean.Authors" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Danh sách sách</title>
</head>
<body class="bg-dark">
    <section class="content my-3">
        <div id="wrapper">
            <div class="container">
                <div class="row justify-content-around">
                    <form action="ManageBook" class="bg-black text-light col-md-10 bg-light p-3 my-3">
                        <div class="row">
                    		<div class="col-md-9">
		                    	<h1 class="tex-uppercase h3 py-2">List of books</h1>
		                    </div>
		                    
		                    <div class="col-md-3 d-flex justify-content-end ">			                            	
	                           	<button type="button" class="btn btn-success mt-6" id="edit" onclick="location.href='AddBook'">
							        <h6>Add books</h6><i class="fa-solid fa-plus"></i> 
							    </button>
	                    	</div>
                    	</div>
                        
                        <div class="container mt-3">          
                            <table class="table table-dark text-light">
                                <thead>
                                    <tr class="">
                                        <th>No.</th>
                                        <th>Name Book</th>
                                        <th>Category</th>
                                        <th>Authors</th>
                                        <th>BookShelf</th>
                                        <th>Amount</th>
                                        <th>Edit</th>
                                        <th>Delete</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="book" items="${bookList}">
                                        <tr>
                                            <td><c:out value="${bookList.indexOf(book) + 1}" /></td>
                                            <td><c:out value="${book.nameBook}" /></td>
                                            <td><c:out value="${book.category.nameCategory}" /></td>
                                            <td><c:out value="${book.authors.nameAuthors}" /></td>
                                            <td><c:out value="${book.bookShelf.nameBookShelf}" /></td>
                                            <td><c:out value="${book.amount}" /></td>
                                            <td>
                                                <a href="EditBook?idBook=${book.idBook}&id=${book.idBook}"><i class="fa-solid fa-pen-to-square"></i></a>
                                            </td>
                                            <td>
                                                <a href="DeleteBook?idBook=${book.idBook}&idBook=${book.idBook}">
                                                    <i class="fa-solid fa-trash"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>