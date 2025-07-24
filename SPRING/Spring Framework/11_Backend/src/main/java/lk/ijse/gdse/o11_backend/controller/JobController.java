package lk.ijse.gdse.o11_backend.controller;

import lk.ijse.gdse.o11_backend.dto.JobDTO;
import lk.ijse.gdse.o11_backend.entity.Job;
import lk.ijse.gdse.o11_backend.service.impl.JobServiceImpl;
import lk.ijse.gdse.o11_backend.util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/job")
@RestController
@RequiredArgsConstructor
public class JobController {
    private final JobServiceImpl jobService;

    // property injection
   /* @Autowired
    private  JobService jobService;*/

    //constructor injection
    /* public JobController(JobService jobService) {
       this.jobService = jobService;
    }*/

    @PostMapping("create")
    public ResponseEntity<APIResponse> createJob(@RequestBody JobDTO jobDTO){
       /*jobService.saveJob(jobDTO);
       return ResponseEntity.ok(new APIResponse(200,"Success", "Job created successfully"));*/

        // mehma use krnnth puluwn status eka wenama dala ok,created,accepted,bad_request,bad_gateway etc....
        // new keyword eka use krta kmk naa methana...
        jobService.saveJob(jobDTO);
        return new ResponseEntity(new APIResponse(200, "Success", "Job created successfully"), HttpStatus.OK);

    }

    @GetMapping("get/{id}")
    public ResponseEntity<APIResponse> getJob(@PathVariable String id){
        JobDTO jobDTO = jobService.getJob(id);
        return ResponseEntity.ok(new APIResponse(200,"Success", jobDTO));
    }

    @PutMapping("update")
    public ResponseEntity<APIResponse> updateJob(@RequestBody JobDTO jobDTO){
        jobService.updateJob(jobDTO);
        return ResponseEntity.ok(new APIResponse(200,"Success", "Job updated successfully"));
    }

    @GetMapping("all")
    public ResponseEntity<APIResponse> getAllJobs(){
        List<JobDTO> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(new APIResponse(200,"Success", jobs));
    }

    @PatchMapping("status/{id}")
    public ResponseEntity<APIResponse> changeStatus(@PathVariable String id){
        jobService.changeJobStatus(id);
        return ResponseEntity.ok(new APIResponse(200,"Success", "Job status changed successfully"));
    }

    @GetMapping("search/{keyword}")
    public ResponseEntity<APIResponse> searchJob(@PathVariable String keyword) {
        List<Job> jobs = jobService.searchJob(keyword);
        return ResponseEntity.ok(new APIResponse(200, "Success", jobs));
    }

    @GetMapping
    public ResponseEntity<APIResponse> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<Job> jobPage = jobService.getJobs(page, size);
        return ResponseEntity.ok(new APIResponse(200, "Success", jobPage));
    }

}
