package com.ascencio.dev.springwebfluxapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ProgramsResponseDto implements Serializable {
   private static final long serialVersionUID = 8735757125749188522L;

   private Integer id;
   private String name;
   private String type;
   private String beneficiary;
   private String responsible;
   private String description;
   private Integer duration;
   private String condition;

   public ProgramsResponseDto() {

   }
}
