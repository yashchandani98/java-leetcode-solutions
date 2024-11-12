package Graph;

import java.util.*;
public class RelationalQueries {
    static Set<String> dfsVisited = new HashSet<>();
    public static void main(String[] args) {
        List<String[]> inputRelations = new ArrayList<>();
        inputRelations.add(new String[]{"a", "<", "b"});
        inputRelations.add(new String[]{"b", ">", "c"});
        inputRelations.add(new String[]{"a", "<", "c"});
        inputRelations.add(new String[]{"d", ">", "b"});

        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"a", "<", "b"});
        queries.add(new String[]{"b", "<", "a"});

        System.out.println(solve(inputRelations, queries));
    }

    private static boolean solve(List<String[]> relations, List<String[]> queries) {
        Map<String , List<String>> relationAdjacencyList = new HashMap<>();

        for(String[] relation: relations){
            if(Objects.equals(relation[1], "<")) {
                relationAdjacencyList.computeIfAbsent(relation[0], k-> new ArrayList<>()).add(relation[2]);
            } else {
                relationAdjacencyList.computeIfAbsent(relation[2], k-> new ArrayList<>()).add(relation[0]);
            }
        }

        for(String[] query: queries){
            if(Objects.equals(query[1], "<")) {
                if(!dfs(query[0], query[2], relationAdjacencyList)){
                   return false;
                }
            } else {
                if(!dfs(query[2], query[0], relationAdjacencyList)){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean dfs(String source, String dest, Map<String , List<String>> relationAdjacencyList){
        if(Objects.equals(source, dest)) {
            return true;
        } else if(dfsVisited.contains(source)) {
            return false;
        }

        List<String> neighbours = relationAdjacencyList.getOrDefault(source, new ArrayList<>());
        dfsVisited.add(source);
        for(String nei: neighbours){
            if(dfs(nei, dest, relationAdjacencyList)){
                return true;
            }
        }
        return false;
    }
}
