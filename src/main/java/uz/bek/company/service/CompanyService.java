package uz.bek.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bek.company.entity.Address;
import uz.bek.company.entity.Company;
import uz.bek.company.payload.ApiResponse;
import uz.bek.company.payload.CompanyDto;
import uz.bek.company.repository.AddressRepository;
import uz.bek.company.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;

    /**
     * GET COMPANIES
     * @return LIST<COMPANY>
     */
    public List<Company> getCompanies(){
        return companyRepository.findAll();
    }

    /**
     * GET COMPANY BY ID
     * @param id
     * @return APIRESPONSE
     */
    public Company getCompanyById(Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);
    }

    /**
     * ADD COMPANY
     * @param companyDto
     * @return ApiResponse
     */
    public ApiResponse addCompany(CompanyDto companyDto){
        boolean existsByCorpName = companyRepository.existsByCorpName(companyDto.getCorpName());
        if (existsByCorpName)
            return new ApiResponse("Company name already exist", false);

        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (optionalAddress.isEmpty())
            return new ApiResponse("Address not found", false);
        Address address = optionalAddress.get();

        Company company = new Company();
        company.setDirectorName(companyDto.getDirectorName());
        company.setCorpName(companyDto.getCorpName());
        company.setAddress(address);
        companyRepository.save(company);
        return new ApiResponse("Company added", true);
    }

    /**
     * EDIT COMPANY
     * @param companyDto
     * @param id
     * @return APIRESPONSE
     */
    public ApiResponse editCompany(CompanyDto companyDto, Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty())
            return new ApiResponse("Company not found", false);

        Optional<Address> optionalAddress = addressRepository.findById(companyDto.getAddressId());
        if (optionalAddress.isEmpty())
            return new ApiResponse("Address not found", false);
        Address address = optionalAddress.get();

        Company company = optionalCompany.get();
        company.setAddress(address);
        company.setDirectorName(companyDto.getDirectorName());
        company.setCorpName(companyDto.getCorpName());
        companyRepository.save(company);
        return new ApiResponse("Company edited", true);
    }

    /**
     * DELETE COMPANY
     * @param id
     * @return APIRESPONSE
     */
    public ApiResponse deleteCompany(Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty())
            return new ApiResponse("Company not found", false);
        companyRepository.deleteById(id);
        return new ApiResponse("Company deleted", true);
    }
}









