<%@include file="head.jsp"%>
<html>
<body>
<h2>User Display Exercise - Week 1</h2>
<p>Sandi Schwert</p>
<form action="searchUser" method="GET">
    <h3>Search for User: </h3>
    <input type="text" name="last_name" placeholder="Enter last name"/>
    <input type="submit" value="Submit"/>
    <br/>
    Leave field blank to Select All
</form>
</body>
</html>