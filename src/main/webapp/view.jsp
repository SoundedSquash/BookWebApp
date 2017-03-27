<%-- 
    Document   : _Layout
    Created on : Feb 6, 2017, 12:44:15 PM
    Author     : kanst_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <h1>Author View</h1>
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
            </tbody>
        </table>
        <br>
        <a href="authors?action=list">Return to list.</a>
    <script type="text/javascript">
    $(document).ready(function() {
        $('#table').DataTable();
    });
    </script>
