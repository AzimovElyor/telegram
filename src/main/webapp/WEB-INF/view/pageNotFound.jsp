<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 27/08/2023
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>404 Error</title>
  <style>
    body {
      background-image: url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZd1outXxx3T5B0MwJK5XSt1OaJbF6jaq8Sg&usqp=CAU");
      background-size: 80%;
      background-position:center;
      background-attachment: fixed;
    }
    .error-container {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }
    h1 {
      font-size: 6rem;
      color: #e74c3c;
      margin: 0;
    }
    p {
      font-size: 1.5rem;
      color: #333;
      margin: 10px 0;
    }
    a {
      color: blue;
      font-size: 30px;
      text-decoration: none;
      font-weight: bold;
    }
  </style>
</head>
<body>
<div class="error-container">
  <h1>404 Error</h1>
  <a href="${pageContext.request.contextPath}/telegram/home">Back to Home</a>
</div>
</body>
</html>

