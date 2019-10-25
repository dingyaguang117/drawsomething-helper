package org.huohua.drawsomething;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("words.txt"));
        String word =null;
        ArrayList<String> words = new ArrayList<String>();
        while((word = br.readLine())!=null)
        {
            boolean valid = true;
            word = word.toLowerCase();
            for(int i=0; i< word.length(); ++i) {
                int ord = word.charAt(i) - 'a';
                if(ord < 0 || ord > 25) {
                    valid = false;
                    break;
                }
            }
            if(valid) {
                words.add(word);
            }

        }
        System.out.printf("word count: %d\n", words.size());
        TrieTree tree = new TrieTree(words);
        System.out.printf("max deep: %d\n", tree.root.maxdeep);


        Scanner sc =new Scanner(System.in);
        while(true)
        {
            System.out.print("input chars: ");
            String s = sc.next();
            System.out.print("input length: ");
            int num = sc.nextInt();
            tree.FindDrawSomething(s,num);
            for(Iterator<String> it = TrieNode.Result.iterator(); it.hasNext(); )
            {
                System.out.print(it.next() + " ");
            }
            System.out.println("\n");
        }
    }
}