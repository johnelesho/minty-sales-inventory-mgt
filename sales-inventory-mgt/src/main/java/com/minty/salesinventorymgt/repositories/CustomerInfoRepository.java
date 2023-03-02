package com.minty.salesinventorymgt.repositories;


import com.minty.lib.models.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfo, Long> {

    boolean existsByEmailAddressIgnoreCase(String email);

    boolean existsByPhoneNumber(String email);

    boolean existsByUsernameIgnoreCase(String email);

    @Query(value = """
            select distinct  c
            from CustomerInfo c
            where lower(c.emailAddress) = :value
            or lower(c.phoneNumber) = :value
            or lower(c.username) = :value
            """
    )
    boolean existsByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase(@Param("value") String unique);

    boolean existsByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase(String email, String phone, String username);

    @Query(value = """
            select distinct  c
            from CustomerInfo c
            where lower(c.emailAddress) = :value
            or lower(c.phoneNumber) = :value
            or lower(c.username) = :value
            """
    )
    Optional<CustomerInfo> findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase(@Param("value") String uniqueKey);

    Optional<CustomerInfo> findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase(String email, String phone, String username);
}