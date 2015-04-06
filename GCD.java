//coderadi
import java.util.*;
import java.io.*;

public class GCD {
	public static int gcd(int a, int b){
		if(a == 0)
			return b;
		else
			return gcd(b%a, a);
	}

	public static void main(String[] args)throws IOException {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter two num");
		int a = s.nextInt();
		int b = s.nextInt();
		System.out.println(gcd(a, b));
	}
}