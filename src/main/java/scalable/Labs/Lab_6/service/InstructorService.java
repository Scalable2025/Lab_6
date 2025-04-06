package scalable.Labs.Lab_6.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import scalable.Labs.Lab_6.model.Instructor;
import scalable.Labs.Lab_6.repository.InstructorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    // Retrieve all instructors.
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    // Retrieve an instructor by its ID.
    public Instructor getInstructorById(Integer id) {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        if (instructor.isPresent()) {
            return instructor.get();
        } else {
            throw new RuntimeException("Instructor not found with id " + id);
        }
    }

    // Save a new instructor.
    public Instructor saveInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    // Update an existing instructor.
    public Instructor updateInstructor(Integer id, Instructor updatedInstructor) {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        if (instructor.isPresent()) {
            Instructor existingInstructor = instructor.get();

            existingInstructor.setName(updatedInstructor.getName());
            existingInstructor.setEmail(updatedInstructor.getEmail());
            existingInstructor.setCourses(updatedInstructor.getCourses());
            return instructorRepository.save(existingInstructor);
        }
        else {
            throw new RuntimeException("Instructor with id " + id + " not found");
        }

    }

    // Delete an instructor by its ID.
    public void deleteInstructor(Integer id) {
        instructorRepository.deleteById(id);
    }
}
