package com.crio.starter.exchange;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class MemeRequestDTO {

    private String id;
    
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "url is mandatory")
    private String url;

    @NotBlank(message = "caption is mandatory")
    private String caption;
}
