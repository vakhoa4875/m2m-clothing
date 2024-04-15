package m2m_phase2.clothing.clothing.service;

import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.data.dto.UserDto;
import m2m_phase2.clothing.clothing.data.model.UserM;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    /**
     * @api
     * @return list containing all [user] from database
     */
    List<UserM> getAllUser() throws SQLException;

    /**
     * @api
     * @param userDto as a map contained essential attribute for a query
     * @return list as result of query
     */
//    List<UserM> getUserByDto(UserDto userDto) throws SQLException;
    UserM getUserByUsernameAndEmail(UserDto userDto) throws SQLException;
    UserM getUserByEmail(UserDto userDto) throws  SQLException;
    byte updateUserInfo(UserDto userDto) throws SQLException;
    boolean isUserExist(UserDto userDto) throws SQLException;
    byte saveUser(UserDto userDto) throws SQLException;
    byte disableUser(UserDto userDto) throws SQLException;
    void saveAdminTokenToSession(HttpSession session, UserDto userDto) throws SQLException;
//    String checkUserAdminRole(UserDto userDto);
//    void saveToSession(HttpSession httpSession, UserDto userDto);
}
