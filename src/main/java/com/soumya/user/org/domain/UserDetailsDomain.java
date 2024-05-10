package com.soumya.user.org.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@Table(name = "table_user")
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDomain {
	
	@Id
	private String userId;
	private String userName;
	private String name;
	private Long phoneNumber;
	@Transient
	private List<Ratings> ratings;
	
}
