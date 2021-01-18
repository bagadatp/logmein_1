package org.logmein.interview.shoppinglist.controller;

import org.logmein.interview.shoppinglist.service.ShopListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/alive")
    public ResponseEntity isAlive() {
        logger.debug("isAlive(): {}", "OK");
        return ShopListResponse.createNilResponseOk();
    }
}
