package m2m_phase2.clothing.clothing.repository;

import jakarta.transaction.Transactional;
import m2m_phase2.clothing.clothing.data.entity.VoucherDetailsE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherDetailsRepo extends JpaRepository<VoucherDetailsE, Integer> {

    @Modifying
    @Transactional
    @Query(value = "insert into voucher_details (voucher_id, user_id) values (:voucherID,:userID)", nativeQuery = true)
    void insertVoucherDetails(@Param("voucherID")Integer voucherID,
                       @Param("userID")Long userID);

    @Modifying
    @Transactional
    @Query(value = "delete from voucher_details WHERE voucher_id = :voucherID AND user_id = :userID", nativeQuery = true)
    void deleteVoucherDetails(@Param("voucherID")Integer voucherID,
                              @Param("userID")Long userID);

    @Query( value =
            "select * " +
                    "from voucher_details vd " +
                    "where (vd.voucher_id = :voucherID) " +
                    "       and (vd.user_id = :userID) " , nativeQuery = true)
    VoucherDetailsE getVoucherDetailsByVoucherIDAndUserID(@Param("voucherID")Integer voucherID,@Param("userID")Long userID);


}
