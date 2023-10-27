package com.ambapharm.data.models;

public class IssueCard {
    private String mainTitle;
    private String cardNum;
    private String subtitle;

    public IssueCard(String mainTitle, String cardNum, String subtitle, String cardComment) {
        this.mainTitle = mainTitle;
        this.cardNum = cardNum;
        this.subtitle = subtitle;
        this.cardComment = cardComment;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getCardComment() {
        return cardComment;
    }

    public void setCardComment(String cardComment) {
        this.cardComment = cardComment;
    }

    private String cardComment;



}
