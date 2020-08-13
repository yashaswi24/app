package fin.fin.restcalls;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.persistence.queries.Call;

import com.sun.research.ws.wadl.Response;

import pojo.User;

@Path("/Login")
public class Login{
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public User getUser(User json) { 
		
		User u1=null,u2=null;
		int f=0;
		try{  

		Class.forName("oracle.jdbc.driver.OracleDriver");  
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl1","INSIGHTTEST","INSIGHTTEST");    
		java.sql.Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from eisusers");  

		
		while(rs.next())  
		{
			System.out.println("mo"+rs.getString("name")+"  "+rs.getString("password")+"  "); 
			System.out.println("jon-"+json.getUsername()+"  "+json.getPassword()+"  ");
			if(json.username.equalsIgnoreCase(rs.getString("Name")) && json.password.equalsIgnoreCase(rs.getString("Password")))
				{
				      f=1;    
				}
		}		  
		con.close();   
		}
		catch(Exception e){ System.out.println(e);}  
		if(f==1)
			return json;
		else 
		return new User("fail","fail");
		} 
    }
