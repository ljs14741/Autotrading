<%--
  Created by IntelliJ IDEA.
  User: 82107
  Date: 2023-08-21
  Time: 오후 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>title</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/style.css">
  </head>
  <body>
  <h2>Main Test </h2>
  <div>
    <button class="btn-gradient cyan small" type="button" onclick="location.href='testAccountInfo'">나의 계좌 정보</button>
  </div>
  <form action="testRequestOrder.do" method="get">
    <button class="btn-gradient cyan small" type="submit">매수하기</button>
  </form>
  <div>
   <button class="btn-gradient cyan small" type="button">매도하기</button>
  </div>
  </body>
</html>
