package main;

import java.math.BigDecimal;

/**
 * @author Marius
 *
 */
public class Employee {
	/**
	 * Instance Variables
	 */
	private int employeeNo;
	private static int nextEmployeeNo = 1;
	private String firstName;
	private String lastName;
	private BigDecimal salary;
	
	/**
	 * Default Constructor
	 */
	public Employee(){
		employeeNo = nextEmployeeNo++;
		firstName = "";
		lastName = "";
		salary = new BigDecimal("0.0");
	}
	/**
	 * Overloaded Constructor
	 * @param firstNameIn
	 * @param lastNameIn
	 * @param salaryIn
	 */
	public Employee(String firstNameIn, String lastNameIn, BigDecimal salaryIn){
		employeeNo = nextEmployeeNo++;
		firstName = firstNameIn;
		lastName = lastNameIn;
		salary = salaryIn;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Set firstName
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		if(firstName != null){
			if(firstName.length() > 20)
				throw new IllegalArgumentException("Name too long (max 20 characters)");
			if(!firstName.matches("[a-zA-Z]+"))
				throw new IllegalArgumentException("Name can only contain letters.");	
			else
				this.firstName = firstName;
		}	
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Set lastName
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		if(lastName != null){
			if(lastName.length() > 50)
				throw new IllegalArgumentException("Name too long (max 50 characters)");
			if(!lastName.matches("[a-zA-Z]+"))
				throw new IllegalArgumentException("Name can only contain letters.");	
			else
				this.lastName = lastName;
		}
	}
	/**
	 * @return the salary
	 */
	public BigDecimal getSalary() {
		return salary;
	}
	/**
	 * Set salary
	 * @param salary
	 */
	public void setSalary(BigDecimal salary) {
		if(salary != null){
			if(salary.doubleValue() > 500000)
				throw new NumberFormatException("Salary cannot exceed 500'000");
			if(salary.doubleValue() > 0)
				this.salary = salary;
		}
	}
	/**
	 * @return the employeeNo
	 */
	public int getEmployeeNo() {
		return employeeNo;
	}
	/**
	 * Set employeeNo
	 * @param employeeNo
	 */
	public void setEmployeeNo(int employeeNo){
		this.employeeNo = employeeNo;
	}
	/**
	 * @return the Employee details
	 */
	@Override
	public String toString() {
		return "Employee [employeeNo=" + employeeNo + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", salary=" + salary + "]";
	}
}
