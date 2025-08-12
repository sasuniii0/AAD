package lk.ijse.gdse.o11_backend.service.impl;

import jakarta.validation.Valid;
import lk.ijse.gdse.o11_backend.dto.JobDTO;
import lk.ijse.gdse.o11_backend.entity.Job;
import lk.ijse.gdse.o11_backend.exception.ResourceAlreadyFoundException;
import lk.ijse.gdse.o11_backend.exception.ResourceNotFoundException;
import lk.ijse.gdse.o11_backend.exception.ResourseEmptyException;
import lk.ijse.gdse.o11_backend.repository.JobRepository;
import lk.ijse.gdse.o11_backend.service.JobService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveJob(JobDTO jobDTO) {
        if (jobRepository.existsByIdAndJobTitle(jobDTO.getId(), jobDTO.getJobTitle())) {
            throw new ResourceAlreadyFoundException("Job with this id and  title already exists");
        }
        jobRepository.save(modelMapper.map(jobDTO, Job.class));
    }

    @Override
    public void updateJob(JobDTO jobDTO ) {
        if (!jobRepository.existsById(jobDTO.getId())) {
            throw new ResourceNotFoundException("Job does not exist");
        }
        jobRepository.save(modelMapper.map(jobDTO, Job.class));
    }

    @Override
    public List<JobDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        if (jobs.isEmpty()) {
            throw new ResourceNotFoundException("No jobs found");
        }
        //throw new ResourceNotFoundException("Error"); // 404 error
        return modelMapper.map(jobs, new TypeToken<List<JobDTO>>() { }.getType());
    }

    @Override
    public void changeJobStatus(String id) {
        if (id.isEmpty()){
            throw new ResourseEmptyException("Job id is empty");
        }
        jobRepository.updateJobStatus(id);
    }

    @Override
    public List<Job> searchJob(String keyword) {
        if (keyword.isEmpty()) {
            throw new NullPointerException("Search keyword is empty");
        }
        return jobRepository.findJobByJobTitleContainingIgnoreCase(keyword);
    }

    @Override
    public JobDTO getJob(String id) {
        if (id.isEmpty()) {
            throw new ResourceNotFoundException("Job id is empty");
        }
        return modelMapper.map(jobRepository.findById(Integer.valueOf(id)).get(), JobDTO.class);
    }

    @Override
    public Page<Job> getJobs(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page number must be non-negative and size must be positive");
        }
        Pageable pageable = PageRequest.of(page, size);
        return jobRepository.findAll(pageable);
    }


    public void deleteJob(@Valid String id) {

    }
}
