package uz.pdp.appcrudfull.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appcrudfull.dto.CreateStudentDTO;
import uz.pdp.appcrudfull.dto.StudentDTO;
import uz.pdp.appcrudfull.service.StudentService;
import uz.pdp.appcrudfull.utils.AppConstants;

import java.util.List;


@RestController
@CrossOrigin(AppConstants.REACT_URL)
@RequiredArgsConstructor
public class StudentControllerImpl implements StudentController {

    private final StudentService studentService;


    @Override
    public HttpEntity<List<StudentDTO>> getAll() {
        try {
            List<StudentDTO> list = studentService.getAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HttpEntity<StudentDTO> getById(Integer id) {
        try {
            StudentDTO studentDTO = studentService.getById(id);
            return ResponseEntity.ok(studentDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HttpEntity<StudentDTO> add(CreateStudentDTO createStudentDTO) {

        try {
            StudentDTO studentDTO = studentService.create(createStudentDTO);
            return ResponseEntity.ok(studentDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HttpEntity<StudentDTO> edit(Integer id, CreateStudentDTO createStudentDTO) {
        try {
            StudentDTO studentDTO = studentService.edit(id, createStudentDTO);
            return ResponseEntity.ok(studentDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HttpEntity<?> deleteById(Integer id) {
        try {
            studentService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HttpEntity<?> deleteAll() {
        try {
            studentService.deleteAll();
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
