package com.exam.controller;


import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizService quizService;


    //add quiz service
    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    //update quiz
    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    //get quiz
    @GetMapping("/")
    public ResponseEntity<?> getQuiz() {
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    //get quiz with id
    @GetMapping("/{qid}")
    public Quiz getQuizId(@PathVariable("qid") Long qid) {
        return this.quizService.getQuiz(qid);
    }

    //delete quiz
    @DeleteMapping("{qid}")
    public void deleteQuiz(@PathVariable("qid") Long qid) {
        this.quizService.deleteQuiz(qid);
    }
}
