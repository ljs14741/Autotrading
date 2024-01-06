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
    public List<CoinPriceDTO> coinInfoSave() throws Exception {
//        this.coinKind = coinKindService.coinKind();
        String coinKind = coinKindService.coinKind();
        log.info("coinKind: " + coinKind);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.upbit.com/v1/ticker?markets" + coinKind) // 이거하면 새로고침 2번 시 오류 // 업비트 api에러인듯 해결방법 모르겠음
//                .url("https://api.upbit.com/v1/ticker?markets=KRW-BTC,KRW-ETH,KRW-NEO,KRW-MTL,KRW-XRP,KRW-ETC,KRW-SNT,KRW-WAVES,KRW-XEM,KRW-QTUM,KRW-LSK,KRW-STEEM,KRW-XLM,KRW-ARDR,KRW-ARK,KRW-STORJ,KRW-GRS,KRW-ADA,KRW-SBD,KRW-POWR,KRW-BTG,KRW-ICX,KRW-EOS,KRW-TRX,KRW-SC,KRW-ONT,KRW-ZIL,KRW-POLYX,KRW-ZRX,KRW-LOOM,KRW-BCH,KRW-BAT,KRW-IOST,KRW-CVC,KRW-IQ,KRW-IOTA,KRW-HIFI,KRW-ONG,KRW-GAS,KRW-UPP,KRW-ELF,KRW-KNC,KRW-BSV,KRW-THETA,KRW-QKC,KRW-BTT,KRW-MOC,KRW-ENJ,KRW-TFUEL,KRW-MANA,KRW-ANKR,KRW-AERGO,KRW-ATOM,KRW-TT,KRW-CRE,KRW-MBL,KRW-WAXP,KRW-HBAR,KRW-MED,KRW-MLK,KRW-STPT,KRW-ORBS,KRW-VET,KRW-CHZ,KRW-STMX,KRW-DKA,KRW-HIVE,KRW-KAVA,KRW-AHT,KRW-LINK,KRW-XTZ,KRW-BORA,KRW-JST,KRW-CRO,KRW-TON,KRW-SXP,KRW-HUNT,KRW-PLA,KRW-DOT,KRW-MVL,KRW-STRAX,KRW-AQT,KRW-GLM,KRW-SSX,KRW-META,KRW-FCT2,KRW-CBK,KRW-SAND,KRW-HPO,KRW-DOGE,KRW-STRK,KRW-PUNDIX,KRW-FLOW,KRW-AXS,KRW-STX,KRW-XEC,KRW-SOL,KRW-MATIC,KRW-AAVE,KRW-1INCH,KRW-ALGO,KRW-NEAR,KRW-AVAX,KRW-T,KRW-CELO,KRW-GMT,KRW-APT,KRW-SHIB,KRW-MASK,KRW-ARB,KRW-EGLD,KRW-SUI,KRW-GRT,KRW-BLUR,KRW-IMX,KRW-SEI")
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