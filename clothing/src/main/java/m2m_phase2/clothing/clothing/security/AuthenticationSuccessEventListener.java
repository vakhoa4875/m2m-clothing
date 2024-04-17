package m2m_phase2.clothing.clothing.security;

import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.service.UserService;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Map;

@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        // Kiểm tra xem nguồn của sự kiện có phải là OAuth2AuthenticationToken hay không
        if (event.getSource() instanceof OAuth2AuthenticationToken) {
            // Ép kiểu event.getSource() về OAuth2AuthenticationToken để truy cập các thông tin của người dùng
            OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) event.getSource();

            // Lấy các thông tin của người dùng từ authenticationToken
            Map<String, Object> attributes = authenticationToken.getPrincipal().getAttributes();

            // Gọi hàm saveAccGG để lưu thông tin người dùng
            try {


                saveAccGG(attributes);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void saveAccGG(Map<String, Object> attributes) throws SQLException {


        UserE user = new UserE();
        user.setFullname((String) attributes.get("name"));
        user.setEmail((String) attributes.get("email"));
        user.setGgToken((String) attributes.get("sub"));
        user.setUsername((String) attributes.get("name"));
        user.setHashedPassword(new PasswordEncoderUtil().encodePassword((String) attributes.get("name")));

        userService.saveUserGG(user);
    }
}
