package org.nicolau.biddingscrapinggov.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@IdClass(BiddingKey.class)
public class Bidding {

    @Column(length = 1000)
    private String institution;
    @Id
    private String UASGCode;

    @Id
    private String modality;

    @Id
    private String number;

    @Column(length = 1000)
    private String object;
    private LocalDate startDate;
    private String startDateDetails;
    private String address;
    private String telephone;
    private String fax;
    private LocalDate deliveryDateProposal;
    private String deliveryDateProposalDetails;
    @Column(updatable = false)
    private Boolean viewed = false;
}
