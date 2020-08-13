package fin.fin.restcalls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pojo.Meter;
import pojo.allmet;

@Path("/allmeter")
public class allmeter {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public allmet getallmeterdata() 
	
	{
		allmet al=null;
		ArrayList<String> ali=new ArrayList<String>();
		ArrayList<String> ali2=new ArrayList<String>();
		ArrayList<String> l1=new ArrayList<String>();
		ArrayList<String> l2=new ArrayList<String>();
		ArrayList<String> l3=new ArrayList<String>();
		
		try
		{  
	Class.forName("oracle.jdbc.driver.OracleDriver");  
	System.out.println("connected");
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl1","INSIGHTTEST","INSIGHTTEST");    
	PreparedStatement stmt=con.prepareStatement("select metername,active from meterinfo");
	ResultSet rs=stmt.executeQuery(); 
	while(rs.next())  
	{
		ali.add(rs.getString(1));	
		ali2.add(rs.getString(2));
		
	}
	System.out.print(ali);
	PreparedStatement stmt4=con.prepareStatement("select * from latlong");
	ResultSet rs4=stmt4.executeQuery();
	while(rs4.next())
	{
		l1.add(rs4.getString(1));
		l2.add(rs4.getString(2));
		l3.add(rs4.getString(3));
	}
	System.out.println(l1);
	System.out.println(l2);
	System.out.println(l3);
	con.close();  
}
		
catch(Exception e){ System.out.println(e);}  
		
		al=new allmet(ali.get(0),ali.get(1),ali.get(2),ali.get(3),ali2.get(0),ali2.get(1),ali2.get(2),ali2.get(3),l1.get(0),l1.get(1),l1.get(2),l1.get(3),l2.get(0),l2.get(1),l2.get(2),l2.get(3),l3.get(0),l3.get(1),l3.get(2),l3.get(3));
  return al;
} 
	}
