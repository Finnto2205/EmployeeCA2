package ie.atu.hotel;

import java.util.Scanner;

public abstract class Person {
	protected Name name;
	protected String phoneNumber;
	
	public Person() {
		this.name=new Name();
		this.phoneNumber = "";
		
	}
	
	public Person(Name name, String phoneNumber) {
		this.name=name;
		this.phoneNumber=phoneNumber;
	}
	@Override
	public String toString(){
		return name+"'s phone number is: "+ phoneNumber;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		Person pObject;
		if(obj instanceof Person)
			pObject = (Person)obj;
		else
			return false;
		
		return name.equals(pObject.name)
				&& phoneNumber.equals(pObject.phoneNumber);
	}

	public Name getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public boolean read() {
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Title");
		name.setTitle(kb.nextLine());
		System.out.println("First Name");
		name.setFirstName(kb.nextLine());
		System.out.println("Surname");
		name.setSurname(kb.nextLine());
		
		System.out.println("Phone Number");
		this.phoneNumber = kb.nextLine();
		return true;
	}
	
		
	}
