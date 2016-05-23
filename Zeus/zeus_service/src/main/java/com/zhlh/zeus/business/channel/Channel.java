package com.zhlh.zeus.business.channel;

import java.util.Vector;

public class Channel {
	String code;
	boolean autoIdentityCollection = false;
	ChannelRestrict[] restricts;
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append( code ).append( " : " );
		
		for( ChannelRestrict one : restricts ) {
			buffer.append( one.toString( ) ).append( "; " );
		}
		
		return buffer.toString( );
	}
	
	public boolean needAutoIdCollection() {
		return autoIdentityCollection;
	}
	
	public void pack() {
		Vector<ChannelRestrict> lst = new Vector<ChannelRestrict>( );
		
		for( ChannelRestrict one : restricts ) {
			if( one != null ) {
				lst.add( one );
			}
		}
		
		restricts = new ChannelRestrict[ lst.size( ) ];
		lst.toArray( restricts );
	}
	
	public boolean isEnableInsurer( String insurerCode ) {
		for( ChannelRestrict one : restricts ) {
			if( one.insurerCode.equals( insurerCode ) ) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isOpen( String insurerCode, String licenseNo ) {
		for( ChannelRestrict one : restricts ) {
			if( one.insurerCode.equals( insurerCode ) ) {
				return one.isOpen( licenseNo );
			}
		}
		return false;
	}
	
	public String getCode() {
		return code;
	}

	public String[] getEnableInsurers( ) {
		
		String[] insurers = new String[ restricts.length ];
		int i = 0;
		for( ChannelRestrict rst : restricts ) {
			insurers[ i++ ] = rst.insurerCode;
		}
		
		return insurers;
	}
}
