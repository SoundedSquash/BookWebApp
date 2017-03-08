<%-- 
    Document   : _Layout
    Created on : Feb 6, 2017, 12:44:15 PM
    Author     : kanst_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

        <h1>Edit Author</h1>
        <form method="post" action="authors">
            <table id="table" class="display" cellspacing="0">
                <thead>
                <tr>
                    <th>
                        ID
                    </th>
                    <th>
                        Author's First Name
                    </th>
                    
                    <th>
                        Author's Last Name
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
                                <c:set var="authorName" value="${fn:split(author.authorName, ' ')}" />
                                
                                <input type="hidden" name="aid" value="${author.authorId}" />
                                ${author.authorId}
                            </td>
                            <td>
                                <c:set var="firstName" value="" />
                                <c:forEach items="${authorName}" begin="0" end="${fn:length(authorName)-2}" var="name">
                                    <c:set var="firstName" value="${firstName} ${name}"/>
                                </c:forEach>
                                <input type="text" name="firstName" value="${firstName}" />                                
                            </td>
                            <td>
                                <input type="text" name="lastName" value="${authorName[fn:length(authorName)-1]}" />  
                            </td>
                            <td>
                                <fmt:formatDate pattern="MM/dd/yyyy" value="${author.dateAdded}" />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <input type="submit" name="action" value="Update" />
            <input type="button" name="action" value="Cancel" />
        </form>
        <br>
        <a href="authors?action=list">Go Back</a>
    <script type="text/javascript">
    $(document).ready(function() {
        $('#table').DataTable();
    });
    </script>
