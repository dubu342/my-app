package com.example.app.controller;

import com.example.app.model.*;
import com.example.app.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "http://localhost:5500")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // 피드백 생성 API
    @PostMapping
    public FeedbackResponse getFeedback(@RequestBody EmailRequest emailRequest) {
        return feedbackService.getFeedback(emailRequest);
    }

    // 모든 피드백 조회 API
    @GetMapping("/all")
    public List<Feedback> getAllFeedback() {
        return feedbackService.getAllFeedback();  // 파일에서 모든 피드백 조회
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable int index) {
        try {
            boolean isDeleted = feedbackService.deleteFeedback(index);
            if (isDeleted) {
                System.out.println("Feedback at index " + index + " deleted successfully.");
                return ResponseEntity.ok().build();  // 성공 시 200 OK 반환
            } else {
                System.err.println("Failed to delete feedback at index " + index + ". Index out of range or file not found.");
                return ResponseEntity.badRequest().build();  // 실패 시 400 Bad Request 반환
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // 서버 오류 시 500 반환
        }
    }
}
