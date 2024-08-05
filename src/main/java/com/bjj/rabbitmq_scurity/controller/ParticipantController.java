package com.bjj.rabbitmq_scurity.controller;

import com.bjj.rabbitmq_scurity.entity.Participant;
import com.bjj.rabbitmq_scurity.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/participant")
public class ParticipantController {
    @Autowired
    ParticipantRepository participantRepository;

}
