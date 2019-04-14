package trie;

public class Main {
    public static void main(String[] args) {
        Trie words = new Trie();
        words.insert("hello apple");

        words.search("hello apple");
        words.search("hello apples");
        words.search("hello appl");
    }
}
