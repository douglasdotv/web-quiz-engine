package br.com.dv.engine.service;

import br.com.dv.engine.dto.AnswerSubmissionRequest;
import br.com.dv.engine.dto.AnswerSubmissionResponse;
import br.com.dv.engine.dto.QuizRequest;
import br.com.dv.engine.dto.QuizResponse;
import br.com.dv.engine.entity.AppUser;
import br.com.dv.engine.entity.Quiz;
import br.com.dv.engine.exception.DuplicateAnswerIndicesException;
import br.com.dv.engine.exception.QuizNotFoundException;
import br.com.dv.engine.exception.QuizNotOwnedByUserException;
import br.com.dv.engine.repository.AppUserRepository;
import br.com.dv.engine.repository.QuizRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.StreamSupport;

@Service
public class QuizServiceImpl implements QuizService {

    private static final String CORRECT_FEEDBACK = "Congratulations, you're right!";
    private static final String INCORRECT_FEEDBACK = "Wrong answer! Please try again.";
    private static final String USER_NOT_FOUND_TEMPLATE = "User with e-mail %s not found.";

    private final QuizRepository quizRepository;
    private final AppUserRepository userRepository;

    public QuizServiceImpl(QuizRepository quizRepository, AppUserRepository userRepository) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
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
        return quizRepository.findById(id)
                .map(quiz -> new QuizResponse(quiz.getId(), quiz.getTitle(), quiz.getText(), quiz.getOptions()))
                .orElseThrow(QuizNotFoundException::new);
    }

    @Override
    @Transactional
    public QuizResponse addQuiz(QuizRequest quiz) {
        Set<Integer> uniqueAnswerIndices = getUniqueAnswerIndices(quiz.answerIndices());
        AppUser authenticatedUser = getAuthenticatedUser();

        Quiz newQuiz = new Quiz();

        newQuiz.setTitle(quiz.title());
        newQuiz.setText(quiz.text());
        newQuiz.setOptions(quiz.options());
        newQuiz.setAnswerIndices(uniqueAnswerIndices);
        authenticatedUser.addQuiz(newQuiz);

        quizRepository.save(newQuiz);

        return new QuizResponse(newQuiz.getId(), newQuiz.getTitle(), newQuiz.getText(), newQuiz.getOptions());
    }

    @Override
    @Transactional
    public void deleteQuizById(Integer id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(QuizNotFoundException::new);

        if (isNotQuizAuthor(quiz)) {
            throw new QuizNotOwnedByUserException();
        }

        quiz.getAuthor().removeQuiz(quiz);
    }

    @Override
    @Transactional
    public QuizResponse updateQuizById(Integer id, QuizRequest newQuiz) {
        Set<Integer> uniqueAnswerIndices = getUniqueAnswerIndices(newQuiz.answerIndices());

        Quiz currQuiz = quizRepository.findById(id).orElseThrow(QuizNotFoundException::new);

        if (isNotQuizAuthor(currQuiz)) {
            throw new QuizNotOwnedByUserException();
        }

        currQuiz.setTitle(newQuiz.title());
        currQuiz.setText(newQuiz.text());
        currQuiz.setOptions(newQuiz.options());
        currQuiz.setAnswerIndices(uniqueAnswerIndices);

        quizRepository.save(currQuiz);

        return new QuizResponse(currQuiz.getId(), currQuiz.getTitle(), currQuiz.getText(), currQuiz.getOptions());
    }

    @Override
    @Transactional(readOnly = true)
    public AnswerSubmissionResponse submitAnswer(Integer id, AnswerSubmissionRequest answer) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(QuizNotFoundException::new);

        Set<Integer> correctAnswerIndices = quiz.getAnswerIndices();
        Set<Integer> submittedAnswerIndices = getUniqueAnswerIndices(answer.answerIndices());

        boolean isCorrect = verifyAnswer(correctAnswerIndices, submittedAnswerIndices);
        String feedback = isCorrect ? CORRECT_FEEDBACK : INCORRECT_FEEDBACK;

        return new AnswerSubmissionResponse(isCorrect, feedback);
    }

    private AppUser getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedEmail = authentication.getName();

        return userRepository.findByEmail(authenticatedEmail)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_TEMPLATE, authenticatedEmail
                        )));
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

    private boolean isNotQuizAuthor(Quiz quiz) {
        AppUser authenticatedUser = getAuthenticatedUser();
        return !authenticatedUser.equals(quiz.getAuthor());
    }

    private boolean verifyAnswer(Set<Integer> correctAnswerIndices, Set<Integer> submittedAnswerIndices) {
        return correctAnswerIndices.equals(submittedAnswerIndices);
    }

}
