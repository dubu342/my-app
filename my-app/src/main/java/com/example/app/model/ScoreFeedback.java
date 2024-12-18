package com.example.app.model;

public class ScoreFeedback {
    private int titleScore;
    private int structureScore;
    private int flowScore;
    private int grammarScore;
    private int toneScore;

    // Getters and Setters
    public int getTitleScore() {
        return titleScore;
    }

    public void setTitleScore(int titleScore) {
        this.titleScore = titleScore;
    }

    public int getStructureScore() {
        return structureScore;
    }

    public void setStructureScore(int structureScore) {
        this.structureScore = structureScore;
    }

    public int getFlowScore() {
        return flowScore;
    }

    public void setFlowScore(int flowScore) {
        this.flowScore = flowScore;
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
}
