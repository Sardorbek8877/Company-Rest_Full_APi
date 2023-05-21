package uz.bek.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.bek.company.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);
}
