package fr.efrei.server.web.rest;

import fr.efrei.server.domain.Student;
import fr.efrei.server.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class StudentResourceIT {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Transactional
    void createStudent() throws Exception {
        int databaseSizeBeforeCreate = studentRepository.findAll().size();
        assertThat(databaseSizeBeforeCreate).isEqualTo(0);

        Student student = new Student();
        student.setName("Pierre");
        studentRepository.save(student);

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeCreate + 1);
    }

    @Test
    @Transactional
    void getStudent() throws Exception {
        // Assuming there is a student with ID 1 in the database
        Student existingStudent = studentRepository.save(new Student("John", 20));

        Student retrievedStudent = studentRepository.findById(existingStudent.getId()).orElse(null);
        assertThat(retrievedStudent).isNotNull();
        assertThat(retrievedStudent.getName()).isEqualTo("John");
    }

    @Test
    @Transactional
    void updateStudent() throws Exception {
        // Assuming there is a student with ID 1 in the database
        Student existingStudent = studentRepository.save(new Student("Alice", 20));

        existingStudent.setName("Alice Updated");
        studentRepository.save(existingStudent);

        Student updatedStudent = studentRepository.findById(existingStudent.getId()).orElse(null);
        assertThat(updatedStudent).isNotNull();
        assertThat(updatedStudent.getName()).isEqualTo("Alice Updated");
    }

    @Test
    @Transactional
    void deleteStudent() throws Exception {
        // Assuming there is a student with ID 1 in the database
        Student existingStudent = studentRepository.save(new Student("Bob",    20));

        int databaseSizeBeforeDelete = studentRepository.findAll().size();
        assertThat(databaseSizeBeforeDelete).isEqualTo(1);

        studentRepository.deleteById(existingStudent.getId());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
