package com.assignment.nace;
/**
 * @author Lakshmi Sankari .S
 *
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaceRepository extends JpaRepository<NaceDetails, Integer>{
}