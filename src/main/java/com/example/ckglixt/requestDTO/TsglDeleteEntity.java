package com.example.ckglixt.requestDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TsglDeleteEntity {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;
}
