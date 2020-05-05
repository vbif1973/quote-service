package com.epam.quoteservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.Objects;

public class QuoteDTO {

    public QuoteDTO() {
    }

    public QuoteDTO(String isin, Float bid, Float ask) {
        this.isin = isin;
        this.bid = bid;
        this.ask = ask;
    }

    private String isin;
    private Float bid;
    private Float ask;
    @JsonIgnore
    private LocalDateTime creationDate = LocalDateTime.now();;

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public Float getBid() {
        return bid;
    }

    public void setBid(Float bid) {
        this.bid = bid;
    }

    public Float getAsk() {
        return ask;
    }

    public void setAsk(Float ask) {
        this.ask = ask;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuoteDTO quoteDTO = (QuoteDTO) o;
        return Objects.equals(isin, quoteDTO.isin) &&
                Objects.equals(bid, quoteDTO.bid) &&
                Objects.equals(ask, quoteDTO.ask) &&
                Objects.equals(creationDate, quoteDTO.creationDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(isin, bid, ask, creationDate);
    }

    @Override
    public String toString() {
        return "QuoteDTO{" +
                "isin='" + isin + '\'' +
                ", bid=" + bid +
                ", ask=" + ask +
                ", creationDate=" + creationDate +
                '}';
    }
}
