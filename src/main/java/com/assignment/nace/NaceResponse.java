package com.assignment.nace;
/**
 * @author Lakshmi Sankari .S
 *
 */
public class NaceResponse {

	private String message;
	private String fileDownloadUri;
	

	  /**
	 * @param message
	 * @param fileDownloadUri
	 */
	public NaceResponse(String message, String fileDownloadUri) {
	    this.message = message;
	    this.fileDownloadUri = fileDownloadUri;
	  }

	  /**
	 * @return
	 */
	public String getMessage() {
	    return message;
	  }

	  /**
	 * @param message
	 */
	public void setMessage(String message) {
	    this.message = message;
	  }

	/**
	 * @return
	 */
	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	/**
	 * @param fileDownloadUri
	 */
	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

}