package br.com.campo_minado.modelo;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excecao.ExplosaoException;
import modelo.Campo;

class CampoTeste {

	private int linha = 5;
	private int coluna = 6;
	private Campo campo;
	
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(linha, coluna);
	}
	
	// teste geral da função
	/* @Test
	void testeVizinhoReal() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	*/
	
	// Testes especificos
	@Test
	void testeVizinhoRealDistancia1Esquerda() {
		Campo vizinho = new Campo(linha, coluna - 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistancia1Direita() {
		Campo vizinho = new Campo(linha, coluna + 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistancia1EmCima() {
		Campo vizinho = new Campo(linha - 1, coluna);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistancia1Embaixo() {
		Campo vizinho = new Campo(linha + 1, coluna);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistancia2DiagonalSuperiorEsquerdo() {
		Campo vizinho = new Campo(linha - 1, coluna - 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistancia2DiagonalSuperiorDireito() {
		Campo vizinho = new Campo(linha - 1, coluna + 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistancia2DiagonalInferiorEsquerdo() {
		Campo vizinho = new Campo(linha + 1, coluna - 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistancia2DiagonalInferiorDireito() {
		Campo vizinho = new Campo(linha + 1, coluna + 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	// Teste de não vizinho - manual
	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}
	
	// Testes de Marcação e abertura
	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void testeAlternarMarcacaoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoMarcado() {
		campo.alternarMarcacao();
		campo.Minar();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.Minar();
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
	}
	
	
	
	

}
