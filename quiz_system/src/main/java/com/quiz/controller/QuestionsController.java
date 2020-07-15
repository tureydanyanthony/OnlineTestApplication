package com.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.model.Attempt;
import com.quiz.model.Questionmodel;
import com.quiz.service.QuestionsService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class QuestionsController {
	
	 
	
	@Autowired
    private QuestionsService service;
	
	
	@PostMapping("/question")
	public Questionmodel storeQuestion(@RequestBody Questionmodel question) {
		
	 return service.storeQuestions(question);
	}
	
	@GetMapping("/questions")
	public List<Questionmodel> getQuestions(){
		Questionmodel q1=new Questionmodel(1,"What is java","prg lang","scripting lang",1);
		Questionmodel q2=new Questionmodel(2,"How many access specifiers are available in java","two","four",2);
		Questionmodel q3=new Questionmodel(3,"What does oops stand for","object oriented analysis and design","object oriented pgm lang ",2);
		Questionmodel q4=new Questionmodel(4,"Does java support multiple inheritance","no","yes",1);
		service.storeQuestions(q1);
		service.storeQuestions(q2);
		service.storeQuestions(q3);
		service.storeQuestions(q4);
		return service.getQuestionmodels();
	}
	
	@PostMapping("/evaluate")
	public int scoreAnswers(@RequestBody List<Questionmodel> questionmodels) {
		int v=service.getResults(questionmodels);
		Attempt a=new Attempt(v);
		service.storeAttempts(a);
		return v;
	}
	
	@GetMapping("/attempts")
	public  List<Attempt> getAttempts(){
		return  service.getAttempts();
	}
	

}
