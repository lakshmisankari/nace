package com.assignment.nace;

/**
 * @author Lakshmi Subbiah
 *
 */

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/nace")
public class NaceController {

	@Autowired
	NaceService fileService;

	/**
	 * @param file
	 * @return
	 */
	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<NaceResponse> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (NaceHelper.hasCSVFormat(file)) {
			try {

				fileService.save(file);

				message = "Uploaded the file successfully: " + file.getOriginalFilename();

				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/nace/download/")
						.path(file.getOriginalFilename()).toUriString();

				return ResponseEntity.status(HttpStatus.OK).body(new NaceResponse(message, fileDownloadUri));
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new NaceResponse(message, ""));
			}
		}

		message = "Please upload a csv file";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new NaceResponse(message, ""));
	}

	/**
	 * @return
	 */
	@GetMapping("/getAllDetails")
	public ResponseEntity<List<NaceDetails>> getAllNace() {
		try {
			List<NaceDetails> nacedetails = fileService.getAllNace();

			if (nacedetails.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(nacedetails, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/getDetails/{id}")
	public ResponseEntity<NaceDetails> getNaceDetails(@PathVariable("id") int id) {
		try {
			Optional<NaceDetails> nacedetails = fileService.getNaceDetails(id);

			if (nacedetails.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(nacedetails.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @param fileName
	 * @return
	 */
	@GetMapping("/download/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
		InputStreamResource file = new InputStreamResource(fileService.load());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
				.contentType(MediaType.parseMediaType("application/csv")).body(file);
	}
}
