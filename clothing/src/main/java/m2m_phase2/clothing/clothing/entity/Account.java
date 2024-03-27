package m2m_phase2.clothing.clothing.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Account")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {

	private static final long serialVersionUID = 2859623393838379704L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;

	@Column(name = "username", unique = true, nullable = false, length = 63)
	private String username;

	@Column(name = "email", unique = true, nullable = false, length = 255)
	private String email;

	@Column(name = "hashed_password", nullable = false, length = 255)
	private String hashedPassword;

	@Column(name = "is_admin")
	private boolean isAdmin;

	@Column(name = "is_disable")
	private boolean isDisable;

	@OneToOne(mappedBy = "account")
	private Userinfo userinfo;

	public boolean isDisable() {
		return isDisable;
	}

	@Override
	public String toString() {
		return "Account{" +
				"userId=" + userId +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", hashedPassword='" + hashedPassword + '\'' +
				", isAdmin=" + isAdmin +
				", isDisable=" + isDisable +
				", userinfo=" + userinfo +
				'}';
	}

	public void setDisable(boolean isDisable) {
		this.isDisable = isDisable;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Userinfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
