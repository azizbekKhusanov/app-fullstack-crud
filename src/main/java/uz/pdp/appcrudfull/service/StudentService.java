package uz.pdp.appcrudfull.service;

import uz.pdp.appcrudfull.dto.CreateStudentDTO;
import uz.pdp.appcrudfull.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    List<StudentDTO> getAll();

    StudentDTO getById(Integer id);

    StudentDTO create(CreateStudentDTO createStudentDTO);

    StudentDTO edit(Integer id, CreateStudentDTO createStudentDTO);

    void deleteById(Integer id);

    void deleteAll();

}
