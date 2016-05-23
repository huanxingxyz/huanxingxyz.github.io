package com.zhlh.zeus.business.idcard;

public class IdCardArea {
	String[] areaCode = new String[3]; //前6位数地区代码，
	
	String areaName; //地区名称
	
	IdCardArea( String str1, String str2, String str3, String name ) {
		areaCode[0] = str1;
		areaCode[1] = str2;
		areaCode[2] = str3;
		
		areaName = name;
	}
	
	int compareTo( IdCardArea other ) {
		return compareTo( other.areaCode );
	}
	
	int compareTo( String[] codes ) {
		int result = 0;
		
		for( int i = 0; i < areaCode.length; i++ ) {
			result = this.areaCode[i].compareTo( codes[i] );
			if( result != 0 ) {
				return result;
			}
		}
		
		return 0;
	}

	public String toString() {
		return areaCode[0] + areaCode[1] + areaCode[2] + ":" + areaName;
	}
}
