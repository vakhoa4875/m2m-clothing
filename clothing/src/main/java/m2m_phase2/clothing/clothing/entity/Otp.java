package m2m_phase2.clothing.clothing.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Otp {
	String otp1, otp2, otp3, otp4, otp5, otp6;

	public String getOtp1() {
		return otp1;
	}

	public void setOtp1(String otp1) {
		this.otp1 = otp1;
	}

	public String getOtp2() {
		return otp2;
	}

	public void setOtp2(String otp2) {
		this.otp2 = otp2;
	}

	public String getOtp3() {
		return otp3;
	}

	public void setOtp3(String otp3) {
		this.otp3 = otp3;
	}

	public String getOtp4() {
		return otp4;
	}

	public void setOtp4(String otp4) {
		this.otp4 = otp4;
	}

	public String getOtp5() {
		return otp5;
	}

	public void setOtp5(String otp5) {
		this.otp5 = otp5;
	}

	public String getOtp6() {
		return otp6;
	}

	public void setOtp6(String otp6) {
		this.otp6 = otp6;
	}

}
