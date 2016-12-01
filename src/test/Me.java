package test;


public class Me extends ContactAbstractClass{
	static Me me=new Me();
	
	public static void main(String[] args) {
		
		me.hasQuestion();
		
	}
	private void hasQuestion() {
		You you=new You();
		you.setCallBack("小明", "第十五道选择题选什么",new ContactAbstractClass() {
			
			@Override
			public void setAnswer(String answer) {
				// TODO Auto-generated method stub
				this.answer=answer;
				
				
			}
			
			@Override
			public void callBackByTel(String who,String answer) {
				// TODO Auto-generated method stub
				System.out.println("小明请求了你，你又回调了"+who+"居然回调我"+answer);
			}
		});
		new Thread(you).start();
		
		
	}
	@Override
	public void callBackByTel(String who,String answer) {
		// TODO Auto-generated method stub
		System.out.println("Ok,我知道答案了选"+answer);
	}
	@Override
	public void setAnswer(String answer) {
		// TODO Auto-generated method stub
		me.answer=answer;
		
	}
	

}
