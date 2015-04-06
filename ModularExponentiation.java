//coderadi
//http://discuss.codechef.com/questions/20451/a-tutorial-on-fast-modulo-multiplication-exponential-squaring
//http://www.geeksforgeeks.org/fast-multiplication-method-without-using-multiplication-operator-russian-peasants-algorithm/

import java.io.*;
import java.util.*;
import java.math.*;

public class ModularExponentiation{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

//////////////////////////////////ModExpo/////////////////////////////////////////////////////	
	static long mulOver(long a, long b, long mod){		//multiplies two numbers without overflow
		long result = 0;
		while(b > 0){
			if((b&1)!= 0){				//if second number becomes odd add the first to the result
				result = (result + a)%mod;
			}

			//double the first number and half the second
			a = (a<<1)%mod;
			b = b>>1;
		}
		return result;
	}

	static long modExpo(long base, long expo, long mod){		//complexity is log(n), exponentiation by squaring
		if(expo == 1)
			return base;
		else{
			if((expo & 1) == 0){				//if even
				long baseRec = modExpo(base, expo/2, mod);
				return mulOver(baseRec, baseRec, mod);
			} 
			else{								//if odd multiply base into other two halfs
				long baseRec = modExpo(base, expo/2, mod);
				return mulOver(mulOver(baseRec, baseRec, mod), base, mod);
			}
		}
	}

	static long modExpo2(long base, long expo, long mod){			//complexity is log(n) but space complexity is much less, uses binay representation of expo
		long result = 1L;
		while(expo > 0){
			if((expo & 1) > 0)
				result = mulOver(result, base, mod);
			base = mulOver(base, base, mod);					//in case expo is 1 we do not need this as expo/2 = 0
			expo = expo>>1;
		}
		return result%mod;
	}

//////////////////////////////////ModExpo////////////////////////////////////////////////



	public static void main(String[] args)throws IOException {			//main method
		
		System.out.println("Enter Base, Expo, Mod");
		long base = readLong();
		long expo = readLong();
		long mod = readLong();

		long ans = modExpo(base, expo, mod);
		System.out.println(ans);
		System.out.println(modExpo2(base, expo, mod));
	}

 //////////////////////////////////////////////////////////////////////////////////////////////////////   
    private static int readInt()throws IOException{		//method for fast input in java
        int n=0;
        char ch = (char)br.read();
        int sign = 1;
	    while(ch < '0' || ch > '9'){		
		    if(ch == '-')
			    sign = -1;
		    ch = (char)br.read();
	    }
	    while(ch >= '0' && ch <= '9'){	
		    n = (n<<3) + (n<<1) + (int)ch - '0';
		    ch = (char)br.read();
	    }
	    n = n*sign;
	    return n;
    }

    private static long readLong()throws IOException{		//method for fast input in java
        long n=0;
        char ch = (char)br.read();
        int sign = 1;
	    while(ch < '0' || ch > '9'){		
		    if(ch == '-')
			    sign = -1;
		    ch = (char)br.read();
	    }
	    while(ch >= '0' && ch <= '9'){	
		    n = (n<<3) + (n<<1) + (int)ch - '0';
		    ch = (char)br.read();
	    }
	    n = n*sign;
	    return n;
    }
}