package m2m_phase2.clothing.clothing.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    public static String email;

    public static void logOut() {
        email = null;
    }

    //	  // Mã hóa mật khẩu bằng thuật toán PBKDF2
//    public static String encodePassword(String rawPassword) {
//        Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();
//        return passwordEncoder.encode(rawPassword);
//    }
//
//    // Xác minh mật khẩu đã mã hóa với mật khẩu gốc
//    public static boolean verifyPassword(String rawPassword, String encodedPassword) {
//        Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();
//        return passwordEncoder.matches(rawPassword, encodedPassword);
//    }
    //M2M- 010 TanLoc Begin
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword(String rawPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        }
        return passwordEncoder.encode(rawPassword);
    }

    public static boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    //M2M- 010 TanLoc End
}
