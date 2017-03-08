<%-- 
    Document   : _Layout
    Created on : Feb 6, 2017, 12:44:15 PM
    Author     : kanst_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <h1>Add Author</h1>
        <form method="get" action="authors" class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-sm-1" for="firstName">First Name:</label>
                <div class="col-sm-2">
                    <input class="form-control" type="text" name="firstName" />
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-1" for="lastName">Last Name:</label>
                <div class="col-sm-2">
                    <input class="form-control" type="text" name="lastName" />
                </div>
            </div>
            <div class="form-group"> 
                <div class="col-sm-offset-1 col-sm-2">
            <input class="btn btn-default" type="submit" name="action" value="Add" />
            <input class="btn btn-default" type="button" name="action" value="Cancel" />
                </div>
            </div>
        </form>
        <br>
        <a href="authors?action=list">Go Back</a>
    <script type="text/javascript">
    $(document).ready(function() {
        $('#table').DataTable();
    });
    </script>
