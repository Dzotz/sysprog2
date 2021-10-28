package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Automaton {
    public List<String> A = new ArrayList<>();
    public List<Integer> S;
    public int s0;
    public HashMap<Integer, HashMap<String, Integer>> f = new HashMap<>();
    public List<Integer> fin = new ArrayList<>();

    public void readAutomaton(BufferedReader reader) throws IOException {
        String alphabet = reader.readLine();
        this.A = Arrays.stream(alphabet.split(" ")).collect(Collectors.toList());
        String states = reader.readLine();
        this.S = Arrays.stream(states.split(" ")).map(Integer::valueOf).collect(Collectors.toList());
        String startState = reader.readLine();
        this.s0 = Integer.parseInt(startState);
        String finalStates = reader.readLine();
        this.fin = Arrays.stream(finalStates.split(" ")).map(Integer::valueOf).collect(Collectors.toList());
        String changeFunc = reader.readLine();
        while (!changeFunc.equals("end")){
            int curState = Integer.parseInt(changeFunc.substring(0,changeFunc.indexOf(" ")));
            changeFunc = changeFunc.substring(changeFunc.indexOf(" ")+1);
            String sym = changeFunc.substring(0, changeFunc.indexOf(" "));
            changeFunc = changeFunc.substring(changeFunc.indexOf(" ")+1);
            int nextState = Integer.parseInt(changeFunc);
            if (!this.f.containsKey(curState)){
                HashMap<String, Integer> val = new HashMap<>();
                val.put(sym, nextState);
                this.f.put(curState, val);
            }
            else{
                this.f.get(curState).put(sym, nextState);
            }
            changeFunc = reader.readLine();
        }
    }

    @Override
    public String toString() {
        return "Automaton{" +
                "A=" + A +
                ", S=" + S +
                ", s0=" + s0 +
                ", f=" + f +
                ", fin=" + fin +
                '}';
    }



}
