package com.dmurealfinal.vicmag.service;

import com.dmurealfinal.vicmag.domain.dto.AccountDTO;
import com.dmurealfinal.vicmag.domain.dto.CompanyDTO;
import com.dmurealfinal.vicmag.domain.dto.UserDTO;
import com.dmurealfinal.vicmag.domain.entity.account.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;

    /** 전체 계정 조회(테스트) */
    public List<Account> findAccounts() {
        return accountRepository.findAll();
    }

    /** 유저 상세 조회 */
    @Transactional
    public UserDTO findUser(String userId) {
        User user = userRepository.findByUserId(userId);

        if(user == null) return null;

        UserDTO result = user.toDTO();
        return result;
    }

    /** 잡지사 상세 조회 */
    public CompanyDTO findCompany(String companyId) {
        Company company = companyRepository.findByCompanyId(companyId);

        if(company == null) return null;

        CompanyDTO result = company.toDTO();
        return result;
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
}
