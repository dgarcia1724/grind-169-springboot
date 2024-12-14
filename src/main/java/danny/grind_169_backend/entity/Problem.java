package danny.grind_169_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "problems")
@Data
@NoArgsConstructor
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer orderNumber;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String problemName;

    @Column(columnDefinition = "TEXT")
    private String linkToProblem;

    @Column(columnDefinition = "TEXT")
    private String solution;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;

    @Column(length = 100)
    private String topic;

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10.0")
    @Column(nullable = false)
    private Double confidenceRating = 0.0;

    @Column(nullable = false)
    private LocalDateTime lastEdited;

    @PrePersist
    protected void onCreate() {
        lastEdited = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastEdited = LocalDateTime.now();
    }

    public enum Difficulty {
        EASY, MEDIUM, HARD
    }
}
