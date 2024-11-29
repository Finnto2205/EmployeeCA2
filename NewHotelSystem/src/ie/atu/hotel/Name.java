package ie.atu.hotel;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Name {
	private String title;
	private String firstName;
	private String surname;
	
	//Default Constructor
	//Called when we create Objects with no peramiters
	//Name n1 = new Name()
	
	public Name()
	{
		title=firstName=surname;
		
	}
	public Name(JComboBox<String> txtTitle, JTextField txtFirstName, JTextField txtSurName)
	{
		title=firstName=surname;
		
	}
	
	
	//initialization constructor
	//Called when we create objects passing parameters
	//Name n2 = new Name("Mr", "Joe", "Bloggs");
	public Name(String t,String fN,String sN) {
		title=t;
		firstName=fN;
		surname=sN;
	}
	
	@Override
	public String toString() {
		return title + " " + firstName + " " + surname+ " "; 
		
	}
	//An equals() method
	//Called when comparing two objects
	//if(n1.equals(n2))..
	@Override
	public boolean equals(Object obj) {
		Name nObject;
		if(obj instanceof Name)
			nObject = (Name)obj;
		else
			return false;
		
		return this.title.equals(nObject.title)
				&& this.firstName.equals(nObject.firstName)
				&& this.surname.equals(nObject.surname);
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public boolean isFemale() 
	{
		if(title.equalsIgnoreCase("Ms")
			|| title.equalsIgnoreCase("Miss")
			|| title.equalsIgnoreCase("Mrs"))
			return true;
		else
			return false;
			
	}
	
}

