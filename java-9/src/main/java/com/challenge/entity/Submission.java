package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="submission")
public class Submission {
    @EmbeddedId
    private SubmissionId id;

    @Column(nullable = false)
    private float score;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    public Submission() {
    }
}
