package org.nicolau.biddingscrapinggov.repository;

import org.nicolau.biddingscrapinggov.model.Bidding;
import org.nicolau.biddingscrapinggov.model.BiddingKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface BiddingRepository extends JpaRepository<Bidding, BiddingKey> {

    Optional<Bidding> findById(BiddingKey biddingKey);

    @Query(value = "SELECT p FROM Bidding p "
            + "WHERE (LOWER(p.address) LIKE %:searchTerm% "
            + "OR LOWER(p.number) LIKE %:searchTerm% "
            + "OR LOWER(p.object) LIKE %:searchTerm% "
            + "OR LOWER(p.institution) LIKE %:searchTerm% "
            + "OR LOWER(p.modality) LIKE %:searchTerm% "
            + "OR LOWER(p.UASGCode) LIKE %:searchTerm% "
            + "OR LOWER(p.telephone) LIKE %:searchTerm%) "
            + "AND (:viewed IS NULL OR p.viewed = :viewed)")

    Page<Bidding> find(String searchTerm, Boolean viewed, Pageable pageable);

    Optional<Bidding> findByUASGCodeAndModalityAndNumber(String UASGCode, String modality, String number);


    @Modifying
    @Query(value = "UPDATE bidding SET viewed = true " +
            "WHERE modality = :modality " +
            "AND uasgcode=  :UASGCode " +
            "AND  number = :number ", nativeQuery = true)
    void viewedCheck( String UASGCode, String modality, String number);
}



