package com.zhlh.zeus.business.channel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ChannelRestrict {
	String insurerCode;
	HashMap<String, Boolean> rules = new HashMap<String, Boolean>( );
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append( insurerCode ).append( "=[" );
		Iterator iter = rules.entrySet().iterator(); 
		while (iter.hasNext()) { 
		    Map.Entry entry = (Map.Entry) iter.next(); 
		    buffer.append( " " ).append(  entry.getKey() ).append( "=" ).append( entry.getValue() ).append( " " );
		}
		buffer.append( "]" );
		return buffer.toString( );
	}
	
	ChannelRestrict( String insurer ) {
		this.insurerCode = insurer;
	}
	
	boolean isOpen( String licenseNo ) {
		
		if( rules.size( ) <= 0 ) {
			return true;
		}
		
		Boolean defaultRule = rules.get( "ALL" );
		if( defaultRule == null ) {
			defaultRule = false;
		}
		
		Boolean bigRule = rules.get( licenseNo.substring( 0, 1 ) );
		Boolean smallRule = rules.get( licenseNo.substring( 0, 2 ) );
		
		if( smallRule != null ) {
			return smallRule;
		}
		
		if( bigRule != null ) {
			return bigRule;
		}
		
		return defaultRule;
	}
	
	public ChannelRestrict addRule( String code, boolean isOpen ) {
		rules.put( code, isOpen );
		return this;
	}

	
}
