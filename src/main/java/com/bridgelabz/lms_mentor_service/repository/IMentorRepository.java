package com.bridgelabz.lms_mentor_service.repository;

import com.bridgelabz.lms_mentor_service.model.MentorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * purpose:creating mentor repository
 */
@Repository
public interface IMentorRepository extends JpaRepository<MentorModel,Long> {
    Optional<MentorModel> findByEmployeeId(String mentorId);
}
