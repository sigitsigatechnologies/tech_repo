package com.bjj.rabbitmq_scurity.service;

import com.bjj.rabbitmq_scurity.model.dto.ParticipantDto;
import com.bjj.rabbitmq_scurity.model.entity.ParticipantEntity;
import com.bjj.rabbitmq_scurity.model.payload.response.CollectionPageableResponse;
import org.springframework.data.domain.Page;

public interface ParticipantService {
    CollectionPageableResponse getAllParticipant(int pageNo, int pageSize, String sortBy, String sortDir);

    ParticipantDto createParticipant(ParticipantDto participant);

    ParticipantDto updateParticipant(long id, ParticipantDto participantDto);

    void deleteParticipant(long id);

    ParticipantEntity getParticipantById(long id);
}
