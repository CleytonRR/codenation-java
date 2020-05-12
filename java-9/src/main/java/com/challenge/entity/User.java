package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name",
            length = 100,
            nullable = false)
    private String fullname;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String nickname;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(name = "created_at")
    @CreatedDate
    private Date createAt;

    @OneToMany(mappedBy = "user")
    private List<Candidate> candidates;

    @OneToMany(mappedBy = "user")
    private List<Submission> submissions;

    public User() {
    }
}
