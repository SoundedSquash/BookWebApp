<%-- 
    Document   : _Layout
    Created on : Feb 6, 2017, 12:44:15 PM
    Author     : kanst_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Menu</title>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.13/datatables.min.css"/>
    <script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous"></script>
 
        <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.13/datatables.min.js"></script>
    </head>
    <body>
        <h1>Authors</h1>
        
        <table id="table" class="display" cellspacing="0">
            <thead>
            <tr>
                <th>
                    ID
                </th>
                <th>
                    Author's Name
                </th>
                <th>
                    Date Added
                </th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="author" items="${authors}">
                    <tr>
                        <td>
                            ${author.authorId}
                        </td>
                        <td>
                            ${author.authorName}
                        </td>
                        <td>
                            <fmt:formatDate pattern="MM/dd/yyyy" value="${author.dateAdded}" /> 
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
    <script type="text/javascript">
        $(document).ready(function() {
    $('#table').DataTable();
} );
    </script>
</html>
