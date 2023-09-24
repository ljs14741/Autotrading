<%--
  Created by IntelliJ IDEA.
  User: 82107
  Date: 2023-08-21
  Time: 오후 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/style.css">
  </head>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>

  <script type="text/javascript">
    $(document).ready(function (){
      $("#tb1").css('display','none');
      $("#tb2").css('display','none');
    });

    function test(){
      var data = {};
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


  </script>
  <body>
    <h2>백테스팅</h2>
    <form>
      <div>
        <label>입력값1</label>
        <input type="text"></div>
      <div>
        <label>입력값2</label>
        <input type="text"></div>
      <div>
        <label>입력값3</label>
        <input type="text"></div>
    </form>

    <button onclick="test()">백테스팅시작</button>
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
