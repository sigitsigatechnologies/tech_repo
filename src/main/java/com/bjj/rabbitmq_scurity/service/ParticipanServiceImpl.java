package com.bjj.rabbitmq_scurity.service;

import com.bjj.rabbitmq_scurity.config.mapper.ParticipantMapper;
import com.bjj.rabbitmq_scurity.exception.ResourceNotFoundException;
import com.bjj.rabbitmq_scurity.model.dto.ParticipantDto;
import com.bjj.rabbitmq_scurity.model.entity.ParticipantEntity;
import com.bjj.rabbitmq_scurity.model.payload.response.CollectionPageableResponse;
import com.bjj.rabbitmq_scurity.repository.ParticipantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParticipanServiceImpl implements ParticipantService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ParticipantRepository participantRepository;

    @Override
    public CollectionPageableResponse getAllParticipant(int pageNo, int pageSize, String sortBy, String sortDir) {
        List<ParticipantEntity> data = new ArrayList<ParticipantEntity>();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<ParticipantEntity> participantEntities = participantRepository.findAll(pageable);
        data = participantEntities.getContent();
        CollectionPageableResponse result = new CollectionPageableResponse<>(
                data,
                participantEntities.getNumber(),
                participantEntities.getSize(),
                participantEntities.getTotalPages(),
                participantEntities.getTotalElements()
        );
        return result;
//       return participantEntities.map(ParticipantMapper::mapToParticipantDto);
    }

    @Override
    public ParticipantDto createParticipant(ParticipantDto participant) {

            ParticipantEntity participantEntity = ParticipantMapper.mapToParticipant(participant);
            if (participantRepository.findByEmail(participant.getEmail()) != null ){
                throw new RuntimeException("Email Has exist");
            }
            ParticipantEntity saved = participantRepository.save(participantEntity);
            ParticipantDto result = ParticipantMapper.mapToParticipantDto(saved);
           return result;
    }

    @Override
    public ParticipantDto updateParticipant(long id, ParticipantDto participantDto) {
        ParticipantEntity participants = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant", "id", id));
        participants.setName(participantDto.getName());
        participants.setEmail(participantDto.getEmail());
        ParticipantEntity updated = participantRepository.save(participants);
        return ParticipantMapper.mapToParticipantDto(updated);
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
