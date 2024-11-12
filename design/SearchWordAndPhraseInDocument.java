package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class SearchWordAndPhraseInDocument {
    /*

    https://leetcode.com/discuss/interview-question/1878821/Confluent-or-Onsite-or-Search-Phrase-in-Document/1326396
    * **/



    /**
     * Use Inverted Index implementation
     *
     *  TC:
     *  Search or Filtering candidates: O(n*m)
     *  n = phrase length
     *  m = number of Occurences of the first Phrase word in an inverted index for all the documents.
     *
     *  Inverted Index Creation:
     *   O(W)
     *   W = Total number of words in all the documents.
     *
     * */

    static class Pair<K,V>{
        K key;
        V value;


        public K getKey(){
            return this.key;
        }

        public V getValue(){
            return this.value;
        }

        Pair(K key, V value){
            this.key = key;
            this.value = value;
        }


    }

    private List<Integer> searchPhrase(List<Pair<Integer, String>> documents, String phrase){

        Map<String, Set<Pair<Integer, Integer>>> wordToDocIDAndPos = new HashMap<>();
        List<Integer> arr = Arrays.asList(1,2,3,4,5,6);
//        arr = arr.stream().filter((val)->val>2).collect(Collectors.toList());


        int[] arr1 = new int[] {1,2,3,4,5};
        List<Integer> ls = Arrays.stream(arr1).boxed().collect(Collectors.toList());
        arr1 = ls.stream().mapToInt(Integer::intValue).toArray();

        // Building an invertedIndex lookup map
        for(Pair<Integer, String> docContent: documents) {
            String[] words = docContent.getValue().replaceAll("[.,!]", "").toLowerCase().split(" ");
            int pos = 0;
            int docId = docContent.key;
            for(String word: words){
                wordToDocIDAndPos.computeIfAbsent(word, k -> new HashSet<>()).add(new Pair<Integer, Integer>(pos++, docId));
            }
        }


        String[] phraseSplit = phrase.toLowerCase().split(" ");
        Set<Pair<Integer, Integer>> candidates = wordToDocIDAndPos.getOrDefault(phraseSplit[0], new HashSet<>());

        for(int i = 1; i<phraseSplit.length; i++){
            Set<Pair<Integer, Integer>> phraseWordIdx = wordToDocIDAndPos.getOrDefault(phraseSplit[i], new HashSet<>());

            Set<Pair<Integer, Integer>> newCandidates = new HashSet<>();

            for(Pair<Integer, Integer> pair: candidates){
                Pair<Integer, Integer> possiblePhraseIdx = new Pair<>(pair.getKey()+1, pair.getValue());
                // Filtering out valid docs in the candidates where phrase can be present.
                if(phraseWordIdx.contains(possiblePhraseIdx)){
                    newCandidates.add(possiblePhraseIdx);
                }
            }
            candidates = newCandidates;
        }

        List<Integer> docIds = new ArrayList<>();

        for(Pair<Integer, Integer> can: candidates){
            // add the docIDS
            docIds.add(can.getValue());
        }
        return docIds;
    }



    /**
     *
     *
     * Use Suffix Trie implementation
     * TC:
     * Phrase Search: O(K) k = length of the phrase
     * Insert: O(W^2) W = Total words
     * For each Document of L words, L suffixes are inserted
     * For all Documents of total W words, W^2 suffixes are inserted
    * **/

    static class TrieNode {
        Map<String, TrieNode> children = new HashMap<>();
        List<Integer> docIds = new ArrayList<>();
    }

    private TrieNode root = new TrieNode();

    public void insert(String document, int docId) {
        String[] words = document.toLowerCase().split("\\W+"); // Split into words
        for (int i = 0; i < words.length; i++) {
            TrieNode node = root;
            for (int j = i; j < words.length; j++) {
                node.children.putIfAbsent(words[j], new TrieNode());
                node = node.children.get(words[j]);
                if (!node.docIds.contains(docId)) {
                    node.docIds.add(docId);
                }
            }
        }
    }


    public List<Integer> searchPhraseUsingTrie(String phrase) {
        String[] words = phrase.toLowerCase().split("\\W+");
        TrieNode node = root;

        // Traverse the Trie for the given phrase
        for (String word : words) {
            if (!node.children.containsKey(word)) {
                return Collections.emptyList(); // Phrase not found
            }
            node = node.children.get(word);
        }

        // Return document IDs at the end of the phrase
        return node.docIds;
    }


    public static void main(String[] args){
        List<Pair<Integer, String>> documents = new ArrayList<>();
//        documents.add()
        List<Integer> matches = new ArrayList<>();

//        matches = searchPhrase(documents, phrase);

    }
}
