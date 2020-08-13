package fin.fin.restcalls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pojo.axes;

@Path("/Visualize")
public class Visualize {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public axes getVis()
	{
		System.out.print("arrived");
		ArrayList<String> ali=new ArrayList<String>();
		ArrayList<String> ali2=new ArrayList<String>();
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");  
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl1","INSIGHTTEST","INSIGHTTEST");    
			java.sql.Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select createdat,lastreading2 from eischannel");  

			
			while(rs.next())  
			{
				
				ali.add(dateFormat.format(rs.getDate(1)).substring(0, 2));
				ali2.add(rs.getString(2));
			}		
			System.out.print(ali.get(0)+" " +ali2.get(0)+" "+ali.get(1)+" "+ali2.get(1));
			return new axes(ali.get(0),ali2.get(0),ali.get(1),ali2.get(1));
			
			}
			
			catch(Exception e){ System.out.println(e);}  
		return null;
	}
}
