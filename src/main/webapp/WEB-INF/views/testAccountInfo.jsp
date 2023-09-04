<%--
  Created by IntelliJ IDEA.
  User: 82107
  Date: 2023-08-21
  Time: 오후 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  Object obj = request.getAttribute("list");

  List<HashMap<String, Object>> list = null;
  if(obj != null)
    list = (List<HashMap<String, Object>>)obj;
%>
<html>
  <head>
    <title>계좌정보</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/style.css">
  </head>
  <body>
  <h2>나의계좌정보</h2>
  <table>
    <thead>
      <tr>
        <th>순번</th>
        <th>화폐를 의미하는 영문 대문자 코드</th>
        <th>주문가능 금액/수량</th>
        <th>주문 중 묶여있는 금액/수량</th>
        <th>매수평균가</th>
        <th>매수평균가 수정 여부</th>
        <th>평단가 기준 화폐</th>
      </tr>
    </thead>
    <tbody>
    <%
      if(list != null) {
        for(int i=0; i<list.size(); i++) { %>
          <tr>
            <td><%= i+1%></td>
            <td><%= list.get(i).get("currency") %></td>
            <td><%= list.get(i).get("balance") %></td>
            <td><%= list.get(i).get("locked") %></td>
            <td><%= list.get(i).get("avg_buy_price") %></td>
            <td><%= list.get(i).get("avg_buy_price_modified") %></td>
            <td><%= list.get(i).get("unit_currency") %></td>
          </tr>
    <% }
      } %>
    </tbody>
  </table>
  </body>
</html>
