package practice_java;


import java.io.*;
import java.util.*;


public class p0308 {
	int a;
	p0308(int a){
		System.out.println("hlel");
		this.a = a;
	}
	static void test(){
		System.out.println("hlel");
	}
	public static void main(String[] args) throws IOException {
		ArrayList alist = new ArrayList();
		 alist.add(1);
		alist.add(2);
		alist.add(3);
		alist.add(4);
		alist.add("hello");
		String hello = (String) alist.get(4);
		
		//System.out.println(alist.get(1));
		
		Queue<Integer> queue= new LinkedList<>();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue);
		
		Stack<Integer> stack= new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack);
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String t = bf.readLine();
		
		Scanner scanner = new Scanner(System.in);
		String t_s = scanner.next();
		System.out.println(t_s);
		
		StringTokenizer st = new StringTokenizer(t, " ");
		System.out.println(st.nextToken()+st.nextToken());
		
		p0308 w = new p0308(10);
		System.out.println(w.a);
		
	}

}
