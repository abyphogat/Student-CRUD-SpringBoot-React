package com.project1.SBR_demo.service;

import com.project1.SBR_demo.exception.StudentAlreadyExistsException;
import com.project1.SBR_demo.exception.StudentNotFoundException;
import com.project1.SBR_demo.model.Student;
import com.project1.SBR_demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
//@RequiredArgsConstructor
public class StudentService implements IStudentService{

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        if(studentAlreadyExists(student.getEmail())){
            throw new StudentAlreadyExistsException(student.getEmail() +" already exists");
        }
        return studentRepository.save(student);
    }




    @Override
    public Student updateStudent(Student student, Long id) {
        return studentRepository.findById(id).map(st ->{
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());

            return studentRepository.save(st);

        }).orElseThrow(()-> new StudentNotFoundException("Sorry, this student could not be found "));
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(()-> new StudentNotFoundException("Sorry, no  student found with id : "+ id));
    }

    @Override
    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id)){
              throw new StudentNotFoundException("Sorry, student not found with id : "+ id);
        }

        studentRepository.deleteById(id);
    }

    private boolean studentAlreadyExists(String email) {

        return studentRepository.findByEmail(email).isPresent();
    }
}
