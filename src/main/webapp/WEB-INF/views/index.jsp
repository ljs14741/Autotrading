<%--
  Created by IntelliJ IDEA.
  User: 82107
  Date: 2023-08-21
  Time: 오후 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Main</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/style.css">
  </head>
  <body>
  <h2>종목선택</h2>
  <table>
    <tr>
      <th>코인</th>
      <td>
        <select id="coin" name="coin" size="1">
          <c:forEach var="list" items="${list}">
            <option value = "1">${list.market}</option>
          </c:forEach>
        </select>
      </td>
    </tr>
    <tr>
      <th>시장가</th>
      <td>값</td>
    </tr>
    <tr>
      <th>평균가</th>
      <td>값</td>
    </tr>
    <tr>
      <th>고점가(일)</th>
      <td>값</td>
    </tr>
    <tr>
      <th>저점가(일)</th>
      <td>값</td>
    </tr>
  </table>
  <h2>매수 수량 설정</h2>
  <table>
    <tr>
      <th>사용가능금액</th>
      <td>값</td>
    </tr>
    <tr>
      <th>자본금설정</th>
      <td>값</td>
    </tr>
    <tr>
      <th>예상 매수 가능 금액</th>
      <td>값</td>
    </tr>
    <tr>
      <th>예상 매수 가능 수량</th>
      <td>값</td>
    </tr>
    <tr>
      <th>저점가(일)</th>
      <td>값</td>
    </tr>
  </table>
  <h2>차트 분석 전략</h2>
  <table>
    <tr>
      <th>지표</th>
      <td>콤보박스</td>
    </tr>
    <tr>
      <th>매수조건</th>
      <td>값</td>
    </tr>
    <tr>
      <th>매도조건</th>
      <td>rsi값: ${rsi}</td>
    </tr>
  </table>
  <button>백테스팅 시작</button>
  <button>자동거래 시작</button>
  </body>
</html>
