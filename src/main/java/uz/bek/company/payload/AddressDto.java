package uz.bek.company.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    @NotNull(message = "Street does not to be empty")
    private String street;

    @NotNull(message = "Home number does not to be empty")
    private String homeNumber;
}
