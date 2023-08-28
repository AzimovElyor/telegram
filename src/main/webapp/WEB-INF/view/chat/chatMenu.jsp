<%@ page import="java.util.List" %>
<%@ page import="uz.pdp.telegram.dto.chat.ChatResponseDto" %>
<%@ page import="uz.pdp.telegram.dto.chat.ChatRequestDto" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 27/08/2023
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>My Chats</title>
  <style>
    /* styles.css */
    body {
      margin: 0;
      font-family: Arial, sans-serif;
    }

    .navbar {
      display: flex;
      justify-content: space-between;
      align-items: center;
      background-color: #0088cc;
      padding: 10px 20px;
      color: white;
    }

    .logo img {
      width: 40px;
      height: 40px;
    }

    .menu a {
      text-decoration: none;
      color: white;
      margin: 0 10px;
    }

    .search input {
      padding: 5px;
      border: 1px solid #ccc;
      border-radius: 3px;
    }

    .search button {
      background-color: #0088cc;
      color: white;
      border: none;
      border-radius: 3px;
      padding: 5px 10px;
      cursor: pointer;
    }

    .profile img {
      width: 40px;
      height: 40px;
      border-radius: 50%;
    }
    /* styles.css */
    /* Oldingi stillar bilan boshqarmang, kerakli o'zgarishlarni qo'shing */

    .chat-container {
      margin-top: 20px;
    }

    .chat-table {
      width: 100%;
      border-collapse: collapse;
    }

    .chat-table th,
    .chat-table td {
      border: 1px solid #ccc;
      padding: 8px;
      text-align: center;
    }

    .delete-button,
    .chat-button {
      background-color: #e74c3c;
      color: white;
      border: none;
      border-radius: 3px;
      padding: 5px 10px;
      cursor: pointer;
    }


  </style>
</head>
<body>
<nav class="navbar">
  <div class="logo">
    <img class="icon" src="data:image/jpeg;base64,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">
  </div>
  <div class="menu">
    <a href="${pageContext.request.contextPath}/telegram/home">Home</a>
    <a href="#">About</a>
    <a href="#">My Contacts</a>
    <div class="search">
      <form  method="get">
        <input type="text" placeholder="Search">
        <button type="submit">Search</button>
      </form>

    </div>
  </div>
  <div class="profile">
    <img class="icon" src="data:image/jpeg;base64,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" alt="User profile Image">
  </div>
</nav>

<div class="chat-container">
  <table class="chat-table">
    <thead>
    <tr>
      <th>Profile</th>
      <th>Name</th>
      <th>Delete</th>
      <th>Chat</th>
    </tr>
    </thead>
    <tbody>
    <% List<ChatResponseDto> myChats = (List<ChatResponseDto>) request.getAttribute("myChats");%>
    <%for (ChatResponseDto responseDto : myChats) {%>
    <tr>
      <td>
        <img src="user1_profile.jpg" alt="User 1 Profile"></td>
      <a href="${pageContext.request.contextPath}/chat/chatting/"<%=responseDto.getId()%>>
        <td><%=responseDto.getMemberSecond().getFirstName() +  " " + responseDto.getMemberSecond().getLastName()%></td>
      </a>
      <td><button class="delete-button">Delete</button></td>
      <td><button class="chat-button">Chat</button></td>
    </tr>
    <%}%>
    <!-- Boshqa chatlar va ma'lumotlarni shu yerda qo'shishingiz mumkin -->
    </tbody>
  </table>
</div>

</body>
</html>

