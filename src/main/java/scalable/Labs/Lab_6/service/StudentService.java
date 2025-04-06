package scalable.Labs.Lab_6.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import scalable.Labs.Lab_6.model.Student;
import scalable.Labs.Lab_6.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // Constructor injection for StudentRepository
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Retrieve all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Retrieve a student by ID
    @Cacheable(value = "students_cache",key = "#id")
    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    // Create or update a student
    @CachePut(value = "student_cache",key = "#result.id")
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Integer id, Student student) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            Student updatedStudent = studentOptional.get();
            updatedStudent.setName(student.getName());
            updatedStudent.setEmail(student.getEmail());
            updatedStudent.setCourses(student.getCourses());
            return studentRepository.save(updatedStudent);
        }
        else {
            throw new RuntimeException("Student with id " + id + " not found");
        }
    }

    // Delete a student by ID
    @CacheEvict(value = "student_cache",key = "#id")
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }
}
