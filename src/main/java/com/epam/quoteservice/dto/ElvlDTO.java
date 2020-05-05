package com.epam.quoteservice.dto;

import java.util.Objects;

public class ElvlDTO {

    public ElvlDTO() {
    }

    public ElvlDTO(String isin, Float elvl) {
        this.isin = isin;
        this.elvl = elvl;
    }

    private String isin;
    private Float elvl;

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

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
        ElvlDTO elvlDTO = (ElvlDTO) o;
        return Objects.equals(isin, elvlDTO.isin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isin);
    }

    @Override
    public String toString() {
        return "ElvlDTO{" +
                "isin='" + isin + '\'' +
                ", elvl=" + elvl +
                '}';
    }
}
