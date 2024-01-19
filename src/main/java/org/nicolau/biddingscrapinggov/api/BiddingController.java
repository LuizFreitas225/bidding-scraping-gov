package org.nicolau.biddingscrapinggov.api;

import lombok.RequiredArgsConstructor;
import org.nicolau.biddingscrapinggov.model.Bidding;
import org.nicolau.biddingscrapinggov.service.BiddingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bidding")
@RequiredArgsConstructor
public class BiddingController {

    private final BiddingService biddingService;

    @GetMapping("search")
    public ResponseEntity<Page<Bidding>> searchBiddings(
            @RequestParam( defaultValue = "") String searchTerm,
            @RequestParam( required = false) Boolean viewed,
            Pageable pageable) {
        Page<Bidding> biddings = biddingService.searchBiddings( searchTerm, viewed,  pageable);
        return ResponseEntity.ok(biddings);
    }

    @GetMapping("by-id")
    public ResponseEntity<Bidding> getBiddingById(
            @RequestParam( required = true) String uasgCode,
            @RequestParam( required = true) String modality,
            @RequestParam( required = true) String number) {
        Bidding bidding = biddingService.getBiddingById(uasgCode, modality, number);
        return ResponseEntity.ok(bidding);
    }

    @PatchMapping("viewed")
    public void updateViewed(
            @RequestParam( required = true) String uasgCode,
            @RequestParam( required = true) String modality,
            @RequestParam( required = true) String number) {
        biddingService.viewedCheck(uasgCode, modality, number);
    }
}
