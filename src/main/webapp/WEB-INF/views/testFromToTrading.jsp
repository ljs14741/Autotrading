<%--
  Created by IntelliJ IDEA.
  User: 82107
  Date: 2023-08-21
  Time: 오후 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
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
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/style.css">
  </head>
  <body>
    <h2>여기는 테스트2화면</h2>
    <table>
      <thead>
        <tr>
          <th>주문ID</th>
          <th>마켓ID</th>
          <th>거래시간</th>
          <th>매수/매도</th>
          <th>거래 가격</th>
          <th>거래량</th>
          <th>수익률(매도시)</th>
          <th>누적수익률(매도시)</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>값</td>
          <td>값</td>
          <td>값</td>
          <td>값</td>
          <td>값</td>
          <td>값</td>
          <td>값</td>
          <td>값</td>
        </tr>
      </tbody>
      <thead>
      <tr>
        <th>순번</th>
        <th>마켓명</th>
        <th>캔들 기준 시각(UTC)</th>
        <th>캔들 기준 시각(KTC)</th>
        <th>시가</th>
        <th>고가</th>
        <th>저가</th>
        <th>종가</th>
        <th>마지막 틱이 저장된 시각</th>
        <th>누적 거래 금액</th>
        <th>누적 거래량</th>
        <th>전일 종가(UTC 0시 기준)</th>
        <th>전일 종가 대비 변화 금액</th>
        <th>전일 종가 대비 변화량</th>
        <th>rsi</th>
      </tr>
      </thead>
      <tbody>
      <%
        if(list != null) {
          for(int i=0; i<list.size(); i++) { %>
      <tr>
        <td><%= i+1%></td>
        <td><%= list.get(i).get("market") %></td>
        <td><%= list.get(i).get("candle_date_time_utc") %></td>
        <td><%= list.get(i).get("candle_date_time_kst") %></td>
        <td><%= list.get(i).get("opening_price") %></td>
        <td><%= list.get(i).get("high_price") %></td>
        <td><%= list.get(i).get("low_price") %></td>
        <td><%= list.get(i).get("trade_price") %></td>
        <td><%= list.get(i).get("timestamp") %></td>
        <td><%= list.get(i).get("candle_acc_trade_price") %></td>
        <td><%= list.get(i).get("candle_acc_trade_volume") %></td>
        <td><%= list.get(i).get("prev_closing_price") %></td>
        <td><%= list.get(i).get("change_price") %></td>
        <td><%= list.get(i).get("change_rate") %></td>
        <td><%= list.get(i).get("rsi") %></td>
      </tr>
      <% }
      } %>
      </tbody>
    </table>
  </body>
</html>
