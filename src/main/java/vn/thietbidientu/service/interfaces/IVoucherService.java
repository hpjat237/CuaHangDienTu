package vn.thietbidientu.service.interfaces;

import jakarta.validation.Valid;
import vn.thietbidientu.dto.VoucherDTO;
import vn.thietbidientu.entity.Voucher;

import org.springframework.data.domain.Page;

import java.util.List;

public interface IVoucherService {
    List<Voucher> getAllVouchers();

    Voucher saveVoucher(Voucher voucher);

    void addVoucher(VoucherDTO voucher);

    Voucher updateVoucher(Long id, Voucher updatedVoucher);

    Voucher updateVoucher(Voucher updatedVoucher);

    void deleteVoucher(Long id);

    Voucher getVoucherById(Long id);

    boolean existsByVoucherCode(String voucherCode);

    Page<Voucher> getVouchersWithPagination(int page, int size);

    Long countByVoucherCode(String voucherCode);

    Long countByUsedTrueAndVoucherCode(String voucherCode);

    Long countByUsedFalseAndVoucherCode(String voucherCode);

    void updateVoucher(@Valid VoucherDTO voucher);

    Page<Voucher> getVouchersWithSearch(String searchTerm, int page, int size);
}