package test2;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr=new int[]{6087,5173,1358,3825,2531};
		test(arr);
	
	}
	
	private static void test(int[] testArray){
		
		for(int i=1000;i<10000;i++){
			int[] a=getArr(i);
			int count=0;
			for(int j=0;j<testArray.length;j++){
				int[] b=getArr(testArray[j]);
				if(isSuccess(a, b)){
					count++;
				}
				if(j==testArray.length-1&&count==5){
					System.out.println(i);
				}				
			}			
		}
		System.out.println("ÔËÐÐ½áÊø");
		
		
	}
	private static int[] getArr(int num) {
		int[] arr=new int[4];
		arr[0]=num/1000;
		arr[1]=num/100%10;
		arr[2]=num/10%10;
		arr[3]=num%10;
		return arr;
		
	}
	private static boolean isSuccess(int[] a,int[] b) {
		int count=0;
		boolean[][] bp=new boolean[4][4];
		
		for(int i=0;i<a.length;i++){
			
			for(int j=0;j<b.length;j++){
				if(a[i]==b[j]&i==j){
					return false;
				}
				if(a[i]==b[j]&i!=j){
					bp[i][j]=true;	
					count++;
				}			
			}
		}

		if(count==2){
			return true;
		}		
		return false;
	}

}
