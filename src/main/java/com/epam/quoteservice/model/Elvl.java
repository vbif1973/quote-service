package com.epam.quoteservice.model;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ELVL")
public class Elvl implements Serializable {

    private String isin;
    private Float elvl;

    @Id
    @Column(name = "isin", nullable = false)
    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    @Basic
    @Column(name = "elvl", nullable = false)
    public Float getElvl() {
        return elvl;
    }

    public void setElvl(Float elvl) {
        this.elvl = elvl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elvl elvl = (Elvl) o;
        return Objects.equals(isin, elvl.isin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isin);
    }

    @Override
    public String toString() {
        return "Elvl{" +
                "isin='" + isin + '\'' +
                ", elvl=" + elvl +
                '}';
    }
}
