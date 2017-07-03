package com.lsy.test;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class Md5Demo {

	@Test
	public void test1(){
		String salt="Day Day Up!";
				
		String  str="123456";
		
		String md5 = DigestUtils.md5Hex(str+salt);
		
		System.out.println(md5);
	}
	

}
