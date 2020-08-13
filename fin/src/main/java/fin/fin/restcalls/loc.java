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
import pojo.loco;

@Path("/loc")
public class loc {
	@POST
    @Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	
	public loco getLoc(String m)
	{
		loco loc = null;
		try{  
			
			System.out.println("m" +m);
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl1","INSIGHTTEST","INSIGHTTEST");    
		
		PreparedStatement stmt=con.prepareStatement("(select address from meterinfo where meterid=(select meterid from customerinfoo where customername =?))");
		
		stmt.setString(1,m); 
		ResultSet rs=stmt.executeQuery(); 
		String city=null;
		while(rs.next())  
		{
			city=rs.getString(1);
		}
		
		
		PreparedStatement stmt3=con.prepareStatement("select active from meterinfo where address=?");
		stmt3.setString(1,city);
		ResultSet rs3=stmt3.executeQuery();
		String mark=null;
		while(rs3.next())
		{
			mark=(rs3.getString(1));
		}
		
		System.out.println("city"+city);
		PreparedStatement stmt2=con.prepareStatement("select * from latlong where city=?");
		System.out.println(4);
		stmt2.setString(1,city); 
		ResultSet rs2=stmt2.executeQuery();
		System.out.println(mark);
		while(rs2.next())
		{
			System.out.println(rs2.getString(1)+" "+rs2.getString(2)+ " " +rs2.getString(3)+" "+mark);
			loc=new loco(rs2.getString(1),rs2.getString(2),rs2.getString(3),mark);
		}
		con.close();  
		  
		}catch(Exception e){ System.out.println(e);}  
		   
		return loc;
	}

}
