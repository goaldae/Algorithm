package practice_java;

public class Outter {
	int a;
	int b;
	
	public Outter(int a, int b){
		this.a = a;
		this.b = b;
	}
	public void printO(){
		System.out.println("Outter");
	}
	public class Inner{
		int c;
		int d;
		
		public Inner(int c, int d){
			this.c = c;
			this.d = d;
		}
		public void printI(){
			System.out.println("Inner");
		}
	}
}
