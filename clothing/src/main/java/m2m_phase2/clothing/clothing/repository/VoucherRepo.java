package m2m_phase2.clothing.clothing.repository;

import jakarta.transaction.Transactional;
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
    @Query(value = "insert into Voucher (voucher_name, reduce,quantity, start_day, end_day) " +
            "values (:voucherName, :reduce, :quantity, :startDay, :endDay)", nativeQuery = true)
    void insertVoucher(@Param("voucherName")String voucherName,
                       @Param("reduce")Integer reduce,
                       @Param("quantity")Integer quantity,
                       @Param("startDay")Date startDay,
                       @Param("endDay") Date endDay);

    @Modifying
    @Transactional
    @Query(value =  "update Voucher set "+
            "voucher_name = :voucherName," +
            "reduce = :reduce, " +
            "quantity = :quantity, " +
            "start_day = :startDay, " +
            "end_day = :endDay " +
            "where voucher_id = :voucherID", nativeQuery = true)
    void updateVoucher(@Param("voucherName")String voucherName,
                        @Param("reduce")Integer reduce,
                        @Param("quantity")Integer quantity,
                        @Param("startDay")Date startDay,
                        @Param("endDay")Date endDay,
                        @Param("voucherID")Integer voucherID);

    @Query(value = "select v.voucher_id," +
            "              v.voucher_name," +
            "              v.reduce," +
            "              v.quantity," +
            "              v.start_day," +
            "              v.end_day from Voucher v " +
            "       join voucher_details vd on v.voucher_id = vd.voucher_id " +
            "       join [user] u on u.id = vd.user_id"+
            "       where u.email = :email AND v.end_day > GETDATE()", nativeQuery = true)
    List<VoucherE> findVouchersInfoByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "delete from Voucher WHERE :toDay > end_day", nativeQuery = true)
    void deleteVoucherByDay(@Param("toDay")Date toDay);

    VoucherE findByVoucherID(Integer voucherID);
}
