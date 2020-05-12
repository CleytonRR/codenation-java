package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "candidate")
public class Candidate {

    @EmbeddedId
    private CandidateId id;

    @Column(nullable = false)
    private int status;

    @CreatedDate
    @Column(name = "created_at")
    private Date created_at;

    public Candidate() {
    }
}
