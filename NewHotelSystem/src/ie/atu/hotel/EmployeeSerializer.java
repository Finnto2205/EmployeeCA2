package ie.atu.hotel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import ie.atu.hotel.Employee;

public class EmployeeSerializer {
	private ArrayList<Employee> employees;
	
	private final String FILENAME = "employees.bin";	
	private File employeesFile;	
	
	// Default Constructor
	public EmployeeSerializer(){
		// Construct EmployeeList ArrayList
		employees = new ArrayList<Employee>();
		
		
		employeesFile = new File(FILENAME);
		
		try {
			if(employeesFile.createNewFile()) {	
				JOptionPane.showMessageDialog(null,"File has been Created" + employeesFile.getName());
			}else {
				JOptionPane.showMessageDialog(null, "This file exists");
			}
			
		}catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Issue creating file");
		}
		}
		// TODO Construct employeesFile and physically create the File
	

	/////////////////////////////////////////////////////////////
	// Method Name : add()								              //
	// Return Type : void								              //
	// Parameters : None								                 //
	// Purpose : Reads one Employee record from the user       //
	//           and adds it to the ArrayList called employees //
	/////////////////////////////////////////////////////////////
	public void add(){ 
	    // Create an Employee object
		 Employee employee = new Employee();
 
       // TODO - Update add() so it checks if Cancel was clicked when reading Employee
       
		 // Read its details
		 employee.read();
		 // And add it to the employees ArrayList
		 employees.add(employee);	
	}

	///////////////////////////////////////////////////////
	// Method Name : list()						              //
	// Return Type : void					   	           //
	// Parameters : None						                 //
	// Purpose : Lists all Employee records in employees //
	///////////////////////////////////////////////////////		
	public void list() {
	    String employeesToList = "";

	    if (!employees.isEmpty()) {
	        // for every Employee object in employees
	        for (Employee tmpEmployee : employees) {
	            // add it to employeesToList
	            employeesToList += tmpEmployee.toString(); // Call the updated toString() method
	            // add a newline
	            employeesToList += "\n\n"; // Add a space between employee entries
	        }

	        // Display employeesToList in a messageDialog
	        JOptionPane.showMessageDialog(null, employeesToList, "EMPLOYEE LIST", JOptionPane.INFORMATION_MESSAGE);    
	    } else {
	        // Display "No Employees stored..." in messageDialog
	        JOptionPane.showMessageDialog(null, "No Employees to list.", "EMPLOYEE LIST", JOptionPane.INFORMATION_MESSAGE);    
	    }
	}

	////////////////////////////////////////////////////////////////
	// Method Name : view()									              //
	// Return Type : Employee								              //
	// Parameters : None								                    //
	// Purpose : Displays the required Employee record on screen  //
	//         : And returns it, or null if not found             //   
	////////////////////////////////////////////////////////////////	
	public Employee view() {
	    String in = JOptionPane.showInputDialog(null, "Enter employee number to find employee: ");
	    
	    if (in == null || in.trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "There is no employee with that number", "Error", JOptionPane.ERROR_MESSAGE);
	        return null;
	    }
	    try {
	        int empNum = Integer.parseInt(in.trim());
	        
	        for (Employee employee : employees) {
	            if (employee.getNumber() == empNum) {
	                JOptionPane.showMessageDialog(null, "Employee Details: \n" + employee.toString(), "Employee has been found", JOptionPane.INFORMATION_MESSAGE);
	                return employee;
	            }
	        }
	        JOptionPane.showMessageDialog(null, "Employee can't be found", "Error", JOptionPane.ERROR_MESSAGE);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Invalid Employee Number, Please re-enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
	    }

	    return null;        
	}
	
	///////////////////////////////////////////////////////////////////
	// Method Name : delete()							        	           //
	// Return Type : void								        	           //
	// Parameters : None									                    //
	// Purpose : Deletes the required Employee record from employees //
	///////////////////////////////////////////////////////////////////	
	public void delete(){
	    String in = JOptionPane.showInputDialog(null, "Enter Employee number to find employee");
	    
	    if(in == null || in.trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null,"There is no employee with that number", "Error",JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    try {
	        int empNum = Integer.parseInt(in.trim());
	        
	        Employee foundEmployee = null;
	        for(Employee employee : employees) {
	            if(employee.getNumber() == empNum) {
	                foundEmployee = employee;
	                break;
	            }
	        }
	        
	        if(foundEmployee != null) {
	            JOptionPane.showMessageDialog(null, "Employee Details: \n"+ foundEmployee, "Employee Found", JOptionPane.INFORMATION_MESSAGE);
	            
	            String[] DelOptions = {"Delete", "Cancel"};
	            int choice = JOptionPane.showOptionDialog(null,"Do you want to delete this employee?", "Delete Employee",
	                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, DelOptions, DelOptions[1]);
	            
	            if(choice == 0) {
	                deleteEmployeeFromArray(empNum);
	            }
	            else {
	                JOptionPane.showMessageDialog(null, "Employee deletion canceled", "Canceled", JOptionPane.INFORMATION_MESSAGE);
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Employee can't be found","Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } catch(NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Invalid Employee Number, Please re-enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	private void deleteEmployeeFromArray(int empNum) {
	    // Find the index of the employee to delete
	    int indexToDelete = -1;
	    
	    for (int i = 0; i < employees.size(); i++) {
	        if (employees.get(i).getNumber() == empNum) {
	            indexToDelete = i;
	            break;
	        }
	    }

	    if (indexToDelete == -1) {
	        JOptionPane.showMessageDialog(null, "Employee not found for deletion", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Remove the employee from the list
	    employees.remove(indexToDelete);
	    
	    // Show confirmation message
	    JOptionPane.showMessageDialog(null, "Employee has been deleted successfully.");
	}



		 // TODO - Write the code for delete()

	   // JOptionPane.showMessageDialog(null, "delete() method must be coded!", "NOT IMPLEMENTED", JOptionPane.INFORMATION_MESSAGE);
	///////////////////////////////////////////////////////////////
	// Method Name : edit()			  					                //
	// Return Type : void								    	          //
	// Parameters : None								     	             //
	// Purpose : Edits the required Employee record in employees //
	///////////////////////////////////////////////////////////////	
	public void edit(){
	    String in = JOptionPane.showInputDialog(null, "Enter employee number to find employee:");

	    if (in == null || in.trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "There is no employee with that number", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    try {
	        // Parse the employee number
	        int empNum = Integer.parseInt(in.trim());

	        // Search for the employee with the matching employee number
	        boolean employeeFound = false;
	        for (Employee employee : employees) {
	            if (employee.getNumber() == empNum) {
	                employeeFound = true;
	                
	                // Show the current details of the employee
	                JOptionPane.showMessageDialog(null, "Employee Details: \n" + employee, "Employee has been found", JOptionPane.INFORMATION_MESSAGE);

	                // Ask user for which detail to edit
	                String[] editOptions = {"Title", "First Name", "Second Name", "Phone Number", "Salary", "Cancel"};
	                int choice = JOptionPane.showOptionDialog(null, "Select what you want to edit", "Edit Employee",
	                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, editOptions, editOptions[0]);

	                if (choice == 0) {
	                    // Edit Title
	                    String newTitle = JOptionPane.showInputDialog(null, "Enter new title for the employee:");
	                    if (newTitle != null && !newTitle.trim().isEmpty()) {
	                        employee.name.setTitle(newTitle.trim());
	                        JOptionPane.showMessageDialog(null, "Employee title updated to: " + newTitle);
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Title cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
	                    }
	                } else if (choice == 1) {
	                    // Edit First Name
	                    String newFName = JOptionPane.showInputDialog(null, "Enter new first name for the employee:");
	                    if (newFName != null && !newFName.trim().isEmpty()) {
	                        employee.name.setFirstName(newFName.trim());
	                        JOptionPane.showMessageDialog(null, "Employee first name updated to: " + newFName);
	                    } else {
	                        JOptionPane.showMessageDialog(null, "First name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
	                    }
	                } else if (choice == 2) {
	                    // Edit Second Name (Surname)
	                    String newSName = JOptionPane.showInputDialog(null, "Enter new surname for the employee:");
	                    if (newSName != null && !newSName.trim().isEmpty()) {
	                        employee.name.setSurname(newSName.trim());
	                        JOptionPane.showMessageDialog(null, "Employee surname updated to: " + newSName);
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Surname cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
	                    }
	                } else if (choice == 3) {
	                    // Edit Phone Number
	                    String newPhone = JOptionPane.showInputDialog(null, "Enter new phone number for the employee:");
	                    if (newPhone != null && !newPhone.trim().isEmpty()) {
	                        // Validate phone number format (example: only digits and length check can be added)
	                        employee.setPhoneNumber(newPhone.trim());
	                        JOptionPane.showMessageDialog(null, "Employee phone number updated to: " + newPhone);
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Phone number cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
	                    }
	                } else if (choice == 4) {
	                    // Edit Salary
	                    String newSalaryStr = JOptionPane.showInputDialog(null, "Enter new salary for the employee:");
	                    if (newSalaryStr != null && !newSalaryStr.trim().isEmpty()) {
	                        try {
	                            double newSalary = Double.parseDouble(newSalaryStr.trim());
	                            employee.setSalary(newSalary);
	                            JOptionPane.showMessageDialog(null, "Employee salary updated to: " + newSalary);
	                        } catch (NumberFormatException e) {
	                            JOptionPane.showMessageDialog(null, "Invalid salary format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
	                        }
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Salary cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
	                    }
	                }

	                // Display updated employee details
	                JOptionPane.showMessageDialog(null, "Updated Employee Details: \n" + employee, "Updated Employee", JOptionPane.INFORMATION_MESSAGE);
	                break;
	            }
	        }

	        // If the employee is not found, display an error message
	        if (!employeeFound) {
	            JOptionPane.showMessageDialog(null, "Employee can't be found", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (NumberFormatException e) {
	        // If the user inputs a non-numeric value for employee number, show an error message
	        JOptionPane.showMessageDialog(null, "Invalid Employee Number, Please re-enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

		
	    		
	
	    //JOptionPane.showMessageDialog(null, "edit() method must be coded!", "NOT IMPLEMENTED", JOptionPane.INFORMATION_MESSAGE);		
	
	// This method will serialize the employees ArrayList when called, 
	// i.e. it will write employees to a file called employees.bin
	public void serializeEmployees(){
		 // TODO - Write the code for serializeEmployees()
		try 
		{
			FileOutputStream file = new FileOutputStream(employeesFile);
			ObjectOutputStream out = new ObjectOutputStream(file);
			
			out.writeObject(employees);
			
			out.close();
			//file.close();
			
			JOptionPane.showMessageDialog(null, "Object has been serialized");
		}catch(IOException ex)
		{
			JOptionPane.showMessageDialog(null,"IOException is caught");
		}
		
		}

	// This method will deserialize the Employees ArrayList when called, 
	// i.e. it will restore the employees ArrayList from the file employees.bin
	public void deserializeEmployees(){
		 ObjectInputStream is=null;
		
		 try {
			 // Deserialize the ArrayList...
			 FileInputStream fileStream = new FileInputStream(employeesFile);
				
			 is = new ObjectInputStream(fileStream);
						
			 ArrayList<Employee> deserialized = (ArrayList<Employee>)is.readObject();
			employees = deserialized;
          
			 //Employee.setNextNumber(lastNumber+1);
			 
          is.close();
		}
		catch(FileNotFoundException fNFE){
			 System.out.println("Cannot open file " + employeesFile.getName() + ".");
		}
		catch(IOException ioE){
			 System.out.println("Cannot read from " + employeesFile.getName() + ".");
		}
		catch(Exception e){
			 System.out.println("Cannot read from " + employeesFile.getName() + ".");
		}
	}
}