package com.example.demo;

public class OutputGenerator {
    int seed = 0;
    int line = 0;
    String generatedInput;
    String output = "";

    OutputGenerator(int seed, int line) {
        this.seed = seed;
        this.line = line;
        generatedInput = new InputGenerator(seed, line).generate();
    }

    public String generate() {
        AvlTree tree = new AvlTree();
        String[] lines = generatedInput.split("</br>");
        String firstLine = lines[0];
        String[] firstLineSplit = firstLine.split(" ");
        tree.insert(new Member(Double.parseDouble(firstLineSplit[1]), firstLineSplit[0]));
        for (int i = 1; i < lines.length; i++) {
            String out = "";
            String inputLine = lines[i];
            String[] input = inputLine.split(" ");
            if (input[0].equals("MEMBER_IN")) {
                out = tree.insert(new Member(Double.parseDouble(input[2]), input[1]));
            } else if (input[0].equals("MEMBER_OUT")) {
                out = tree.remove(new Member(Double.parseDouble(input[2]), input[1]));
            } else if (input[0].equals("INTEL_TARGET")) {
                out = tree.lca(new Member(Double.parseDouble(input[2]), input[1]),
                        new Member(Double.parseDouble(input[4]), input[3]));
            } else if (input[0].equals("INTEL_DIVIDE")) {
                out = tree.divide();
            } else if (input[0].equals("INTEL_RANK")) {
                out = tree.rankAnalysis(new Member(Double.parseDouble(input[2]), input[1]));
            }
            output = output.concat(out);
        }
        return output;
    }
}
