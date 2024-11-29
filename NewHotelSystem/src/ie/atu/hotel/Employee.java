package ie.atu.hotel;

import java.text.DecimalFormat;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

// INHERITANCE - Employee IS-A Person, and CAN-DO Payable methods
public class Employee extends Person implements Payable {
   // private Date dob;		// Employee has name&phoneNumber from Person
   // private Date startDate;	// AND dob,startdate,salary, & number	
    private double salary;
    private int number;

	private static int nextNumber=10000;	// static for unique number - starts off at 1
	
	private final double MAX_SALARY = 150000;	
	
	String[] s1 = { "Mr", "Ms", "Mrs", "Miss"};
	String salaryText;

    // Default Constructor
	// Called when object is created like this ==> Employee eObj = new Employee();	
    public Employee(){
    	super();		// NOTE:Not needed
    //	dob=new Date();
    	//startDate=new Date();
 		salary=0.0;
		// Set number to static nextNumber before incrementing nextNumber
 		number=nextNumber++;
    }
	
    // Initialization Constructor
    // Called when object is created like this ==>
    //  Name name=new Name("Mr","Joe","Cole");
    //  Employee e2=new Employee(name,"087 1234567", new Date(25,12,1970),
    //                           new Date(10,11,2009),55000);
    public Employee(String[] s1, Name name, String phoneNo/* Date dob,Date startDate*/, double salary){
          // Call super class constructor - Passing parameters required by Person ONLY!
	   super(name, phoneNo);
	   // And then initialise Employees own instance variables
	 //  this.dob=dob;
	 //  this.startDate=startDate;	// Set instance variable to parameter
	   this.salary=salary;
	   this.s1=s1;
	   
		// Set number to static nextNumber before incrementing nextNumber
	   number = nextNumber++;						
	}
    
	// OVERRIDING the Person toString() method!
	// Calling Persons toString() method, and adding additional bits
	@Override
	public String toString(){
	    DecimalFormat df = new DecimalFormat("#.00");
	    return "Employee Number: " + number + " " +
	    		"Name: "+ s1[0] + " " 
	            + name.getFirstName() + " " + name.getSurname() + " " +
	           "Phone Number: " + phoneNumber + " " +
	           "Salary: €" + df.format(salary);
	}

	// equals() method
	// ==> Called when comparing an object with another object, 
	//     e.g. - if(e1.equals(e2))				
	// ==> Probably sufficient to compare customer numbers as they're unique
	@Override
	public boolean equals(Object obj){
		Employee eObject;
		if (obj instanceof Employee)
		   eObject = (Employee)obj;
		else
		   return false;
		   
	    return(this.number==eObject.number);
	}

	// set() and get() methods
	//public void setDOB(Date dob){
	//	this.dob=dob;
	//}
	//public Date getDOB(){
	//	return dob;
	//}
	//public void setStartDate(Date startDate){
	//	this.startDate=startDate;
	//}
	//public Date getStartDate(){
	//	return startDate;
	//}	
	
	public static void setNextNumber(int newNextNumber){
		nextNumber = newNextNumber;
	}
	public void setSalary(double salary){
		this.salary=salary;
	}
	public double getSalary(){
		return salary;
	}
	// You shouldn't be able to setNumber() as it is unique, 
	// so don't provide a setNumber() method
	public int getNumber(){
		return number;
	}	
	
	// read() method
	// ==> Called when you when you read() 
	//     an Employees details from the user... 
	//     e.g. e1.read();	
	public boolean read() {
		JTextField txtNumber = new JTextField();
		txtNumber.setEditable(false);
	    JTextField txtFirstName = new JTextField();
	    JTextField txtPhoneNumber = new JTextField();
	    JTextField txtSurName = new JTextField();
	    JComboBox<String> txtTitle = new JComboBox<>(s1);
	    JTextField txtSalary = new JTextField();

	    Object[] message = {
	    	"Number", txtNumber,
	        "Title", txtTitle,
	        "First Name:", txtFirstName,
	        "Surname:", txtSurName,
	        "Phone Number:", txtPhoneNumber,
	        "Salary", txtSalary,
	    };

	    JDialog dialog = new JDialog();
	    dialog.setAlwaysOnTop(true);

	    int option = JOptionPane.showConfirmDialog(dialog, message, "Enter Employees Details", JOptionPane.OK_CANCEL_OPTION);

	    if (option == JOptionPane.OK_OPTION) {
	        this.name.setFirstName(txtFirstName.getText());
	        this.name.setSurname(txtSurName.getText());
	        this.phoneNumber = txtPhoneNumber.getText();
	        this.s1 = new String[]{(String) txtTitle.getSelectedItem()};
	        this.salaryText = txtSalary.getText();

	        if (name.getFirstName().isEmpty() || name.getSurname().isEmpty() || phoneNumber.isEmpty() || salaryText.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Please fill in all boxes.", "Error", JOptionPane.ERROR_MESSAGE);
	        } else {
	            try {
	                salary = Double.parseDouble(salaryText);

	                // Create a full name combining the title, first name, and surname
	                String fullName = s1[0] + " " + name.getFirstName() + " " + name.getSurname();
	                System.out.println("Employee Full Name: " + fullName);  // For debugging, can be removed

	                // Create Employee object with the given details
	                Employee employee = new Employee();
	                employee.setName(name);
	                employee.setPhoneNumber(phoneNumber);
	                employee.setSalary(salary);

	                JOptionPane.showMessageDialog(null, "Employee added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
	            } catch (NumberFormatException e) {
	                JOptionPane.showMessageDialog(null, "Invalid Salary. Enter a suitable number.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }

	        return true;
	    } else {
	        JOptionPane.showMessageDialog(null, "Addition canceled", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
	        return false;
	    }
	}

	@Override
	public double calculatePay(double taxPercentage) {
		// return the monthly pay as salary/12 less taxPercentage
		double pay=salary/12;
		pay -= (pay * (taxPercentage/100));
		return pay;
	}

	@Override
	public double incrementSalary(double incrementAmount) {
		// add incrementAmount to, and return the new salary
		// salary should not go over a maximum salary of €150,000
		salary += incrementAmount;
		
		if(salary > MAX_SALARY)
			salary = MAX_SALARY;
		
		return salary;
	}
}
