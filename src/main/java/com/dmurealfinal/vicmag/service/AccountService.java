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

    public List<Account> findAccounts() {
        return accountRepository.findAll();
    }
}
