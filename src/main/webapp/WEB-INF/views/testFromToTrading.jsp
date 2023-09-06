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
<%@ page import="com.bitcoin.autotrading.candle.domain.Candle" %>
<%@ page import="com.bitcoin.autotrading.order.domain.Order" %>
<%@ page import="com.bitcoin.autotrading.account.domain.Account" %>
<%@ page import="com.bitcoin.autotrading.user.domain.ResponseBackTestingDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  //Object obj = request.getAttribute("list");

  /*
  List<HashMap<String, Object>> list = null;
  if(obj != null)
    list = (List<HashMap<String, Object>>)obj;
  */
  ResponseBackTestingDTO res = (ResponseBackTestingDTO) request.getAttribute("list");


  List<Candle> candleList = res.getCandleList();
  List<Order> orderList = res.getOrderList();
  Account account = res.getAccount();



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
      <%
        if(orderList != null) {
          for(int j=0; j<orderList.size(); j++) { %>
      <tr>
        <td><%= j+1%></td>
        <td><%= orderList.get(j).getUuid()%></td>
        <td><%= orderList.get(j).getMarket() %></td>
        <td><%= orderList.get(j).getTrades_created_at() %></td>
        <td><%= orderList.get(j).getSide() %></td>
        <td><%= orderList.get(j).getPrice() %></td>
        <td><%= orderList.get(j).getVolume() %></td>
        <td><%= orderList.get(j).getPortfolio() %></td>
        <td><%= 1 %></td>
      </tr>
      <% }
      } %>
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
        <!--
        <th>전일 종가(UTC 0시 기준)</th>
        <th>전일 종가 대비 변화 금액</th>
        <th>전일 종가 대비 변화량</th>
        -->
        <th>rsi</th>
      </tr>

      </thead>
      <tbody>
      <%
        if(candleList != null) {
          for(int i=0; i<candleList.size(); i++) { %>
      <tr>
        <td><%= i+1%></td>
        <td><%= candleList.get(i).getMarket() %></td>
        <td><%= candleList.get(i).getCandle_date_time_utc() %></td>
        <td><%= candleList.get(i).getCandle_date_time_kst() %></td>
        <td><%= candleList.get(i).getOpening_price() %></td>
        <td><%= candleList.get(i).getHigh_price() %></td>
        <td><%= candleList.get(i).getLow_price() %></td>
        <td><%= candleList.get(i).getTrade_price() %></td>
        <td><%= candleList.get(i).getTimestamp() %></td>
        <td><%= candleList.get(i).getCandle_acc_trade_price() %></td>
        <td><%= candleList.get(i).getCandle_acc_trade_volume() %></td>
        <td><%= candleList.get(i).getRsi() %></td>
      </tr>
      <% }
      } %>
      </tbody>
    </table>
  </body>
</html>
