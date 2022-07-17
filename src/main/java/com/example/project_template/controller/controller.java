package com.example.project_template.controller;

import com.kss.autoconfigure.common.ResponseData;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class controller {
    @GetMapping("/test")
    public ResponseData get() {
        try {
            log.info("day la log info");
            log.error("day la log error");
            throw new Exception("abc");
        } catch (Exception ex) {
            log.info(ex);
            log.error(ex);
            log.error("loi o day",ex);
        }
        return new ResponseData();

    }
}
