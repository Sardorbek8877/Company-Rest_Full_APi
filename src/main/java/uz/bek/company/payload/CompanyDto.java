package uz.bek.company.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    @NotNull(message = "Name does not to be empty")
    private String corpName;

    @NotNull(message = "Director name does not to be empty")
    private String directorName;

    @NotNull(message = "Address ID does not to be empty")
    private Integer addressId;
}
