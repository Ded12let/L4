<%@ page import="model.DB" %>
<%@ page import="java.sql.*" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Panel - Professors</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center text-danger mb-4">Professor List</h2>

    <table class="table table-striped table-hover border">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Surname</th>
        </tr>
        </thead>
        <tbody>
        <%
            try (ResultSet rs = DB.getAllProfessors()) {
                while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getInt("id") %></td>
            <td><%= rs.getString("firstName") %></td>
            <td><%= rs.getString("lastName") %></td>
            <td><%= rs.getString("surname") %></td>
        </tr>
        <%
            }
        } catch (Exception e) {
        %>
        <tr>
            <td colspan="4" class="text-danger">Error: <%= e.getMessage() %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <div class="text-center mt-4">
        <a href="index.jsp" class="btn btn-secondary">Logout</a>
    </div>
</div>
</body>
</html>
