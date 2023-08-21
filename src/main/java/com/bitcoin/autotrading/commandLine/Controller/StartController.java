package com.bitcoin.autotrading.commandLine.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StartController {
    @RequestMapping("/")
    public static String index() {
        log.info("index탔어");
        return "index";
    }
}
