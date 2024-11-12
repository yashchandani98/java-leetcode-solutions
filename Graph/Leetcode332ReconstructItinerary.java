package Graph;

import java.util.*;

public class Leetcode332ReconstructItinerary {
    Map<String, PriorityQueue<String>> ticketsList = new HashMap<>();

    Set<String> ticketsUsed = new HashSet<>();

    public List<String> findItinerary(List<List<String>> tickets) {

        LinkedList<String> dests = new LinkedList<>();

        constructGraph(tickets);

        dfs("JFK", dests);

        return dests;
    }


    private void dfs(String airport, LinkedList<String> dests){
        PriorityQueue<String> destAirports = ticketsList.get(airport);

        while(destAirports!=null && !destAirports.isEmpty()) {
            dfs(destAirports.poll(), dests);
        }
        dests.addFirst(airport);
    }

    private void constructGraph(List<List<String>> tickets){
        for(List<String> ticket: tickets) {
            ticketsList.computeIfAbsent(ticket.get(0), k-> new PriorityQueue<>()).add(ticket.get(1));
        }
    }
}
