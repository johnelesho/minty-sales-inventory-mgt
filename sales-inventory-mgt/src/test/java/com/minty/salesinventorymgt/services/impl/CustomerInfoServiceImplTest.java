package com.minty.salesinventorymgt.services.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.minty.lib.dtos.request.CustomerInfoRequest;
import com.minty.lib.dtos.response.CustomerInfoResponse;
import com.minty.lib.mappers.HelpMapper;
import com.minty.lib.models.CustomerInfo;
import com.minty.salesinventorymgt.exceptions.BadRequestException;
import com.minty.salesinventorymgt.exceptions.NotFoundException;
import com.minty.salesinventorymgt.repositories.CustomerInfoRepository;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomerInfoServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CustomerInfoServiceImplTest {
    @MockBean
    private CustomerInfoRepository customerInfoRepository;

    @Autowired
    private CustomerInfoServiceImpl customerInfoServiceImpl;

    @MockBean
    private HelpMapper helpMapper;

    /**
     * Method under test: {@link CustomerInfoServiceImpl#isExist(String)}
     */
    @Test
    void testIsExist() {
        when(customerInfoRepository.existsByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any()))
                .thenReturn(true);
        assertTrue(customerInfoServiceImpl.isExist("Unique Key"));
        verify(customerInfoRepository).existsByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link CustomerInfoServiceImpl#isExist(String)}
     */
    @Test
    void testIsExist2() {
        when(customerInfoRepository.existsByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any()))
                .thenReturn(false);
        assertFalse(customerInfoServiceImpl.isExist("Unique Key"));
        verify(customerInfoRepository).existsByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link CustomerInfoServiceImpl#isExist(String)}
     */
    @Test
    void testIsExist3() {
        when(customerInfoRepository.existsByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any()))
                .thenThrow(new BadRequestException("An error occurred"));
        assertThrows(BadRequestException.class, () -> customerInfoServiceImpl.isExist("Unique Key"));
        verify(customerInfoRepository).existsByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link CustomerInfoServiceImpl#findOneEntity(String)}
     */
    @Test
    void testFindOneEntity() {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setAddress("42 Main St");
        customerInfo.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setEmailAddress("42 Main St");
        customerInfo.setFullName("Dr Jane Doe");
        customerInfo.setId(1L);
        customerInfo.setPhoneNumber("6625550144");
        customerInfo.setUpdatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setUsername("janedoe");
        Optional<CustomerInfo> ofResult = Optional.of(customerInfo);
        when(customerInfoRepository.findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any()))
                .thenReturn(ofResult);
        assertSame(customerInfo, customerInfoServiceImpl.findOneEntity("Unique Key"));
        verify(customerInfoRepository).findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link CustomerInfoServiceImpl#findOneEntity(String)}
     */
    @Test
    void testFindOneEntity2() {
        when(customerInfoRepository.findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any()))
                .thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> customerInfoServiceImpl.findOneEntity("Unique Key"));
        verify(customerInfoRepository).findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link CustomerInfoServiceImpl#findOneEntity(String)}
     */
    @Test
    void testFindOneEntity3() {
        when(customerInfoRepository.findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any()))
                .thenThrow(new BadRequestException("An error occurred"));
        assertThrows(BadRequestException.class, () -> customerInfoServiceImpl.findOneEntity("Unique Key"));
        verify(customerInfoRepository).findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link CustomerInfoServiceImpl#findOne(String)}
     */
    @Test
    void testFindOne() {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setAddress("42 Main St");
        customerInfo.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setEmailAddress("42 Main St");
        customerInfo.setFullName("Dr Jane Doe");
        customerInfo.setId(1L);
        customerInfo.setPhoneNumber("6625550144");
        customerInfo.setUpdatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setUsername("janedoe");
        Optional<CustomerInfo> ofResult = Optional.of(customerInfo);
        when(customerInfoRepository.findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any()))
                .thenReturn(ofResult);
        CustomerInfoResponse customerInfoResponse = new CustomerInfoResponse();
        when(helpMapper.convertToCustomerResonse((CustomerInfo) any())).thenReturn(customerInfoResponse);
        assertSame(customerInfoResponse, customerInfoServiceImpl.findOne("Unique Key"));
        verify(customerInfoRepository).findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any());
        verify(helpMapper).convertToCustomerResonse((CustomerInfo) any());
    }

    /**
     * Method under test: {@link CustomerInfoServiceImpl#findOne(String)}
     */
    @Test
    void testFindOne2() {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setAddress("42 Main St");
        customerInfo.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setEmailAddress("42 Main St");
        customerInfo.setFullName("Dr Jane Doe");
        customerInfo.setId(1L);
        customerInfo.setPhoneNumber("6625550144");
        customerInfo.setUpdatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setUsername("janedoe");
        Optional<CustomerInfo> ofResult = Optional.of(customerInfo);
        when(customerInfoRepository.findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any()))
                .thenReturn(ofResult);
        when(helpMapper.convertToCustomerResonse((CustomerInfo) any()))
                .thenThrow(new BadRequestException("An error occurred"));
        assertThrows(BadRequestException.class, () -> customerInfoServiceImpl.findOne("Unique Key"));
        verify(customerInfoRepository).findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any());
        verify(helpMapper).convertToCustomerResonse((CustomerInfo) any());
    }

    /**
     * Method under test: {@link CustomerInfoServiceImpl#findOne(String)}
     */
    @Test
    void testFindOne3() {
        when(customerInfoRepository.findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any()))
                .thenReturn(Optional.empty());
        when(helpMapper.convertToCustomerResonse((CustomerInfo) any())).thenReturn(new CustomerInfoResponse());
        assertThrows(NotFoundException.class, () -> customerInfoServiceImpl.findOne("Unique Key"));
        verify(customerInfoRepository).findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link CustomerInfoServiceImpl#addNew(CustomerInfoRequest)}
     */
    @Test
    void testAddNew() throws ParseException {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setAddress("42 Main St");
        customerInfo.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setEmailAddress("42 Main St");
        customerInfo.setFullName("Dr Jane Doe");
        customerInfo.setId(1L);
        customerInfo.setPhoneNumber("6625550144");
        customerInfo.setUpdatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setUsername("janedoe");
        when(customerInfoRepository.existsByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any()))
                .thenReturn(true);
        when(customerInfoRepository.save((CustomerInfo) any())).thenReturn(customerInfo);

        CustomerInfo customerInfo1 = new CustomerInfo();
        customerInfo1.setAddress("42 Main St");
        customerInfo1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo1.setEmailAddress("42 Main St");
        customerInfo1.setFullName("Dr Jane Doe");
        customerInfo1.setId(1L);
        customerInfo1.setPhoneNumber("6625550144");
        customerInfo1.setUpdatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo1.setUsername("janedoe");
        when(helpMapper.convertToCustomerEntity((CustomerInfoRequest) any())).thenReturn(customerInfo1);
        assertThrows(BadRequestException.class, () -> customerInfoServiceImpl
                .addNew(new CustomerInfoRequest("Dr Jane Doe", "6625550144", "42 Main St", "janedoe", "42 Main St")));
        verify(customerInfoRepository).existsByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any());
        verify(helpMapper).convertToCustomerEntity((CustomerInfoRequest) any());
    }

    /**
     * Method under test: {@link CustomerInfoServiceImpl#addNew(CustomerInfoRequest)}
     */
    @Test
    void testAddNew2() throws ParseException {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setAddress("42 Main St");
        customerInfo.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setEmailAddress("42 Main St");
        customerInfo.setFullName("Dr Jane Doe");
        customerInfo.setId(1L);
        customerInfo.setPhoneNumber("6625550144");
        customerInfo.setUpdatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setUsername("janedoe");
        when(customerInfoRepository.existsByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any()))
                .thenReturn(true);
        when(customerInfoRepository.save((CustomerInfo) any())).thenReturn(customerInfo);
        when(helpMapper.convertToCustomerEntity((CustomerInfoRequest) any()))
                .thenThrow(new BadRequestException("An error occurred"));
        assertThrows(BadRequestException.class, () -> customerInfoServiceImpl
                .addNew(new CustomerInfoRequest("Dr Jane Doe", "6625550144", "42 Main St", "janedoe", "42 Main St")));
        verify(helpMapper).convertToCustomerEntity((CustomerInfoRequest) any());
    }

    /**
     * Method under test: {@link CustomerInfoServiceImpl#addNew(CustomerInfoRequest)}
     */
    @Test
    void testAddNew3() throws ParseException {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setAddress("42 Main St");
        customerInfo.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setEmailAddress("42 Main St");
        customerInfo.setFullName("Dr Jane Doe");
        customerInfo.setId(1L);
        customerInfo.setPhoneNumber("6625550144");
        customerInfo.setUpdatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setUsername("janedoe");
        when(customerInfoRepository.existsByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any()))
                .thenReturn(true);
        when(customerInfoRepository.save((CustomerInfo) any())).thenReturn(customerInfo);
        when(helpMapper.convertToCustomerEntity((CustomerInfoRequest) any()))
                .thenThrow(new ParseException("Add New Customer method", -1));
        assertThrows(BadRequestException.class, () -> customerInfoServiceImpl
                .addNew(new CustomerInfoRequest("Dr Jane Doe", "6625550144", "42 Main St", "janedoe", "42 Main St")));
        verify(helpMapper).convertToCustomerEntity((CustomerInfoRequest) any());
    }

    /**
     * Method under test: {@link CustomerInfoServiceImpl#addNew(CustomerInfoRequest)}
     */
    @Test
    void testAddNew4() throws ParseException {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setAddress("42 Main St");
        customerInfo.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setEmailAddress("42 Main St");
        customerInfo.setFullName("Dr Jane Doe");
        customerInfo.setId(1L);
        customerInfo.setPhoneNumber("6625550144");
        customerInfo.setUpdatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setUsername("janedoe");
        when(customerInfoRepository.existsByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any()))
                .thenReturn(false);
        when(customerInfoRepository.save((CustomerInfo) any())).thenReturn(customerInfo);

        CustomerInfo customerInfo1 = new CustomerInfo();
        customerInfo1.setAddress("42 Main St");
        customerInfo1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo1.setEmailAddress("42 Main St");
        customerInfo1.setFullName("Dr Jane Doe");
        customerInfo1.setId(1L);
        customerInfo1.setPhoneNumber("6625550144");
        customerInfo1.setUpdatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo1.setUsername("janedoe");
        CustomerInfoResponse customerInfoResponse = new CustomerInfoResponse();
        when(helpMapper.convertToCustomerResonse((CustomerInfo) any())).thenReturn(customerInfoResponse);
        when(helpMapper.convertToCustomerEntity((CustomerInfoRequest) any())).thenReturn(customerInfo1);
        assertSame(customerInfoResponse, customerInfoServiceImpl
                .addNew(new CustomerInfoRequest("Dr Jane Doe", "6625550144", "42 Main St", "janedoe", "42 Main St")));
        verify(customerInfoRepository, atLeast(1))
                .existsByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any());
        verify(customerInfoRepository).save((CustomerInfo) any());
        verify(helpMapper).convertToCustomerResonse((CustomerInfo) any());
        verify(helpMapper).convertToCustomerEntity((CustomerInfoRequest) any());
    }

    /**
     * Method under test: {@link CustomerInfoServiceImpl#findAll(PageRequest)}
     */
    @Test
    void testFindAll() {
        when(customerInfoRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(customerInfoServiceImpl.findAll(null).isEmpty());
        verify(customerInfoRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link CustomerInfoServiceImpl#findAll(PageRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindAll2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.minty.salesinventorymgt.exceptions.BadRequestException: An error occurred
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.ArrayList$Itr.forEachRemaining(ArrayList.java:1003)
        //       at java.util.Spliterators$IteratorSpliterator.forEachRemaining(Spliterators.java:1845)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:575)
        //       at java.util.stream.AbstractPipeline.evaluateToArrayNode(AbstractPipeline.java:260)
        //       at java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:616)
        //       at java.util.stream.ReferencePipeline.toArray(ReferencePipeline.java:622)
        //       at java.util.stream.ReferencePipeline.toList(ReferencePipeline.java:627)
        //       at com.minty.salesinventorymgt.services.impl.CustomerInfoServiceImpl.findAll(CustomerInfoServiceImpl.java:74)
        //   See https://diff.blue/R013 to resolve this issue.

        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setAddress("42 Main St");
        customerInfo.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setEmailAddress("42 Main St");
        customerInfo.setFullName("Dr Jane Doe");
        customerInfo.setId(1L);
        customerInfo.setPhoneNumber("6625550144");
        customerInfo.setUpdatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setUsername("janedoe");

        ArrayList<CustomerInfo> customerInfoList = new ArrayList<>();
        customerInfoList.add(customerInfo);
        PageImpl<CustomerInfo> pageImpl = new PageImpl<>(customerInfoList);
        when(customerInfoRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        when(helpMapper.convertToCustomerResonse((CustomerInfo) any()))
                .thenThrow(new BadRequestException("An error occurred"));
        customerInfoServiceImpl.findAll(null);
    }

    /**
     * Method under test: {@link CustomerInfoServiceImpl#findAll(PageRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindAll3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.data.domain.Page.stream()" because the return value of "com.minty.salesinventorymgt.repositories.CustomerInfoRepository.findAll(org.springframework.data.domain.Pageable)" is null
        //       at com.minty.salesinventorymgt.services.impl.CustomerInfoServiceImpl.findAll(CustomerInfoServiceImpl.java:74)
        //   See https://diff.blue/R013 to resolve this issue.

        when(customerInfoRepository.findAll((Pageable) any())).thenReturn(null);
        when(helpMapper.convertToCustomerResonse((CustomerInfo) any()))
                .thenThrow(new BadRequestException("An error occurred"));
        customerInfoServiceImpl.findAll(null);
    }

    /**
     * Method under test: {@link CustomerInfoServiceImpl#updateOne(String, CustomerInfoRequest)}
     */
    @Test
    void testUpdateOne() {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setAddress("42 Main St");
        customerInfo.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setEmailAddress("42 Main St");
        customerInfo.setFullName("Dr Jane Doe");
        customerInfo.setId(1L);
        customerInfo.setPhoneNumber("6625550144");
        customerInfo.setUpdatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setUsername("janedoe");
        Optional<CustomerInfo> ofResult = Optional.of(customerInfo);
        when(customerInfoRepository.findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any()))
                .thenReturn(ofResult);
        CustomerInfoResponse customerInfoResponse = new CustomerInfoResponse();
        when(helpMapper.convertToCustomerResonse((CustomerInfo) any())).thenReturn(customerInfoResponse);
        assertSame(customerInfoResponse, customerInfoServiceImpl.updateOne("Unique Key",
                new CustomerInfoRequest("Dr Jane Doe", "6625550144", "42 Main St", "janedoe", "42 Main St")));
        verify(customerInfoRepository).findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any());
        verify(helpMapper).convertToCustomerResonse((CustomerInfo) any());
    }

    /**
     * Method under test: {@link CustomerInfoServiceImpl#updateOne(String, CustomerInfoRequest)}
     */
    @Test
    void testUpdateOne2() {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setAddress("42 Main St");
        customerInfo.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setEmailAddress("42 Main St");
        customerInfo.setFullName("Dr Jane Doe");
        customerInfo.setId(1L);
        customerInfo.setPhoneNumber("6625550144");
        customerInfo.setUpdatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setUsername("janedoe");
        Optional<CustomerInfo> ofResult = Optional.of(customerInfo);
        when(customerInfoRepository.findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any()))
                .thenReturn(ofResult);
        when(helpMapper.convertToCustomerResonse((CustomerInfo) any()))
                .thenThrow(new BadRequestException("An error occurred"));
        assertThrows(BadRequestException.class, () -> customerInfoServiceImpl.updateOne("Unique Key",
                new CustomerInfoRequest("Dr Jane Doe", "6625550144", "42 Main St", "janedoe", "42 Main St")));
        verify(customerInfoRepository).findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any());
        verify(helpMapper).convertToCustomerResonse((CustomerInfo) any());
    }

    /**
     * Method under test: {@link CustomerInfoServiceImpl#updateOne(String, CustomerInfoRequest)}
     */
    @Test
    void testUpdateOne3() {
        when(customerInfoRepository.findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any()))
                .thenReturn(Optional.empty());
        when(helpMapper.convertToCustomerResonse((CustomerInfo) any())).thenReturn(new CustomerInfoResponse());
        assertThrows(NotFoundException.class, () -> customerInfoServiceImpl.updateOne("Unique Key",
                new CustomerInfoRequest("Dr Jane Doe", "6625550144", "42 Main St", "janedoe", "42 Main St")));
        verify(customerInfoRepository).findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any());
    }

    /**
     * Method under test: {@link CustomerInfoServiceImpl#updateOne(String, CustomerInfoRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateOne4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Source must not be null
        //       at com.minty.salesinventorymgt.services.impl.CustomerInfoServiceImpl.updateOne(CustomerInfoServiceImpl.java:82)
        //   See https://diff.blue/R013 to resolve this issue.

        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setAddress("42 Main St");
        customerInfo.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setEmailAddress("42 Main St");
        customerInfo.setFullName("Dr Jane Doe");
        customerInfo.setId(1L);
        customerInfo.setPhoneNumber("6625550144");
        customerInfo.setUpdatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        customerInfo.setUsername("janedoe");
        Optional<CustomerInfo> ofResult = Optional.of(customerInfo);
        when(customerInfoRepository.findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase((String) any()))
                .thenReturn(ofResult);
        when(helpMapper.convertToCustomerResonse((CustomerInfo) any())).thenReturn(new CustomerInfoResponse());
        customerInfoServiceImpl.updateOne("Unique Key", null);
    }
}

