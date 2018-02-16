package com.codecool.githubParser;

import java.util.List;

public class GithubStat {

    String repositoryName;
    List<Contributor> contributorList;

    public GithubStat(String repositoryName, List<Contributor> contributorList) {
        this.repositoryName = repositoryName;
        this.contributorList = contributorList;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public List<Contributor> getContributorList() {
        return contributorList;
    }
}
