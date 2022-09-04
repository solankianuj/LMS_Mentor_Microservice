package com.bridgelabz.lms_mentor_service.services;

import com.bridgelabz.lms_mentor_service.dto.MentorDTO;
import com.bridgelabz.lms_mentor_service.model.MentorModel;
import com.bridgelabz.lms_mentor_service.util.Response;

public interface IMentorServices {
    Response addMentor(String token,MentorDTO mentorDTO);
    Response getMentor(String token,long id);
    Response updateMentor(String token, long id, MentorDTO mentorDTO);

    Response deleteMentor(String token, long id);

    Response getMentorByRole(String token, String metorRole);

    Response getMentorByMentorId(String token, String mentorId);
    Response mentorsCount(String token);
}
