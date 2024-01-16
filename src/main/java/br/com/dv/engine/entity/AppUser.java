package br.com.dv.engine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class AppUser {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(unique = true)
    private String email;

    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Quiz> quizzes = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    public void addQuiz(Quiz quiz) {
        this.quizzes.add(quiz);
        quiz.setAuthor(this);
    }

    public void removeQuiz(Quiz quiz) {
        this.quizzes.remove(quiz);
        quiz.setAuthor(null);
    }

}
