package com.company.shipapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShipDTO {

    private String type;
    private Integer mass;
    private Integer crew;
}
