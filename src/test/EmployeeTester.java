/**
 * 
 */
package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.DatabaseHandler;
import main.Employee;

/**
 * @author Marius
 *
 */
public class EmployeeTester {
	static DatabaseHandler db1;
	static Employee e1, e2, e3;
	private Employee testEmp;

	/**
	 * Set up some objects before execution of tests
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		e1 = new Employee();
		e2 = new Employee();
		e3 = new Employee();
		
		db1 = mock(DatabaseHandler.class);
		when(db1.getEmployee(1)).thenReturn(e1);
		when(db1.getEmployee(2)).thenReturn(e2);
		when(db1.getEmployee(3)).thenReturn(e3);
		
	}
	/**
	 * Set up some values before execution of each test
	 * @throws Exception
	 */
	@Before
	public void setUpBeforeTests() throws Exception {
		e2.setFirstName("Joe");
		e2.setLastName("Bloggs");
		e2.setSalary(new BigDecimal(25000));
		
		e3.setFirstName("Joe");
		e3.setLastName("Bloggs");
		e3.setSalary(new BigDecimal(25000));
	}
	/**
	 * Check if employees are different due to employeeNo
	 */
	@Test
	public void testSameDetailEmployees() {
		assertNotEquals(db1.getEmployee(2), db1.getEmployee(3));
	}
	/**
	 * Check if employee id's are automatically set
	 */
	@Test
	public void testEmployeeNo(){
		assertEquals(1, db1.getEmployee(1).getEmployeeNo());
		assertEquals(2, db1.getEmployee(2).getEmployeeNo());
		assertEquals(3, db1.getEmployee(3).getEmployeeNo());
	}
	/**
	 * Try to set a negative salary
	 */
	@Test
	public void testNegativeSalary(){
		testEmp = db1.getEmployee(2);
		testEmp.setSalary(new BigDecimal(-2000));
		assertTrue(testEmp.getSalary().doubleValue() > 0);
	}
	/**
	 * Try to set null salary
	 */
	@Test
	public void testNullSalary(){
		testEmp = db1.getEmployee(2);
		BigDecimal salaryBefore = testEmp.getSalary();
		testEmp.setSalary(null);
		assertEquals(salaryBefore, testEmp.getSalary());
	}
	/**
	 * Try to set passable salary
	 */
	@Test
	public void testPassableSalary(){
		testEmp = db1.getEmployee(2);
		testEmp.setSalary(new BigDecimal(30000));
		assertEquals(new BigDecimal(30000), testEmp.getSalary());
	}
	/**
	 * Try to set fName with numbers
	 * @throws java.lang.IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testFnameWithNumbers(){
		testEmp = db1.getEmployee(3);
		testEmp.setFirstName("Bob123");
	}
	/**
	 * Try to set fName with characters
	 * @throws java.lang.IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testFnameWithCharacters(){
		testEmp = db1.getEmployee(2);
		testEmp.setFirstName("Ga%r&y");
	}
	/**
	 * Set fName longer than allowed
	 * @throws java.lang.IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testFnameTooLong(){
		testEmp = db1.getEmployee(1);
		testEmp.setFirstName("abcdefghijklmnopqrstuvwxyz");
	}
	/**
	 * Check if you can set null fName
	 */
	@Test
	public void testNullFirstName(){
		testEmp = db1.getEmployee(3);
		testEmp.setFirstName(null);
		assertEquals("Joe", testEmp.getFirstName());
	}
	/**
	 * Try to set passable first name
	 */
	@Test
	public void testPassableFirstName(){
		testEmp = db1.getEmployee(3);
		testEmp.setFirstName("John");
		assertEquals("John", testEmp.getFirstName());
	}
	/**
	 * Try to set lName with numbers
	 * @throws java.lang.IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testLnameWithNumbers(){
		testEmp = db1.getEmployee(2);
		testEmp.setLastName("3Blo45ggs");
	}
	/**
	 * Try to set lName with characters
	 * @throws java.lang.IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testLnameWithCharacters(){
		testEmp = db1.getEmployee(2);
		testEmp.setFirstName("Blo&gg@s");
	}
	/**
	 * Set lName longer than allowed
	 * @throws java.lang.IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testLnameTooLong(){
		testEmp = db1.getEmployee(1);
		testEmp.setLastName("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz");
	}
	/**
	 * Check if you can set null first name
	 */
	@Test
	public void testNullLastName(){
		testEmp = db1.getEmployee(3);
		testEmp.setLastName(null);
		assertEquals("Bloggs", testEmp.getLastName());
	}
	/**
	 * Try to set passable last name
	 */
	@Test
	public void testPassableLastName(){
		testEmp = db1.getEmployee(3);
		testEmp.setLastName("McMahon");
		assertEquals("McMahon", testEmp.getLastName());
	}
}