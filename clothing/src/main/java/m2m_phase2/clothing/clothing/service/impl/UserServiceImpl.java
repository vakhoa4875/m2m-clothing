package m2m_phase2.clothing.clothing.service.impl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import m2m_phase2.clothing.clothing.constant.CommonEnum;
import m2m_phase2.clothing.clothing.data.dto.UserDto;
import m2m_phase2.clothing.clothing.data.dto.VoucherDetailsDto;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.data.model.UserM;
import m2m_phase2.clothing.clothing.repository.UserRepo;
import m2m_phase2.clothing.clothing.security.service.AuthService;
import m2m_phase2.clothing.clothing.service.AccountService;
import m2m_phase2.clothing.clothing.service.UserService;
import m2m_phase2.clothing.clothing.utils.CommonFunction;
import m2m_phase2.clothing.clothing.utils.DateUtils;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final AccountService accountService;
    private final AuthService authService;

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
        return 0;
    }

    @Override
    public boolean isUserExist(UserDto userDto) throws SQLException {
        var user = userRepo.getUserByUsernameAndEmail(userDto.getUsername(), userDto.getEmail());
        return Objects.nonNull(user);
    }

    @Override
    public byte saveUser(UserDto userDto) throws SQLException {
        if (this.isUserExist(userDto)) {
            var avatar = userDto.getAvatar().contains(",")
                    ? CommonFunction.handleBase64Img(userDto.getAvatar(), CommonEnum.imagesUser.getValue())
                    : userDto.getAvatar();
            userRepo.updateUser(userDto.getUsername(),
                    userDto.getEmail(),
                    userDto.getFullname(),
                    userDto.getGender(),
                    userDto.getRoleId(),
                    userDto.getRoleName(),
                    userDto.getDescription(),
                    userDto.getJobTitle(),
                    DateUtils.toDateFormat(userDto.getDob(), "dd/MM/yyyy", "yyyy-MM-dd"),
                    avatar);
        } else {
            userRepo.insertNewUser(userDto.getUsername(),
                    userDto.getEmail(),
                    userDto.getFullname(),
                    userDto.getHashedPassword(),
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
        var account = accountService.findByemail(userDto.getEmail());
        if (!account.isAdmin()) {
            return 0;
        }
        if (!PasswordEncoderUtil.verifyPassword(userDto.getPassword(), account.getHashedPassword())) {
            return 2;
        }
        return 1;
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

    @Override
    public UserM getCurrentUser() throws SQLException {
        var currentUserEmail = authService.getCurrentUserEmail();
        var userE = userRepo.findByEmail(currentUserEmail);
        return userE != null ? UserM.convertUserEToUserM(userE) : null;
    }
}
