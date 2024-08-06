package com.bjj.rabbitmq_scurity.model.payload.request;

import lombok.Data;

@Data
public class ParticipantRequest {
    private String name;
    private String email;
}
