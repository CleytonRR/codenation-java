package com.challenge.repository;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import com.challenge.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Long> {

    Optional<Candidate> findById(CandidateId id);

    @Query(value = "select * from candidate " +
            "where user_id = :userId " +
            "and company_id = :companyId " +
            "and acceleration_id = :accelerationId", nativeQuery = true)
    Optional<Candidate> findById(@Param("userId") Long userId,
                                 @Param("companyId") Long companyId,
                                 @Param("accelerationId") Long accelerationId);

    @Query("SELECT candidate.id.user FROM Candidate candidate WHERE candidate.id.company.id = :companyId")
    List<Candidate> findByCompanyId(@Param("companyId") Long companyId);

    @Query(value = "SELECT candidate.id.user FROM Candidate candidate WHERE " +
            "candidate.id.company.id = :accelerationId", nativeQuery = true)
    List<Candidate> findByAccelerationId(@Param("accelerationId") Long accelerationId);

}
