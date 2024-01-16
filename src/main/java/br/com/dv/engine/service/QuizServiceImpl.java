package br.com.dv.engine.service;

import br.com.dv.engine.dto.AnswerSubmissionRequest;
import br.com.dv.engine.dto.AnswerSubmissionResponse;
import br.com.dv.engine.dto.QuizRequest;
import br.com.dv.engine.dto.QuizResponse;
import br.com.dv.engine.entity.Quiz;
import br.com.dv.engine.exception.DuplicateAnswerIndicesException;
import br.com.dv.engine.exception.QuizNotFoundException;
import br.com.dv.engine.repository.QuizRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.StreamSupport;

@Service
public class QuizServiceImpl implements QuizService {

    private static final String CORRECT_FEEDBACK = "Congratulations, you're right!";
    private static final String INCORRECT_FEEDBACK = "Wrong answer! Please try again.";

    private final QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizResponse> getAllQuizzes() {
        Iterable<Quiz> quizzes = quizRepository.findAll();

        return StreamSupport.stream(quizzes.spliterator(), false)
                .map(quiz -> new QuizResponse(quiz.getId(), quiz.getTitle(), quiz.getText(), quiz.getOptions()))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public QuizResponse getQuizById(Integer id) {
        Optional<Quiz> quizOptional = quizRepository.findById(id);

        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            return new QuizResponse(quiz.getId(), quiz.getTitle(), quiz.getText(), quiz.getOptions());
        }

        throw new QuizNotFoundException();
    }

    @Override
    @Transactional
    public QuizResponse addQuiz(QuizRequest quiz) {
        Set<Integer> uniqueAnswerIndices = getUniqueAnswerIndices(quiz.answerIndices());

        Quiz newQuiz = new Quiz();

        newQuiz.setTitle(quiz.title());
        newQuiz.setText(quiz.text());
        newQuiz.setOptions(quiz.options());
        newQuiz.setAnswerIndices(uniqueAnswerIndices);

        quizRepository.save(newQuiz);

        return new QuizResponse(newQuiz.getId(), newQuiz.getTitle(), newQuiz.getText(), newQuiz.getOptions());
    }

    @Override
    @Transactional(readOnly = true)
    public AnswerSubmissionResponse submitAnswer(Integer id, AnswerSubmissionRequest answer) {
        Optional<Quiz> quizOptional = quizRepository.findById(id);

        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();

            Set<Integer> correctAnswerIndices = quiz.getAnswerIndices();
            Set<Integer> submittedAnswerIndices = getUniqueAnswerIndices(answer.answerIndices());

            boolean isCorrect = verifyAnswer(correctAnswerIndices, submittedAnswerIndices);
            String feedback = isCorrect ? CORRECT_FEEDBACK : INCORRECT_FEEDBACK;

            return new AnswerSubmissionResponse(isCorrect, feedback);
        }

        throw new QuizNotFoundException();
    }

    private Set<Integer> getUniqueAnswerIndices(List<Integer> answerIndices) {
        if (answerIndices != null) {
            Set<Integer> uniqueAnswerIndices = new HashSet<>(answerIndices);
            if (uniqueAnswerIndices.size() != answerIndices.size()) {
                throw new DuplicateAnswerIndicesException();
            }
            return uniqueAnswerIndices;
        }
        return Collections.emptySet();
    }

    private boolean verifyAnswer(Set<Integer> correctAnswerIndices, Set<Integer> submittedAnswerIndices) {
        return correctAnswerIndices.equals(submittedAnswerIndices);
    }

}
