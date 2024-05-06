package m2m_phase2.clothing.clothing.data.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Account")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

//	@OneToOne(mappedBy = "account")
//	@JsonIgnore
//	private Userinfo userinfo;

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
//				", userinfo=" + userinfo +
				'}';
	}
}
