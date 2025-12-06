public class Main
{
        public static void main(String[] args) {
                System.out.println("Hello World");
                pattern1(5);
                pattern2(5);
        }
  
        public static void pattern1(int n) {
            for(int i = 1 ; i <= 2 * n - 1; i++) {
                int Colcheck = ( i > n )? 2 * n - i : i;
                for(int j = 1 ; j <= Colcheck ; j++) {
                    System.out.print( j +" ");
                }
                System.out.println();
            }
        }

      public static void pattern2(int n) {
	    for(int i = 1 ; i < 2 * n ; i++ ) {
	        for (int j = 1 ; j < 2 * n ; j++) {
	            int checkinpostion = Math.min(Math.min(i - 1,2 * n - i - 1), Math.min(j - 1,2 * n-j - 1));
	            System.out.print((n - checkinpostion) + " ");
	            }  
	             System.out.println();
	        }
	    }
}
