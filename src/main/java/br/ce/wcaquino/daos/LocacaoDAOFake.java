package br.ce.wcaquino.daos;

import br.ce.wcaquino.entidades.Locacao;

public class LocacaoDAOFake implements LocacaoDAO {

	//Utilizando de fake objects e possível testar testes relacionados a persistencia de dados
	//porem quando vc testa assim eles se tornam testes integrados e nao unitarios
	//para mante-los unitarios e necessario instanciar esse objeto falso
	//mas essa nao e a forma mais ideal o melhor e usar o mockito
	public void Salvar(Locacao locacao) {
	}

}
