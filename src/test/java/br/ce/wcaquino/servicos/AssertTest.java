package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;

public class AssertTest {

	@Test
	public void test() {
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		
		
		Assert.assertEquals("Erro de comparacao",1, 1);
		
		//quando for double precisa colocar uma margem de erro
		Assert.assertEquals(0.512346, 0.512, 0.001);
		Assert.assertEquals(Math.PI, 3.14, 0.01);
		
		int i = 5;
		Integer i2 = 5;
		Assert.assertEquals(Integer.valueOf(i), i2);
		Assert.assertEquals(i, i2.intValue());
		
		Assert.assertEquals("Bola", "Bola");
		Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
		Assert.assertTrue("bola".startsWith("b"));
		
		Usuario u1 = new Usuario("Usuario 1");
		Usuario u2 = new Usuario("Usuario 1");
		Usuario u3 = null;
		
		Assert.assertEquals(u1, u2);
		
		//verifica se o objeto é o mesmo
		Assert.assertSame(u2, u2);
		Assert.assertNotSame(u1, u2);
		
		Assert.assertTrue(u3 == null);
		Assert.assertNull(u3);
		Assert.assertNotNull(u2);
		
		
		Assert.assertNotEquals("bola", "casa");
	}
}
