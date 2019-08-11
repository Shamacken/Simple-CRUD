<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update page</title>
</head>
<body>

<div>Name: <c:out value="${requestScope.user.name}"/> </div>
<div>Age: <c:out value="${requestScope.user.age}"/> </div>
<br />

<form method="post" action="<c:url value='/update'/>">

    <label>New name: <input type="text" name="name" /></label><br>
    <label>New age: <input type="number" name="age" /></label><br>
    <input type="number" hidden name="id" value="${requestScope.user.id}"/>

    <input type="submit" value="Ok" name="Ok"><br>
</form>
</body>
</html>