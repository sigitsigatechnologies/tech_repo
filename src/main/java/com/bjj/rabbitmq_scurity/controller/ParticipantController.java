package com.bjj.rabbitmq_scurity.controller;

import com.bjj.rabbitmq_scurity.config.Utils;
import com.bjj.rabbitmq_scurity.model.dto.ParticipantDto;
import com.bjj.rabbitmq_scurity.model.entity.ParticipantEntity;
import com.bjj.rabbitmq_scurity.model.payload.response.ApiResponse;
import com.bjj.rabbitmq_scurity.model.payload.response.CollectionPageableResponse;
import com.bjj.rabbitmq_scurity.service.ParticipantService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/participant")
public class ParticipantController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ParticipantService participantService;

    @GetMapping(value = "/getAllParticipant")
    ResponseEntity<Page<ParticipantEntity>> getFilteredStudent(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                        @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                        @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                                                                        @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok().body(participantService.getAllParticipant(page, size, orderBy, direction));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ParticipantDto> getParticipantById(@PathVariable(name = "id") Long id) {
        ParticipantEntity participant = participantService.getParticipantById(id);

        // convert entity to DTO
        ParticipantDto result = modelMapper.map(participant, ParticipantDto.class);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ApiResponse<?>> createParticipant(@Valid @RequestBody ParticipantDto participantDto) {
        ParticipantDto data = participantService.createParticipant(participantDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(Utils.SUCESS.getValue(), data));
    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ParticipantDto> updateParticipant(@PathVariable long id, @RequestBody ParticipantDto participantDto) {

        participantDto.setId(id);
        ParticipantDto updatedUser = participantService.updateParticipant(id, participantDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


}
