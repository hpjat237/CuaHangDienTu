package vn.thietbidientu.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import vn.thietbidientu.dto.VoucherDTO;
import vn.thietbidientu.entity.Voucher;
import vn.thietbidientu.exception.CustomException;
import vn.thietbidientu.repository.VoucherRepository;
import vn.thietbidientu.service.interfaces.IVoucherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoucherService implements IVoucherService {
    @Autowired
    VoucherRepository voucherRepository;

    public Voucher getVoucherByVoucherCode(String voucherCode) {
        return voucherRepository.findFirstByVoucherCodeAndUsedFalseAndStartDateBeforeAndEndDateAfter(voucherCode, LocalDateTime.now(), LocalDateTime.now()).orElseThrow(() -> new CustomException("Voucher " + voucherCode + " is not available"));
    }

    @Override
    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    @Override
    public Voucher saveVoucher(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    @Override
    public void addVoucher(VoucherDTO voucher) {
        Long quantity = voucher.getQuantity();
        for (int i = 0; i < quantity; i++) {
            Voucher voucherEntity = new Voucher();
            voucherEntity.setVoucherCode(voucher.getVoucherCode());
            voucherEntity.setVoucherValue(voucher.getVoucherValue());
            voucherEntity.setStartDate(voucher.getStartDate());
            voucherEntity.setEndDate(voucher.getEndDate());
            voucherRepository.save(voucherEntity);
        }
    }

    @Override
    public Voucher updateVoucher(Long id, Voucher updatedVoucher) {
        Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy voucher"));

        if (voucher.getUsed()) {
            throw new IllegalStateException("Không thể chỉnh sửa voucher do mã đang được sử dụng cho đơn hàng hệ thống.");
        }

        voucher.setVoucherCode(updatedVoucher.getVoucherCode());
        voucher.setVoucherValue(updatedVoucher.getVoucherValue());
        voucher.setStartDate(updatedVoucher.getStartDate());
        voucher.setEndDate(updatedVoucher.getEndDate());
        return voucherRepository.save(voucher);
    }

    @Override
    public Voucher updateVoucher(Voucher updatedVoucher) {
        Voucher voucher = voucherRepository.findById(updatedVoucher.getVoucherId())
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy voucher"));

        if (voucher.getUsed()) {
            throw new IllegalStateException("Không thể chỉnh sửa voucher do mã đang được sử dụng cho đơn hàng hệ thống.");
        }

        voucher.setVoucherCode(updatedVoucher.getVoucherCode());
        voucher.setVoucherValue(updatedVoucher.getVoucherValue());
        voucher.setStartDate(updatedVoucher.getStartDate());
        voucher.setEndDate(updatedVoucher.getEndDate());
        return voucherRepository.save(voucher);
    }

    //cần thiết vì là phuonương thức deleteAll
    @Transactional
    @Override
    public void deleteVoucher(Long id) {
        //lấy ra mã voucher
        Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy voucher"));
        String voucherCode = voucher.getVoucherCode();
        // chỉ xóa những voucher chưa được sử dụng
        voucherRepository.deleteAllByVoucherCodeAndUsedFalse(voucherCode);
    }

    @Override
    public Voucher getVoucherById(Long id) {
        return voucherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Voucher ID " + id + " không tồn tại"));
    }

    @Override
    public boolean existsByVoucherCode(String voucherCode) {
        return voucherRepository.existsByVoucherCode(voucherCode);
    }

    @Override
    public Page<Voucher> getVouchersWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("startDate").descending());
//        return voucherRepository.findAll(pageable);
        return voucherRepository.findDistinctVouchers(pageable);
    }

    @Override
    public Long countByVoucherCode(String voucherCode) {
        return voucherRepository.countByVoucherCode(voucherCode);
    }

    @Override
    public Long countByUsedTrueAndVoucherCode(String voucherCode) {
        return voucherRepository.countByUsedTrueAndVoucherCode(voucherCode);
    }

    @Override
    public Long countByUsedFalseAndVoucherCode(String voucherCode) {
        return voucherRepository.countByUsedFalseAndVoucherCode(voucherCode);
    }

    @Override
    public void updateVoucher(@Valid VoucherDTO voucher) {
        Voucher voucherEntity = voucherRepository.findById(voucher.getVoucherId())
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy voucher"));

        //get voucherCode
        String voucherCode = voucherEntity.getVoucherCode();
        //lấy chênh lệch giữa số lượng cũ và số lượng mới
        Long quantity = voucherRepository.countByUsedFalseAndVoucherCode(voucherCode);
        Long newQuantity = voucher.getQuantityAvailable();
        long diff = Math.abs(newQuantity - quantity);
        if (newQuantity < quantity) {
            for (int i = 0; i < diff; i++) {
                Voucher voucherToDelete = voucherRepository.findFirstByVoucherCodeAndUsedFalse(voucherCode)
                        .orElseThrow(() -> new CustomException("Voucher " + voucherCode + " đã được sử dụng"));
                voucherRepository.delete(voucherToDelete);
            }
        }else if (newQuantity > quantity) {
            for (int i = 0; i < diff; i++) {
                Voucher newVoucher = new Voucher();
                newVoucher.setVoucherCode(voucherCode);
                newVoucher.setVoucherValue(voucher.getVoucherValue());
                newVoucher.setStartDate(voucher.getStartDate());
                newVoucher.setEndDate(voucher.getEndDate());
                voucherRepository.save(newVoucher);
            }
        }
    }

    @Override
    public Page<Voucher> getVouchersWithSearch(String searchTerm, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("startDate").descending());
        return voucherRepository.findDistinctVouchersWithSearch(searchTerm, pageable);
    }
}
