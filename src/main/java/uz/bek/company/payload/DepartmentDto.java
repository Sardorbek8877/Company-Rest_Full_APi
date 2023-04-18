package uz.bek.company.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    @NotNull(message = "Name does not to be empty")
    private String name;

    @NotNull(message = "Company id does not to be empty")
    private Integer companyId;
}
