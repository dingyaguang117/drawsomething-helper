package org.huohua.drawsomething;


import java.util.*;

class TrieNode {
    TrieNode[] children;
    boolean end;
    int maxdeep;

    static Set<String> Result = new HashSet<String>();

    TrieNode() {
        children = new TrieNode[26];
        end = false;
        maxdeep = 0;
    }

    private int ord(char c) {
        return c - 'a';
    }

    int Insert(String word, int index) {
        if (index == word.length()) {
            end = true;
            return 0;
        }
        int sub = ord(word.charAt(index));
        if (children[sub] == null) {
            children[sub] = new TrieNode();
        }
        int deep = children[sub].Insert(word, index + 1);
        if (deep + 1 > maxdeep) maxdeep = deep + 1;
        return deep + 1;
    }

    // 普通Trie树的查找，本例中用不到
    boolean Find(String word, int index) {
        if (index == word.length()) {
            return end;
        }
        int sub = ord(word.charAt(index));
        if (sub > 25 || sub < 0)
            return false;
        if (children[sub] == null)
            return false;
        return children[sub].Find(word, index + 1);
    }

    // Drawsomething查找， 从当前节点出发，只允许使用chars集合中的字母，找到剩余num长度的单词。 cur为前面的字符部分
    boolean FindDrawSomething(String chars, int num, String cur) {
        if (maxdeep < num)
            return false;

        // 已经到最大长度
        if (num == 0) {
            // 如果此节点是一个单词的结束， 则搜索成功
            if (end) {
                Result.add(cur);
                return true;
            }
            return false;
        }

        boolean found = false;
        // 对于每一个剩余的可选字符，尝试是否有有效的Trie路径
        for (int i = 0; i < chars.length(); ++i) {
            int sub = ord(chars.charAt(i));
            if (sub > 25 || sub < 0)
                continue;
            if (children[sub] == null)
                continue;
            // 将当前选中的字符从 chars 中剔除，进行下一层搜索
            String nextchars = chars.substring(0, i) + chars.substring(i + 1);
            boolean ret = children[sub].FindDrawSomething(nextchars, num - 1, cur + chars.charAt(i));
            if (ret)
                found = true;
        }
        return found;
    }
}
