package com.soumya.user.org.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class APIException {
	
	
	private String errorType;
	private String errorCode;
	private String errorDescription;

}
