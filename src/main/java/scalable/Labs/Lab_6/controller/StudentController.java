package scalable.Labs.Lab_6.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scalable.Labs.Lab_6.model.Student;
import scalable.Labs.Lab_6.service.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    // Constructor injection for StudentService
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET /students - Retrieve all students.
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // GET /students/{id} - Retrieve a specific student by ID.
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        Optional<Student> studentOpt = studentService.getStudentById(id);
        return studentOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /students - Create a new student.
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    // PUT /students/{id} - Update an existing student.
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        // Set the ID so that the service updates the existing record.
        student.setId(id);
        Student updatedStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(updatedStudent);
    }

    // DELETE /students/{id} - Delete a student by ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}