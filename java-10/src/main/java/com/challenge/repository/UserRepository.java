package com.challenge.repository;

import com.challenge.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(Long idUser);

    @Query("SELECT candidate.id.user FROM Candidate candidate WHERE candidate.id.acceleration.name = :name")
    List<User> findByAccelerationName(@Param("name") String name);

    @Query("SELECT candidate.id.user FROM Candidate candidate WHERE candidate.id.company.id = :companyId")
    List<User> findByCompanyId(@Param("companyId") Long companyId);

}
