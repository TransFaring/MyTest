package refactoringtest;

import java.util.Enumeration;
import java.util.Vector;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

public class Customer {
	private String mName;
	private Vector mRental=new Vector();
	public Customer(String mName) {
		super();
		this.mName = mName;
	}
	public String getmName() {
		return mName;
	}
	
	public void addRental(Rental arg){
		mRental.addElement(arg);
		
	}
	public String statement(){
		double totoalAmount=0;
		int frequentRenterPoints=0;
		Enumeration rentals=mRental.elements();
		String result="Rental Record for"+getmName()+"\n";
		while(rentals.hasMoreElements()){
			double thisAmount=0;
			Rental each=(Rental) rentals.nextElement();
			switch (each.getmMovie().getmPriceCode()) {
			case Movie.REGULAR:
				thisAmount+=2;
				
				
				break;

			default:
				break;
			}
		}
		return result;
	}

}
