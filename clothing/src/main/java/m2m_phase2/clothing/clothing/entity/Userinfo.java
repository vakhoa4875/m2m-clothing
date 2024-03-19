package m2m_phase2.clothing.clothing.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Userinfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Userinfo implements Serializable {

	private static final long serialVersionUID = 6023540828358092434L;

	@Id
	@Column(name = "user_id")
	private int userId;

	@Column(name = "fullname", length = 127)
	private String fullname;

	@Column(name = "gender", length = 20)
	private String gender;

	@Column(name = "avatar", length = 255)
	private String avatar;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "description", length = 300)
	private String description;

	@Column(name = "job_title", length = 63)
	private String jobTitle;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Account account;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
