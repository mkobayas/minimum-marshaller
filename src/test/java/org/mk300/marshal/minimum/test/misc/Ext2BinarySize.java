package org.mk300.marshal.minimum.test.misc;


import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.io.HexDump;
import org.apache.commons.lang3.RandomStringUtils;
import org.mk300.marshal.minimum.MinimumMarshaller;
import org.mk300.marshal.minimum.test.pojo.POJO001;
import org.mk300.marshal.minimum.test.pojo.POJO005;
import org.mk300.marshal.minimum.test.pojo.POJO00A;
import org.mk300.marshal.minimum.test.pojo.POJO00B;
import org.mk300.marshal.minimum.test.pojo.POJO00C;
import org.mk300.marshal.minimum.test.pojo.POJO00D;
import org.mk300.marshal.minimum.test.pojo.POJO00E;
import org.mk300.marshal.minimum.test.pojo.POJO00F;
import org.mk300.marshal.minimum.test.pojo.POJO00G;
import org.mk300.marshal.minimum.test.pojo.POJO00H;
import org.mk300.marshal.minimum.test.pojo.POJO00I;
import org.mk300.marshal.minimum.test.pojo.POJO00J;
import org.mk300.marshal.minimum.test.pojo.POJO00K;
import org.mk300.marshal.minimum.test.pojo.POJO010;
import org.mk300.marshal.minimum.test.pojo.POJO020;
import org.mk300.marshal.minimum.test.pojo.POJO050;
import org.mk300.marshal.minimum.test.pojo.POJO100;


public class Ext2BinarySize {

	public static void main(String[] args) throws Exception {
		

		
		{
			POJO001 obj = new POJO001();
			obj.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
			printJavaSerializeBinarySize(obj);
		}
		
		
		{
			POJO005 obj = new POJO005();
			obj.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute2(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute3(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute4(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute5(RandomStringUtils.randomAlphanumeric(10));
			printJavaSerializeBinarySize(obj);
		}
		
		
		{
			POJO010 obj = new POJO010();
			obj.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute2(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute3(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute4(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute5(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute6(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute7(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute8(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute9(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute10(RandomStringUtils.randomAlphanumeric(10));
			printJavaSerializeBinarySize(obj);
		}
		
		
		{
			POJO020 obj = new POJO020();
			obj.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute2(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute3(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute4(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute5(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute6(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute7(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute8(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute9(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute10(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute11(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute12(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute13(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute14(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute15(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute16(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute17(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute18(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute19(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute20(RandomStringUtils.randomAlphanumeric(10));
			printJavaSerializeBinarySize(obj);
		}

		{
			POJO050 obj = new POJO050();
			obj.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute2(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute3(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute4(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute5(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute6(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute7(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute8(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute9(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute10(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute11(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute12(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute13(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute14(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute15(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute16(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute17(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute18(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute19(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute20(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute21(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute22(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute23(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute24(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute25(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute26(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute27(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute28(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute29(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute30(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute31(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute32(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute33(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute34(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute35(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute36(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute37(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute38(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute39(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute40(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute41(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute42(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute43(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute44(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute45(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute46(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute47(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute48(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute49(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute50(RandomStringUtils.randomAlphanumeric(10));
			printJavaSerializeBinarySize(obj);
		}

		{
			POJO100 obj = new POJO100();
			obj.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute2(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute3(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute4(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute5(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute6(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute7(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute8(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute9(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute10(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute11(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute12(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute13(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute14(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute15(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute16(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute17(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute18(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute19(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute20(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute21(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute22(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute23(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute24(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute25(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute26(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute27(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute28(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute29(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute30(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute31(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute32(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute33(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute34(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute35(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute36(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute37(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute38(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute39(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute40(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute41(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute42(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute43(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute44(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute45(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute46(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute47(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute48(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute49(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute50(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute51(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute52(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute53(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute54(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute55(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute56(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute57(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute58(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute59(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute60(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute61(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute62(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute63(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute64(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute65(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute66(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute67(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute68(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute69(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute70(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute71(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute72(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute73(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute74(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute75(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute76(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute77(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute78(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute79(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute80(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute81(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute82(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute83(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute84(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute85(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute86(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute87(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute88(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute89(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute90(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute91(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute92(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute93(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute94(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute95(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute96(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute97(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute98(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute99(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute100(RandomStringUtils.randomAlphanumeric(10));
			printJavaSerializeBinarySize(obj);
		}
		
		{
			POJO00A obj = new POJO00A();
			obj.setAttribute1(new Date());
			printJavaSerializeBinarySize(obj);
		}
		
		{
			POJO00B obj = new POJO00B();
			obj.setAttribute1(Integer.MAX_VALUE);
			printJavaSerializeBinarySize(obj);
		}
		
		{
			POJO00C obj = new POJO00C();
			obj.setAttribute1("Hello World".getBytes());
			printJavaSerializeBinarySize(obj);
		}
		
		{
			POJO00D obj = new POJO00D();
			obj.addDate(new Date());
			printJavaSerializeBinarySize(obj);
		}
		
		{
			POJO00E obj = new POJO00E();
			obj.setAttribute1(true);
			printJavaSerializeBinarySize(obj);
		}
		
		{
			POJO00F obj = new POJO00F();
			obj.setAttribute1(Double.MAX_VALUE);
			printJavaSerializeBinarySize(obj);
		}
		
		{
			POJO00G obj = new POJO00G();
			obj.setAttribute1(Float.MAX_VALUE);
			printJavaSerializeBinarySize(obj);
		}

		{
			POJO00H obj = new POJO00H();
			obj.setAttribute1(Long.MAX_VALUE);
			printJavaSerializeBinarySize(obj);
		}

		{
			POJO00I obj = new POJO00I();
			obj.setAttribute1(new BigDecimal(Long.MAX_VALUE));
			printJavaSerializeBinarySize(obj);
		}
		
		{
			POJO00J obj = new POJO00J();
			obj.addDate(new Date());
			obj.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute2(new Date());
			obj.setAttribute3(Integer.MAX_VALUE);
			obj.setAttribute4("Hello World".getBytes());
			obj.setAttribute5(true);
			obj.setAttribute6(Double.MAX_VALUE);
			obj.setAttribute7(Float.MAX_VALUE);
			obj.setAttribute8(Long.MAX_VALUE);
			printJavaSerializeBinarySize(obj);
		}
		
		{
			POJO00K obj = new POJO00K();
			
			{
				POJO001 b = new POJO001();
				b.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
				obj.addDate(b);
			}
			
			{
				POJO001 b = new POJO001();
				b.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
				obj.addDate(b);
			}
			
			{
				POJO001 b = new POJO001();
				b.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
				obj.addDate(b);
			}
			
			obj.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
			obj.setAttribute2(new Date());
			obj.setAttribute3(Integer.MAX_VALUE);
			obj.setAttribute4("Hello World".getBytes());
			obj.setAttribute5(true);
			obj.setAttribute6(Double.MAX_VALUE);
			obj.setAttribute7(Float.MAX_VALUE);
			obj.setAttribute8(Long.MAX_VALUE);
//			printJavaSerializeBinarySize(obj);
			
			
			POJO00J value = new POJO00J();
			value.addDate(new Date());
			value.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
			value.setAttribute2(new Date());
			value.setAttribute3(Integer.MAX_VALUE);
			value.setAttribute4("Hello World".getBytes());
			value.setAttribute5(true);
			value.setAttribute6(Double.MAX_VALUE);
			value.setAttribute7(Float.MAX_VALUE);
			value.setAttribute8(Long.MAX_VALUE);
			printJavaSerializeBinarySize(value);

			printJavaSerializeBinarySize("A|4|9426|AAAAAAA");
		}
	}

	public static void printJavaSerializeBinarySize(Object target) throws Exception{
		try {
			
			byte[] bytes = MinimumMarshaller.marshal(target);
			
			System.out.println(target.getClass().getSimpleName() + " binaly size is " + bytes.length);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			HexDump.dump(bytes, 0, os, 0);
			System.out.println(os.toString());
		    
			Object o = MinimumMarshaller.unmarshal(bytes);
			
			// Verify correct unmarshalling among before and after
			if( ! o.equals(target) ) {
				throw new RuntimeException("Different! " + target );
			}
			
		} finally {
		}
	}
	
	
}
