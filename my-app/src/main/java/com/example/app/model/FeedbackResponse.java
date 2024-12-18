package com.example.app.model;

public class FeedbackResponse {
    private String correctedTitle;
    private String correctedBody;
    private String grammarFeedback;
    private String toneFeedback;
    private String contextFeedback;
    private String improvementFeedback;  // 가장 부족한 항목 피드백
    private ScoreFeedback scoreFeedback; // 카테고리별 점수 객체

    // Getter와 Setter 메서드

    public String getCorrectedTitle() {
        return correctedTitle;
    }

    public void setCorrectedTitle(String correctedTitle) {
        this.correctedTitle = correctedTitle;
    }

    public String getCorrectedBody() {
        return correctedBody;
    }

    public void setCorrectedBody(String correctedBody) {
        this.correctedBody = correctedBody;
    }

    public String getGrammarFeedback() {
        return grammarFeedback;
    }

    public void setGrammarFeedback(String grammarFeedback) {
        this.grammarFeedback = grammarFeedback;
    }

    public String getToneFeedback() {
        return toneFeedback;
    }

    public void setToneFeedback(String toneFeedback) {
        this.toneFeedback = toneFeedback;
    }

    public String getContextFeedback() {
        return contextFeedback;
    }

    public void setContextFeedback(String contextFeedback) {
        this.contextFeedback = contextFeedback;
    }

    public String getImprovementFeedback() {
        return improvementFeedback;
    }

    public void setImprovementFeedback(String improvementFeedback) {
        this.improvementFeedback = improvementFeedback;
    }

    public ScoreFeedback getScoreFeedback() {
        return scoreFeedback;
    }

    public void setScoreFeedback(ScoreFeedback scoreFeedback) {
        this.scoreFeedback = scoreFeedback;
    }

    @Override
    public String toString() {
        return "FeedbackResponse{" +
                "correctedTitle='" + correctedTitle + '\'' +
                ", correctedBody='" + correctedBody + '\'' +
                ", grammarFeedback='" + grammarFeedback + '\'' +
                ", toneFeedback='" + toneFeedback + '\'' +
                ", contextFeedback='" + contextFeedback + '\'' +
                ", improvementFeedback='" + improvementFeedback + '\'' +
                ", scoreFeedback=" + scoreFeedback +
                '}';
    }
}
