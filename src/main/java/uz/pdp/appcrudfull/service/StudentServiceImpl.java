package uz.pdp.appcrudfull.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.appcrudfull.dto.CreateStudentDTO;
import uz.pdp.appcrudfull.dto.StudentDTO;
import uz.pdp.appcrudfull.model.Student;
import uz.pdp.appcrudfull.repository.StudentRepository;
import uz.pdp.appcrudfull.utils.AppConstants;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<StudentDTO> getAll() {

        List<Student> all = studentRepository.findAll();
        if (all.isEmpty()) {
            throw new RuntimeException("This list is empty");
        }

        return all.stream()
                .map(this::mapToStudentDTOFromStudent)
                .toList();
    }

    @Override
    public StudentDTO getById(Integer id) {
        Student student = studentRepository
                .findById(id)
                .orElseThrow();

        return mapToStudentDTOFromStudent(student);
    }

    @Override
    public StudentDTO create(CreateStudentDTO createStudentDTO) {


        if(!createStudentDTO.getEmail().matches(AppConstants.EMAIL_REGEX))
            throw new RuntimeException("Email don't match");

        if(studentRepository.existsByEmail(createStudentDTO.getEmail()))
            throw new RuntimeException("This student is already exist");

        if(!Objects.equals(createStudentDTO.getPassword(), createStudentDTO.getPrePassword()))
            throw new RuntimeException("Password don't match");
        Student save = studentRepository.save(mapToStudentDTOFromStudent(createStudentDTO));

        return mapToStudentDTOFromStudent(save);
    }

    @Override
    public StudentDTO edit(Integer id, CreateStudentDTO createStudentDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));


        if(!createStudentDTO.getEmail().matches(AppConstants.EMAIL_REGEX))
            throw new RuntimeException("Email don't match");

        if(studentRepository.existsByEmail(createStudentDTO.getEmail()))
            throw new RuntimeException("This student is already exist");

        if(!Objects.equals(createStudentDTO.getPassword(), createStudentDTO.getPrePassword()))
            throw new RuntimeException("Password don't match");


        student.setEmail(createStudentDTO.getEmail());
        student.setName(createStudentDTO.getName());
        student.setPassword(createStudentDTO.getPassword());

        return mapToStudentDTOFromStudent(studentRepository.save(student));
    }

    @Override
    public void deleteById(Integer id) {
        studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        studentRepository.deleteById(id);
    }


    @Override
    public void deleteAll() {
        if (studentRepository.findAll().isEmpty())
            throw new RuntimeException("This list is empty");
    }


    private StudentDTO mapToStudentDTOFromStudent(Student student) {
        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .password(student.getPassword())
                .build();
    }

    private Student mapToStudentDTOFromStudent(CreateStudentDTO createStudentDTO) {
        return Student.builder()
                .name(createStudentDTO.getName())
                .email(createStudentDTO.getEmail())
                .password(createStudentDTO.getPassword())
                .build();
    }

}
