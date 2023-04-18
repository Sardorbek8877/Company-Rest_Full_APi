package uz.bek.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.bek.company.entity.Address;
import uz.bek.company.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsByCorpName(String name);
}
