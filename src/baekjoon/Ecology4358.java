package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

class Trie{
    TrieNode root;

    Trie(){
        root = new TrieNode(' ');
    } //루트 노드 data는 공백

    void insert(String str){
        int level;
        char data;
        int index;
        TrieNode current = root;

        for(level = 0; level<str.length(); level++){
            data = str.charAt(level);
            index = data;
            if(current.children[index]==null) current.children[index] = new TrieNode(data);

            current = current.children[index];
        }
        if(!current.isFinished){ //단어가 저장이 안되어있을 떄
            current.word = str; //마지막 노드에 단어 전체 저장
            current.isFinished = true; //저장 체크
        }
        current.speciesNum++;
    }

    void searchAll(TrieNode tempNode, int totalTree){
        if(tempNode.isFinished){
            System.out.print(tempNode.word+" ");
            System.out.printf("%.4f\n", ((double)tempNode.speciesNum/totalTree)*100);
        }else{
            for(TrieNode temp : tempNode.children){
                if(temp != null){
                    searchAll(temp, totalTree);
                }
            }
        }
    }
}
class TrieNode{
    TrieNode[] children;
    char data;
    boolean isFinished;
    int speciesNum;
    String word;

    TrieNode(char data){
        this.data = data;
        children = new TrieNode[128];
        isFinished = false;
        speciesNum = 0;
    }
}

public class Ecology4358{
    static int totalTree = 0;

    public static void main(String[] args) throws IOException {
        Trie tr = new Trie();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while((input = br.readLine())!=null&& input.length() != 0){
            tr.insert(input);
            totalTree++;
        }

        tr.searchAll(tr.root, totalTree);
    }
}