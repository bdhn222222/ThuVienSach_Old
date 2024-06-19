<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.Reader" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách hội viên</title>
</head>
<body class="bg-dark">
	<section class="content my-3">
        <div id="wrapper">
            <div class="container">
                <div class="row justify-content-around">
                    <form action="" class="bg-black text-light col-md-10 bg-light p-3 my-3">
                    	<div class="row">
                    		<div class="col-md-9">
		                    	<h1 class=" tex-uppercase h3 py-2">List of Readers</h1>
		                    </div>
		                    
		                    <div class="col-md-3 d-flex justify-content-end ">			                            	
	                           	<button type="button" class="btn btn-success mt-6" id="edit" onclick="location.href='AddReader'">
							        <h6>Add Reader</h6><i class="fa-solid fa-plus"></i> 
							    </button>
	                    	</div>
                    	</div>
                    
                        <div class="container mt-3">          
                            <table class="table table-dark text-light">
                              <thead>
                                <tr class="">
                                  <th>STT</th>
                                  <th>Member's Name</th>
                                  <th>Identity</th>
                                  <th>Telephone</th>
                                  <th>Edit</th>
                                  <th>Delete</th>
                                </tr>
                              </thead>
                              <tbody>
                               <% 
                                List<Reader> readerList = (List<Reader>) request.getAttribute("readerList");
                                if (readerList != null) {
                                	int status = 1;
                                    for (Reader reader : readerList) {
                                    	
                                %>
                                    <tr>
                                        <td><%= status++ %></td>
                                        <td><%= reader.getNameReader() %></td>
                                        <td><%= reader.getIdentity() %></td>
                                        <td><%= reader.getTelReader() %></td>
                                        
                                        <td>
                                            <a href="EditReader?idReader=<%= reader.getIdReader() %>&idReader=<%= reader.getIdReader() %>"><i class="fa-solid fa-pen-to-square"></i></a>
                                        </td>
                                        <td>
                                            <a href="DeleteReader?idReader=<%= reader.getIdReader() %>&idReader=<%= reader.getIdReader() %>"><i class="fa-solid fa-trash"></i></a>
                                        </td>
                                    </tr>
                                    
                                <% 
                                    }
                                }
                                %>
                                </tbody>
                            </table>
                          </div>
                    </form>
                </div>
            </div>
        </div>
      </div>
    </section>
</body>
</html>