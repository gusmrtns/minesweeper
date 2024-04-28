package br.com.campo_minado.modelo;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excecao.ExplosaoException;
import modelo.Campo;

class CampoTeste {

	private int linha = 3;
	private int coluna = 3;
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
		Campo vizinho = new Campo(5, 6);
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
	
	@Test
	void testeAbrirComVizinhos() {
		 
		Campo campo11 = new Campo(1, 1);
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);
		
		campo.adicionarVizinho(campo22);
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isAberto());
		
		
	}
	
	@Test
	void testeAbrirComVizinhosMinado() {
		 
		Campo campo11 = new Campo(1, 1);
		Campo campo12 = new Campo(1, 2);
		campo12.Minar();
		
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		
		campo.adicionarVizinho(campo22);
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isFechado());
		
		
	}
	
	@Test 
	void testeObjetivoAlcancado() {
		Campo campo23 = new Campo(2, 3);
		// testar se esta desvendado, não minado e aberto;
		// campo23.abrir();
		
		// testar se esta protegido, minado e marcado
		campo23.Minar();
		campo23.alternarMarcacao();
		
		assertTrue(campo23.objetivoAlcancado());
	}
	
	@Test 
	void testeObjetivoNaoAlcancado() {
		Campo campo23 = new Campo(2, 3);
		// testar se nã esta desvendado:
		// minado e aberto vai lançar exceção então não é necessario testar
		// nao minado e fechado, estado padrao do campo ao ser inicializado
		
		// testar se não protegido:
        // nao minado e marcado
		// campo23.alternarMarcacao();
		// minado e não marcado
		// campo23.Minar();
		
		assertFalse(campo23.objetivoAlcancado());
	}
	
	@Test
	void testMinasNaVizinhanca() {
		
		Campo campo12 = new Campo(1, 2);
		Campo campo11 = new Campo(1, 1);
		Campo campo22 = new Campo(2, 2);
		Campo campo23 = new Campo(2, 3);
		
		campo22.adicionarVizinho(campo12);
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo23);
		
		campo23.Minar();
		campo11.Minar();
		campo12.Minar();
		
		assertEquals(3, campo22.minasNaVizinhanca());
		
	}
	
	@Test
	void testeReiniciar() {
		
		Campo campo12 = new Campo(1, 2);
		
		campo12.abrir();
		campo12.Minar();
		campo12.alternarMarcacao();
		
		campo12.reiniciar();
		
		assertFalse(campo.isAberto() && campo.isMinado() && campo.isMarcado());
		
	}
	
	@Test
	void testeToStringMarcado() {
		
		Campo campo12 = new Campo(1, 2);
		
		campo12.alternarMarcacao();
		
		assertEquals("x", campo12.toString());
		
	}
	
	@Test
	void testeToStringAbertoEMinado() {
		
		Campo campo12 = new Campo(1, 2);
		
		campo12.abrir();
		campo12.Minar();
		
		assertEquals("*", campo12.toString());
		
	}
	
	@Test
	void testeToStringAbertoEMinasVizinhanca() {
		
		Campo campo12 = new Campo(1, 2);
		Campo campo11 = new Campo(1, 1);
		Campo campo22 = new Campo(2, 2);
		Campo campo23 = new Campo(2, 3);
		
		campo22.adicionarVizinho(campo12);
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo23);
		
		campo23.Minar();
		campo11.Minar();
		campo12.Minar();
		campo22.abrir();
		
		assertEquals("3", campo22.toString());
		
	}
	
	@Test
	void testeToStringAberto() {
		
		Campo campo12 = new Campo(1, 2);
		
		campo12.abrir();
		
		assertEquals(" ", campo12.toString());
		
	}
	
	
	
	
	
	
	
	

}
