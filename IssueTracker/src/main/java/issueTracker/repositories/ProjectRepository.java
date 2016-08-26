package issueTracker.repositories;

import issueTracker.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query("SELECT project FROM Project AS project WHERE project.id=:id")
    List<Project> findAllById(@Param(value = "id") Long id);
}
