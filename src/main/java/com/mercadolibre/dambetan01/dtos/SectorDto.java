package com.mercadolibre.dambetan01.dtos;

import lombok.Data;

public class SectorDto {
    private Long sectionCode;
    private Long warehouseCode;

    public SectorDto() {
    }

    public SectorDto(Long sectionCode, Long warehouseCode) {
        this.sectionCode = sectionCode;
        this.warehouseCode = warehouseCode;
    }

    public Long getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(Long sectionCode) {
        this.sectionCode = sectionCode;
    }

    public Long getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(Long warehouseCode) {
        this.warehouseCode = warehouseCode;
    }
}
