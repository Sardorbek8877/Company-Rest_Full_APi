package uz.bek.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.bek.company.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    boolean existsByName(String name);
}
