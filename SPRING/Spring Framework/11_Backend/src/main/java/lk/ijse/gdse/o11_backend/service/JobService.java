package lk.ijse.gdse.o11_backend.service;

import lk.ijse.gdse.o11_backend.dto.JobDTO;
import lk.ijse.gdse.o11_backend.entity.Job;

import java.util.List;

public interface JobService {
    void saveJob(JobDTO jobDTO);
    void updateJob(JobDTO jobDTO );
    List<JobDTO> getAllJobs();
    void changeJobStatus(String id);
    List<Job> searchJob(String id);
    JobDTO getJob(String id);
}
