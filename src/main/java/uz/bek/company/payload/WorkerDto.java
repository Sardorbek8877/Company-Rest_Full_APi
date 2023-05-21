package uz.bek.company.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {

    @NotNull(message = "Name does not to be empty")
    private String name;

    @NotNull(message = "Phone number does not to be empty")
    private String phoneNumber;

    @NotNull(message = "Address id does not to be empty")
    private Integer addressId;

    @NotNull(message = "Department id does not to be empty")
    private Integer departmentId;
}
