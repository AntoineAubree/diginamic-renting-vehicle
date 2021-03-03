package fr.diginamic.reflect;

import org.junit.Test;

import fr.diginamic.composants.reflect.ReflectUtils;

public class ReflectUtilsTest {

	@Test
	public void testInstantiate() {
		System.out.println("\1F586");
		ReflectUtils.invoke("testService.modifier(22)");
		
		final char[] chars = Character.toChars(0xF303);
		final String s = new String(chars);
		System.out.println(s);
	}

}
