package org.mk300.marshal.minimum.test.misc;


import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;


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


public class MinimumMarshallerPerfomanceTest {
	
	public static void main(String[] args) throws Exception {
		MinimumMarshallerPerfomanceTest o = new MinimumMarshallerPerfomanceTest();

		int THREADS = Runtime.getRuntime().availableProcessors() -1 ;
		if( THREADS == 0) {
			THREADS = 1;
		}

		System.out.println("MinimumMarshaller");
		
		System.out.println("RampUP");
		o.testThread(true, value_POJO001, 1000, THREADS);
		o.testThread(true, value_POJO005, 1000, THREADS);
		o.testThread(true, value_POJO010, 1000, THREADS);
		o.testThread(true, value_POJO020, 1000, THREADS);
		o.testThread(true, value_POJO050, 1000, THREADS);
		o.testThread(true, value_POJO100, 1000, THREADS);
		
		o.testThread(true, value_POJO00A, 1000, THREADS);
		o.testThread(true, value_POJO00B, 1000, THREADS);
		o.testThread(true, value_POJO00C, 1000, THREADS);
		o.testThread(true, value_POJO00D, 1000, THREADS);
		o.testThread(true, value_POJO00E, 1000, THREADS);
		o.testThread(true, value_POJO00F, 1000, THREADS);
		o.testThread(true, value_POJO00G, 1000, THREADS);
		o.testThread(true, value_POJO00H, 1000, THREADS);
		o.testThread(true, value_POJO00I, 1000, THREADS);
		o.testThread(true, value_POJO00J, 1000, THREADS);
		o.testThread(true, value_POJO00K, 1000, THREADS);
//
//		System.out.println("Start");
		o.testThread(false, value_POJO001, 3000, THREADS);
		o.testThread(false, value_POJO005, 3000, THREADS);
		o.testThread(false, value_POJO010, 3000, THREADS);
		o.testThread(false, value_POJO020, 3000, THREADS);
		o.testThread(false, value_POJO050, 3000, THREADS);
		o.testThread(false, value_POJO100, 3000, THREADS);
		
		o.testThread(false, value_POJO00A, 3000, THREADS);
		o.testThread(false, value_POJO00B, 3000, THREADS);
		o.testThread(false, value_POJO00C, 3000, THREADS);
		o.testThread(false, value_POJO00D, 3000, THREADS);
		o.testThread(false, value_POJO00E, 3000, THREADS);
		o.testThread(false, value_POJO00F, 3000, THREADS);
		o.testThread(false, value_POJO00G, 3000, THREADS);
		o.testThread(false, value_POJO00H, 3000, THREADS);
		o.testThread(false, value_POJO00I, 3000, THREADS);
		o.testThread(false, value_POJO00J, 3000, THREADS);
		o.testThread(false, value_POJO00K, 3000, THREADS);

		System.out.println("Done");
	}
	

	private void testThread(boolean rampup, final Object target, final long time, int threadNum) throws Exception {
		System.gc();
		
		final CountDownLatch startLatch = new CountDownLatch(1);
		final CountDownLatch endLatch = new CountDownLatch(threadNum);
		final AtomicLong count = new AtomicLong(0);
		final AtomicLong mTime = new AtomicLong(0);
		final AtomicLong uTime = new AtomicLong(0);
		for(int i=0; i<threadNum ; i++) {
			Thread t = new Thread(new Runnable() {
				long p1, p2, p3, p4, p5;
				public long sum(){return p1+p2+p3+p4+p5;} // avoid false sharing.
				@Override
				public void run() {

					long localCount = 0;
					long marTime=0, unmarTime=0;
					try {
						
						startLatch.await();
						long end = System.currentTimeMillis() + time;
						byte[] b = null;
						long start = 0;
						while(System.currentTimeMillis() < end) {
							
							start = System.nanoTime();
							b =MinimumMarshaller.marshal(target, 32);
							marTime += (System.nanoTime() - start);
							
							start = System.nanoTime();
							MinimumMarshaller.unmarshal(b);
							unmarTime += (System.nanoTime() - start);
							
//							if(!target.equals(o)) {
//								throw new RuntimeException();
//							}
							
							localCount++;
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						count.addAndGet(localCount);
						mTime.addAndGet(marTime);
						uTime.addAndGet(unmarTime);
						endLatch.countDown();
					}
				}
				
			});
			t.start();
		}

		startLatch.countDown();
		endLatch.await();
		
		if( rampup == false) {
			System.out.println(threadNum + " Thread throghput = " +  String.format("%12s", String.format("%,d",  (long)(((double)count.get())*1000d/time) )) + ", obj=" + target.getClass().getSimpleName()
					 + ", total run=" + String.format("%15s", String.format("%,d", count.get()) )
					 + ", mTime = " +  String.format("%7s", String.format("%,d",  (long)(((double)mTime.get())/count.get()) ))
					 + ", uTime = " +  String.format("%7s", String.format("%,d",  (long)(((double)uTime.get())/count.get()) )));

		}
	}
	
	

	

	static POJO001 value_POJO001 = new POJO001();
	static POJO005 value_POJO005 = new POJO005();
	static POJO010 value_POJO010 = new POJO010();
	static POJO020 value_POJO020 = new POJO020();
	static POJO050 value_POJO050 = new POJO050();
	static POJO100 value_POJO100 = new POJO100();
	
	static POJO00A value_POJO00A = new POJO00A();
	static POJO00B value_POJO00B = new POJO00B();
	static POJO00C value_POJO00C = new POJO00C();
	static POJO00D value_POJO00D = new POJO00D();
	static POJO00E value_POJO00E = new POJO00E();
	static POJO00F value_POJO00F = new POJO00F();
	static POJO00G value_POJO00G = new POJO00G();
	static POJO00H value_POJO00H = new POJO00H();
	static POJO00I value_POJO00I = new POJO00I();
	static POJO00J value_POJO00J = new POJO00J();
	static POJO00K value_POJO00K = new POJO00K();
	
	static {
		// POJO001
		value_POJO001.setAttribute1(RandomStringUtils.randomAlphanumeric(10));

		// POJO005
		value_POJO005.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
		value_POJO005.setAttribute2(RandomStringUtils.randomAlphanumeric(10));
		value_POJO005.setAttribute3(RandomStringUtils.randomAlphanumeric(10));
		value_POJO005.setAttribute4(RandomStringUtils.randomAlphanumeric(10));
		value_POJO005.setAttribute5(RandomStringUtils.randomAlphanumeric(10));

		// POJO0010
		value_POJO010.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
		value_POJO010.setAttribute2(RandomStringUtils.randomAlphanumeric(10));
		value_POJO010.setAttribute3(RandomStringUtils.randomAlphanumeric(10));
		value_POJO010.setAttribute4(RandomStringUtils.randomAlphanumeric(10));
		value_POJO010.setAttribute5(RandomStringUtils.randomAlphanumeric(10));
		value_POJO010.setAttribute6(RandomStringUtils.randomAlphanumeric(10));
		value_POJO010.setAttribute7(RandomStringUtils.randomAlphanumeric(10));
		value_POJO010.setAttribute8(RandomStringUtils.randomAlphanumeric(10));
		value_POJO010.setAttribute9(RandomStringUtils.randomAlphanumeric(10));
		value_POJO010.setAttribute10(RandomStringUtils.randomAlphanumeric(10));
		
		// POJO0020
		value_POJO020.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
		value_POJO020.setAttribute2(RandomStringUtils.randomAlphanumeric(10));
		value_POJO020.setAttribute3(RandomStringUtils.randomAlphanumeric(10));
		value_POJO020.setAttribute4(RandomStringUtils.randomAlphanumeric(10));
		value_POJO020.setAttribute5(RandomStringUtils.randomAlphanumeric(10));
		value_POJO020.setAttribute6(RandomStringUtils.randomAlphanumeric(10));
		value_POJO020.setAttribute7(RandomStringUtils.randomAlphanumeric(10));
		value_POJO020.setAttribute8(RandomStringUtils.randomAlphanumeric(10));
		value_POJO020.setAttribute9(RandomStringUtils.randomAlphanumeric(10));
		value_POJO020.setAttribute10(RandomStringUtils.randomAlphanumeric(10));
		value_POJO020.setAttribute11(RandomStringUtils.randomAlphanumeric(10));
		value_POJO020.setAttribute12(RandomStringUtils.randomAlphanumeric(10));
		value_POJO020.setAttribute13(RandomStringUtils.randomAlphanumeric(10));
		value_POJO020.setAttribute14(RandomStringUtils.randomAlphanumeric(10));
		value_POJO020.setAttribute15(RandomStringUtils.randomAlphanumeric(10));
		value_POJO020.setAttribute16(RandomStringUtils.randomAlphanumeric(10));
		value_POJO020.setAttribute17(RandomStringUtils.randomAlphanumeric(10));
		value_POJO020.setAttribute18(RandomStringUtils.randomAlphanumeric(10));
		value_POJO020.setAttribute19(RandomStringUtils.randomAlphanumeric(10));
		value_POJO020.setAttribute20(RandomStringUtils.randomAlphanumeric(10));

		// POJO0050
		value_POJO050.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute2(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute3(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute4(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute5(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute6(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute7(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute8(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute9(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute10(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute11(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute12(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute13(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute14(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute15(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute16(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute17(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute18(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute19(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute20(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute21(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute22(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute23(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute24(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute25(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute26(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute27(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute28(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute29(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute30(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute31(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute32(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute33(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute34(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute35(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute36(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute37(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute38(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute39(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute40(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute41(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute42(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute43(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute44(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute45(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute46(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute47(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute48(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute49(RandomStringUtils.randomAlphanumeric(10));
		value_POJO050.setAttribute50(RandomStringUtils.randomAlphanumeric(10));
		


		// POJO00100
		value_POJO100.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute2(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute3(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute4(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute5(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute6(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute7(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute8(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute9(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute10(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute11(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute12(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute13(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute14(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute15(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute16(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute17(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute18(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute19(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute20(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute21(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute22(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute23(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute24(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute25(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute26(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute27(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute28(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute29(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute30(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute31(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute32(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute33(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute34(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute35(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute36(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute37(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute38(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute39(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute40(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute41(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute42(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute43(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute44(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute45(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute46(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute47(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute48(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute49(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute50(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute51(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute52(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute53(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute54(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute55(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute56(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute57(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute58(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute59(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute60(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute61(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute62(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute63(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute64(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute65(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute66(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute67(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute68(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute69(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute70(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute71(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute72(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute73(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute74(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute75(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute76(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute77(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute78(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute79(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute80(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute81(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute82(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute83(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute84(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute85(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute86(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute87(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute88(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute89(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute90(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute91(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute92(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute93(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute94(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute95(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute96(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute97(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute98(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute99(RandomStringUtils.randomAlphanumeric(10));
		value_POJO100.setAttribute100(RandomStringUtils.randomAlphanumeric(10));

		
		// POJO00A
		value_POJO00A.setAttribute1(new Date());
		
		// POJO00B
		value_POJO00B.setAttribute1(Integer.MAX_VALUE);
		
		// POJO00C
		value_POJO00C.setAttribute1("Hello World".getBytes());
		
		// POJO00D
		value_POJO00D.addDate(new Date());
		
		// POJO00E
		value_POJO00E.setAttribute1(true);
		
		// POJO00F
		value_POJO00F.setAttribute1(Double.MAX_VALUE);
		
		// POJO00G
		value_POJO00G.setAttribute1(Float.MAX_VALUE);
		
		// POJO00H
		value_POJO00H.setAttribute1(Long.MAX_VALUE);
		
		// POJO00I
		value_POJO00I.setAttribute1(new BigDecimal(Long.MAX_VALUE));
		
		// POJO00J
		value_POJO00J.addDate(new Date());
		value_POJO00J.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
		value_POJO00J.setAttribute2(new Date());
		value_POJO00J.setAttribute3(Integer.MAX_VALUE);
		value_POJO00J.setAttribute4("Hello World".getBytes());
		value_POJO00J.setAttribute5(true);
		value_POJO00J.setAttribute6(Double.MAX_VALUE);
		value_POJO00J.setAttribute7(Float.MAX_VALUE);
		value_POJO00J.setAttribute8(Long.MAX_VALUE);
		
		// POJO00K
		{
			POJO001 b = new POJO001();
			b.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
			value_POJO00K.addDate(b);
		}
		
		{
			POJO001 b = new POJO001();
			b.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
			value_POJO00K.addDate(b);
		}
		
		{
			POJO001 b = new POJO001();
			b.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
			value_POJO00K.addDate(b);
		}
		
		value_POJO00K.setAttribute1(RandomStringUtils.randomAlphanumeric(10));
		value_POJO00K.setAttribute2(new Date());
		value_POJO00K.setAttribute3(Integer.MAX_VALUE);
		value_POJO00K.setAttribute4("Hello World".getBytes());
		value_POJO00K.setAttribute5(true);
		value_POJO00K.setAttribute6(Double.MAX_VALUE);
		value_POJO00K.setAttribute7(Float.MAX_VALUE);
		value_POJO00K.setAttribute8(Long.MAX_VALUE);
	}
}
