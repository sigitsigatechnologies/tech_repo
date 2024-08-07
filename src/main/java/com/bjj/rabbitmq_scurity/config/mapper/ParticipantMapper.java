package com.bjj.rabbitmq_scurity.config.mapper;

import com.bjj.rabbitmq_scurity.model.dto.ParticipantDto;
import com.bjj.rabbitmq_scurity.model.entity.ParticipantEntity;

public class ParticipantMapper {

    // Convert User JPA Entity into UserDto
    public static ParticipantDto mapToParticipantDto(ParticipantEntity participant){
        ParticipantDto dto = new ParticipantDto(
                participant.getId(),
                participant.getName(),
                participant.getEmail()
        );
        return dto;
    }

    // Convert UserDto into User JPA Entity
    public static ParticipantEntity mapToParticipant(ParticipantDto participantDto){
        ParticipantEntity participant = new ParticipantEntity(
                participantDto.getId(),
                participantDto.getName(),
                participantDto.getEmail()
        );
        return participant;
    }
}
