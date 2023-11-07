package uz.pdp.appcrudfull.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcrudfull.dto.CreateStudentDTO;
import uz.pdp.appcrudfull.dto.StudentDTO;
import uz.pdp.appcrudfull.utils.AppConstants;

import java.util.List;

@RequestMapping(AppConstants.STUDENT_BASE_PATH)
public interface StudentController {

    @GetMapping
    HttpEntity<List<StudentDTO>> getAll();

    @GetMapping("/{id}")
    HttpEntity<StudentDTO> getById(@PathVariable Integer id);

    @PostMapping("/add")
    HttpEntity<StudentDTO> add(@Valid @RequestBody CreateStudentDTO createStudentDTO);

    @PutMapping("/edit/{id}")
    HttpEntity<StudentDTO> edit(@PathVariable Integer id,
                                @Valid @RequestBody CreateStudentDTO createStudentDTO);

    @DeleteMapping("/delete/{id}")
    HttpEntity<?> deleteById(@PathVariable Integer id);

    @DeleteMapping("/delete")
    HttpEntity<?> deleteAll();

}
