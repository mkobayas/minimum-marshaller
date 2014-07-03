package org.mk300.marshal.minimum.test;


import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.mk300.marshal.minimum.MinimumMarshaller;
import org.mk300.marshal.minimum.test.pojo.POJO100;


public class LargeStringTest {

	@Test
	public void large100_400000Test() throws Exception {

		int length = 1024*1024;
		
		POJO100 value_POJO100 = new POJO100();
		value_POJO100.setAttribute1(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute2(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute3(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute4(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute5(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute6(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute7(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute8(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute9(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute10(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute11(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute12(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute13(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute14(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute15(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute16(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute17(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute18(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute19(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute20(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute21(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute22(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute23(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute24(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute25(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute26(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute27(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute28(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute29(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute30(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute31(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute32(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute33(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute34(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute35(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute36(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute37(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute38(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute39(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute40(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute41(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute42(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute43(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute44(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute45(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute46(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute47(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute48(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute49(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute50(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute51(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute52(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute53(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute54(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute55(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute56(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute57(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute58(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute59(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute60(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute61(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute62(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute63(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute64(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute65(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute66(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute67(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute68(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute69(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute70(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute71(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute72(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute73(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute74(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute75(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute76(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute77(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute78(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute79(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute80(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute81(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute82(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute83(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute84(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute85(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute86(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute87(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute88(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute89(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute90(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute91(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute92(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute93(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute94(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute95(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute96(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute97(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute98(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute99(RandomStringUtils.randomAlphanumeric(length));
		value_POJO100.setAttribute100(RandomStringUtils.randomAlphanumeric(length));
		
		
		long start1 = System.currentTimeMillis();
		byte[] bytes = MinimumMarshaller.marshal(value_POJO100);
		long end1 = System.currentTimeMillis();
		
		System.out.println(value_POJO100.getClass().getSimpleName() + " binary size is " + bytes.length);

		long start2 = System.currentTimeMillis();
		Object o = MinimumMarshaller.unmarshal(bytes);
		long end2 = System.currentTimeMillis();
		

		System.out.println("   marshall time(ms)=" + (end1 -start1));
		System.out.println(" unmarshall time(ms)=" + (end2 -start2));

		System.out.println("");
		
		
		// 正確に復元されていることの検証
		if( ! o.equals(value_POJO100) ) {
			throw new RuntimeException("オブジェクトが異なります。" + value_POJO100 );
		}
		

	}
	

}
