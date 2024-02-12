package com.bitcoin.autotrading.user.service;

import com.bitcoin.autotrading.candle.domain.dto.CandleDTO;
import com.bitcoin.autotrading.candle.domain.entity.Candle;
import com.bitcoin.autotrading.candle.domain.entity.Rsi;
import com.bitcoin.autotrading.candle.domain.repository.CandleRepository;
import com.bitcoin.autotrading.candle.domain.repository.CandleRsiRepository;
import com.bitcoin.autotrading.candle.domain.repository.RsiRepository;
import com.bitcoin.autotrading.candle.service.GetRsi;
import com.bitcoin.autotrading.common.JsonTransfer;
import com.bitcoin.autotrading.common.RequestUpbit;
import com.bitcoin.autotrading.user.domain.entity.UserCondition;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.lang.Math.round;

@Service
@Slf4j
public class JinsuBackTestingService {
    @Autowired
    private MapperService mapperService;
    @Autowired
    private RequestUpbit requestUpbit;

    @Autowired
    GetRsi getRsi;
    private final CandleRepository candleRepository;
    private final RsiRepository rsiRepository;

    private final CandleRsiRepository candleRsiRepository;

    private String currentDateString;


    private Double firstTradePrice; // 매수한날 종가
    private Double nowTradePrice; // 현재가
    private Double earnings; // 수익률
    private String chk = "0";

    public JinsuBackTestingService(CandleRepository candleRepository, RsiRepository rsiRepository, CandleRsiRepository candleRsiRepository) {
        this.candleRepository = candleRepository;
        this.rsiRepository = rsiRepository;
        this.candleRsiRepository = candleRsiRepository;
    }

    public List<CandleDTO> JinsuBackTesting(UserCondition userCondition) throws JSONException, IOException, ParseException {
        // 0. 파라미터 받기
        this.currentDateString = userCondition.getSrtDttm().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        LocalDateTime curruntDate = userCondition.getSrtDttm();
        log.info("curruntDate : " + curruntDate);


        // 1. 코인 정보
        String url = "https://api.upbit.com/v1/candles/days?market=" + userCondition.getMarket() + "&to="+ currentDateString + "&count=10";
        String data = requestUpbit.request(url);
        JSONArray jsonArray = new JSONArray(data);
        List<CandleDTO> list = JsonTransfer.getListObjectFromJSONObject(jsonArray, new TypeReference<CandleDTO>() {
        });

        list.forEach(item -> {
            coinVolatilityInsert(item);
        });

        //curruntDate : 2023-12-18T22:26
        // 2. rsi 저장
        log.info("candleDTO list : " + list);
        for(int i=0; i<list.size(); i++) {
            log.info("ㅋ_ㅋ : " + list.get(i).getCandleDateTimeKst());
            this.currentDateString = list.get(i).getCandleDateTimeKst();
            double rsidata = getRsi.getRsi(currentDateString,"days",userCondition.getMarket());
            log.info("rsidata : " + rsidata);

            Rsi rsi = Rsi.builder()
                    .rsiValue(rsidata)
                    .candleDateTimeKst(currentDateString)
                    .build();
            rsiRepository.save(rsi);
        }

        // 3. 1번 2번 조인 및 매수시점, 수익률계산
        // 3-1 조인
        List<CandleDTO> qwer = GetJoinResults();
        log.info("하하하" + qwer);

        // 3-2 매수시점 및 수익률계산
        for(int i=qwer.size()-1; i > -1; i--) {
            log.info("테스트 i값 : " + i);
            log.info("테스트rsi값 : " + qwer.get(i).getRsiValue());

            if(qwer.get(i).getRsiValue() < 30 && !this.chk.equals("1")) {
                qwer.get(i).setBuyDay("○");
                this.firstTradePrice = qwer.get(i).getTradePrice();
                log.info("firstTradePrice : " + this.firstTradePrice);
                this.chk = "1";
            }

            if(this.chk.equals("1")) {
                this.nowTradePrice = qwer.get(i).getTradePrice();
                log.info("nowTradePrice : " + nowTradePrice);
                this.earnings = Math.round(this.nowTradePrice/this.firstTradePrice*100) / 100.0;
                log.info("earnings : " + earnings);
                log.info("earningsround : " + Math.round(earnings));
                qwer.get(i).setEarnings(earnings);
            }
        }

        // 4. 화면 반환
//        List<CandleDTO> can = mapperService.mapAll(candleRepository.selectSQL(),CandleDTO.class);
//        log.info("진백 can: " + can);
        return qwer;
    }
    @Transactional
    public List<CandleDTO> GetJoinResults(){
        log.info("GetJoinResults");

        return mapperService.mapAll(candleRsiRepository.findByCandleRsi(),CandleDTO.class);
    }

    @Transactional

    public void coinVolatilityInsert(CandleDTO candleDTO) {
        log.info("insertinsertinsert: " + candleDTO);

        Candle candle = Candle.builder()
                .market(candleDTO.getMarket())
                .candleDateTimeKst(candleDTO.getCandleDateTimeKst())
                .openingPrice(candleDTO.getOpeningPrice())
                .highPrice(candleDTO.getHighPrice())
                .lowPrice(candleDTO.getLowPrice())
                .tradePrice(candleDTO.getTradePrice())
                .build();
        candleRepository.save(candle);
    }
}
