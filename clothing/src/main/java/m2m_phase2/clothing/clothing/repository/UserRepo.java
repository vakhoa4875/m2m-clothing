package m2m_phase2.clothing.clothing.repository;

import jakarta.transaction.Transactional;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.data.entity.VoucherE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserE, Integer> {
    //M2M- 010 TanLoc Begin
    UserE save(UserE userE);
    //M2M- 010 TanLoc End
    @Query(value = "select * from [user] u where u.is_disable <> 1", nativeQuery = true)
    List<UserE> findAll();
    @Modifying
    @Transactional
    @Query(value = "insert into [user] (username, email, fullname, hashed_pass, role_id, role_name, description, processed) " +
            "values (:username, :email, :fullname, :hashedPassword, :roleId, :roleName, :description, 1)", nativeQuery = true)
    void insertNewUser(@Param("username")String username,
                       @Param("email")String email,
                       @Param("fullname")String fullname,
                       @Param("hashedPassword")String hashedPassword,
//                       @Param("gender")String gender,
                       @Param("roleId")Integer roleId,
                       @Param("roleName")String roleName,
                       @Param("description")String description);

//    @Override
//    <UserDto extends UserE> UserDto save(UserDto userDto);
    @Modifying
    @Transactional
    @Query(value =  "update [user] " +
                    "set hashed_pass = :hashedPassword," +
                    "fullname = :fullname," +
                    "gender = :gender," +
                    "role_id = :roleId," +
                    "role_name = :roleName," +
                    "description = :description," +
                    "job_title = :jobTitle," +
                    "dob = :dob," +
                    "avatar = :avatar " +
                    "where username = :username and email = :email", nativeQuery = true)
    void updateUser(@Param("username")String username,
                   @Param("email")String email,
                   @Param("fullname")String fullname,
                   @Param("hashedPassword")String hashedPassword,
                   @Param("gender")String gender,
                   @Param("roleId")Integer roleId,
                   @Param("roleName")String roleName,
                    @Param("description")String description,
                    @Param("jobTitle")String jobTitle,
                   @Param("dob")String dob,
                   @Param("avatar")String avatar);

    @Modifying
    @Transactional
    @Query(value =  "update [user] " +
            "set is_disable = 1 " +
            "where username = :username and email = :email", nativeQuery = true)
    void disableUser(@Param("username")String username, @Param("email")String email);
    @Query( value =
            "select *" +
            "from [user] u " +
            "where  (:username is null or u.username = :username) " +
            "       and (:email is null or u.email = :email) " +
                    "and (u.is_disable <> 1)", nativeQuery = true)
    UserE getUserByUsernameAndEmail(@Param("username")String username, @Param("email")String email);
//    @Query( value = "select * " +
//            "from [user] u " +
//            "where  (:dto.username is null or u.username = :dto.username)" +
//            "       and (:dto.email is null or u.email = :dto.email)", nativeQuery = true)
//    List<UserE> getUserByDto(@Param("dto")UserDto userDto);


    @Query( value =
            "select * " +
                    "from [user] u " +
                    "where (:email is null or u.email = :email) " +
                    "and (u.is_disable <> 1)", nativeQuery = true)
    UserE getUserByEmail(@Param("email")String email);

    @Modifying
    @Transactional
    @Query(value =  "update [user] set "+
            "fullname = :fullname," +
            "sdt = :sdt, " +
            "avatar = :avatar " +
            "where username = :username and email = :email", nativeQuery = true)
    void updateUserInfo(@Param("username")String username,
                    @Param("email")String email,
                    @Param("fullname")String fullname,
                    @Param("sdt")String sdt,
                    @Param("avatar")String avatar);

    @Query(value = "select * from [user] u where u.id not in (select distinct v.user_id from VoucherDetails v where v.voucher_id = :voucherID)", nativeQuery = true)
    List<UserE> findUserNotInVoucher(@Param("voucherID")Integer voucherID);
}
