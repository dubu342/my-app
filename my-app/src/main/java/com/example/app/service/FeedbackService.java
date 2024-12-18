package com.example.app.service;

import com.example.app.model.EmailRequest;
import com.example.app.model.Feedback;
import com.example.app.model.FeedbackResponse;
import com.example.app.model.ScoreFeedback;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService {

	private static final String FILE_PATH = "feedback.json"; // 피드백을 저장할 파일 경로
	private static final ObjectMapper objectMapper = new ObjectMapper(); // Jackson 라이브러리로 JSON 처리

	// 제출된 데이터를 파일에 저장
	public FeedbackResponse getFeedback(EmailRequest emailRequest) {
		// 피드백 생성 및 수정
		String correctedTitle = emailRequest.getEmailTitle() + " - 검토 요청";
		String correctedBody = emailRequest.getEmailBody().replace("부탁", "요청");

		// 점수 계산
		ScoreFeedback scoreFeedback = calculateScores(emailRequest);

		// 개선 피드백 생성
		String improvementFeedback = generateImprovementFeedback(scoreFeedback);

		// FeedbackResponse 객체 생성
		FeedbackResponse response = new FeedbackResponse();
		response.setCorrectedTitle(correctedTitle);
		response.setCorrectedBody(correctedBody);
		response.setGrammarFeedback("문법 오류 없음.");
		response.setToneFeedback("톤이 적절합니다.");
		response.setContextFeedback("내용이 이해하기 쉽습니다.");
		response.setImprovementFeedback(improvementFeedback);
		response.setScoreFeedback(scoreFeedback);

		// Feedback 객체 생성하여 JSON 파일에 저장
		Feedback feedback = new Feedback();
		feedback.setEmailTitle(emailRequest.getEmailTitle());
		feedback.setEmailBody(emailRequest.getEmailBody());
		feedback.setCorrectedTitle(correctedTitle);
		feedback.setCorrectedBody(correctedBody);
		feedback.setGrammarFeedback("문법 오류 없음.");
		feedback.setToneFeedback("톤이 적절합니다.");
		feedback.setContextFeedback("내용이 이해하기 쉽습니다.");
		feedback.setImprovementFeedback(improvementFeedback);
		feedback.setGrammarScore(scoreFeedback.getGrammarScore());
		feedback.setToneScore(scoreFeedback.getToneScore());
		feedback.setFlowScore(scoreFeedback.getFlowScore());
		feedback.setStructureScore(scoreFeedback.getStructureScore());
		feedback.setTitleScore(scoreFeedback.getTitleScore());

		// 기존 피드백 리스트에 새 피드백 추가 후 저장
		List<Feedback> feedbackList = loadFeedbackList();
		feedbackList.add(feedback);
		saveFeedbackList(feedbackList);

		return response;
	}

	// 점수 계산 로직
	private ScoreFeedback calculateScores(EmailRequest emailRequest) {
		ScoreFeedback scores = new ScoreFeedback();
		scores.setTitleScore(
				emailRequest.getEmailTitle().length() > 10 && emailRequest.getEmailTitle().contains("요청") ? 9 : 6);
		scores.setStructureScore(
				emailRequest.getEmailBody().split("\\.").length > 3 && emailRequest.getEmailBody().length() < 500 ? 9
						: 5);
		scores.setFlowScore(emailRequest.getEmailBody().matches(".*(따라서|그러나|결과적으로).*") ? 9 : 6);
		scores.setGrammarScore(!emailRequest.getEmailBody().matches(".*\\s{2,}.*")
				&& !emailRequest.getEmailBody().matches(".*(\\b\\w+\\b).*\\1.*") ? 9 : 5);
		scores.setToneScore(
				emailRequest.getEmailBody().contains("감사합니다") || emailRequest.getEmailBody().contains("부탁드립니다") ? 10
						: 6);
		return scores;
	}

	// 개선 피드백 생성
	private String generateImprovementFeedback(ScoreFeedback scores) {
		int minScore = Math.min(Math.min(scores.getTitleScore(), scores.getStructureScore()),
				Math.min(scores.getFlowScore(), Math.min(scores.getGrammarScore(), scores.getToneScore())));

		if (minScore == scores.getTitleScore()) {
			return "제목이 모호하거나 키워드가 부족합니다. 제목에 '요청', '문의' 등의 단어를 포함해 보세요.";
		} else if (minScore == scores.getStructureScore()) {
			return "문장 구조가 복잡하거나 문장이 너무 깁니다. 짧고 간결하게 작성하세요.";
		} else if (minScore == scores.getFlowScore()) {
			return "문맥 흐름이 자연스럽지 않습니다. '따라서', '그러나' 등의 연결어를 사용해 보세요.";
		} else if (minScore == scores.getGrammarScore()) {
			return "문법 오류가 있습니다. 반복된 단어나 불필요한 공백을 제거해 보세요.";
		} else {
			return "톤이 적절하지 않습니다. 정중한 표현('감사합니다', '부탁드립니다')을 추가해 보세요.";
		}
	}

	public boolean deleteFeedback(int index) {
		try {
			File file = new File(FILE_PATH);
			if (!file.exists() || file.length() == 0) {
				System.err.println("File not found or empty: " + FILE_PATH);
				return false; // 파일이 없거나 비어 있으면 삭제 실패
			}

			// 기존 데이터 불러오기
			List<Feedback> feedbackList = loadFeedbackList();

			// 인덱스 범위 확인
			if (index < 0 || index >= feedbackList.size()) {
				System.err.println("Invalid index: " + index + ". Total items: " + feedbackList.size());
				return false; // 인덱스가 범위를 벗어난 경우 삭제 실패
			}

			// 데이터 삭제
			feedbackList.remove(index);

			// 변경된 데이터 저장
			saveFeedbackList(feedbackList);
			System.out.println("Feedback at index " + index + " deleted. Remaining items: " + feedbackList.size());
			return true; // 삭제 성공
		} catch (Exception e) {
			e.printStackTrace();
			return false; // 예외 발생 시 삭제 실패
		}
	}

	// 피드백 리스트 로드 메서드 (기존)
	private List<Feedback> loadFeedbackList() {
		try {
			File file = new File(FILE_PATH);
			if (!file.exists() || file.length() == 0) {
				return new ArrayList<>(); // 파일이 없거나 비어 있으면 빈 리스트 반환
			}
			return objectMapper.readValue(file,
					objectMapper.getTypeFactory().constructCollectionType(List.class, Feedback.class));
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	// 피드백 리스트 저장 메서드 (기존)
	private void saveFeedbackList(List<Feedback> feedbackList) {
		try {
			objectMapper.writeValue(new File(FILE_PATH), feedbackList);
			System.out.println("Updated feedback list saved to file: " + FILE_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 모든 피드백을 조회하는 메서드
	public List<Feedback> getAllFeedback() {
		return loadFeedbackList();
	}
}
