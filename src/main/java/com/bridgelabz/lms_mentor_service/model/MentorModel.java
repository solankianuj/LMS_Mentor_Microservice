package com.bridgelabz.lms_mentor_service.model;

import com.bridgelabz.lms_mentor_service.dto.MentorDTO;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * purpose:creating mentor model
 * @author Anuj Solanki
 */
@Entity
@Table(name = "mentorData")
@Data
public class MentorModel {
    @Id
    @GenericGenerator(name = "mentorData",strategy = "increment")
    @GeneratedValue(generator = "mentorData")
    private Long id;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String mentorType;
    private String mentorRole;
    private String mobileNumber;
    private String email;
    private String experienceYears;
    private String preferredTime;
    private LocalDate startDate;
    private String status;
    private String mentorDescription;
    private String profileImageUrl;
    private int creatorUser;
    private long supervisorId;
    private LocalDateTime createdTimeStamp;
    private LocalDateTime updateTimeStamp;

    public MentorModel(MentorDTO mentorDTO) {
        this.employeeId=mentorDTO.getEmployeeId();
        this.firstName=mentorDTO.getFirstName();
        this.lastName=mentorDTO.getLastName();
        this.mentorType=mentorDTO.getMentorType();
        this.mentorRole=mentorDTO.getMentorRole();
        this.mobileNumber=mentorDTO.getMobileNumber();
        this.email=mentorDTO.getEmail();
        this.experienceYears=mentorDTO.getExperienceYears();
        this.preferredTime=mentorDTO.getPreferredTime();
        this.startDate=mentorDTO.getStartDate();
        this.status=mentorDTO.getStatus();
        this.mentorDescription=mentorDTO.getMentorDescription();
        this.profileImageUrl=mentorDTO.getProfileImageUrl();
        this.creatorUser=mentorDTO.getCreatorUser();
        this.supervisorId=mentorDTO.getSupervisorId();

    }

    public MentorModel() {

    }
}
