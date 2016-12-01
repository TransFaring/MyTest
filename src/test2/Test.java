package test2;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] arr=new String[]{"6087","5173","1358","3825","2531"};
		test(arr);	
	}
	
	private static void test(String[] array){
		
		for(int i=0;i<10000;i++){
			int a[];
			int count=0;		
			String m=String.format("%04d", i);
			a=getArr(m);
			for(int j=0;j<array.length;j++){
				int[] b=getArr(array[j]);
				if(isSuccess(a, b)){
					count++;
				}			
			}
			if(count==5)
				System.out.println(i);
		}
		System.out.println("ÔËÐÐ½áÊø");		
		
	}
	private static int[] getArr(String s) {
		int[] arr=new int[s.length()];		
		for (int i = 0; i < s.length(); i++) {  
			 char c = s.charAt(i);  
			 arr[i] = Character.getNumericValue(c);  
		}  
		return arr;		
	}
	private static boolean isSuccess(int[] a,int[] b) {
		int count=0;
		for(int i=0;i<a.length;i++){
			
			for(int j=0;j<b.length;j++){
				if(a[i]==b[j]&i==j){
					return false;
				}
				if(a[i]==b[j]&i!=j){
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
