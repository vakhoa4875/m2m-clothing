package m2m_phase2.clothing.clothing.repository;

import jakarta.transaction.Transactional;
import m2m_phase2.clothing.clothing.data.entity.VoucherDetailsE;
import m2m_phase2.clothing.clothing.data.entity.VoucherE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface VoucherDetailsRepo extends JpaRepository<VoucherDetailsE, Integer> {

    @Modifying
    @Transactional
    @Query(value = "insert into VoucherDetails (voucher_id, user_id) values (:voucherID,:userID)", nativeQuery = true)
    void insertVoucherDetails(@Param("voucherID")Integer voucherID,
                       @Param("userID")Long userID);

}
