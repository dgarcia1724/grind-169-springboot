package danny.grind_169_backend.repository;

import danny.grind_169_backend.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {
    
    // For difficulty sorting
    @Query("SELECT p FROM Problem p ORDER BY CASE p.difficulty " +
           "WHEN 'EASY' THEN 1 " +
           "WHEN 'MEDIUM' THEN 2 " +
           "WHEN 'HARD' THEN 3 END ASC")
    List<Problem> findAllByOrderByDifficultyAsc();

    @Query("SELECT p FROM Problem p ORDER BY CASE p.difficulty " +
           "WHEN 'EASY' THEN 1 " +
           "WHEN 'MEDIUM' THEN 2 " +
           "WHEN 'HARD' THEN 3 END DESC")
    List<Problem> findAllByOrderByDifficultyDesc();
    
    // For topic sorting (case insensitive, nulls last)
    @Query("SELECT p FROM Problem p ORDER BY LOWER(p.topic) ASC NULLS LAST")
    List<Problem> findAllByOrderByTopicAscNullsLast();
}
