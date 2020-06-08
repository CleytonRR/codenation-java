package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "acceleration")
@EntityListeners(AuditingEntityListener.class)
public class Acceleration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(max = 100)
    @NotNull
    private String name;

    @Size(max = 50)
    @NotNull
    private String slug;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "acceleration")
    private List<Candidate> candidates;

    public Acceleration() {
    }
}
