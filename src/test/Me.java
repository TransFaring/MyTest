package test;


public class Me extends ContactAbstractClass{
	static Me me=new Me();
	
	public static void main(String[] args) {
		
		me.hasQuestion();
		
	}
	private void hasQuestion() {
		You you=new You();
		you.setCallBack("С��", "��ʮ���ѡ����ѡʲô",new ContactAbstractClass() {
			
			@Override
			public void setAnswer(String answer) {
				// TODO Auto-generated method stub
				this.answer=answer;
				
				
			}
			
			@Override
			public void callBackByTel(String who,String answer) {
				// TODO Auto-generated method stub
				System.out.println("С���������㣬���ֻص���"+who+"��Ȼ�ص���"+answer);
			}
		});
		new Thread(you).start();
		
		
	}
	@Override
	public void callBackByTel(String who,String answer) {
		// TODO Auto-generated method stub
		System.out.println("Ok,��֪������ѡ"+answer);
	}
	@Override
	public void setAnswer(String answer) {
		// TODO Auto-generated method stub
		me.answer=answer;
		
	}
	

}
