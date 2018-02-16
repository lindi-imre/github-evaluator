package com.codecool.githubParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GithubStatParser {

    public static GithubStat getGithubStat(String repositoryName) {

        JSONArray jsonStatArray = getStatJSON(repositoryName);
        List<Contributor> contributors = new ArrayList<Contributor>();

        for (int i = 0; i < jsonStatArray.length(); i++) {
            contributors.add(getContributorFromJSONObject(jsonStatArray.getJSONObject(i)));
        }

        return new GithubStat(repositoryName, contributors);
    }

    private static JSONArray getStatJSON(String repositoryName) {

        String response = "";

        try {
            URL oracle = new URL("https://api.github.com/repos/CodecoolMSC2017/" + repositoryName + "/stats/contributors");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));
            String inputLine = "";
            while ((inputLine = in.readLine()) != null)
                response += inputLine;
            in.close();
        }
        catch (Exception e) {
            System.out.println("Error while parsing data!");
        }
        System.out.println(response);
        JSONArray jsonObj = new JSONArray(response);

        return jsonObj;
    }

    private static Contributor getContributorFromJSONObject(JSONObject contributorJSONObject) {
        JSONArray weeks = contributorJSONObject.getJSONArray("weeks");
        int commits = 0;
        int additions = 0;
        int deletions = 0;
        for (int i = 0; i < weeks.length(); i++) {
            JSONObject actualWeek = weeks.getJSONObject(i);
            commits += actualWeek.getInt("c");
            additions += actualWeek.getInt("a");
            deletions += actualWeek.getInt("d");
        }

        String author = contributorJSONObject.getJSONObject("author").getString("login");

        return new Contributor(author, additions, deletions, commits);
    }


}
