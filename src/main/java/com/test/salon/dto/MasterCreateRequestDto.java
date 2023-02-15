package com.test.salon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterCreateRequestDto {

    private String name;
    private String surname;
    private Integer rang;
}
