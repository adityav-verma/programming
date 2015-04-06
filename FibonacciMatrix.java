import java.io.*;
import java.util.*;
import java.math.*;

public class FibonacciMatrix {

	static int[][] matrixMul(int[][] a, int[][] b){
		int[][] res = new int[2][2];

		for (int i=0; i<2; i++) {
			for (int j = 0; j<2; j++) {
				for (int k = 0 ; k<2; k++) {
					res[i][j] += a[i][k]*b[k][j];
				}
			}
		}
		return res;
	}
	static int[][] getFibo(int[][] fibo, int n){

		if(n == 1)
			return fibo;
		if(n%2 == 0)
			fibo = matrixMul(getFibo(fibo, n/2), getFibo(fibo, n/2));
		else
			fibo = matrixMul(fibo, matrixMul(getFibo(fibo, n/2), getFibo(fibo, n/2)));

		return fibo;
	}

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the nth fibonacci number you want to calculate");
		int n = Integer.parseInt(br.readLine());

		int[][] fibo = {{1, 1}, {1, 0}};

		int[][] ans = getFibo(fibo, n);
		for (int i=1; i<11; i++) {
			System.out.println(getFibo(fibo, i)[0][1]);
		}
		
	}
}