package repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.essential.dto.BdoUpdatedDto;
import com.essential.entites.Bdo;
import com.essential.exceptions.BdoNotFoundException;
import com.essential.exceptions.OperationFaliureException;
import com.essential.repository.BdoRepository;
import com.essential.repositoryImpl.BdoRepositoryImpl;
import com.essential.utility.MyServerStarter;

public class BdoRepositoryTest {
	
	
	public static BdoRepository brepo;
	public static Connection conn;
	
	@BeforeEach
	public  void setUp() throws SQLException {
		  conn = MyServerStarter.connectToDatabase();
//		 conn.setAutoCommit(false);
		 brepo= new BdoRepositoryImpl(conn);
		
		
		
	}
	

	@Test
	@Order(1)
	public void  addBdo_WhenBdoProvied_ShouldReturnExpected() throws SQLException, OperationFaliureException {
		
		
		//Arrange
		 
		 Bdo ob = new Bdo();
		 ob.setEmail("xyz001@gmail.com");
		 ob.setFirstName("xyz");
		 ob.setMobno("1234567892");
		 ob.setPassword("xyz12345");
		 ob.setDob(LocalDate.parse("1997-01-01") );
		 
		 
		 //Act
		 Optional<Bdo> result = brepo.addBdo(ob);
		 
		 
		 //Assert
		 Assertions.assertEquals(ob.getEmail(),result.get().getEmail());
	}
	
	
	@Test
	@Order(2)
	public void getAllBdo_WhenAskedForAllBdo_ShouldReturnListOfBdos() throws SQLException, OperationFaliureException {
		
		//Arrange
		  int result=0;
	    //Act
		 Optional<List<Bdo>> res= brepo.getAllBdo();
		 result=res.get().size();
		 
		 
		//Assert
		 Assertions.assertNotEquals(0,result );
		 
	}
	
	@Test
	@Order(3)
	public void getBdo_WhenAskedForBdoDetails_shouldReturnBdoObject () throws SQLException, OperationFaliureException
	{
		
		
		Bdo result=null;
		
		Optional<Bdo> res= brepo.getBdo(25);
		result= res.get();
		
		Assertions.assertEquals(25,result.getBdoId(),"Invalid Bdo Id passed");
	}
	
	
	
	@Test
	public void updateBdoDetails_WhenNewEmailPassed_shouldReturnUpdatedBdoObject() throws SQLException, BdoNotFoundException, OperationFaliureException {
		
		Bdo result=null;
		BdoUpdatedDto ob= new BdoUpdatedDto();
	    ob.setEmail("abcnew@gmail.com");
		Optional<Bdo> res= brepo.updateBdoDetails(25, ob);
		result= res.get();
		
		Assertions.assertEquals("abcnew@gmail.com",result.getEmail() );
	}
	
	
	@Test
	public void updateBdoDetails_WhenNewFirstNamePassed_ShoudReturnUpdatedBdoObject() throws SQLException, BdoNotFoundException, OperationFaliureException {
		
		Bdo result=null;
		BdoUpdatedDto ob= new BdoUpdatedDto();
	    ob.setFirstName("NewFirstName");
		Optional<Bdo> res= brepo.updateBdoDetails(25, ob);
		result= res.get();
		
		Assertions.assertEquals("NewFirstName",result.getFirstName() );
	}
	
	

	@Test
	public void updateBdoDetails_WhenNewLastNamePassed_ShoudReturnUpdatedBdoObject() throws SQLException, BdoNotFoundException, OperationFaliureException {
		
		Bdo result=null;
		BdoUpdatedDto ob= new BdoUpdatedDto();
	    ob.setLastName("NewLastName");
		Optional<Bdo> res= brepo.updateBdoDetails(25, ob);
		result= res.get();
		
		Assertions.assertEquals("NewLastName",result.getLastName() );
	}
	
	@AfterEach
	public    void cleanUp() throws SQLException {
		
		
		if(!conn.isClosed())
		{
//			conn.rollback();
			 MyServerStarter.closeConnection(conn);
			 
		}
	}
	
	
	
	@AfterAll
	public static   void finalCleanUp() throws SQLException {
		
		
		if(!conn.isClosed())
		{
//			conn.rollback();
			 MyServerStarter.closeConnection(conn);
			 
		}
	}
	
	

}
