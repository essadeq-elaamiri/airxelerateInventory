package com.airxelerate.airxelerateInventory.dto;

import com.airxelerate.airxelerateInventory.enums.UserRole;

public record SignupRequest(String fullName, String email, String password, UserRole userRole) {
}
