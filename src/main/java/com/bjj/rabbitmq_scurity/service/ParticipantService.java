package com.bjj.rabbitmq_scurity.service;

import com.bjj.rabbitmq_scurity.model.entity.ParticipantEntity;
import com.bjj.rabbitmq_scurity.model.payload.response.ParticipantResponse;
import org.springframework.data.domain.Page;

public interface ParticipantService {
    Page<ParticipantResponse> getAllParticipant(int pageNo, int pageSize, String sortBy, String sortDir);

    ParticipantEntity createParticipant(ParticipantEntity participantEntity);

    ParticipantEntity updateParticipant(long id, ParticipantEntity participantEntity);

    void deleteParticipant(long id);

    ParticipantEntity getParticipantById(long id);
}
