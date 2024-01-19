package org.nicolau.biddingscrapinggov.api;

import lombok.RequiredArgsConstructor;
import org.nicolau.biddingscrapinggov.model.Bidding;
import org.nicolau.biddingscrapinggov.service.BiddingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bidding")
@RequiredArgsConstructor
public class BiddingController {

    private final BiddingService biddingService;

    @GetMapping
    public ResponseEntity<Page<Bidding>> searchBiddings(Pageable pageable) {
        Page<Bidding> biddings = biddingService.searchBiddings(pageable);
        return ResponseEntity.ok(biddings);
    }

    @GetMapping("/{uasgCode}/{modality}/{number}")
    public ResponseEntity<Bidding> getBiddingById(
            @PathVariable String uasgCode,
            @PathVariable String modality,
            @PathVariable String number) {
        Bidding bidding = biddingService.getBiddingById(uasgCode, modality, number);
        return ResponseEntity.ok(bidding);
    }
}
