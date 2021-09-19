package bmw.student_payment.dao;

import bmw.student_payment.domain.StudentOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentOrderStatusRepository  extends JpaRepository<StudentOrderStatus, Long> {
}
