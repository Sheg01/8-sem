package com.test.salon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSubmitDto {

    private String serviceType;
    private String date;
    private String time;
    private String master;

}
