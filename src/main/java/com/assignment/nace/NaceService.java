package com.assignment.nace;

/**
 * @author Lakshmi Subbiah
 *
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Component
public class NaceService {
  @Autowired
  NaceRepository repository;

  /**
 * @param file
 */
public void save(MultipartFile file) {
    try {
      List<NaceDetails> nacedetails = NaceHelper.csvToNace(file.getInputStream());
      repository.saveAll(nacedetails);
    } catch (IOException e) {
      throw new RuntimeException("fail to save the file data: " + e.getMessage());
    }
  }

  /**
 * @return
 */
public ByteArrayInputStream load() {
    List<NaceDetails> nacedetails = repository.findAll();

    ByteArrayInputStream in = NaceHelper.naceDetailsToCSV(nacedetails);
   return in;
	 
  }

  /**
 * @return
 */
public List<NaceDetails> getAllNace() {
    return repository.findAll();
	 
  }
  
  /**
 * @param id
 * @return
 */
public Optional<NaceDetails> getNaceDetails(int id) {
	    return repository.findById(id);
	  }
}
