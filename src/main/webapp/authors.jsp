<%-- 
    Document   : _Layout
    Created on : Feb 6, 2017, 12:44:15 PM
    Author     : kanst_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <div class="container">
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
                <th>
                    Actions
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
                        <td>
                            <form method="post" action="authors">
                                <input type="hidden" name="aid" value="${author.authorId}" />
                                <input type="submit" name="action" value="View" />
                                &nbsp;|&nbsp;
                                <input type="submit" name="action" value="Edit" />
                                &nbsp;|&nbsp;
                                <input type="submit" name="action" value="Delete" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form method="post" action="authors">
            <input type="submit" name="action" value="Add New" />
        </form>
        <br>
        <a href="index.jsp">Return to main menu.</a>
        </div>
    <script type="text/javascript">
    $(document).ready(function() {
        $('#table').DataTable();
    });
    </script>
