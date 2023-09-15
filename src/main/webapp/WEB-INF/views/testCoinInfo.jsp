<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 82107
  Date: 2023-09-04
  Time: 오후 4:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%--%>
<%--    Object obj = request.getAttribute("list");--%>

<%--    List<HashMap<String, Object>> list = null;--%>
<%--    if(obj != null)--%>
<%--        list = (List<HashMap<String, Object>>)obj;--%>
<%--%>--%>
<html>
<head>
    <title>코인정보</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/style.css">
</head>
<body>
<h2>코인정보</h2>
<table>
    <thead>
    <tr>
        <th>순번</th>
        <th>종목 구분 코드</th>
        <th>시가</th>
        <th>고가</th>
        <th>저가</th>
        <th>종가(현재가)</th>
        <th>change</th>
    </tr>
    </thead>
<%--    <tbody>--%>
<%--    <%--%>
<%--        if(list != null) {--%>
<%--            for(int i=0; i<list.size(); i++) { %>--%>
<%--    <tr>--%>
<%--        <td><%= i+1%></td>--%>
<%--        <td><%= list.get(i).get("market") %></td>--%>
<%--        <td><%= list.get(i).get("opening_price") %></td>--%>
<%--        <td><%= list.get(i).get("high_price") %></td>--%>
<%--        <td><%= list.get(i).get("low_price") %></td>--%>
<%--        <td><%= list.get(i).get("trade_price") %></td>--%>
<%--        <td><%= list.get(i).get("change") %></td>--%>
<%--    </tr>--%>
<%--    <% }--%>
<%--    } %>--%>
<%--    </tbody>--%>
    <tbody>
        <c:forEach var="list2" items="${list2}">
            <tr>
                <td>${list2.id}</td>
                <td>${list2.market}</td>
                <td>${list2.opening_price}</td>
                <td>${list2.high_price}</td>
                <td>${list2.low_price}</td>
                <td>${list2.trade_price}</td>
                <td>${list2.change}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
