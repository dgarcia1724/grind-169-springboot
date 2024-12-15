package danny.grind_169_backend.controller;

import danny.grind_169_backend.entity.Problem;
import danny.grind_169_backend.service.ProblemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problems")
@CrossOrigin(origins = "*")
@Tag(name = "Problem", description = "Problem management APIs")
public class ProblemController {

    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @Operation(summary = "Create a new problem")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Problem created successfully",
                    content = @Content(schema = @Schema(implementation = Problem.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Problem> createProblem(@Valid @RequestBody Problem problem) {
        Problem createdProblem = problemService.createProblem(problem);
        return new ResponseEntity<>(createdProblem, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all problems with default sorting")
    @ApiResponse(responseCode = "200", description = "Retrieved all problems")
    @GetMapping
    public ResponseEntity<List<Problem>> getAllProblems() {
        return ResponseEntity.ok(problemService.getAllProblems());
    }

    @Operation(summary = "Update a problem by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Problem updated successfully"),
        @ApiResponse(responseCode = "404", description = "Problem not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Problem> updateProblem(
            @Parameter(description = "Problem ID") @PathVariable Long id,
            @Valid @RequestBody Problem problemDetails) {
        Problem updatedProblem = problemService.updateProblem(id, problemDetails);
        return ResponseEntity.ok(updatedProblem);
    }

    @Operation(summary = "Delete a problem by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Problem deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Problem not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProblem(@Parameter(description = "Problem ID") @PathVariable Long id) {
        problemService.deleteProblem(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get problems sorted by last edited (oldest first)")
    @ApiResponse(responseCode = "200", description = "Retrieved sorted problems")
    @GetMapping("/sort/last-edited")
    public ResponseEntity<List<Problem>> getProblemsSortedByLastEdited() {
        return ResponseEntity.ok(problemService.getAllProblemsSortedByLastEditedAsc());
    }

    @Operation(summary = "Get problems sorted by confidence (lowest first)")
    @ApiResponse(responseCode = "200", description = "Retrieved sorted problems")
    @GetMapping("/sort/confidence")
    public ResponseEntity<List<Problem>> getProblemsSortedByConfidence() {
        return ResponseEntity.ok(problemService.getAllProblemsSortedByConfidenceAsc());
    }

    @Operation(summary = "Get problems sorted by difficulty (hardest first)")
    @ApiResponse(responseCode = "200", description = "Retrieved sorted problems")
    @GetMapping("/sort/difficulty/desc")
    public ResponseEntity<List<Problem>> getProblemsSortedByDifficultyDesc() {
        return ResponseEntity.ok(problemService.getAllProblemsSortedByDifficultyDesc());
    }

    @Operation(summary = "Get problems sorted by difficulty (easiest first)")
    @ApiResponse(responseCode = "200", description = "Retrieved sorted problems")
    @GetMapping("/sort/difficulty/asc")
    public ResponseEntity<List<Problem>> getProblemsSortedByDifficultyAsc() {
        return ResponseEntity.ok(problemService.getAllProblemsSortedByDifficultyAsc());
    }

    @Operation(summary = "Get problems sorted by topic (A-Z)")
    @ApiResponse(responseCode = "200", description = "Retrieved sorted problems")
    @GetMapping("/sort/topic")
    public ResponseEntity<List<Problem>> getProblemsSortedByTopic() {
        return ResponseEntity.ok(problemService.getAllProblemsSortedByTopic());
    }

    @Operation(summary = "Get problems sorted by order number")
    @ApiResponse(responseCode = "200", description = "Retrieved sorted problems")
    @GetMapping("/sort/order")
    public ResponseEntity<List<Problem>> getProblemsSortedByOrderNumber() {
        return ResponseEntity.ok(problemService.getAllProblemsSortedByOrderNumber());
    }

    @Operation(summary = "Update timestamp for a problem")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Timestamp updated successfully"),
        @ApiResponse(responseCode = "404", description = "Problem not found")
    })
    @PatchMapping("/{id}/timestamp")
    public ResponseEntity<Problem> updateTimestamp(@Parameter(description = "Problem ID") @PathVariable Long id) {
        Problem updatedProblem = problemService.updateTimestamp(id);
        return ResponseEntity.ok(updatedProblem);
    }
}
