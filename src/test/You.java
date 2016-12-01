package test;

import java.util.Observable;

public class You implements Runnable{
	private String who;
	private ContactAbstractClass callBack;
	public You(){
		
	}
	public void setCallBack(String who,String question,ContactAbstractClass callback){
		this.who=who;
		System.out.println("You说,当前联系到我的人是"+who+"问题是"+question);
		this.callBack=callback;
		
		
	}
	public void hadleThings(){
		for(int i=0;i<100000;i++){
			if(true){
				System.out.println("You正在思考问题。。。"+i);
			}
		}
		
		System.out.println("You说，想到答案了，准备打回去，告诉"+who+"答案");
		callBack.setAnswer("C");
		callBack.callBackByTel(who,callBack.answer);
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
			hadleThings();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
