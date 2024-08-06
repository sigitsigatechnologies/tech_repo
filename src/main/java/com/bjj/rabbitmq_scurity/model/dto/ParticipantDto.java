package com.bjj.rabbitmq_scurity.model.dto;

import jakarta.persistence.Column;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class ParticipantDto {
    private String id;
    private String name;
    private String email;

    public ParticipantDto(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
