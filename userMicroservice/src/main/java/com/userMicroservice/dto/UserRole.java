package com.userMicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum UserRole {
	ADMIN("Admin"),
	USER("User"),
	OFFICER("Officer");
	
	private String displayName;
	
	public String getDisplayName() {
		return displayName;
	}
}
