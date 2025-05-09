<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Registration</title></head>
<body>
<h2>Course Registration Status</h2>
<c:if test="${not empty message}">
    <p style="color:green;">${message}</p>
</c:if>
<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>
<a href="courses">Back to Courses</a>
</body>
</html>
