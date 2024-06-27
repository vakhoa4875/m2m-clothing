package m2m_phase2.clothing.clothing.service;

import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.data.dto.UserDto;
import m2m_phase2.clothing.clothing.data.dto.VoucherDetailsDto;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.data.model.UserM;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    /**
     * @api
     * @return list containing all [user] from database
     */
    List<UserM> getAllUser() throws SQLException;
    UserM getUserByUsernameAndEmail(UserDto userDto) throws SQLException;
    UserM getUserByEmail(UserDto userDto) throws  SQLException;
    byte updateUserInfo(UserDto userDto) throws SQLException;
    boolean isUserExist(UserDto userDto) throws SQLException;
    byte saveUser(UserDto userDto) throws SQLException;
    byte disableUser(UserDto userDto) throws SQLException;
    void saveAdminTokenToSession(HttpSession session, UserDto userDto) throws SQLException;
    //M2M- 010 TanLoc Begin
    void saveUserGG(UserE usere);
    //M2M- 010 TanLoc End
    //voucher
    List<UserM> findUserNotInVoucher(VoucherDetailsDto voucherDetailsDto) throws SQLException;
    // new login
    UserM getCurrentUser() throws SQLException;
}
