package repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.essential.entites.Employee;
import com.essential.exceptions.EmployeeAlreadyExistExcepton;
import com.essential.repository.EmployeeRepository;
import com.essential.repositoryImpl.EmployeeRepositoryImpl;
import com.essential.utility.MyServerStarter;

public class EmployeeRepositoryTest {
	
	public static EmployeeRepository erepo;
	public static Connection conn;
	
	@BeforeEach
	public void setUp() throws SQLException {
		conn=MyServerStarter.connectToDatabase();
		erepo= new EmployeeRepositoryImpl(conn);
	}

	@Test
	public void addEmployee_WhenEmployeeProvided_ShouldReturnAddedEmployeewithId() throws SQLException, EmployeeAlreadyExistExcepton {
		
		
		Employee emp = new Employee();
		emp.setFname("Rahul");
		emp.setLname("singh");
		emp.setEmail("Rahul@gmail.com");
		emp.setGender("Male");
		emp.setMobno("1234567897");
		emp.setPasword("Rahul12345");
		
		
		Optional<Employee> result = erepo.addEmployee(emp);
		
		
		Assertions.assertEquals(emp.getEmail(),result.get().getEmail(),"Employee details need to check before processing");
		
	}
	
	@AfterEach
	public void cleanUp() throws SQLException {
		if(conn!=null)
			 MyServerStarter.closeConnection(conn);
	}

}
