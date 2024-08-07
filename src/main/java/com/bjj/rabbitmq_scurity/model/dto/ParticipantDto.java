package com.bjj.rabbitmq_scurity.model.dto;

import lombok.Data;

@Data
public class ParticipantDto {
    private Long id;
    private String name;
    private String email;

    public ParticipantDto(Long id,String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
