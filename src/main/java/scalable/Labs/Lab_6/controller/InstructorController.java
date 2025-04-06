package scalable.Labs.Lab_6.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scalable.Labs.Lab_6.model.Instructor;
import scalable.Labs.Lab_6.service.CourseService;
import scalable.Labs.Lab_6.service.InstructorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        List<Instructor> instructors = instructorService.getAllInstructors();
        return ResponseEntity.ok(instructors);
    }

    // GET /instructors/{id} - Retrieve an instructor by ID.
    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable Integer id) {
        return ResponseEntity.ok(instructorService.getInstructorById(id));
    }

    // POST /instructors - Create a new instructor.
    @PostMapping
    public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor instructor) {
        Instructor savedInstructor = instructorService.getAllInstructors().contains(instructor) ? instructor : instructorService.saveInstructor(instructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInstructor);
    }

    // PUT /instructors/{id} - Update an existing instructor.
    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Integer id, @RequestBody Instructor updatedInstructor) {
        // Set the ID to update the correct record.
        updatedInstructor.setId(id);
        Instructor instructor = instructorService.saveInstructor(updatedInstructor);
        return ResponseEntity.ok(instructor);
    }

    // DELETE /instructors/{id} - Delete an instructor.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Integer id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }
}
