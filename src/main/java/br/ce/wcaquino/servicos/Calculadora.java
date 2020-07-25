package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exceptions.NaoPodeDividrPorZeroException;

public class Calculadora {

	public int somar(int a, int b) {
		System.out.println("Executando somar");
		return a + b;
	}

	public int subtrair(int a, int b) {
		return a - b;
	}

	public int divir(int a, int b) {
		if(b == 0) {
			throw new NaoPodeDividrPorZeroException();
		}
		return a / b;
	}
}
