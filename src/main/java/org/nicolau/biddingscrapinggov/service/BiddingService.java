package org.nicolau.biddingscrapinggov.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nicolau.biddingscrapinggov.config.exception.exception.NotFoundException;
import org.nicolau.biddingscrapinggov.model.Bidding;
import org.nicolau.biddingscrapinggov.repository.BiddingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BiddingService {
    private final BiddingRepository biddingRepository;

    @Transactional
    public void saveOrUpdate(List<Bidding> list){
        List<Bidding> resultList = list.isEmpty() ? list : biddingRepository.saveAll(list) ;
        log.info(resultList.toString());
    }

    public Page<Bidding> searchBiddings (String searchTerm, Boolean viewed ,Pageable pageable) {
        return biddingRepository.find(searchTerm.toLowerCase(), viewed,pageable);
    }

    public Bidding getBiddingById(String uasgCode, String modality, String number) {
        return biddingRepository.findByUASGCodeAndModalityAndNumber(uasgCode, modality, number)
                .orElseThrow(NotFoundException::new);
    }
    @Transactional
    public void viewedCheck(String uasgCode, String modality, String number) {
        Bidding bidding = biddingRepository.findByUASGCodeAndModalityAndNumber(uasgCode, modality, number)
                .orElseThrow(NotFoundException::new);
        biddingRepository.viewedCheck(uasgCode,modality,number);
    }


}
