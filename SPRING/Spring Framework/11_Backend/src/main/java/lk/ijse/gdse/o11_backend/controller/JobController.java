package lk.ijse.gdse.o11_backend.controller;

import lk.ijse.gdse.o11_backend.dto.JobDTO;
import lk.ijse.gdse.o11_backend.entity.Job;
import lk.ijse.gdse.o11_backend.service.impl.JobServiceImpl;
import lombok.RequiredArgsConstructor;
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
    public void createJob(@RequestBody JobDTO jobDTO){
        jobService.saveJob(jobDTO);
    }

    @PutMapping("update")
    public void updateJob(@RequestBody JobDTO jobDTO){
        jobService.updateJob(jobDTO);
    }

    @GetMapping("all")
    public List<JobDTO> getAllJobs(){
        return jobService.getAllJobs();
    }

    @PatchMapping("status/{id}")
    public void changeStatus(@PathVariable String id){
        jobService.changeJobStatus(id);
    }

    @GetMapping("search/{keyword}")
    public List<Job> searchJob(@PathVariable String keyword) {
        return jobService.searchJob(keyword);
    }

}
