package uz.bek.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bek.company.entity.Address;
import uz.bek.company.payload.AddressDto;
import uz.bek.company.payload.ApiResponse;
import uz.bek.company.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    /**
     * Get Addresses
     * @return List<Address>
     */
    public List<Address> getAddresses(){
        return addressRepository.findAll();
    }

    /**
     * Get Address by id
     * @param id
     * @return Address
     */
    public Address getAddressById(Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElse(null);
    }

    /**
     * Add Address
     * @param addressDto
     * @return ApiResponse
     * Create Validation
     */
    public ApiResponse addAddress(AddressDto addressDto){
        boolean existsByHomeNumberAndStreet = addressRepository.existsByHomeNumberAndStreet(addressDto.getHomeNumber(), addressDto.getStreet());
        if (existsByHomeNumberAndStreet)
            return new ApiResponse("Address already exist", false);

        Address address = new Address();
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setStreet(addressDto.getStreet());
        addressRepository.save(address);
        return new ApiResponse("Address added", true);
    }

    /**
     * EDIT ADDRESS
     * @param addressDto
     * @param AddressDto, id
     * @return APIRESPONSE
     */
    public ApiResponse editAddress(AddressDto addressDto, Integer id){
        boolean existsByHomeNumberAndStreet = addressRepository.existsByHomeNumberAndStreet(addressDto.getHomeNumber(), addressDto.getStreet());
        if (existsByHomeNumberAndStreet)
            return new ApiResponse("Address already exist", false);

        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(address);
        return new ApiResponse("Address edited", true);
    }


    /**
     * DELETE ADDRESS
     * @param id
     * @return APIRESPONSE
     */
    public ApiResponse deleteAddress(Integer id){
        try {
            addressRepository.deleteById(id);
            return new ApiResponse("Address deleted", true);
        }
        catch (Exception e){
            return new ApiResponse("Address not found", false);
        }


    }
}
