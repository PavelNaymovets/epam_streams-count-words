package com.efimchick.ifmo.streams.countwords;


import java.util.*;
import java.util.stream.Collectors;

public class Words {

    public String countWords(List<String> lines) {
        Map<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        StringBuilder outSb = new StringBuilder();
        String[] outString;
        List<String> list = new ArrayList<>();
        String strOut;
        Map<String, Integer> treeMap;
        int indicesOfLastNewLine;

        lines.forEach(s -> {
            sb.append(s);
            sb.append(" ");
        });

        outString = sb.toString().replaceAll("[^a-zA-Zа-яА-Я]", " ").split(" ");


        Arrays.stream(outString).filter(s ->
                s.length() >= 4).forEach(list::add);

        list.stream()
                .map(str -> map.containsKey(str.toLowerCase()) ? map.put(str.toLowerCase(), map.get(str.toLowerCase()) + 1) : map.put(str.toLowerCase(), 1))
                .collect(Collectors.toList());
        treeMap = new TreeMap<>(Collections.reverseOrder());
        treeMap.putAll(map);
        List<Map.Entry<String, Integer>> copy = new ArrayList<>(treeMap.entrySet());

        copy.sort(dialPadNumCompare);
        copy.sort(new CustomComparator());


        copy.stream().filter(s -> s.getValue() >= 10).forEach(s -> {
            outSb.append(s).append("\n");
        });


        indicesOfLastNewLine =  outSb.lastIndexOf("\n");
        outSb.replace(indicesOfLastNewLine, indicesOfLastNewLine + 1, "");
        strOut = outSb.toString().replaceAll("=", " - ");
        return strOut;
    }

    public static Comparator<Map.Entry<String, Integer>> dialPadNumCompare = new Comparator<Map.Entry<String, Integer>>() {
        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            return o1.getKey().compareTo(o2.getKey());
        }
    };
}
