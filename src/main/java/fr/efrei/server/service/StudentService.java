package fr.efrei.server.service;

import fr.efrei.server.domain.Student;
import fr.efrei.server.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    public final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Student findById(Integer id){
        return studentRepository.findById(id).orElse(null);
    }

    public Student createStudent(Student studentToCreate){
        Student student = new Student();
        student.setName(studentToCreate.getName());
        student.setAge(studentToCreate.getAge());
        student.setId(studentRepository.findAll().size() + 1);
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        Student studentToUpdate = studentRepository.findById(student.getId()).orElse(null);
        if (studentToUpdate != null) {
            studentToUpdate.setName(student.getName());
            studentToUpdate.setAge(student.getAge());
            return studentRepository.save(studentToUpdate);
        }
        return null;
    }

    public void deleteStudent(Integer id) {
        Student studentToDelete = studentRepository.findById(id).orElse(null);
        if (studentToDelete != null) {
            studentRepository.delete(studentToDelete);
        }
    }
}
