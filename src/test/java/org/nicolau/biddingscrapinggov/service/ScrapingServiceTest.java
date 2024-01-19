package org.nicolau.biddingscrapinggov.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;



@SpringBootTest
class ScrapingServiceTest {

    @Autowired
    private ScrapingService scrapingService;

    @MockBean
    private BiddingService biddingService;



    @Test
    void runScraping() throws Exception {
        scrapingService.runScraping();
    }
}