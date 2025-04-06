package scalable.Labs.Lab_6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import scalable.Labs.Lab_6.model.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {


}
