package com.exam.controller;


import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/{qId}")
    public Quiz getQuizId(@PathVariable("qId") Long qId) {
        return this.quizService.getQuiz(qId);
    }

    //delete quiz
    @DeleteMapping("{qId}")
    public void deleteQuiz(@PathVariable("qId") Long qId) {
        this.quizService.deleteQuiz(qId);
    }

    //get quiz by cid category
    @GetMapping("/category/{cId}")
    public List<Quiz> getQuizzesOfCategory(@PathVariable("cId") Long cId) {
        Category category = new Category();
        category.setcId(cId);
        return this.quizService.getQuizzesOfCategory(category);
    }

    //get active quizzes
    @GetMapping("/active")
    public List<Quiz> getActiveQuizzes() {
        return this.quizService.getActiveQuiz();
    }

    //get active quizzes of category
    @GetMapping("/category/active/{cId}")
    public List<Quiz> getActiveQuizzes(@PathVariable("cId") Long cId) {
        Category category = new Category();
        category.setcId(cId);
        return this.quizService.getActiveQuizzesOfCategory(category);
    }
}
