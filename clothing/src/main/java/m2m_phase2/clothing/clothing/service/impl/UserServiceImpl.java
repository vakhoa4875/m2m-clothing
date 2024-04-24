package m2m_phase2.clothing.clothing.service.impl;

import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.data.dto.UserDto;
import m2m_phase2.clothing.clothing.data.dto.VoucherDetailsDto;
import m2m_phase2.clothing.clothing.data.dto.VoucherDto;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.data.model.UserM;
import m2m_phase2.clothing.clothing.repository.UserRepo;
import m2m_phase2.clothing.clothing.service.UserService;
import m2m_phase2.clothing.clothing.utils.DateUtils;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public List<UserM> getAllUser() throws SQLException {
        return UserM.converListUserEToListUserM(userRepo.findAll());
    }

    @Override
    public UserM getUserByUsernameAndEmail(UserDto userDto) throws SQLException {
        if (!this.isUserExist(userDto)) return null;
        return UserM.convertUserEToUserM(userRepo.getUserByUsernameAndEmail(userDto.getUsername(), userDto.getEmail()));
    }

    @Override
    public UserM getUserByEmail(UserDto userDto) throws SQLException {
        if (!this.isUserExist(userDto)) return null;
        return UserM.convertUserEToUserM(userRepo.getUserByEmail(userDto.getEmail()));
    }

    @Override
    public byte updateUserInfo(UserDto userDto) throws SQLException {
        if (this.isUserExist(userDto)) {
            userRepo.updateUserInfo(userDto.getUsername(),
                    userDto.getEmail(),
                    userDto.getFullname(),
                    userDto.getSdt(),
                    userDto.getAvatar());
            return 1;
        }
        return  0;
    }

    @Override
    public boolean isUserExist(UserDto userDto) throws SQLException{
        var user = userRepo.getUserByUsernameAndEmail(userDto.getUsername(), userDto.getEmail());
        return Objects.nonNull(user);
    }

    @Override
    public byte saveUser(UserDto userDto) throws SQLException {
        if (this.isUserExist(userDto)) {
            userRepo.updateUser(userDto.getUsername(),
                    userDto.getEmail(),
                    userDto.getFullname(),
                    userDto.getHashedPassword(),
                    userDto.getGender(),
                    userDto.getRoleId(),
                    userDto.getRoleName(),
                    userDto.getDescription(),
                    userDto.getJobTitle(),
                    DateUtils.toDateFormat(userDto.getDob(), "dd/MM/yyyy", "yyyy-MM-dd"),
                    userDto.getAvatar());
        } else {
//            var x = userRepo.save(UserE.convertUserDtoToUserE(userDto));
            userRepo.insertNewUser(userDto.getUsername(),
                    userDto.getEmail(),
                    userDto.getFullname(),
                    userDto.getHashedPassword(),
//                    userDto.getGender(),
                    userDto.getRoleId(),
                    userDto.getRoleName(),
                    userDto.getDescription());
        }
        return 1;
    }

    @Override
    public byte disableUser(UserDto userDto) throws SQLException {
        if (this.isUserExist(userDto)) {
            userRepo.disableUser(userDto.getUsername(), userDto.getEmail());
        } else {
            System.out.println("cannot find user with ..");
        }
        return 1;
    }

    public byte statusAdminLogin(UserDto userDto) throws SQLException {
        if (!this.isUserExist(userDto)) {
            return 3;
        }
        var userM = this.getUserByUsernameAndEmail(userDto);
        if (Objects.equals(userM.getRoleId(), 3)) {
            return 0;
        }
        if (!PasswordEncoderUtil.verifyPassword(userDto.getPassword(), userM.getHashedPassword())) {
            return 2;
        }
        return 1;
//        try {
//            UserM userM = UserM.convertUserEToUserM(userRepo.getUserByUsernameAndEmail(userDto.getUsername(), userDto.getEmail()));
//            if (userM.getRoleId() == 3)
//                return 0;//khong du quyen
//            if (!PasswordEncoderUtil.verifyPassword(userDto.getPassword(), userM.getHashedPassword())) {
//                return 2;//sai mat khau
//            } else {
//                return 1;//dung mat khau va du quyen
//            }
//        } catch (Exception e) {
//            return 3;//tai khoan khong ton tai
//        }
    }

    @Override
    public void saveAdminTokenToSession(HttpSession session, UserDto userDto) throws SQLException {
        if (statusAdminLogin(userDto) == 1) {
            session.setAttribute("adminToken", PasswordEncoderUtil.encodePassword(userDto.getEmail() + userDto.getPassword()));
        } else {
            session.removeAttribute("adminToken");
        }
    }
    //M2M- 010 TanLoc Begin
    @Override
    public void saveUserGG(UserE userE) {
        userRepo.save(userE);
    }

    @Override
    public List<UserM> findUserNotInVoucher(VoucherDetailsDto voucherDetailsDto) {
        return UserM.converListUserEToListUserM(userRepo.findUserNotInVoucher(voucherDetailsDto.getVoucher().getVoucherID()));
    }
    //M2M- 010 TanLoc End
//    @Override
//    public List<UserM> getUserByDto(UserDto userDto) throws SQLException {
//        return UserM.converListUserEToListUserM(userRepo.getUserByDto(userDto));
//    }
//
//    @Autowired
//    private AccountRepo accRepo;
//
//    @Autowired
//    private UserinfoRepo in4Repo;
//
//    @Autowired
//    private HttpSession session;
//
//    public boolean isAdminAuth() {
//        return false;
//    }
//
//    @Override
//    public byte updateUser(UserDto userDto) throws SQLException {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(Integer id) {
//        Account acc = accRepo.findByuserId(id);
//        acc.setDisable(true);
//        accRepo.save(acc);
//    }
//
//    @Override
//    public void save(Account acc, Userinfo info) {
//        accRepo.save(acc);
//        in4Repo.save(info);
//    }
//
//    @Override
//    public UserM findUserById(int id) throws SQLException {
//        return UserM.convertAccountToUserM(accRepo.findByuserId(id));
//    }
//
//    @Override
//    public Map<Account, Userinfo> getAll() {
//        List<Userinfo> listIn4 = in4Repo.findAll();
//        List<Account> listAcc = accRepo.findAll();
//
//        Map<Account, Userinfo> map = new HashMap<>();
//
//        for (int i = 0; i < listAcc.size(); i++) {
//            map.put(listAcc.get(i), listIn4.get(i));
//        }
//
//        return map;
//    }
//
//    @Override
//    public String checkUserAdminRole(UserDto userDto) {// remember to create constant class
////        System.out.println("userDto toString: " + userDto.toString() +
////                "\n" + "accountE: " + accRepo.findByemail(userDto.getEmail()).toString());
//        UserM userM = UserM.convertUserEToUserM(userRepo.getUserByUsernameAndEmail(userDto.getUsername(), userDto.getEmail()));
//
//        try {
//            if (!this.isUserExist(userDto)) {
//                return "Email not registered yet!!";
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        if (!PasswordEncoderUtil.verifyPassword(userDto.getPassword(), userM.getHashedPassword())) {
//            System.out.println(PasswordEncoderUtil.verifyPassword("123", "e71f60c54423de2e70cfaef0b89e565d1be007c038854dd206dff45491af6b988fe025a688fbe218") +
//                    "/naccountE verify: " + PasswordEncoderUtil.verifyPassword("123", "47be8c36c20369f8ca8f665267661c0e873a4b17370a42809b18300864434fb35d880be64134fb65"));
//            return "Wrong password! Please check & try again!!";
//        }
//        if (userM.getRoleId() == 3) {
//            return "Authorization failed!!";
//        }
//        return "Login Succeed";
//    }
////
//    @Override
//    public void saveToSession(HttpSession httpSession, UserDto userDto) {
//        userDto.setHashedPassword(userDto.getPassword());
//        System.out.println("status: " + checkUserAdminRole(userDto));
//        if (checkUserAdminRole(userDto).equals("Login Succeed")) {
//            httpSession.setAttribute("adminToken", PasswordEncoderUtil.encodePassword(userDto.getUsername() + userDto.getEmail()));
//            System.out.println("session value: " + httpSession.getAttribute("adminToken") +
//                    "\nraw value: " + PasswordEncoderUtil.encodePassword(userDto.getUsername() + userDto.getEmail()));
//        } else {
//            httpSession.removeAttribute("adminToken");
//            System.out.println("fail session");
//        }
//    }

}
