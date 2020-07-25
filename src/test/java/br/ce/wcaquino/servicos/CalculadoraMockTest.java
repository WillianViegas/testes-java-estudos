package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;


public class CalculadoraMockTest {

	@Test
	public void teste() {
		Calculadora calc = Mockito.mock(Calculadora.class);
		//se utilizar um macther como parametro o outro tem que ser um matcher tambem
		//se quiser utilizar um valor fixo com matcher deve utilizar o Mockito.eq(valor)
		Mockito.when(calc.somar(Mockito.eq(1), Mockito.anyInt())).thenReturn(5);
		
		Assert.assertEquals(5,calc.somar(1, 2));
	}
}
