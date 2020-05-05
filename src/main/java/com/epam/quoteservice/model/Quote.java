package com.epam.quoteservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "QUOTE")
public class Quote implements Serializable {

    private Long id;
    private String isin;
    private Float bid;
    private Float ask;
    private LocalDateTime creationDate;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="quote_seq")
    @SequenceGenerator(name="quote_seq", sequenceName="quote_seq", allocationSize=1)
//    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "isin", nullable = false)
    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    @Basic
    @Column(name = "bid", nullable = false)
    public Float getBid() {
        return bid;
    }

    public void setBid(Float bid) {
        this.bid = bid;
    }

    @Basic
    @Column(name = "ask", nullable = false)
    public Float getAsk() {
        return ask;
    }

    public void setAsk(Float ask) {
        this.ask = ask;
    }

    @JsonIgnore
    @Basic
    @Column(name = "creationDate", nullable = false)
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
        Quote quote = (Quote) o;
        return  Objects.equals(isin, quote.isin) &&
                Objects.equals(bid, quote.bid) &&
                Objects.equals(ask, quote.ask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isin, bid, ask);
    }


    @Override
    public String toString() {
        return "Quote{" +
                "isin='" + isin + '\'' +
                ", bid=" + bid +
                ", ask=" + ask +
                ", creationDate=" + creationDate +
                '}';
    }
}
