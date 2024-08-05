package com.bjj.rabbitmq_scurity.repository;

import com.bjj.rabbitmq_scurity.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

}
