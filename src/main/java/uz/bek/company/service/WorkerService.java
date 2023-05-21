package uz.bek.company.service;

import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import uz.bek.company.entity.Address;
import uz.bek.company.entity.Department;
import uz.bek.company.entity.Worker;
import uz.bek.company.payload.ApiResponse;
import uz.bek.company.payload.WorkerDto;
import uz.bek.company.repository.AddressRepository;
import uz.bek.company.repository.DepartmentRepository;
import uz.bek.company.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    /**
     * GET WORKERS
     * @return WORKER LIST
     */
    public List<Worker> getWorkers(){
        return workerRepository.findAll();
    }

    /**
     * GET WORKER BY ID
     * @param id
     * @return WORKER
     */
    public Worker getWorker( Integer id ){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.orElse(null);
    }

    /**
     *
     * @param workerDto
     * @return ResponseEntity<ApiResponse>
     */
    public ApiResponse addWorker(WorkerDto workerDto){
        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (existsByPhoneNumber)
            return new ApiResponse("Phone number already exist", false);

        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (optionalAddress.isEmpty())
            return new ApiResponse("Address not found", false);
        Address address = optionalAddress.get();

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (optionalDepartment.isEmpty())
            return new ApiResponse("Department not found", false);
        Department department = optionalDepartment.get();

        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(address);
        worker.setDepartment(department);

        workerRepository.save(worker);
        return new ApiResponse("Worker added", true);
    }

    /**
     * EDIT WORKER
     * @param id
     * @param workerDto
     * @return ApiRasponse
     */
    public ApiResponse editWorker(Integer id, WorkerDto workerDto){

        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty())
            return new ApiResponse("Worker not found", false);
        Worker worker = optionalWorker.get();

        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (existsByPhoneNumber)
            return new ApiResponse("Phone Number already exist", false);

        Optional<Address> optionalAddress = addressRepository.findById(workerDto.getAddressId());
        if (optionalAddress.isEmpty())
            return new ApiResponse("Address not found", false);
        Address address = optionalAddress.get();

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (optionalDepartment.isEmpty())
            return new ApiResponse("Department not found", false);
        Department department = optionalDepartment.get();

        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(address);
        worker.setDepartment(department);

        workerRepository.save(worker);
        return new ApiResponse("Worker edited", true);
    }

    /**
     * DELETE WORKER
     * @param id
     * @return Api Response
     */
    public ApiResponse deleteWorker(Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty())
            return new ApiResponse("Worker not found", false);

        workerRepository.deleteById(id);
        return new ApiResponse("Worker deleted", true);
    }
}
