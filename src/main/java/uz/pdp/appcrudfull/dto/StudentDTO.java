package uz.pdp.appcrudfull.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import uz.pdp.appcrudfull.utils.AppConstants;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {

    @NotBlank
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    @Email(regexp = AppConstants.EMAIL_REGEX)
    private String email;

    @NotBlank
    private String password;
}
