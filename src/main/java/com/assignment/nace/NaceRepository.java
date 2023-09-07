package com.assignment.nace;
/**
 * @author Lakshmi Subbiah
 *
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaceRepository extends JpaRepository<NaceDetails, Integer>{
}
