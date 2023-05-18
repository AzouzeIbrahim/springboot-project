package com.example.msemployer.entities;


import com.example.msemployer.Enums.InterviewResult;
import com.example.msemployer.Enums.InterviewStatus;
import com.example.msemployer.Enums.InterviewType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Intervieww {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInterview;

    private String duration;

    private Date date;

    private String time;

    private InterviewStatus status;

    private InterviewResult interviewResult;

    private InterviewType interviewType;

    private String location;



    @OneToOne
    private Demande demande;


}
