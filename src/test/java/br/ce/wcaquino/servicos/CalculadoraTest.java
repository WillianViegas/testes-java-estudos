package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.exceptions.NaoPodeDividrPorZeroException;

public class CalculadoraTest {
	
	private Calculadora calc;
	
	@Before
	public void setup() {
		calc = new Calculadora();
	}
	
	@Test
	public void deveSomarDoisValores() {
		//cenario
		int a = 5;
		int b = 3;
		
		//acao
		int resultado = calc.somar(a, b);
		
		//verificacao
		Assert.assertEquals(8, resultado);
	}
	
	@Test
	public void deveSubtrairDoisValores() {
		//cenario
		int a = 8;
		int b = 5;
		
		//acao
		int resultado = calc.subtrair(a, b);
		
		//verificacao
		assertEquals(3, resultado);
		
	}
	
	@Test
	public void deveDividirDoisValores() {
		//cenario
		int a = 6;
		int b = 3;
		
		//acao
		int resultado = calc.divir(a, b);
		
		//verificacao
		Assert.assertEquals(2, resultado);
		
	}
	
	@Test(expected = NaoPodeDividrPorZeroException.class)
	public void deveLancarExcecaoAoDivirPorZero() {
		int a = 10;
		int b = 0;
		
		calc.divir(a, b);
		
	}
}
