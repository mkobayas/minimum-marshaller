package org.mk300.marshal.minimum.test;

import java.io.Serializable;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

public class LambdaTest {
    
    @Test
    public void testNonArgLambda() throws Exception {
        SFunction<String, String> f = s -> s+"a";
        String ret1 = f.apply("hoge");

        SFunction<String, String> f2 = TestUtil.testAndPrintHex(f);
        String ret2 = f2.apply("hoge");
        Assert.assertEquals(ret1, ret2);
    }


    @Test
    public void testOneArgLamda() throws Exception {
        String a = "a";
        SFunction<String, String> f = s -> s+a;
        String ret1 = f.apply("hoge");

        SFunction<String, String> f2 = TestUtil.testAndPrintHex(f);
        String ret2 = f2.apply("hoge");
        Assert.assertEquals(ret1, ret2);
    }
    

    @Test
    public void testTwoArgLamda() throws Exception {
        String a = "a";
        SFunction<String, String> f = s -> s + a + this.toString();
        String ret1 = f.apply("hoge");

        SFunction<String, String> f2 = TestUtil.testAndPrintHex(f);
        String ret2 = f2.apply("hoge");
        Assert.assertEquals(ret1, ret2);
    }
    


    @Test
    public void testNestLamda() throws Exception {
        String a = "a";

        SBiFunciton<String, SFunction<String, String>, String> fn = (s, func) -> {
            return func.apply(s) + a;
        };
        
        String ret1 = fn.apply("hoge", s -> s + this.toString());

        SBiFunciton<String, SFunction<String, String>, String> f2 = TestUtil.testAndPrintHex(fn);
        String ret2 = f2.apply("hoge", s -> s + this.toString());
        Assert.assertEquals(ret1, ret2);
    }

    public interface SFunction<T, R> extends Function<T, R>, Serializable {
    }
    
    public interface SBiFunciton<T, U, R> extends BiFunction<T, U, R>, Serializable {
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
