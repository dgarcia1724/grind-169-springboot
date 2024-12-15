package danny.grind_169_backend.service;

import danny.grind_169_backend.entity.Problem;
import danny.grind_169_backend.repository.ProblemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ProblemService {

    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    // Create a new problem
    public Problem createProblem(Problem problem) {
        return problemRepository.save(problem);
    }

    // Get all problems
    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }

    // Update a problem
    public Problem updateProblem(Long id, Problem problemDetails) {
        Problem problem = problemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Problem not found with id: " + id));

        problem.setOrderNumber(problemDetails.getOrderNumber());
        problem.setProblemName(problemDetails.getProblemName());
        problem.setLinkToProblem(problemDetails.getLinkToProblem());
        problem.setSolution(problemDetails.getSolution());
        problem.setDifficulty(problemDetails.getDifficulty());
        problem.setTopic(problemDetails.getTopic());
        problem.setConfidenceRating(problemDetails.getConfidenceRating());

        return problemRepository.save(problem);
    }

    // Delete a problem
    public void deleteProblem(Long id) {
        Problem problem = problemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Problem not found with id: " + id));
        problemRepository.delete(problem);
    }

    // Sort by last edited (oldest first)
    public List<Problem> getAllProblemsSortedByLastEditedAsc() {
        return problemRepository.findAll(Sort.by(Sort.Direction.ASC, "lastEdited"));
    }

    // Sort by confidence rating (lowest first)
    public List<Problem> getAllProblemsSortedByConfidenceAsc() {
        return problemRepository.findAll(Sort.by(Sort.Direction.ASC, "confidenceRating"));
    }

    // Sort by difficulty (HARD -> MEDIUM -> EASY)
    public List<Problem> getAllProblemsSortedByDifficultyDesc() {
        return problemRepository.findAllByOrderByDifficultyDesc();
    }

    // Sort by difficulty (EASY -> MEDIUM -> HARD)
    public List<Problem> getAllProblemsSortedByDifficultyAsc() {
        return problemRepository.findAllByOrderByDifficultyAsc();
    }

    // Sort by topic (A-Z)
    public List<Problem> getAllProblemsSortedByTopic() {
        return problemRepository.findAllByOrderByTopicAscNullsLast();
    }

    // Sort by order number (1,2,3...)
    public List<Problem> getAllProblemsSortedByOrderNumber() {
        return problemRepository.findAll(Sort.by(Sort.Direction.ASC, "orderNumber"));
    }

    // Update only the timestamp of a problem
    public Problem updateTimestamp(Long id) {
        Problem problem = problemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Problem not found with id: " + id));
        
        // Explicitly set the current timestamp
        problem.setLastEdited(LocalDateTime.now());
        
        return problemRepository.save(problem);
    }
}
