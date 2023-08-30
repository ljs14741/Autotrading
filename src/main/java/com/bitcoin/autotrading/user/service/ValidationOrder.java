package com.bitcoin.autotrading.user.service;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ValidationOrder {

    // 검증해서 매매가능 1, 불가 0 세팅
    public int validation(){

        return 0;
    }

}
