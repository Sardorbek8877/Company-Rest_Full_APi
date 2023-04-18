package uz.bek.company.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bek.company.entity.Company;
import uz.bek.company.payload.ApiResponse;
import uz.bek.company.payload.CompanyDto;
import uz.bek.company.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;


    /**
     * GET COMPANIES
     * @return LIST<COMPANY>
     */
    @GetMapping
    public ResponseEntity<List<Company>> getCompanies(){
        List<Company> companies = companyService.getCompanies();
        return ResponseEntity.ok(companies);
    }

    /**
     * GET COMPANY BY ID
     * @param id
     * @return APIRESPONSE
     */
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Integer id){
        Company companyById = companyService.getCompanyById(id);
        return ResponseEntity.ok(companyById);
    }

    /**
     * ADD COMPANY
     * @param companyDto
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addCompany(@Valid @RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = companyService.addCompany(companyDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * EDIT COMPANY
     * @param companyDto
     * @param id
     * @return APIRESPONSE
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCompany(@Valid @RequestBody CompanyDto companyDto, @PathVariable Integer id){
        ApiResponse apiResponse = companyService.editCompany(companyDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * DELETE COMPANY
     * @param id
     * @return APIRESPONSE
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCompany(@PathVariable Integer id){
        ApiResponse apiResponse = companyService.deleteCompany(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

}









