package com.bridgelabz.lms_mentor_service.controller;

import com.bridgelabz.lms_mentor_service.dto.MentorDTO;
import com.bridgelabz.lms_mentor_service.services.IMentorServices;
import com.bridgelabz.lms_mentor_service.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *  Purpose:Mentors Operation Controller
 * @author Anuj Solanki
 * @date 3/09/2022
 */

@RestController
@RequestMapping("/mentor")
public class MentorController {

    @Autowired
    IMentorServices mentorServices;

    /**
     *  Purpose:adding mentors
     *  @param token
     * @param mentorDTO
     * @return
     */
    @PostMapping("/addMentor")
    public ResponseEntity<Response> addMentor(@RequestHeader String token ,@Valid @RequestBody MentorDTO mentorDTO){
        Response response = mentorServices.addMentor(token,mentorDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *  Purpose:getting mentor
     * @param token
     * @param mentorId
     * @return
     */
    @GetMapping("/getMentor")
    public ResponseEntity<Response> getMentor(@RequestHeader String token,@RequestParam long mentorId){
       Response response = mentorServices.getMentor(token,mentorId);
       return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *  Purpose:updating mentors details
     * @param token
     * @param mentorId
     * @param mentorDTO
     * @return
     */
    @PutMapping("/updateMentor")
    public ResponseEntity<Response> updateMentor(@RequestHeader String token, @RequestParam long mentorId, @Valid @RequestBody MentorDTO mentorDTO){
        Response response = mentorServices.updateMentor(token,mentorId, mentorDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *  Purpose:deleting mentor
     * @param token
     * @param mentorId
     * @return
     */

    @DeleteMapping("/deleteMentor")
    public ResponseEntity<Response> deleteMentor(@RequestHeader String token, @RequestParam long mentorId){
        Response response = mentorServices.deleteMentor(token,mentorId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *  Purpose:getting mentor by role
     * @param token
     * @param mentorRole
     * @return
     */
    @GetMapping("/getMentorByRole")
    public ResponseEntity<Response> mentorByRole(@RequestHeader String token, @RequestParam String mentorRole){
        Response response = mentorServices.getMentorByRole(token,mentorRole);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *  Purpose:getting mentor by mentorId
     * @param token
     * @param employeeId
     * @return
     */
    @GetMapping("/getMentorByEmployeeId")
    public ResponseEntity<Response> byMentorId(@RequestHeader String token,@RequestParam String employeeId){
        Response response = mentorServices.getMentorByMentorId(token,employeeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *  Purpose:counting total mentors
     * @param token
     * @return
     */
    @GetMapping("/getMentorCount")
    public ResponseEntity<Response> getTotalMentor(@RequestHeader String token){
        Response response = mentorServices.mentorsCount(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
