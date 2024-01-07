package com.bitcoin.autotrading.commandLine;

import com.bitcoin.autotrading.commandLine.domain.ProgramLog;
import com.bitcoin.autotrading.commandLine.domain.repository.ProgramLogRepository;
import com.bitcoin.autotrading.account.service.AccountInfoService;
import com.bitcoin.autotrading.candle.service.GetCandle;
import com.bitcoin.autotrading.order.service.GetOrdersChance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.json.JSONException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Order(1)
@Component
@Slf4j
public class CmdLine implements ApplicationRunner {
    private final ProgramLogRepository programLogRepository;

    @Autowired
    private AccountInfoService api;


    @Autowired
    private GetCandle candleSearch;

    @Autowired
    private GetOrdersChance getOrdersChance;


    public CmdLine(final ProgramLogRepository programLogRepository){
        this.programLogRepository=programLogRepository;

    }
 
    /*
    java 빌드 구성에서 프로그램 인수 출력
    */
    @Override
    public void run(final ApplicationArguments args) throws IOException, InterruptedException, NoSuchAlgorithmException, JSONException {
        for(int i=0; i<args.getSourceArgs().length; i++){
            log.info("input=["+args.getSourceArgs()[i]+"]");
            programLogRepository.save( ProgramLog.builder()
                            .id(1)
                            .programid("스프링부트실행")
                            .argument1(args.getSourceArgs()[i])
                            .build()
            );

//            log.info("startController.index()");
            //api.main();
//            candleSearch.main();
//            getOrdersChance.main();
//            getRsiByMinutes.main();


        }


    }

}
