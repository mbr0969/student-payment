package bmw.student_payment.dao;

import bmw.student_payment.domain.PassportOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportOfficeRepository extends JpaRepository<PassportOffice,Long> {
}
