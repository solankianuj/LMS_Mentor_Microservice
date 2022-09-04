package com.bridgelabz.lms_mentor_service.services;

import com.bridgelabz.lms_mentor_service.dto.MentorDTO;
import com.bridgelabz.lms_mentor_service.exception.MentorNotFound;
import com.bridgelabz.lms_mentor_service.model.MentorModel;
import com.bridgelabz.lms_mentor_service.repository.IMentorRepository;
import com.bridgelabz.lms_mentor_service.services.mailService.MailServices;
import com.bridgelabz.lms_mentor_service.util.Response;
import com.bridgelabz.lms_mentor_service.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  Purpose:creating Mentors different services
 * @author Anuj Solanki
 */

@Service
public class MentorServices implements IMentorServices{

    @Autowired
    IMentorRepository mentorRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MailServices mailServices;

    @Autowired
    Token tokenutil;

    /**
     *  Purpose:adding mentors
     * @param mentorDTO
     * @param token
     * @return
     */
    @Override
    public Response addMentor(String token,MentorDTO mentorDTO) {
        boolean isUserPresent=restTemplate.getForObject("http://localhost:8082/admin/validatingUser/"+token, Boolean.class);
        if(isUserPresent) {
            MentorModel mentorModel = new MentorModel(mentorDTO);
            mentorModel.setCreatedTimeStamp(LocalDateTime.now());
            mentorRepository.save(mentorModel);
            String body="Mentor Added Successfully .\n" +mentorModel.toString();
            String sub="Adding Mentor";
            mailServices.send(mentorModel.getEmail(),sub,body);
            return new Response("Mentor added successfully",200,mentorModel);
        }
        throw new MentorNotFound(200, "Admin Not Found !");

    }

    /**
     *  Purpose:getting mentor
     * @param mentorId
     * @param token
     * @return
     */
    @Override
    public Response getMentor(String token, long mentorId) {
        boolean isUserPresent=restTemplate.getForObject("http://localhost:8082/admin/validatingUser/"+token, Boolean.class);
        if(isUserPresent) {
            Optional<MentorModel> mentorModel = mentorRepository.findById(mentorId);
            if (mentorModel.isPresent()) {
                return new Response("Fetching Mentor Details ",200,mentorModel.get());
            }
            throw new MentorNotFound(200, "Mentor Not Found !");
        }
        throw new MentorNotFound(200, "Admin Not Found !");
    }

    /**
     *  Purpose:updating mentors details
     * @param mentorId
     * @param mentorDTO
     * @param token
     * @return
     */
    @Override
    public Response updateMentor(String token, long mentorId, MentorDTO mentorDTO) {

        boolean isUserPresent = restTemplate.getForObject("http://localhost:8082/admin/validatingUser/" + token, Boolean.class);
        if (isUserPresent) {
            Optional<MentorModel> mentorModel = mentorRepository.findById(mentorId);
            if (mentorModel.isPresent()) {
                mentorModel.get().setEmployeeId(mentorDTO.getEmployeeId());
                mentorModel.get().setFirstName(mentorDTO.getFirstName());
                mentorModel.get().setLastName(mentorDTO.getLastName());
                mentorModel.get().setEmail(mentorDTO.getEmail());
                mentorModel.get().setMentorRole(mentorDTO.getMentorRole());
                mentorModel.get().setMentorDescription(mentorDTO.getMentorDescription());
                mentorModel.get().setMentorType(mentorDTO.getMentorType());
                mentorModel.get().setStatus(mentorDTO.getStatus());
                mentorModel.get().setCreatorUser(mentorDTO.getCreatorUser());
                mentorModel.get().setExperienceYears(mentorDTO.getExperienceYears());
                mentorModel.get().setMobileNumber(mentorDTO.getMobileNumber());
                mentorModel.get().setPreferredTime(mentorDTO.getPreferredTime());
                mentorModel.get().setProfileImageUrl(mentorDTO.getProfileImageUrl());
                mentorModel.get().setStartDate(mentorDTO.getStartDate());
                mentorModel.get().setSupervisorId(mentorDTO.getSupervisorId());
                mentorModel.get().setUpdateTimeStamp(LocalDateTime.now());
                mentorRepository.save(mentorModel.get());
                return new Response("Updating Mentor Details ", 200, mentorModel.get());
            }
            throw new MentorNotFound(400, "Mentor Not Found !");
        }
        throw new MentorNotFound(400, "Admin Not Found !");

    }

    /**
     *  Purpose:deleting mentor
     * @param mentorId
     * @param token
     * @return
     */
    @Override
    public Response deleteMentor(String token, long mentorId) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8082/admin/validatingUser/" + token, Boolean.class);
        if (isUserPresent) {
            Optional<MentorModel> mentorModel = mentorRepository.findById(mentorId);
            if (mentorModel.isPresent()) {
                mentorRepository.delete(mentorModel.get());
                return new Response("Deleting Mentor ", 200, mentorModel.get());
            }
            throw new MentorNotFound(400, "Mentor Not Found !");
        }
        throw new MentorNotFound(400, "Admin Not Found !");

    }

    /**
     *  Purpose:getting mentor by role
     * @param mentorRole
     * @param token
     * @return
     */
    @Override
    public Response getMentorByRole(String token,String mentorRole) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8082/admin/validatingUser/" + token, Boolean.class);
        if (isUserPresent) {
            List<MentorModel> mentorModels = mentorRepository.findAll();
            List<MentorModel> newList = mentorModels.stream().filter(x -> x.getMentorRole().equals(mentorRole)).collect(Collectors.toList());
            Integer count = newList.size();
            String MentorCount = mentorRole + " - " + count;
            return new Response("Counting Mentor By Role ", 200, MentorCount);
        }
        throw new MentorNotFound(200, "Admin Not Found !");

    }

    /**
     *  Purpose:getting mentor by mentorId
     * @param employeeId
     * @param token
     * @return
     */
    @Override
    public Response getMentorByMentorId(String token, String employeeId) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8082/admin/validatingUser/" + token, Boolean.class);
        if (isUserPresent) {
            Optional<MentorModel> mentorModel = mentorRepository.findByEmployeeId(employeeId);
            if (mentorModel.isPresent()) {
                return new Response("Fetching Mentor By employeeId ", 200, mentorModel.get());
            }
            throw new MentorNotFound(200, "Mentor Not Found !");
        }
        throw new MentorNotFound(200, "Admin Not Found !");

    }

    /**
     *  Purpose:counting total mentors
     *  @param token
     * @return
     */
    @Override
    public Response mentorsCount(String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8082/admin/validatingUser/" + token, Boolean.class);
        if (isUserPresent) {
            List<MentorModel> mentorModel = mentorRepository.findAll();
            Integer count = mentorModel.size();
            String menCount= "Total Mentor is :" + count;
            return new Response("Counting Total Mentor ", 200, menCount);
        }
        throw new MentorNotFound(200, "Admin Not Found !");

    }
}
