package com.soumya.user.org.DTO;

import java.util.List;

import com.soumya.user.org.domain.Ratings;
import com.soumya.user.org.domain.UserDetailsDomain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private String userId;

	@NotNull(message = "user name field is mandatory and need to be provided")
//	@Pattern(regexp = "(/^[A-Za-z]+$/)", message = "user name field should contain only alphabits")
	private String userName;
	
	@NotNull(message = "phone number field is mandatory and need to be provided")
//	@Pattern(regexp = "^[0-9]+$", message = "phone number field should contain only numbers")
	@Size(min =10, max = 10,message = "phone number field should 10 digits in length")
	private String phoneNumber;
	@NotNull(message = "name field is mandatory and need to be provided")
	private String name;
	private List<Ratings> ratings;
	
	public static UserDetailsDomain getUserBO(UserDTO user) {
		
		return UserDetailsDomain.builder().userName(user.userName).name(user.name).phoneNumber(Long.parseLong(user.getPhoneNumber())).build();
	}
	
	public static UserDTO getUserDTO(UserDetailsDomain user) {
		
		return UserDTO.builder()
				.userId(user.getUserId())
				.userName(user.getUserName())
				.name(user.getName())
				.phoneNumber(user.getPhoneNumber()+"")
				.ratings(user.getRatings())
				.build();
	}
}
