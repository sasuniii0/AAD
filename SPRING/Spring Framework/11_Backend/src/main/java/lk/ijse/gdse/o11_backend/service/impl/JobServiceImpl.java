package lk.ijse.gdse.o11_backend.service.impl;

import lk.ijse.gdse.o11_backend.dto.JobDTO;
import lk.ijse.gdse.o11_backend.entity.Job;
import lk.ijse.gdse.o11_backend.repository.JobRepository;
import lk.ijse.gdse.o11_backend.service.JobService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveJob(JobDTO jobDTO) {
        jobRepository.save(modelMapper.map(jobDTO, Job.class));
    }

    @Override
    public void updateJob(JobDTO jobDTO ) {
        jobRepository.save(modelMapper.map(jobDTO, Job.class));
    }

    @Override
    public List<JobDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return modelMapper.map(jobs, new TypeToken<List<JobDTO>>() { }.getType());
    }

    @Override
    public void changeJobStatus(String id) {
        jobRepository.updateJobStatus(id);
    }

    @Override
    public List<Job> searchJob(String keyword) {
        return jobRepository.findJobByJobTitleContainingIgnoreCase(keyword);
    }

    @Override
    public JobDTO getJob(String id) {
        return modelMapper.map(jobRepository.findById(Integer.valueOf(id)).get(), JobDTO.class);
    }


}
