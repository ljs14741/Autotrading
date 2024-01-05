package com.bitcoin.autotrading.user.controller;

import com.bitcoin.autotrading.candle.domain.dto.CandleDTO;
import com.bitcoin.autotrading.candle.service.GetCandle;
import com.bitcoin.autotrading.user.service.VolatilityBackTestingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@Tag(name = "예제 API", description = "Swagger 테스트용 API")
public class VolatilityBackTestingController {

    @Autowired
    public GetCandle getCandle;
    @Autowired
    private VolatilityBackTestingService volatilityBackTestingService;
    @RequestMapping("/volatilityBackTestingController.volatilityBackTesting.do")
    @ResponseBody
    @Operation(summary =  "오퍼레이션", security = @SecurityRequirement(name = "bearerAuth"))
    public List<CandleDTO> volatilityBackTesting(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException, InterruptedException {
//        String dttm = "2023-10-04 09:00:00";
//        String unit = "minutes/240";
//        String market = "KRW-XRP";
//        int cnt = 5;
//        getCandle.getCandle(dttm, unit, market, cnt);
        List<CandleDTO> responseParam = volatilityBackTestingService.volatilityBackTesting();
        log.info("111222" + responseParam);
        return responseParam;
    }
}