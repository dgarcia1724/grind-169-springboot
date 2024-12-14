package danny.grind_169_backend.controller;

import danny.grind_169_backend.entity.Problem;
import danny.grind_169_backend.service.ProblemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problems")
@CrossOrigin(origins = "*")
public class ProblemController {

    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    // Create a new problem
    @PostMapping
    public ResponseEntity<Problem> createProblem(@Valid @RequestBody Problem problem) {
        Problem createdProblem = problemService.createProblem(problem);
        return new ResponseEntity<>(createdProblem, HttpStatus.CREATED);
    }

    // Get all problems (default sorting)
    @GetMapping
    public ResponseEntity<List<Problem>> getAllProblems() {
        return ResponseEntity.ok(problemService.getAllProblems());
    }

    // Update a problem
    @PutMapping("/{id}")
    public ResponseEntity<Problem> updateProblem(@PathVariable Long id, 
                                               @Valid @RequestBody Problem problemDetails) {
        Problem updatedProblem = problemService.updateProblem(id, problemDetails);
        return ResponseEntity.ok(updatedProblem);
    }

    // Delete a problem
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProblem(@PathVariable Long id) {
        problemService.deleteProblem(id);
        return ResponseEntity.noContent().build();
    }

    // Get problems sorted by last edited (oldest first)
    @GetMapping("/sort/last-edited")
    public ResponseEntity<List<Problem>> getProblemsSortedByLastEdited() {
        return ResponseEntity.ok(problemService.getAllProblemsSortedByLastEditedAsc());
    }

    // Get problems sorted by confidence (lowest first)
    @GetMapping("/sort/confidence")
    public ResponseEntity<List<Problem>> getProblemsSortedByConfidence() {
        return ResponseEntity.ok(problemService.getAllProblemsSortedByConfidenceAsc());
    }

    // Get problems sorted by difficulty (hardest first)
    @GetMapping("/sort/difficulty/desc")
    public ResponseEntity<List<Problem>> getProblemsSortedByDifficultyDesc() {
        return ResponseEntity.ok(problemService.getAllProblemsSortedByDifficultyDesc());
    }

    // Get problems sorted by difficulty (easiest first)
    @GetMapping("/sort/difficulty/asc")
    public ResponseEntity<List<Problem>> getProblemsSortedByDifficultyAsc() {
        return ResponseEntity.ok(problemService.getAllProblemsSortedByDifficultyAsc());
    }

    // Get problems sorted by topic (A-Z)
    @GetMapping("/sort/topic")
    public ResponseEntity<List<Problem>> getProblemsSortedByTopic() {
        return ResponseEntity.ok(problemService.getAllProblemsSortedByTopic());
    }

    // Get problems sorted by order number
    @GetMapping("/sort/order")
    public ResponseEntity<List<Problem>> getProblemsSortedByOrderNumber() {
        return ResponseEntity.ok(problemService.getAllProblemsSortedByOrderNumber());
    }
}
