//package com.bitcoin.autotrading.commandLine.service;
//
//public class GetRsiByMinutes {
//    public double GetRsiByMinutes() {
//        final int minutes = 30;
//        final String market = "KRW-BTC";
//        final int maxCount = 200;
//        // 캔들 API 호출 (Docs: https://docs.upbit.com/reference/%EB%B6%84minute-%EC%BA%94%EB%93%A4-1)
//        List<MinuteCandleRes> candleResList = marketPriceReaderService.getCandleMinutes(minutes, market, maxCount);
//        if (CollectionUtils.isEmpty(candleResList)) {
//            return null;
//        }
//    }
//}
