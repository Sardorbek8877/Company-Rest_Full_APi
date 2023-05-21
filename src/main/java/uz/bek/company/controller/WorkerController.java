package uz.bek.company.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bek.company.entity.Worker;
import uz.bek.company.payload.ApiResponse;
import uz.bek.company.payload.WorkerDto;
import uz.bek.company.service.WorkerService;

import java.util.List;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    /**
     * GET WORKERS
     * @return WORKER LIST
     */
    @GetMapping
    public ResponseEntity<List<Worker>> getWorkers(){
        List<Worker> workerList = workerService.getWorkers();
        return ResponseEntity.ok(workerList);
    }

    /**
     * GET WORKER BY ID
     * @param id
     * @return WORKER
     */
    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable Integer id){
        Worker worker = workerService.getWorker(id);
        return ResponseEntity.ok(worker);
    }

    /**
     * ADD WORKER
     * @param workerDto
     * @return ResponseEntity<ApiResponse>
     */
    @PostMapping
    public ResponseEntity<ApiResponse> addWorker(@Valid @RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.addWorker(workerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * EDIT WORKER
     * @param id
     * @param workerDto
     * @return ApiRasponse
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editWorker(@Valid @PathVariable Integer id, @RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.editWorker(id, workerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * DELETE WORKER
     * @param id
     * @return Api Response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteWorker(@PathVariable Integer id){
        ApiResponse apiResponse = workerService.deleteWorker(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
}
