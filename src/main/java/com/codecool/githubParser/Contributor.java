package com.codecool.githubParser;

public class Contributor {
    private String userName;
    private int additions;
    private int deletion;
    private int commits;

    public Contributor(String userName, int additions, int deletion, int commits) {
        this.userName = userName;
        this.additions = additions;
        this.deletion = deletion;
        this.commits = commits;
    }

    public String getUserName() {
        return userName;
    }

    public int getAdditions() {
        return additions;
    }

    public int getDeletion() {
        return deletion;
    }

    public int getCommits() {
        return commits;
    }

    public int getTotalAdditions() {
        return additions - deletion;
    }
}
