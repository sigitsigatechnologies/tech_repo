package com.bjj.rabbitmq_scurity.model.payload.response;

import com.bjj.rabbitmq_scurity.model.dto.ParticipantDto;
import com.bjj.rabbitmq_scurity.model.entity.ParticipantEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ParticipantResponse {
    private String name;
    private String email;

    public static ParticipantResponse fromEntity(ParticipantEntity entity) {
        ParticipantResponse response = new ParticipantResponse();
        response.setName(entity.getName());
        response.setEmail(entity.getEmail());
        return response;
    }
}
