<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index page</title>
</head>
<body>
<h2>All users</h2><br />

<c:forEach var="user" items="${requestScope.users}">
    <ul>
        <li>Name: <c:out value="${user.name}"/></li>

        <li>Age: <c:out value="${user.age}"/></li>

        <form method="post" action="<c:url value='/delete'/>">
            <input type="number" hidden name="id" value="${user.id}" />
            <input type="submit" name="delete" value="Delete"/>
        </form>

        <form method="get" action="<c:url value='/update'/>">
            <input type="number" hidden name="id" value="${user.id}" />
            <input type="submit" value="Update"/>
        </form>
    </ul>
    <hr />
</c:forEach>

<h2>Create new user</h2><br />

<form method="post" action="<c:url value='/add_user'/>">

    <label><input type="text" name="name"></label> Name<br>

    <label><input type="number" name="age"></label> Age<br>

    <input type="submit" value="Ok" name="Ok"><br>
</form>

</body>
</html>