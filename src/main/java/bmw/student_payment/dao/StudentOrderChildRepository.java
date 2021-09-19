package bmw.student_payment.dao;

import bmw.student_payment.domain.StudentOrderChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentOrderChildRepository extends JpaRepository<StudentOrderChild, Long> {
}
