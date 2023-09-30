package com.bitcoin.autotrading.coin.service;

import com.bitcoin.autotrading.coin.domain.CoinPrice;
import com.bitcoin.autotrading.coin.domain.CoinPriceDTO;
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
                .trade_date(coinPriceDTO.getTrade_date())
                .trade_time(coinPriceDTO.getTrade_time())
                .trade_date_kst(coinPriceDTO.getTrade_date_kst())
                .trade_time_kst(coinPriceDTO.getTrade_time_kst())
                .opening_price(coinPriceDTO.getOpening_price())
                .high_price(coinPriceDTO.getHigh_price())
                .low_price(coinPriceDTO.getLow_price())
                .trade_price(coinPriceDTO.getTrade_price())
                .acc_trade_price(coinPriceDTO.getAcc_trade_price())
                .acc_trade_price_24h(coinPriceDTO.getAcc_trade_price_24h())
                .acc_trade_volume(coinPriceDTO.getAcc_trade_volume())
                .acc_trade_volume_24h(coinPriceDTO.getAcc_trade_volume_24h())
                .highest_52_week_price(coinPriceDTO.getHighest_52_week_price())
                .highest_52_week_date(coinPriceDTO.getHighest_52_week_date())
                .lowest_52_week_price(coinPriceDTO.getLowest_52_week_price())
                .lowest_52_week_date(coinPriceDTO.getLowest_52_week_date())
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