package uz.bek.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bek.company.entity.Company;
import uz.bek.company.entity.Department;
import uz.bek.company.payload.ApiResponse;
import uz.bek.company.payload.DepartmentDto;
import uz.bek.company.repository.CompanyRepository;
import uz.bek.company.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;

    /**
     * GET DEPARTMENTS
     * @return LIST<DEPARTMENT>
     */
    public List<Department> getDepartments(){
        return departmentRepository.findAll();
    }

    /**
     * GET DEPARTMENT BY ID
     * @param id
     * @return DEPARTMENT
     */
    public Department getDepartmentById(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElse(null);
    }

    /**
     * ADD DEPARTMENT
     * @param departmentDto
     * @return API RESPONSE
     */
    public ApiResponse addDepartment(DepartmentDto departmentDto){
        boolean existsByName = departmentRepository.existsByName(departmentDto.getName());
        if (existsByName)
            return new ApiResponse("Department name already exist", false);

        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (optionalCompany.isEmpty())
            return new ApiResponse("Company not found", false);
        Company company = optionalCompany.get();

        Department department = new Department();
        department.setCompany(company);
        department.setName(departmentDto.getName());
        departmentRepository.save(department);
        return new ApiResponse("Department added", true);
    }

    /**
     * EDIT DEPARTMENT
     * @param departmentDto
     * @param id
     * @return API RESPONSE
     */
    public ApiResponse editDepartment(DepartmentDto departmentDto, Integer id){

        // Find the Department from Database
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty())
            return new ApiResponse("Department not found", false);
        Department department = optionalDepartment.get();

        //Check the name
        boolean existsByName = departmentRepository.existsByName(departmentDto.getName());
        if (existsByName)
            return new ApiResponse("Depaartment name already exist", false);

        //Find the Company from Database
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (optionalCompany.isEmpty())
            return new ApiResponse("Company not found", false);
        Company company = optionalCompany.get();

        //Add new information
        department.setName(departmentDto.getName());
        department.setCompany(company);
        departmentRepository.save(department);
        return new ApiResponse("Department edited", true);
    }

    /**
     * DELETE DEPARTMENT
     * @param id
     * @return API RESPONSE
     */
    public ApiResponse deleteDepartment(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty())
            return new ApiResponse("Department not found", false);

        departmentRepository.deleteById(id);
        return new ApiResponse("Department deleted", true);
    }
}










