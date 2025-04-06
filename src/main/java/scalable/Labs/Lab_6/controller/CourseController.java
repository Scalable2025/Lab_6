package scalable.Labs.Lab_6.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scalable.Labs.Lab_6.model.Course;
import scalable.Labs.Lab_6.service.CourseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // GET /courses - Retrieve all courses.
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    // GET /courses/{id} - Retrieve a course by its ID.
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
        Optional<Course> courseOpt = courseService.getCourseByID(id);
        return courseOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /courses - Create a new course.
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        // The course is saved and returned; HTTP 201 indicates creation.
        Course savedCourse = courseService.getAllCourses().contains(course) ? course : courseService.saveCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }

    // PUT /courses/{id} - Update an existing course.
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Integer id, @RequestBody Course updatedCourse) {
        // You might want to fetch the existing course and update its fields.
        updatedCourse.setId(id);
        // Here we assume saving an existing course updates it.
        Course course = courseService.saveCourse(updatedCourse);
        return ResponseEntity.ok(course);
    }

    // DELETE /courses/{id} - Delete a course.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
        // Deletion is performed by the service.
        // If the course doesn't exist, you could throw an exception or return 404.
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

}
