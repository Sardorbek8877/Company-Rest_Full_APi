package uz.bek.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.bek.company.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    boolean existsByHomeNumberAndStreet(String homeNumber, String street);
}
