package com.codecool.githubParser;

import java.util.List;

public class Main {

    public static void main(String[] args) {


        PieChart demo = new PieChart("Comparison", "Commits", "commit");
        demo.pack();
        demo.setVisible(true);

        demo = new PieChart("Comparison", "Lines added by...", "total");
        demo.pack();
        demo.setVisible(true);
    }
}
