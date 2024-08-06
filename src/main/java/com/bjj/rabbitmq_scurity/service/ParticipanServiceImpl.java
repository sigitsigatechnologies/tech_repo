package com.bjj.rabbitmq_scurity.service;

import com.bjj.rabbitmq_scurity.exception.ResourceNotFoundException;
import com.bjj.rabbitmq_scurity.model.dto.ParticipantDto;
import com.bjj.rabbitmq_scurity.model.entity.ParticipantEntity;
import com.bjj.rabbitmq_scurity.model.payload.response.ParticipantResponse;
import com.bjj.rabbitmq_scurity.repository.ParticipantRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipanServiceImpl implements ParticipantService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ParticipantRepository participantRepository;


    @Override
    public Page<ParticipantResponse> getAllParticipant(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<ParticipantEntity> participantEntities = participantRepository.findAll(pageable);
        return participantEntities.map(ParticipantResponse::fromEntity);
    }

    @Override
    public ParticipantEntity createParticipant(ParticipantEntity participantEntity) {
        return participantRepository.save(participantEntity);
    }

    @Override
    public ParticipantEntity updateParticipant(long id, ParticipantEntity participantRequest) {
        ParticipantEntity participants = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant", "id", id));
        participants.setName(participantRequest.getName());
        participants.setEmail(participantRequest.getEmail());
        return participants;
    }

    @Override
    public void deleteParticipant(long id) {
        ParticipantEntity participants = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant", "id", id));
        participantRepository.delete(participants);

    }

    @Override
    public ParticipantEntity getParticipantById(long id) {
        Optional<ParticipantEntity> result = participantRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new ResourceNotFoundException("Participant", "id", id);
        }
    }


}
