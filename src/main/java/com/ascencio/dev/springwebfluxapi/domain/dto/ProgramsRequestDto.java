package com.ascencio.dev.springwebfluxapi.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProgramsRequestDto implements Serializable {
    private static final long serialVersionUID = 8222253670338491507L;

    private Integer id;
    private String name;
    private String type;
    private String beneficiary;
    private String responsible;
    private String description;
    private String condition;
    private Integer duration;
}
