package ie.atu.hotel;
import java.util.Scanner;


//Guest inherits from person
public class Guest extends Person {

	private String emailAddress;
	private CreditCard creditCard;
	
	public Guest() {
		super();
		emailAddress="";
	}
	
	public Guest(Name n,String pNo,String email,CreditCard cc){
		super(n, pNo);
		creditCard=cc;
		emailAddress = email;
		
	}
	@Override
	public String toString(){
		return super.toString()+ "\t"+ emailAddress;
		
		
	}
	public boolean equals(Object obj) {
		Guest gObject;
		if(obj instanceof Guest)
			gObject = (Guest)obj;
		else
			return false;
		
		return super.equals(gObject)
				&& emailAddress.equals(gObject.emailAddress);
		
	}
	

	public String getEmailAddress() {
		return emailAddress;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	public boolean read() {
		Scanner kb = new Scanner(System.in);
		
		super.read();
		
		System.out.println("Email Address");
		this.emailAddress=kb.nextLine();
		creditCard.read();
		return true;
	}
}
