package trie;

class TrieNode{
    static final int ALPHABET_NUM = 27; //���� ����
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
            index = (data == 32) ? 26 : data - 'a'; //������ ���� ������ ���

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
            index = (data == 32) ? 26 : data - 'a'; //������ ���� ������ ���

            if(tri.children[index] == null){ //���ڰ� ���� ��
                System.out.println("No data");
                return;
            }else if(tri.children[index].data == data){ //���ڰ� ��ġ�� ��
                tri = tri.children[index]; //���� ���ڷ� �̵�
            }else { //���ڰ� �ٸ���
                System.out.println("No data");
                return;
            }
        }

        if(tri.isfinished) { //������ ���ڿ� �������� �� �������̸�
            System.out.println(key);
        }else { //���� �ܾ��� �� ex)apple, appl
            System.out.println("No data");
        }
    }
}

