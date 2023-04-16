package uz.bek.company.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.bek.company.entity.Address;
import uz.bek.company.payload.AddressDto;
import uz.bek.company.payload.ApiResponse;
import uz.bek.company.service.AddressService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    /**
     * Get Addresses
     * @return List<Address>
     */
    @GetMapping
    public ResponseEntity<List<Address>> getAddresses(){
        List<Address> addresses = addressService.getAddresses();
        return ResponseEntity.ok(addresses);
    }

    /**
     * Get Address by id
     * @param id
     * @return Address
     */
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Integer id){
        Address addressById = addressService.getAddressById(id);
        return ResponseEntity.ok(addressById);
    }

    /**
     * Add Address
     *
     * @param addressDto
     * @return ApiResponse
     * Create Validation
     */
    @PostMapping
    public HttpEntity<ApiResponse> addAddress(@Valid @RequestBody AddressDto addressDto){
        ApiResponse apiResponse = addressService.addAddress(addressDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * EDIT ADDRESS
     * @param addressDto
     * @param AddressDto, id
     * @return APIRESPONSE
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editAddress(@Valid @RequestBody AddressDto addressDto, @PathVariable Integer id){
        ApiResponse apiResponse = addressService.editAddress(addressDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * DELETE ADDRESS
     * @param id
     * @return APIRESPONSE
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Integer id){
        ApiResponse apiResponse = addressService.deleteAddress(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202:409).body(apiResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}











