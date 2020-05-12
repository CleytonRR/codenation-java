package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="submission")
@EntityListeners(AuditingEntityListener.class)
public class Submission {
    @EmbeddedId
    private SubmissionId id;

    @Column(nullable = false)
    @NotNull
    private float score;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    public Submission() {
    }
}
