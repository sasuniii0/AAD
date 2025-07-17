package lk.ijse.gdse.o11_backend.repository;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.o11_backend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Job j SET j.status = 'Deactivate' WHERE j.id = ?1" ,nativeQuery = true)
    void updateJobStatus(String id);
    List<Job> findJobByJobTitleContainingIgnoreCase(String keyword);
}
