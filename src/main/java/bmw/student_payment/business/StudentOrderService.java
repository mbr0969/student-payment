package bmw.student_payment.business;

import bmw.student_payment.dao.*;
import bmw.student_payment.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class StudentOrderService {

    private static final Logger LOG = LoggerFactory.getLogger(StudentOrderService.class);

    @Autowired
    private StudentOrderRepository dao;
    @Autowired
    private StudentOrderChildRepository daoChild;
    @Autowired
    private StreetRepository daoStreet;
    @Autowired
    private StudentOrderStatusRepository daoStatus;
    @Autowired
    private PassportOfficeRepository daoPassport;
    @Autowired
    private UniversityRepository daoUniversity;
    @Autowired
    private RegisterOfficeRepository daoRegisterOffice;

    @Transactional
    public void testSave(){
        StudentOrder so = new StudentOrder();
        so.setStudentOrderDate(LocalDateTime.now());
        so.setStatus(daoStatus.getById(1L));
        so.setHusband(buildPerson(false));
        so.setWife(buildPerson(true));
        so.setCertificateNumber("CERTIFICATE 87126754");
        so.setRegisterOffice(daoRegisterOffice.getById(1L));
        so.setMarriageDate(LocalDate.now());

        dao.save(so);
        StudentOrderChild soc = buildChild(so);
        daoChild.save(soc);
    }

    @Transactional
    public void testGet(){
        List<StudentOrder> list = dao.findAll();
        LOG.info(list.get(0).getWife().getSurName());
        LOG.info(list.get(0).getChildren().get(0).getGivenName());
    }


    private Adult buildPerson(boolean wife){
        Adult p = new Adult();
        Address a = new Address();
        a.setPostCode("192283");
        a.setBuilding("7");
        a.setExtension("1");
        a.setApartment("457");
        p.setAddress(a);

        p.setDateOfBirth(LocalDate.now());
        Street one = daoStreet.getById(1L);
        a.setStreet(one);
        if(wife){
            p.setSurName("Петрова");
            p.setGivenName("Анастасия");
            p.setPatronymic("Ивановна");
            p.setPassportSeria("AAX-I");
            p.setPassportNumber("1231245345");
            p.setPassportOffice(daoPassport.getById(1L));
            p.setIssueDate(LocalDate.now());
            p.setStudentNumber("7824 9076544");
            p.setUniversity(daoUniversity.getById(1L));

        }else{
            p.setSurName("Петров");
            p.setGivenName("Иван");
            p.setPatronymic("Петрович");
            p.setPassportSeria("AX-2");
            p.setPassportNumber("761245345");
            p.setPassportOffice(daoPassport.getById(2L));
            p.setIssueDate(LocalDate.now());
            p.setStudentNumber("1224 774344");
            p.setUniversity(daoUniversity.getById(2L));
        }
        return p;    }

    private StudentOrderChild buildChild(StudentOrder studentOrder){
        StudentOrderChild p = new StudentOrderChild();
        p.setStudentOrder(studentOrder);
        Address a = new Address();
        a.setPostCode("192283");
        a.setBuilding("7");
        a.setExtension("1");
        a.setApartment("457");
        p.setAddress(a);

        p.setDateOfBirth(LocalDate.now());
        Street one = daoStreet.getById(1L);
        a.setStreet(one);
        p.setSurName("Петров");
        p.setGivenName("Сергей");
        p.setPatronymic("Иванович");
        p.setCertificateDate(LocalDate.now());
        p.setCertificateNumber("X334");
        p.setRegisterOffice(daoRegisterOffice.getById(1L));
        return p;    }


}
