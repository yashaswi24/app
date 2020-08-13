package pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class loco {

	public String add;
	public String lat;
	public String lon;
	public String mark;
	public loco(){}
	
	public loco (String add,String lat,String lon,String mark){
		this.add = add ;
		this.lat=lat;
		this.lon=lon;
		this.mark=mark;
	}

	public String getLoc() {
		return add;
	}
	public String getLat() {
		return lat;
	}
	public String getLon() {
		return lon;
	}
	public String getMark() {
		return mark;
	}


}
