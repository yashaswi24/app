package fin.fin.restcalls;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pojo.Meter;
import pojo.User;

@Path("/data")
public class data {
	@POST
    @Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Meter getData(String m)
	{
		Meter met=null;
		User u1=null,u2=null;
		//int num=Integer.parseInt(m);
		System.out.println(m);
		
try{  
	System.out.println(m);
Class.forName("oracle.jdbc.driver.OracleDriver");  
System.out.println(m);
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl1","INSIGHTTEST","INSIGHTTEST");    
System.out.println(m);
PreparedStatement stmt=con.prepareStatement("select * from consumptioninfo where loadprofileid="
		+ "(select loadprofileid from meterinfo where meterid=(select meterid from customerinfoo where customername =?))");
System.out.println(4);
stmt.setString(1,m); 
System.out.println(5);
ResultSet rs=stmt.executeQuery(); 
while(rs.next())  
{
	met=new Meter(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
	System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+" "+rs.getString(4)); 
}

con.close();  
  
}catch(Exception e){ System.out.println(e);}  
  return met;
} 
}

