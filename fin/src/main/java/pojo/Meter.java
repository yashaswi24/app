package pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Meter {
	
	
	public String loadprofileid;
	public String channelid;
	public String datetime;
	public String consumptionreading;
	
	 
	public Meter(){}
	
	public Meter (String loadprofileid, String channelid,String datetime, String consumptionreading){
		this.loadprofileid = loadprofileid ;
		this.channelid = channelid;
		this.datetime=datetime;
		this.consumptionreading=consumptionreading;
	}

	public String getLoadprofileid() {
		return loadprofileid;
	}

	
	public String getChannelid() {
		return channelid;
	}
	public String getDatetime() {
		return datetime;
	}

	
	public String getConsumptionreading() {
		return consumptionreading;
	}
	
}



