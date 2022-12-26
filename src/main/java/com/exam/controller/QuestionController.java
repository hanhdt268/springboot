package com.exam.controller;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    //add question
    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    //update question
    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    //get question
    @GetMapping("/")
    public ResponseEntity<?> getQuestion() {
        return ResponseEntity.ok(this.questionService.getQuestions());
    }

    //get single question


    //get  question
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qid") Long qid) {
//        Quiz quiz = new Quiz();
//        quiz.setqId(qId);
//        Set<Question> questionsOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
//        return ResponseEntity.ok(questionsOfQuiz);

        Quiz quiz = this.quizService.getQuiz(qid);
        Set<Question> questions = quiz.getQuestions();
        List<Question> list = new ArrayList(questions);
        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestion())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestion() + 1));
        }
        list.forEach((q) -> {
            q.setAnswer("");
        });
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    //get all

    @GetMapping("/quiz/all/{qId}")
    public ResponseEntity<?> getQuestionOfQuizAdmin(@PathVariable("qId") Long qId) {
        Quiz quiz = new Quiz();
        quiz.setqId(qId);
        Set<Question> questionsOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
        return ResponseEntity.ok(questionsOfQuiz);

//        Quiz quiz = this.quizService.getQuiz(qid);
//        Set<Question> questions = quiz.getQuestions();
//        List list = new ArrayList(questions);
//        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestion())) {
//            list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestion() + 1));
//        }
//        Collections.shuffle(list);
//        return ResponseEntity.ok(list);
    }

    //delete question
    @DeleteMapping("/{quesId}")
    public void deleteQuestion(@PathVariable("quesId") Long quesId) {
        this.questionService.deleteQuestion(quesId);
    }

    //eval quiz
    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
        double markGot = 0;
        int correctAnswer = 0;
        int attempt = 0;
        for (Question q : questions) {
            Question question = this.questionService.get(q.getQuesId());
            if (question.getAnswer().equals(q.getGivenAnswer())) {
                correctAnswer++;
                double markSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();

                markGot += markSingle;
            }
            if (q.getGivenAnswer() != null) {
                attempt++;
            }

        }
        Map<String, Object> map = Map.of("markGot", markGot, "correctAnswer", correctAnswer, "attempt", attempt);
        return ResponseEntity.ok(map);
    }
}
