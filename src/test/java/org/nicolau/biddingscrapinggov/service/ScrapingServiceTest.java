package org.nicolau.biddingscrapinggov.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ScrapingServiceTest {

    @Autowired
    private ScrapingService scrapingService;
    @Test
    void runScraping() throws IOException {
        scrapingService.runScraping();
    }
}