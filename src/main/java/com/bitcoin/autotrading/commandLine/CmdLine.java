package com.bitcoin.autotrading.commandLine;

import com.bitcoin.autotrading.commandLine.domain.ProgramLog;
import com.bitcoin.autotrading.commandLine.domain.repository.ProgramLogRepository;
import com.bitcoin.autotrading.commandLine.service.Api;
import com.bitcoin.autotrading.commandLine.service.CandleSearch;
import com.bitcoin.autotrading.commandLine.service.GetOrdersChance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
    private Api api;


    @Autowired
    private CandleSearch candleSearch;

    @Autowired
    private GetOrdersChance getOrdersChance;

    public CmdLine(final ProgramLogRepository programLogRepository){
        this.programLogRepository=programLogRepository;

    }
 
    /*
    java 빌드 구성에서 프로그램 인수 출력
    */
    @Override
    public void run(final ApplicationArguments args) throws IOException, InterruptedException, NoSuchAlgorithmException {
        for(int i=0; i<args.getSourceArgs().length; i++){
            log.info("input=["+args.getSourceArgs()[i]+"]");
            programLogRepository.save( ProgramLog.builder()
                            .id(1)
                            .programid("스프링부트실행")
                            .argument1(args.getSourceArgs()[i])
                            .build()
            );
            api.main();
            candleSearch.main();
            getOrdersChance.main();

        }


    }

}
