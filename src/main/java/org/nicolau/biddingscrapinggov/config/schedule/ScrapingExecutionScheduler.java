package org.nicolau.biddingscrapinggov.config.schedule;


import lombok.RequiredArgsConstructor;
import org.nicolau.biddingscrapinggov.service.ScrapingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScrapingExecutionScheduler {

    private final ScrapingService scrapingService;

    @Scheduled(fixedRate = 86400000) // Executa a cada 1 dia
    public void scheduleRunScraping() throws Exception {
        scrapingService.runScraping();
    }
}
