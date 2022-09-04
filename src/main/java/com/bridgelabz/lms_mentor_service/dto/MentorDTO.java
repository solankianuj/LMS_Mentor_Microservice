package com.bridgelabz.lms_mentor_service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * purpose:creating mentor dto and validation on mentor dto
 * @author Anuj Solanki
 */
@Data
public class MentorDTO {
    @NotNull(message = "employeeId is required")
    private String employeeId;
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$", message = " first name is invalid")
    private String firstName;
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$", message = " last name is invalid")
    private String lastName;
    @NotNull(message = "mentorType is required")
    private String mentorType;
    @NotNull(message = "mentorRole is required")
    private String mentorRole;
    @NotNull(message = "mobileNumber is required")
    private String mobileNumber;
    @NotNull(message = "email is required")
    private String email;
    @NotNull(message = "experienceYears is required")
    private String experienceYears;
    @NotNull(message = "preferredTime is required")
    private String preferredTime;
//    @NotNull(message = "startDate is required")
    private LocalDate startDate;
    @NotNull(message = "status is required")
    private String status;
    @NotNull(message = "mentorDescription is required")
    private String mentorDescription;
    @NotNull(message = "profileImageUrl is required")
    private String profileImageUrl;
    @NotNull(message = "creatorUser is required")
    private int creatorUser;
    @NotNull(message = "supervisorId is required")
    private long supervisorId;
}
