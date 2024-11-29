package ie.atu.hotel;
import java.util.Scanner;
import ie.atu.date.*;

public class CreditCard 
{
	private int securityCode;
	private long number;
	private Date expiryDate;
	
	public CreditCard(long num, int secCode, Date expDate)
	{
		securityCode=secCode;
		number=num;
		expiryDate = expDate;
		
	}
	
	@Override
	public String toString() {
		return "Credit Card number: " +number;
	}
	
	@Override
	public boolean equals(Object obj) {
		CreditCard ccObject;
		if(obj instanceof CreditCard)
			ccObject = (CreditCard)obj;
		else
			return false;
		
		return this.securityCode==(ccObject.securityCode)
				&& this.number==(ccObject.number)
				&& this.expiryDate.equals(ccObject.expiryDate);
	}

	public int getSecurityCode() {
		return securityCode;
	}

	public long getNumber() {
		return number;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}
	//read() method
	//==> Called when you want to 'read' details from the user 
	public void read() {
		Scanner kb =new Scanner(System.in);
		
		System.out.println("Enter your card number");
		System.out.println("Number:");
		number = kb.nextLong();
		System.out.println("Expiry Date:");
		expiryDate.setD(kb.nextInt());
		System.out.println("Month:");
		expiryDate.setM(kb.nextInt());
		System.out.println("Year:");
		expiryDate.setY(kb.nextInt());
		System.out.println("Security Code:");
		securityCode=kb.nextInt();
		
		
	}
	
	}


