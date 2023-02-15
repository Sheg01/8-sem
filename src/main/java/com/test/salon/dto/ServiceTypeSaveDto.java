package com.test.salon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceTypeSaveDto {

    private String name;
    private Integer price;
    private String serviceGroup;
    private String description;
    private Integer rang;
}
