<%@include file="head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    $(document).ready(function(){

        $('#searchResultsTable').DataTable();
    });
</script>

<html><body>

<div class="container-fluid">


    <br>

    <table id="searchResultsTable" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Age</th>
        </tr>
        </thead>
        <tfoot>
        <tr></tr>
        </tfoot>
        <tbody>
        <c:forEach var="user" items="${user}">
            <tr>
                <td>${user.userid}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.calculateAge()}</td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
    <br/>
    <a href = "index.jsp">Go back to the User Search</a>

</div>

</body>
</html>
