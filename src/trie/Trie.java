package trie;

class TrieNode{
    static final int ALPHABET_NUM = 27; //공백 포함
    char data;
    boolean isfinished;
    TrieNode[] children;

    TrieNode(char data){
        this.data = data;
        isfinished = false;
        children  = new TrieNode[ALPHABET_NUM];
    }
}

public class Trie {
    TrieNode root;

    Trie(){
        root = new TrieNode(' ');
    }

    public void insert(String str){
        int index;
        int level;
        char data;
        TrieNode tri = root;

        for(level = 0; level<str.length(); level++){
            data = str.charAt(level);
            index = (data == 32) ? 26 : data - 'a'; //공백일 경우와 문자일 경우

            if(tri.children[index]==null) tri.children[index] = new TrieNode(data);

            tri = tri.children[index];
        }
        tri.isfinished = true;
    }

    public void search(String key){
        int level;
        char data;
        int index;
        TrieNode tri = root;

        for(level = 0; level<key.length(); level++){
            data = key.charAt(level);
            index = (data == 32) ? 26 : data - 'a'; //공백일 경우와 문자일 경우

            if(tri.children[index] == null){ //글자가 없을 때
                System.out.println("No data");
                return;
            }else if(tri.children[index].data == data){ //글자가 일치할 때
                tri = tri.children[index]; //다음 글자로 이동
            }else { //글자가 다를때
                System.out.println("No data");
                return;
            }
        }

        if(tri.isfinished) { //마지막 글자에 도달했을 때 마지막이면
            System.out.println(key);
        }else { //없는 단어일 때 ex)apple, appl
            System.out.println("No data");
        }
    }
}

