package scalable.Labs.Lab_6.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import scalable.Labs.Lab_6.model.Course;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
