package com.dmurealfinal.vicmag.domain.entity.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends JpaRepository<Company, String> {

    @Query("SELECT c FROM Company AS c WHERE c.accountId = :companyId")
    Company findByCompanyId(@Param("companyId") String companyId);

    @Modifying
    @Query("UPDATE Company c SET " +
            "c.email = :email, " +
            "c.name = :name, " +
            "c.phone = :phone, " +
            "c.companyRegistrationNumber = :companyRegistrationNumber " +
            "WHERE c.accountId = :companyId")
    int updateCompany(@Param("companyId") String companyId, @Param("email") String email, @Param("name") String name, @Param("phone") String phone, @Param("companyRegistrationNumber") String companyRegistrationNumber);
}