package org.nicolau.biddingscrapinggov.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class BiddingKey implements Serializable {

    private String UASGCode;
    private String modality;
    private String number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiddingKey that = (BiddingKey) o;
        return  (Objects.equals(UASGCode, that.UASGCode) &&
                Objects.equals(modality, that.modality) &&
                Objects.equals(number, that.number));
    }

    @Override
    public int hashCode() {
        return Objects.hash( UASGCode, modality, number);
    }
}

