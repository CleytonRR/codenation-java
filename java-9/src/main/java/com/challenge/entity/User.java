package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name")
    @NotNull
    @Size(max = 100)
    private String fullname;

    @Email
    @Size(max = 100)
    @NotNull
    private String email;

    @NotNull
    @Size(max = 50)
    private String nickname;

    @NotNull
    @Size(max = 255)
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
