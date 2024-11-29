package ie.atu.date;

import java.util.Scanner;

public class Date {
	private int d;
	private int m;
	private int y;
	
	public Date()
	{
		d=m=y;
	}
	
	public Date(int d,int m ,int y)
	{
		if(d<1 || d > 31 || m< 1 || m > 12 || y < 1900 || y > 2024)
			throw new IllegalArgumentException("Invalid D/M/Y");
		
		this.d=d;
		this.m=m;
		this.y=y;
		
	}
	@Override
	public String toString() {
		return d + "/" + m + "/" + y+ " "; 
		
	}
	public boolean equals(Object obj) {
		Date nObject;
		if(obj instanceof Date)
			nObject = (Date)obj;
		else
			return false;
		
		return this.d == (nObject.d)
				&& this.m == (nObject.m)
				&& this.y == (nObject.y);
	}

	public int getD() {
		return d;
	}

	public void setD(int d)throws IllegalArgumentException {
		if(d < 1 || d > 31)
			throw new IllegalArgumentException("Invalid Day");
		this.d = d;
	}

	public int getM() {
		
		return m;
	}

	public void setM(int m)throws IllegalArgumentException  {
		if(m < 1 || m > 12)
			throw new IllegalArgumentException("Invalid Month");
		this.m = m;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) throws IllegalArgumentException {
		if(y < 1900 || y > 2024)
			throw new IllegalArgumentException("Invalid Year");
		this.y = y;
	}
	
	public void read() {
		Scanner kb= new Scanner(System.in);
		
		System.out.println("Day: ");
		d=kb.nextInt();
		System.out.print("Month: ");
		m=kb.nextInt();
		System.out.println("Year:");
		y=kb.nextInt();
	}

}
