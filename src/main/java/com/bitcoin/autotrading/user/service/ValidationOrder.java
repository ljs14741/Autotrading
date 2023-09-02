package com.bitcoin.autotrading.user.service;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ValidationOrder {

    // 검증해서 매매가능 1, 불가 0 세팅
    // 일단 RSI > 40?
    public int validation(){

        //query or 직접 RSI 계산한 값 가져와서
        //40 초과일시 1세팅

        return 0;
    }

}
