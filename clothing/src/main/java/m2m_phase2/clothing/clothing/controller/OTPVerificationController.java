package m2m_phase2.clothing.clothing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
public class OTPVerificationController {

    @Autowired
    private HttpSession session;

    @PostMapping("/verifyOTP")
    public String verifyOTP(@RequestParam("otp1") String otp1,
                            @RequestParam("otp2") String otp2,
                            @RequestParam("otp3") String otp3,
                            @RequestParam("otp4") String otp4,
                            @RequestParam("otp5") String otp5,
                            @RequestParam("otp6") String otp6) {

        String enteredOTP = otp1 + otp2 + otp3 + otp4 + otp5 + otp6;
        String sessionOTP = (String) session.getAttribute("otp");

        if (enteredOTP.equals(sessionOTP)) {
            // Mã OTP hợp lệ, thực hiện các hành động tiếp theo
            return "Mã OTP hợp lệ!";
        } else {
            // Mã OTP không hợp lệ, thông báo cho người dùng
            return "Mã OTP không hợp lệ!";
        }
    }
}

