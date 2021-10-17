package com.dmurealfinal.vicmag.service;

import com.dmurealfinal.vicmag.domain.dto.*;
import com.dmurealfinal.vicmag.domain.entity.account.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    KaKaoAccountRepository kaKaoAccountRepository;
    @Autowired
    AdminRepository adminRepository;


    /** 전체 계정 조회(테스트) */
    public List<AccountDTO> findAccounts() {
        return Account.toDTOList(accountRepository.findAll());
    }

    /** 계정 ID로 조회 */
    @Transactional
    public AccountDTO findAccountById(String accountId) {
        Account account = accountRepository.findById(accountId).get();
        if(account == null) return null;

        AccountDTO result = account.toDTO();
        if(account.getAccountType().equals("user")) {
            result.setUser(account.getUser().toDTO());
            if(account.getKaKaoAccount() != null) {
                result.setKakaoAccount(account.getKaKaoAccount().toDTO());
            }
        } else if(account.getAccountType().equals("company")) {
            result.setCompany(account.getCompany().toDTO());
        } else if(account.getAccountType().equals("admin")) {
            result.setAdmin(account.getAdmin().toDTO());
        }

        return result;
    }

    /** 유저 상세 조회 */
    @Transactional
    public UserDTO findUser(String userId) {
        User user = userRepository.findByUserId(userId);

        if(user == null) return null;

        UserDTO result = user.toDTO();
        Optional<KaKaoAccount> kaKaoAccount = kaKaoAccountRepository.findById(userId);

        if(!kaKaoAccount.isEmpty()) {
            result.setKakaoAccount(kaKaoAccount.get().toDTO());
        }

        return result;
    }

    /** 잡지사 상세 조회 */
    public CompanyDTO findCompany(String companyId) {
        Company company = companyRepository.findByCompanyId(companyId);

        if(company == null) return null;

        CompanyDTO result = company.toDTO();
        Optional<KaKaoAccount> kaKaoAccount = kaKaoAccountRepository.findById(companyId);

        if(!kaKaoAccount.isEmpty()) {
            result.setKakaoAccount(kaKaoAccount.get().toDTO());
        }

        return company.toDTO();
    }

    /** 유저 추가 */
    @Transactional
    public void saveUser(AccountDTO accountDTO, UserDTO userDTO) {
        accountRepository.save(accountDTO.toEntity());
        userRepository.save(userDTO.toEntity());
    }

    /** 잡지사 추가 */
    @Transactional
    public void saveCompany(AccountDTO accountDTO, CompanyDTO companyDTO) {
        accountRepository.save(accountDTO.toEntity());
        companyRepository.save(companyDTO.toEntity());
    }

    /** 관리자 추가 */
    @Transactional
    public void saveAdmin(AccountDTO accountDTO, AdminDTO adminDTO) {
        accountRepository.save(accountDTO.toEntity());
        adminRepository.save(adminDTO.toEntity());
    }

    /** 유저 정보 수정 */
    @Transactional
    public void updateUser(UserDTO userDTO) {
        userRepository.updateUser(userDTO.getAccountId(), userDTO.getEmail(), userDTO.getName(), userDTO.getPhone());
    }

    /** 잡지사 정보 수정 */
    @Transactional
    public void updateCompany(CompanyDTO companyDTO) {
        companyRepository.updateCompany(companyDTO.getAccountId(), companyDTO.getEmail(), companyDTO.getName(), companyDTO.getPhone(), companyDTO.getCompanyRegistrationNumber());
    }

    /** 계정 삭제 */
    @Transactional
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
        accountRepository.deleteById(userId);
    }

    /** 잡지사 삭제 */
    @Transactional
    public void deleteCompany(String companyId) {
        companyRepository.deleteById(companyId);
        accountRepository.deleteById(companyId);
    }

    /** 카카오 계정 연동 */
    @Transactional
    public boolean saveKakaoAccount(KakaoAccountDTO kakaoAccountDTO) {
        KaKaoAccount exist = kaKaoAccountRepository.findByKakaoIdNumberAndAccountId(kakaoAccountDTO.getKakaoIdNumber(), kakaoAccountDTO.getAccountId());
        if(exist == null) {
            KaKaoAccount kaKaoAccount = kaKaoAccountRepository.save(kakaoAccountDTO.toEntity());
            return (kaKaoAccount != null);
        } else { // 이미 연동된 카카오 계정
            return false;
        }
    }

    /** 카카오 ID Number로 Account 찾기 */
    @Transactional
    public AccountDTO findAccountByKakaoIdNumber(Long kakaoIdNumber) {
        Account account = kaKaoAccountRepository.findAccountByKakaoIdNumber(kakaoIdNumber);
        if(account == null) {
            return null;
        } else {
            AccountDTO result = account.toDTO();
            if(account.getAccountType().equals("user")) {
                result.setUser(account.getUser().toDTO());
            } else if(account.getAccountType().equals("company")) {
                result.setCompany(account.getCompany().toDTO());
            }
            return result;
        }
    }

    /** 비밀번호 변경 */
    @Transactional
    public void updatePassword(AccountDTO accountDTO) {
        accountRepository.updatePassword(accountDTO.getAccountId(), accountDTO.getPassword(), LocalDateTime.now());
    }
}
