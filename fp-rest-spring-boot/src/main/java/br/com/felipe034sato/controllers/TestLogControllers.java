package br.com.felipe034sato.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/v1")
public class TestLogControllers {

    private Logger logger = LoggerFactory.getLogger(TestLogControllers.class.getName());

    @GetMapping("/test")
    public String testLog(){
        logger.debug("This is a debug log");
        logger.info("This is an info log");
        logger.warn("This is a warn log");
        logger.error("This is an error log");
        return "Logs generated successfully";
    }


}
