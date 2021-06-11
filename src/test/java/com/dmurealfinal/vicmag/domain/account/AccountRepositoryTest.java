package com.dmurealfinal.vicmag.domain.account;

import com.dmurealfinal.vicmag.domain.dto.AccountDTO;
import com.dmurealfinal.vicmag.domain.dto.CompanyDTO;
import com.dmurealfinal.vicmag.domain.dto.UserDTO;
import com.dmurealfinal.vicmag.domain.entity.account.*;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@TestPropertySource("classpath:application-test.properties")
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyRepository companyRepository;

    @After
    public void cleanup(){
        accountRepository.deleteAll();
        userRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    @Transactional
    public void addUserAndCompany(){
        AccountDTO accountUser = new AccountDTO();
        accountUser.setAccountId("testUser");
        accountUser.setPassword("test");
        accountUser.setAccountType("user");
        UserDTO user = new UserDTO();
        user.setAccountId(accountUser.getAccountId());
        user.setName("테스트");
        user.setPhone("010-1111-1111");
        user.setEmail("testUser@test.com");

        AccountDTO accountCompany = new AccountDTO();
        accountCompany.setAccountId("testCompany");
        accountCompany.setPassword("test");
        accountCompany.setAccountType("company");
        CompanyDTO company = new CompanyDTO();
        company.setAccountId(accountCompany.getAccountId());
        company.setCompanyRegistrationNumber("123456789");
        company.setName("테스트잡지사");
        company.setPhone("010-2222-2222");
        company.setEmail("testCompany@test.com");

        //  유저 생성
        Account accountUserEntity = Account.builder()
                .accountId(accountUser.getAccountId())
                .password(accountUser.getPassword())
                .accountType(accountUser.getAccountType())
                .build();
        accountRepository.save(accountUserEntity);

        userRepository.save(User.builder()
                .accountId(user.getAccountId())
                .name(user.getName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build()
        );

        // 잡지사 생성
        Account accountCompanyEntity = Account.builder()
                .accountId(accountCompany.getAccountId())
                .password(accountCompany.getPassword())
                .accountType(accountCompany.getAccountType())
                .build();

        accountRepository.save(accountCompanyEntity);

        companyRepository.save(Company.builder()
                .accountId(company.getAccountId())
                .companyRegistrationNumber(company.getCompanyRegistrationNumber())
                .name(company.getName())
                .phone(company.getPhone())
                .email(company.getEmail())
                .build()
        );

        // when
        List<User> userList = userRepository.findAll();
        List<Company> companyList = companyRepository.findAll();

        // then
        User userData = userList.get(0);
        assertThat(userData.getAccountId()).isEqualTo(accountUser.getAccountId());

        Company companyData = companyList.get(0);
        assertThat(companyData.getAccountId()).isEqualTo(accountCompany.getAccountId());

        Account accountData = accountRepository.findById(companyData.getAccountId()).get();

        System.out.println("company account id(account) : " + accountData.getAccountId());
        System.out.println("company account id(company) : " + companyData.getAccount().getAccountId());
        System.out.println("user account id : " + userData.getAccount().getAccountId());
    }
}
