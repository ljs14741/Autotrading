<%--
  Created by IntelliJ IDEA.
  User: 82107
  Date: 2023-08-21
  Time: 오후 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
  System.out.print("JSPJSP123123");
%>
<html>
  <head>
    <title>Main</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
  </head>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

  <script type="text/javascript">
    $(document).ready(function (){
      $("#tb1").css('display','none');
      $("#tb2").css('display','none');

    });

    function test(){
      var data = {buy_cnt:"3"};
      data.test1 = "test";
      var jsonStr = JSON.stringify(data) //입력 파라미터
      $.ajax({
        type:"POST",
        url:"/testFromToTrading/trading",
        data:jsonStr,
        contentType:"application/json",
        error:onError,
        success:onSuccess
      });
    }

    function onSuccess(data){

      $("#tb1").DataTable().destroy();
      $("#tb2").DataTable().destroy();


      $("#tb1").dataTable({
        // 표시 건수기능 숨기기
        lengthChange: false,
        // 검색 기능 숨기기
        searching: false,
        // 정보 표시 숨기기
        info: false,
        // 페이징 기능 숨기기
        paging: false,
        data: data.orderList,
        columns:[
          {data:'uuid'},
          {data:'market'},
          {data:'created_at'},
          {data:'side'},
          {data:'price'},
          {data:'volume'},
          {data:'portfolio'}
        ]
      });

      $("#tb2").dataTable({
        // 표시 건수기능 숨기기
        lengthChange: false,
        // 검색 기능 숨기기
        searching: false,
        // 정보 표시 숨기기
        info: false,
        // 페이징 기능 숨기기
        paging: false,
        data: data.candleList,
        columns:[
          {data:'market'},
          {data:'candle_date_time_utc'},
          {data:'candle_date_time_kst'},
          {data:'opening_price'},
          {data:'high_price'},
          {data:'low_price'},
          {data:'trade_price'},
          {data:'timestamp'},
          {data:'candle_acc_trade_price'},
          {data:'candle_acc_trade_volume'},
          {data:'rsi'}
        ]
      });


      $("#tb1").css('display','inline');
      $("#tb2").css('display','inline');



    }
    function onError(e){
      console.log("error : "+e);
    }

    function changeValue() {
      // alert("value " + value_str.options[value_str.selectedIndex].value +
      // "text " + value_str.options[value_str.selectedIndex].text)
      var valueStr = document.getElementById('coin');
      var requestParam = valueStr.options[valueStr.selectedIndex].text; //선택밸류값은 value_str.options[value_str.selectedIndex].value
      $.ajax({
        type: "POST",
        url: "/coinInfoController.coinPriceSelect.do",
        data: {"requestParam": requestParam},
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        error: onError,
        success: onSuccess1
      });

      function onSuccess1(data) {
        alert("111")
        $("#tb3").dataTable({
          lengthChange: false,
          searching: false, // 검색 기능 숨기기
          info: false, // 정보 표시 숨기기
          paging: false, // 페이징 기능 숨기기
          destroy: true, //테이블 초기화
          data: data,
          columns: [
            {data: 'market'},
            {data: 'opening_price'},
            {data: 'high_price'},
            {data: 'low_price'},
            {data: 'trade_price'}
          ]
        });
      }
    }

    $( function() {
      $( ".controlgroup" ).controlgroup()
    } );
  </script>
  <body>
  <h2>종목선택</h2>
    <tr>
      <th>코인</th>
      <td>
        <select id="coin" name="coin" onchange="changeValue()" size="1">
          <c:forEach var="list" items="${list}">
            <option value = "1">${list.market}</option>
          </c:forEach>
        </select>
      </td>
    </tr>

  <table id="tb3">
    <thead>
      <tr>
        <th>코인</th>
        <th>시가</th>
        <th>고가</th>
        <th>저가</th>
        <th>종가</th>
      </tr>
    </thead>
  </table>
  <h2>매수 수량 설정</h2>
  <table>
    <tr>
      <th>사용가능금액</th>
<c:forEach var="accountList" items="${accountList}">
      <td>${accountList.balance}</td>
</c:forEach>
    </tr>
    <tr>
      <th>자본금설정</th>
      <td class="controlgroup"><input id="horizontal-spinner" class="ui-spinner-input"></td>
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
  <button onclick="test()">백테스팅 시작</button>
  <button>자동거래 시작</button>
  <table id="tb1">
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
    </tbody>
  </table>
  <table id="tb2">
    <thead>
    <tr>
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
      <th>rsi</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
  </table>
  </body>
</html>
