package ie.atu.hotel;


//An interface can contain ONLY abstract methods( & constants)
//It cannot contain instance variables, constructors, or 
//concrete methods

public interface Payable {
	double calculatePay(double taxPercentage);
	public abstract double incrementSalary(double incrementAmount);
	
}
//public abstract isn't necessary it'll default itself