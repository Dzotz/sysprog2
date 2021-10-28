package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader(new File("C:\\Users\\User\\IdeaProjects\\sysprog2\\src\\com\\company\\input.txt"));
            BufferedReader reader = new BufferedReader(fr);
            Path path = Path.of("C:\\Users\\User\\IdeaProjects\\sysprog2\\src\\com\\company\\input.txt");
            Automaton aut1= new Automaton(), aut2=new Automaton();

            aut1.readAutomaton(reader);
            aut2.readAutomaton(reader);

            HashMap<Map.Entry<Integer, Integer>, Boolean> used = new HashMap<>();
            ArrayList<Map.Entry<Integer, Integer>> q = new ArrayList<>();
            q.add(new AbstractMap.SimpleEntry<>(aut1.s0, aut2.s0));
            boolean res = true;

            while (q.size()>0){
                Map.Entry<Integer, Integer> pair = q.get(q.size()-1);
                q.remove(q.size()-1);
                int state1 = pair.getKey();
                int state2 = pair.getValue();
                if (aut1.fin.contains(state1)!=aut2.fin.contains(state2)){
                    res = false;
                    break;
                }
                used.put(pair, true);
                for (String c:aut1.A){
                    Map.Entry<Integer, Integer> newPair = new AbstractMap.SimpleEntry<>(aut1.f.get(state1).get(c), aut2.f.get(state2).get(c) );
                    if(!used.containsKey(newPair)){
                        q.add(newPair);
                    }
                }
            }

            System.out.println(res);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
