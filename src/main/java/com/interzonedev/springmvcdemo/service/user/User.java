package com.interzonedev.springmvcdemo.service.user;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

public class User {
	@NotNull
	@Min(1L)
	private Long id;

	@NotEmpty
	@Size(max = 255)
	private String firstName;

	@NotEmpty
	@Size(max = 255)
	private String lastName;

	private boolean admin = false;

	private Date timeCreated;

	private Date timeUpdated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Date getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	public Date getTimeUpdated() {
		return timeUpdated;
	}

	public void setTimeUpdated(Date timeUpdated) {
		this.timeUpdated = timeUpdated;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("firstName", getFirstName()).append("lastName", getLastName())
				.append("admin", isAdmin()).append("timeCreated", getTimeCreated())
				.append("timeUpdated", getTimeUpdated()).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(getFirstName()).append(getLastName()).append(isAdmin()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User)) {
			return false;
		}

		User that = (User) obj;

		boolean equals = new EqualsBuilder().append(getFirstName(), that.getFirstName())
				.append(getLastName(), that.getLastName()).append(isAdmin(), that.isAdmin()).isEquals();

		return equals;
	}
}
