package m2m_phase2.clothing.clothing.repository;

import m2m_phase2.clothing.clothing.data.dto.UserDto;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserE, Integer> {
    @Query(value = "select * from [user] u where u.is_disable <> 1", nativeQuery = true)
    List<UserE> findAll();
    @Query(value = "insert into [user] (username, email, fullname, hashed_pass, gender, roleId, roleName, description) " +
            "values (:username, :email, :fullname, :hashedPassword, :gender, :roleId, :roleName, :description)", nativeQuery = true)
    byte insertNewUser(@Param("username")String username,
                       @Param("email")String email,
                       @Param("fullname")String fullname,
                       @Param("hashedPassword")String hashedPassword,
                       @Param("gender")String gender,
                       @Param("roleId")Integer roleId,
                       @Param("roleName")String roleName,
                       @Param("description")String description);
    @Query(value = "update [user] " +
            "set hashed_pass = :hashedPassword," +
            "fullname = :fullname," +
            "gender = :gender," +
            "role_id = :roleId," +
            "role_name = :roleName," +
            "description = :description," +
            "job_title = :jobTitle," +
            "dob = :dob," +
            "avatar = :avatar," +
            "where username = :username and email = :email", nativeQuery = true)
    byte updateUser(@Param("username")String username,
                   @Param("email")String email,
                   @Param("fullname")String fullname,
                   @Param("hashedPassword")String hashedPassword,
                   @Param("gender")String gender,
                   @Param("roleId")Integer roleId,
                   @Param("roleName")String roleName,
                   @Param("description")String description,
                   @Param("dob")String dob,
                   @Param("avatar")String avatar);
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

}
