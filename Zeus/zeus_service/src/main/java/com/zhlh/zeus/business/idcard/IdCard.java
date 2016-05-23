package com.zhlh.zeus.business.idcard;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdCard {
	
	String cardNumber;
	IdCardArea area;
	String sex;
	String birthDay;
	
	private Boolean cacheValidateResult = null;
    private Date cacheBirthDate = null;
	
	public IdCard( String id ) {
		this.cardNumber = id;
		area = IdCardManager.getAreaById(id);
		sex = IdCardManager.getSexFlag(id);
		if(id.length() == 15){
			try{
				id = getEighteenIDCard(id);
			}catch (Exception e){
			}
		}
		birthDay = id.substring( 6, 10 ) + "-" + id.substring( 10, 12 ) + "-" + id.substring( 12, 14 );
	}
	
	public String getSex() {
		return sex;
	}
	
	public String getBirthDay() {
		return birthDay;
	}
	
	public String getCardIssuer() {
		IdCardArea card = IdCardManager.getAreaById(area.areaCode[0] + "0000");
		return card.areaName + "公安局";
	}
	
	//2005年办理第二代身份证
	//16-25周岁的公民发给有效期十年的居民身份证，
	//26-45周岁的公民发给有效期二十年的居民身份证，
	//46周岁以上的公民发给长期有效的居民身份证。
	public String[] guessPeriodOfValidity() {
		String days[] = birthDay.split( "-" );
		
		int birthYear = Integer.parseInt( days[0] );
		
		if( birthYear >= 1990 ) {
			return new String[] { 	(birthYear + 16)      + days[1] + days[2],
									(birthYear + 16 + 10) + days[1] + days[2] 
									};
		}
		else if( birthYear >= 1980 && birthYear <= 1989 ) {
			return new String[] { 	"2015" + days[1] + days[2],
									"2035" + days[1] + days[2] 
									};
		}
		else if( birthYear < 1960 ) {
			return new String[] { 	"2005" + days[1] + days[2],
									"长期有效"  
								};
		}
		else {
			//return new String[] { 	"20051020", "20251020" };
			return new String[] { 	"2005" + days[1] + days[2],
									"2025" + days[1] + days[2] 
									};
		}
		
	}
	
	
    public boolean validate() {
        if (null == cacheValidateResult) {
            boolean result = true;
            // 身份证号不能为空
            result = result && (null != cardNumber);
            // 身份证号长度是18(新证)
            result = result && IdCardManager.NEW_CARD_NUMBER_LENGTH == cardNumber.length();
            // 身份证号的前17位必须是阿拉伯数字
            for (int i = 0; result && i < IdCardManager.NEW_CARD_NUMBER_LENGTH - 1; i++) {
                char ch = cardNumber.charAt(i);
                result = result && ch >= '0' && ch <= '9';
            }
            // 身份证号的第18位校验正确
            result = result
                    && (IdCardManager.calculateVerifyCode(cardNumber) == cardNumber
                            .charAt(IdCardManager.NEW_CARD_NUMBER_LENGTH - 1));
            // 出生日期不能晚于当前时间，并且不能早于1900年
            try {
                Date birthDate = new SimpleDateFormat( "yyyy-MM-dd" ).parse( birthDay );
                result = result && null != birthDate;
                result = result && birthDate.before(new Date());
                result = result && birthDate.after(IdCardManager.MINIMAL_BIRTH_DATE);
                /**
                 * 出生日期中的年、月、日必须正确,比如月份范围是[1,12],日期范围是[1,31]，还需要校验闰年、大月、小月的情况时，
                 * 月份和日期相符合
                 */
                String realBirthdayPart = new SimpleDateFormat( "yyyy-MM-dd" ).format(birthDate);
                result = result && (birthDay.equals(realBirthdayPart));
            } catch (Exception e) {
                result = false;
            }
            // TODO 完整身份证号码的省市县区检验规则
            cacheValidateResult = Boolean.valueOf(result);
        }
        return cacheValidateResult;
    }

	/**
	 * 根据15位的身份证号码获得18位身份证号码
	 * @param fifteenIDCard 15位的身份证号码
	 * @return 升级后的18位身份证号码
	 * @throws Exception 如果不是15位的身份证号码，则抛出异常
	 */
	public static String getEighteenIDCard(String fifteenIDCard) throws Exception{
		if(fifteenIDCard != null && fifteenIDCard.length() == 15){
			StringBuilder sb = new StringBuilder();
			sb.append(fifteenIDCard.substring(0, 6))
					.append("19")
					.append(fifteenIDCard.substring(6));
			sb.append(getVerifyCode(sb.toString()));
			return sb.toString();
		} else {
			throw new Exception("不是15位的身份证");
		}
	}
	/**
	 * 获取校验码
	 * @param idCardNumber 不带校验位的身份证号码（17位）
	 * @return 校验码
	 * @throws Exception 如果身份证没有加上19，则抛出异常
	 */
	public static char getVerifyCode(String idCardNumber) throws Exception{
		if(idCardNumber == null || idCardNumber.length() < 17) {
			throw new Exception("不合法的身份证号码");
		}
		char[] Ai = idCardNumber.toCharArray();
		int[] Wi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
		char[] verifyCode = {'1','0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
		int S = 0;
		int Y;
		for(int i = 0; i < Wi.length; i++){
			S += (Ai[i] - '0') * Wi[i];
		}
		Y = S % 11;
		return verifyCode[Y];
	}

}
