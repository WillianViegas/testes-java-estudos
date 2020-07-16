package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

//Ordena os testes em ordem alfabetica, porem não é tao pratico, priorize o modelo F.I.R.S.T
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrdemTest {
	
	public static int cont = 0;
	
	@Test
	public void inicia() {
		cont = 1;
	}
	
	@Test
	public void verifica() {
		Assert.assertEquals(1, cont);
	}
}
