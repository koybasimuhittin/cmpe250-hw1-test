package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class InputGenerator {
    int seed = 0;
    int line = 0;
    Random random;

    String[] names = new String[26];
    String[] operations = new String[] { "MEMBER_IN", "MEMBER_OUT", "INTEL_TARGET", "INTEL_DIVIDE", "INTEL_RANK",
            "MEMBER_IN", "MEMBER_IN", "MEMBER_IN", "MEMBER_IN" };
    String result = "";

    Map<Double, String> hm = new HashMap<Double, String>();

    InputGenerator(int seed, int line) {
        this.seed = seed;
        this.line = line;
        random = new Random(seed);
        for (int i = 0; i < 26; i++) {
            names[i] = String.valueOf((char) ('A' + i));
        }
        String name = names[random.nextInt(26)];
        name = name.concat("_");
        name = name.concat(names[random.nextInt(26)]);
        Double gms = random.nextInt(10000) / 1000.0;
        this.hm.put(gms, name);
        result = result.concat(String.format("%s %.3f", name, gms));
        result = result.concat("</br>");
    }

    public String memberIn() {
        String name = names[random.nextInt(26)];
        name = name.concat("_");
        name = name.concat(names[random.nextInt(26)]);
        Double gms;
        while (true) {
            gms = random.nextInt(10000) / 1000.0;
            if (!this.hm.containsKey(gms)) {
                this.hm.put(gms, name);
                break;
            }
        }
        return String.format("%s %s %.3f", operations[0], name, gms);
    }

    public String memberOut() {
        Double gms = (Double) this.hm.keySet().toArray()[random.nextInt(this.hm.size())];
        String name = this.hm.get(gms);
        if (!this.hm.containsKey(gms)) {
            return String.format("%s %s", "not-found", "</br>");
        }
        this.hm.remove(gms);
        return String.format("%s %s %.3f", operations[1], name, gms);
    }

    public String intelTarget() {
        Double gms1 = (Double) this.hm.keySet().toArray()[random.nextInt(this.hm.size())];
        Double gms2 = (Double) this.hm.keySet().toArray()[random.nextInt(this.hm.size())];
        return String.format("%s %s %.3f %s %.3f", operations[2], this.hm.get(gms1), gms1, this.hm.get(gms2), gms2);
    }

    public String intelDivide() {
        return String.format("%s", operations[3]);
    }

    public String intelRank() {
        Double gms = (Double) this.hm.keySet().toArray()[random.nextInt(this.hm.size())];
        return String.format("%s %s %.3f", operations[4], this.hm.get(gms), gms);
    }

    public String generate() {
        for (int i = 0; i < this.line; i++) {
            String operation = operations[random.nextInt(operations.length)];
            if (operation.equals(operations[0])) {
                result = result.concat(memberIn());
                result = result.concat("</br>");
            }
            if (operation.equals(operations[1])) {
                if (this.hm.size() <= 1) {
                    i--;
                    continue;
                }
                result = result.concat(memberOut());
                result = result.concat("</br>");
            }
            if (operation.equals(operations[2])) {
                result = result.concat(intelTarget());
                result = result.concat("</br>");
            }
            if (operation.equals(operations[3])) {
                result = result.concat(intelDivide());
                result = result.concat("</br>");
            }
            if (operation.equals(operations[4])) {
                result = result.concat(intelRank());
                result = result.concat("</br>");
            }
        }
        return result;

    }

    public void main() {

    }
}
