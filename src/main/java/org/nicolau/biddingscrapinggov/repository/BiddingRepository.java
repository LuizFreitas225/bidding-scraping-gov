package org.nicolau.biddingscrapinggov.repository;

import org.nicolau.biddingscrapinggov.model.Bidding;
import org.nicolau.biddingscrapinggov.model.BiddingKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BiddingRepository extends JpaRepository<Bidding, BiddingKey> {

    Optional<Bidding> findById(BiddingKey biddingKey);

    Page<Bidding> findAll(Pageable pageable);

    Bidding findByUASGCodeAndModalityAndNumber(String UASGCode, String modality, String number);
}



