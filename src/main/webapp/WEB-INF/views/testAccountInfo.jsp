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
    <title>$Title$</title>
    <style type="text/css">
      div#container {
        width: 95%;
        margin: 0 auto;
      }

      table {
        width: 90%;
        border: solid 1px gray;
        border-collapse: collapse;
        margin-top: 30px;
        text-align: center;
      }

      th, td {
        border: solid 1px gray;
        border-collapse: collapse;
      }

      tbody tr:hover {
        background-color: #ccc;
        cursor: pointer;
      }

      button {
        margin-top: 30px;
        margin-right: 180px;
        float: right;
      }
    </style>
  </head>
  <body>
  <h2>여기는 테스트2화면</h2>
  <table border = "1" class="editorDemoTable" style="height: 167px; width: 486px; ">
    <thead>
      <tr>
        <td>순번</td>
        <td>화폐를 의미하는 영문 대문자 코드</td>
        <td>주문가능 금액/수량</td>
        <td>주문 중 묶여있는 금액/수량</td>
        <td>매수평균가</td>
        <td>매수평균가 수정 여부</td>
        <td>평단가 기준 화폐</td>
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
