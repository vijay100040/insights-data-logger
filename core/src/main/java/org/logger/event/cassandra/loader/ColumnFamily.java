package org.logger.event.cassandra.loader;

public enum ColumnFamily {

	APIKEY("app_api_key"),
	
	EVENTDETAIL("event_detail"),
	
	EVENTTIMELINE("event_timeline"),
	
	ACTIVITYSTREAM("activity_stream"),
	
	DIMEVENTS("dim_events"),
	
	DIMDATE("dim_date"),
	
	DIMTIME("dim_time"),
	
	DIMUSER("dim_user"),
	
	DIMRESOURCE("dim_resource"),
	
	STAGING("stging_event_resource_user"),
	
	CONFIGSETTINGS("job_config_settings"),
	
	REALTIMECONFIG("real_time_operation_config"),
	
	RECENTVIEWEDRESOURCES("recent_viewed_resources"),
	;
	
	String name;

	
	private ColumnFamily(String name) {
		this.name = name;
	}


	public String getColumnFamily(){
		return name;
	}

}