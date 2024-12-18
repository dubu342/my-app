package com.example.app.model;

import java.io.Serializable;

public class Feedback implements Serializable {

    private String emailTitle;
    private String emailBody;
    private String correctedTitle;
    private String correctedBody;
    private String grammarFeedback;
    private String toneFeedback;
    private String contextFeedback;
    private String improvementFeedback;
    private int grammarScore;
    private int toneScore;
    private int flowScore;
    private int structureScore;
    private int titleScore;

    private String createdAt;  // 저장 시점 기록 (예: "2024-12-17 15:30:00")

    // 기본 생성자
    public Feedback() {
    }

    // Getter와 Setter 메서드
    public String getEmailTitle() {
        return emailTitle;
    }

    public void setEmailTitle(String emailTitle) {
        this.emailTitle = emailTitle;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

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

    public int getGrammarScore() {
        return grammarScore;
    }

    public void setGrammarScore(int grammarScore) {
        this.grammarScore = grammarScore;
    }

    public int getToneScore() {
        return toneScore;
    }

    public void setToneScore(int toneScore) {
        this.toneScore = toneScore;
    }

    public int getFlowScore() {
        return flowScore;
    }

    public void setFlowScore(int flowScore) {
        this.flowScore = flowScore;
    }

    public int getStructureScore() {
        return structureScore;
    }

    public void setStructureScore(int structureScore) {
        this.structureScore = structureScore;
    }

    public int getTitleScore() {
        return titleScore;
    }

    public void setTitleScore(int titleScore) {
        this.titleScore = titleScore;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    // toString() 메서드
    @Override
    public String toString() {
        return "Feedback{" +
                "emailTitle='" + emailTitle + '\'' +
                ", emailBody='" + emailBody + '\'' +
                ", correctedTitle='" + correctedTitle + '\'' +
                ", correctedBody='" + correctedBody + '\'' +
                ", grammarFeedback='" + grammarFeedback + '\'' +
                ", toneFeedback='" + toneFeedback + '\'' +
                ", contextFeedback='" + contextFeedback + '\'' +
                ", improvementFeedback='" + improvementFeedback + '\'' +
                ", grammarScore=" + grammarScore +
                ", toneScore=" + toneScore +
                ", flowScore=" + flowScore +
                ", structureScore=" + structureScore +
                ", titleScore=" + titleScore +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
