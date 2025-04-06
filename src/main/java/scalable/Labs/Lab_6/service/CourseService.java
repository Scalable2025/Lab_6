package scalable.Labs.Lab_6.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import scalable.Labs.Lab_6.model.Course;
import scalable.Labs.Lab_6.repository.CourseRepository;
import scalable.Labs.Lab_6.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
    }

    // Retrieve all courses.
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Retrieve a course by its ID.
    @Cacheable(value = "course_cache",key = "#id")
    public Optional<Course> getCourseByID(Integer id) {
        return courseRepository.findById(id);
    }

    // Save a new course.
    @CachePut(value = "course_cache",key = "#result.id")
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    // Update an existing course.
    @CachePut(value = "course_cache",key = "#result.id")
    public Course updateCourse(Integer id, Course updatedCourse) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id " + id));
        existingCourse.setName(updatedCourse.getName());
        existingCourse.setCode(updatedCourse.getCode());
        existingCourse.setCredit(updatedCourse.getCredit());
        existingCourse.setStudents(updatedCourse.getStudents());
        existingCourse.setInstructor(updatedCourse.getInstructor());
        return courseRepository.save(existingCourse);
    }

    // Delete a course by its ID.
    @CacheEvict(value = "course_cache",key = "#id")
    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }

}
