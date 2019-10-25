package org.huohua.drawsomething;


import java.util.ArrayList;

public class TrieTree {
    TrieNode root;

    public TrieTree() {
        root = new TrieNode();
    }


    public TrieTree(ArrayList<String> words) {
        root = new TrieNode();

        for (String word : words) {
            Insert(word.toLowerCase());
        }

    }


    void Insert(String word) {
        root.Insert(word, 0);
    }


    boolean Find(String word) {
        return root.Find(word.toLowerCase(), 0);
    }

    boolean FindDrawSomething(String chars, int num) {
        if (chars.length() < num)
            return false;
        TrieNode.Result.clear();
        return root.FindDrawSomething(chars.toLowerCase(), num, "");
    }

}