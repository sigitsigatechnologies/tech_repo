package com.bjj.rabbitmq_scurity.controller;

import com.bjj.rabbitmq_scurity.model.dto.ParticipantDto;
import com.bjj.rabbitmq_scurity.model.entity.ParticipantEntity;
import com.bjj.rabbitmq_scurity.model.payload.response.ParticipantResponse;
import com.bjj.rabbitmq_scurity.service.ParticipantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/participant")
public class ParticipantController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ParticipantService participantService;

    @GetMapping(value = "/getAllParticipant")
    ResponseEntity<Page<ParticipantResponse>> getFilteredStudent(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                 @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                 @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                                                                 @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok().body(participantService.getAllParticipant(page, size, orderBy, direction));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ParticipantDto> getParticipantById(@PathVariable(name = "id") Long id) {
        ParticipantEntity participant = participantService.getParticipantById(id);

        // convert entity to DTO
        ParticipantDto participantResponse = modelMapper.map(participant, ParticipantDto.class);

        return ResponseEntity.ok().body(participantResponse);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ParticipantDto> createParticipant(@RequestBody ParticipantDto participantDto) {

        // convert DTO to entity
        ParticipantEntity participantRequest = modelMapper.map(participantDto, ParticipantEntity.class);

        ParticipantEntity participant = participantService.createParticipant(participantRequest);

        // convert entity to DTO
        ParticipantDto participantResponse = modelMapper.map(participant, ParticipantDto.class);

        return new ResponseEntity<ParticipantDto>(participantResponse, HttpStatus.CREATED);
    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ParticipantDto> updateParticipant(@PathVariable long id, @RequestBody ParticipantDto participantDto) {

        // convert DTO to Entity
        ParticipantEntity participantRequest = modelMapper.map(participantDto, ParticipantEntity.class);

        ParticipantEntity participant = participantService.updateParticipant(id, participantRequest);

        // entity to DTO
        ParticipantDto participantResponse = modelMapper.map(participant, ParticipantDto.class);

        return ResponseEntity.ok().body(participantResponse);
    }


}
