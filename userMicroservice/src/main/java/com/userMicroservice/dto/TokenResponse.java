package com.userMicroservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenResponse {

	private String token;
	private String username;
	private String email;
	//private List<String> role;
	private Long role;
	private Long userId;
}
