package org.nicolau.biddingscrapinggov.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@IdClass(BiddingKey.class)
public class Bidding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String institution;
    @Id
    @Column(unique = true)
    private String UASGCode;

    @Id
    @Column(unique = true)
    private String modality;

    @Id
    @Column(unique = true)
    private String number;

    private String object;
    private LocalDate startDate;
    private String startDateDetails;
    private String address;
    private String telephone;
    private String fax;
    private LocalDateTime startDateProposal;
    private String startDateProposalDetails;
    private boolean viewed;
}
