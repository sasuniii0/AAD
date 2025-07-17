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
    @Query(value = "UPDATE Job j\n" +
            "SET j.status = CASE\n" +
            "                 WHEN j.status = 'Active' THEN 'Deactivate'\n" +
            "                 WHEN j.status = 'Deactivate' THEN 'Active'\n" +
            "                 ELSE j.status\n" +
            "               END\n" +
            "WHERE j.id = ?1\n" ,nativeQuery = true)
    void updateJobStatus(String id);
    List<Job> findJobByJobTitleContainingIgnoreCase(String keyword);
}
