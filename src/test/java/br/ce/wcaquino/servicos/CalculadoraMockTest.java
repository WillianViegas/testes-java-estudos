package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


public class CalculadoraMockTest {

	@Mock
	private Calculadora calcMock;
	
	@Spy
	private Calculadora calcSpy;
	
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	
	@Test
	public void devoMostrarDiferencaEntreMockESpy() {
		
		Mockito.when(calcMock.somar(1,2)).thenReturn(8);
//		Mockito.when(calcSpy.somar(1,2)).thenReturn(8);
		Mockito.doReturn(8).when(calcSpy).somar(1, 2);
//		Mockito.doNothing().when(calcSpy).somar(1, 2);
		
		//se n for igual a expectativa ele retorna o valor padrao (nao funciona bem com metodos void)
		System.out.println("Mock " + calcMock.somar(1,2));
		
		//se n for igual a expectativa ele retorna o valor do metodo (nao funciona com interfaces)
		System.out.println("Spy " + calcSpy.somar(1,2));

	}
	
	@Test
	public void teste() {
		Calculadora calc = Mockito.mock(Calculadora.class);
		//se utilizar um macther como parametro o outro tem que ser um matcher tambem
		//se quiser utilizar um valor fixo com matcher deve utilizar o Mockito.eq(valor)
		
		ArgumentCaptor<Integer> argCapt = ArgumentCaptor.forClass(Integer.class);
		Mockito.when(calc.somar(argCapt.capture(), argCapt.capture())).thenReturn(5);
		
		Assert.assertEquals(5,calc.somar(1, 2));
//		System.out.println(argCapt.getAllValues());
	}
}
