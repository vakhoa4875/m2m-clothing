package m2m_phase2.clothing.clothing.repository;

import jakarta.transaction.Transactional;
import m2m_phase2.clothing.clothing.data.entity.UserE;
import m2m_phase2.clothing.clothing.data.entity.VoucherE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VoucherRepo extends JpaRepository<VoucherE, Integer> {
    @Query(value = "select * from Voucher where 1=1", nativeQuery = true)
    List<VoucherE> findAll();

    @Query(value = "select * from Voucher where voucher_id = :voucherID", nativeQuery = true)
    VoucherE findVoucherByID(@Param("voucherID")Integer voucherID);

    @Modifying
    @Transactional
    @Query(value = "insert into Voucher (voucher_name, reduce, start_day, end_day, user_id) " +
            "values (:voucherName, :reduce, :startDay, :endDay, :userID)", nativeQuery = true)
    void insertVoucher(@Param("voucherName")String voucherName,
                       @Param("reduce")Integer reduce,
                       @Param("startDay")Date startDay,
                       @Param("endDay") Date endDay,
                       @Param("userID")Integer userID);

    @Modifying
    @Transactional
    @Query(value =  "update Voucher set "+
            "voucher_name = :voucherName," +
            "reduce = :reduce, " +
            "start_day = :startDay, " +
            "end_day = :endDay " +
            "where voucher_id = :voucherID", nativeQuery = true)
    void updateVoucher(@Param("voucherName")String voucherName,
                        @Param("reduce")Integer reduce,
                        @Param("startDay")Date startDay,
                        @Param("endDay")Date endDay,
                        @Param("voucherID")Integer voucherID);
}
