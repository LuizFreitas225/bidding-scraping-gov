package org.nicolau.biddingscrapinggov.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ScrapingService {

    public void  runScraping() throws IOException {
        Document doc = Jsoup.connect("<https://exemplo.com>").get();
    }


}
