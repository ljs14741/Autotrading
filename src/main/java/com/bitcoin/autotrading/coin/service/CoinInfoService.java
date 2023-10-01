package com.bitcoin.autotrading.coin.service;

import com.bitcoin.autotrading.coin.domain.entity.CoinPrice;
import com.bitcoin.autotrading.coin.domain.dto.CoinPriceDTO;
import com.bitcoin.autotrading.coin.repository.CoinPriceRepository;
import com.bitcoin.autotrading.common.JsonTransfer;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class CoinInfoService {
    @Autowired
    CoinKindService coinKindService;
    private final CoinPriceRepository coinPriceRepository;
    private String coinKind;
    public CoinInfoService(CoinPriceRepository coinPriceRepository) {
        this.coinPriceRepository = coinPriceRepository;
    }

    
    // 여기서 트랜잭션 잡으면 실패가 나오면 롤백
    public List<CoinPriceDTO> coinInfoSave() throws IOException, JSONException {
        this. coinKind = coinKindService.coinKind();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.upbit.com/v1/ticker?markets" + coinKind)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        String data = response.body().string();

        JSONArray jsonArray = new JSONArray(data);
        List<CoinPriceDTO> list = JsonTransfer.getListObjectFromJSONObject(jsonArray, new TypeReference<CoinPriceDTO>() {});

        list.forEach(item -> {
            coinPriceInsert(item);
        });

        log.info("Service의 list: " + list);

        return list;
    }
    // 여기서 트랜잭션 잡으면 실패가 나오면 실패전까지 들어감
    @Transactional
    public void coinPriceInsert(CoinPriceDTO coinPriceDTO) {
        log.info("insertinsertinsert: " + coinPriceDTO);

        CoinPrice coinPrice = CoinPrice.builder()
                .market(coinPriceDTO.getMarket())
                .tradeDate(coinPriceDTO.getTradeDate())
                .tradeTime(coinPriceDTO.getTradeTime())
                .tradeDateKst(coinPriceDTO.getTradeDateKst())
                .tradeTimeKst(coinPriceDTO.getTradeTimeKst())
                .openingPrice(coinPriceDTO.getOpeningPrice())
                .highPrice(coinPriceDTO.getHighPrice())
                .lowPrice(coinPriceDTO.getLowPrice())
                .tradePrice(coinPriceDTO.getTradePrice())
                .accTradePrice(coinPriceDTO.getAccTradePrice())
                .accTradePrice24h(coinPriceDTO.getAccTradePrice24h())
                .accTradeVolume(coinPriceDTO.getAccTradeVolume())
                .accTradeVolume24h(coinPriceDTO.getAccTradeVolume24h())
                .highest52WeekPrice(coinPriceDTO.getHighest52WeekPrice())
                .highest52WeekDate(coinPriceDTO.getHighest52WeekDate())
                .lowest52WeekPrice(coinPriceDTO.getLowest52WeekPrice())
                .lowest52WeekDate(coinPriceDTO.getLowest52WeekDate())
                .timestamp(coinPriceDTO.getTimestamp())
                .change(coinPriceDTO.getChange())
                .build();
        coinPriceRepository.save(coinPrice);
    }

    public List<CoinPrice> coinPriceAllSelect() {
        return coinPriceRepository.findAll();
    }

    public List<CoinPrice> coinPriceSelect(String market) {
        return coinPriceRepository.findAllByMarket(market);
    }
}