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
      $("#tb4").css('display','none');
      $("#tb5").css('display','none');
    });

    function test(){
      var data = {
                    buy_cnt:$("#buy_cnt").val(),
                    sell_cnt:$("#sell_cnt").val(),
                    deposit:$("#deposit").text(),
                    market:$("select[name=market] option:selected").text(),
                    sell_condition:$("#sell_condition").val(),
                    buy_condition:$("#buy_condition").val(),
                    srt_dttm:$("#srt_dttm").val(),
                    end_dttm:$("#end_dttm").val(),
                    unit:$("select[name=unit] option:selected").val(),
                    unit_val:$("#unit_val").val()
                 };
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
      //$("#tb2").DataTable().destroy();


      $("#tb1").dataTable({
        // 표시 건수기능 숨기기
        lengthChange: false,
        // 검색 기능 숨기기
        searching: false,
        // 정보 표시 숨기기
        info: false,
        // 페이징 기능 숨기기
        paging: false,
        data: data.order_list,
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

      /*
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
      */

      $("#tb1").css('display','inline');
      //$("#tb2").css('display','inline');


    }
    function onError(e){
      console.log("error : "+e);
    }

    function changeValue() {
      // alert("value " + value_str.options[value_str.selectedIndex].value +
      // "text " + value_str.options[value_str.selectedIndex].text)
      var valueStr = document.getElementById('market');
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
        console.log("data:: " + data)
        console.log("data.market:: " + market);
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

    function volatilityBackTesting() {
      var requestParam = "123";
      $.ajax({
        type: "POST",
        url: "/volatilityBackTestingController.volatilityBackTesting.do",
        data: {"requestParam": requestParam},
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        error: onError,
        success: onSuccess4
      });
      function onSuccess4(data) {
        alert("onSuccess4")
        $("#tb4").dataTable({
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
            {data: 'trade_price'},
            {data: 'earnings'}
          ]
        });
        $("#tb4").css('display','inline');
      }
    }

    function jinsuBackTesting() {
      var data = {
        market:$("select[name=market] option:selected").text(),
        srt_dttm:$("#srt_dttm").val(),
        end_dttm:$("#end_dttm").val()
      };
      var jsonStr = JSON.stringify(data); //입력 파라미터
      $.ajax({
        type: "POST",
        url: "/jinsuBackTestingController.jinsuBackTesting.do",
        data: jsonStr,
        contentType: "application/json",
        error: onError,
        success: onSuccess5
      });
      function onSuccess5(data) {
        alert("onSuccess5")
        $("#tb5").dataTable({
          lengthChange: false,
          searching: false, // 검색 기능 숨기기
          info: false, // 정보 표시 숨기기
          paging: false, // 페이징 기능 숨기기

          destroy: true, //테이블 초기화
          data: data,
          columns: [
            {data: 'candle_date_time_kst'},
            {data: 'market'},
            {data: 'opening_price'},
            {data: 'high_price'},
            {data: 'low_price'},
            {data: 'trade_price'},
            {data: 'earnings'}
          ]
        });
        $("#tb5").css('display','inline');
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
        <select id="market" name="market" onchange="changeValue()" size="1">
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
        <th>range</th>
      </tr>
    </thead>
  </table>
  <h2>매수 수량 설정</h2>
  <table>
    <tr>
      <th>사용가능금액</th>
      <td id="balance">${balance}</td>
    </tr>
    <tr>
      <th>매수금액설정(%)</th>
      <td class="controlgroup"><input id="buyPercent" class="ui-spinner-input"></td>
    </tr>
    <tr>
      <th>예수금(매수할 금액)</th>
      <td id="deposit">값</td>
    </tr>
    <tr>
      <th>분할매수횟수</th>
      <td class="controlgroup"><input id="buy_cnt" class="ui-spinner-input"></td>
    </tr>
    <tr>
      <th>분할매도횟수</th>
      <td class="controlgroup"><input id="sell_cnt" class="ui-spinner-input"></td>
    </tr>
  </table>

  <h2>매수 매도 조건</h2>
  <table>
    <tr>
      <th>지표</th>
      <td>RSI</td>
    </tr>
    <tr>
      <th>단위</th>
      <td class="controlgroup">
        <select id="unit" name="unit" onchange="changeValue()" size="1">
          <option value = "MIN">분</option>
          <option value = "DAY">일</option>
          <option value = "MON">월</option>
        </select>
      </td>
    </tr>
    <tr>
      <th>단위(값)</th>
      <td class="controlgroup"><input id="unit_val" class="ui-spinner-input"></td>
    </tr>
    <tr>
      <th>매수조건(입력값 이하)</th>
      <td class="controlgroup"><input id="buy_condition" class="ui-spinner-input"></td>
    </tr>
    <tr>
      <th>매도조건(입력값 이상)</th>
      <td class="controlgroup"><input id="sell_condition" class="ui-spinner-input"></td>
    </tr>
  </table>

  <h2>백테스팅 기간 설정</h2>
  <table>
    <tr>
      <th>시작</th>
      <td class="controlgroup"><input type="datetime-local" id="srt_dttm"></td>
    </tr>
    <tr>
      <th>종료</th>
      <td class="controlgroup"><input type="datetime-local" id="end_dttm"></td>
    </tr>
  </table>

  <button onclick="test()">백테스팅 시작</button>
  <button>자동거래 시작</button>
  <button onclick="volatilityBackTesting()">변동성돌파 백테스팅</button>
  <button onclick="jinsuBackTesting()">진수의 백테스팅</button>
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

  <table id="tb4">
    <thead>
      <tr>
        <th>코인</th>
        <th>시가</th>
        <th>고가</th>
        <th>저가</th>
        <th>종가</th>
        <th>수익률</th>
      </tr>
    </thead>
  </table>

  <table id="tb5">
    <thead>
    <tr>
      <th>일자</th>
      <th>코인</th>
      <th>시가</th>
      <th>고가</th>
      <th>저가</th>
      <th>종가</th>
      <th>수익률</th>
    </tr>
    </thead>
  </table>
  <script type="text/javascript">
    var btn = document.getElementById("buyPercent");
    btn.addEventListener("blur", () => {
      var deposit = document.getElementById("deposit");
      var balance = document.getElementById("balance").innerText
      var buyPercent = document.getElementById("buyPercent").value;
      deposit.innerText = Math.floor(balance*buyPercent/100);
    });

  </script>
  </body>
</html>
