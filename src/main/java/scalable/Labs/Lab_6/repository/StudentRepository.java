package scalable.Labs.Lab_6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import scalable.Labs.Lab_6.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
