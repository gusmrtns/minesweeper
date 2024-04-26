package br.com.campo_minado.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	void testeVizinhoRealDistancia2DiagonalSuperiorEsquerda() {
		Campo vizinho = new Campo(linha - 1, coluna - 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistancia2DiagonalSuperiorDireita() {
		Campo vizinho = new Campo(linha - 1, coluna + 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistancia2DiagonalInferiorEsquerda() {
		Campo vizinho = new Campo(linha + 1, coluna - 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoRealDistancia2DiagonalInferiorDireita() {
		Campo vizinho = new Campo(linha + 1, coluna + 1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	// Teste de não vizinho - manual
	@Test
	void testeVizinhoReal() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}

}
