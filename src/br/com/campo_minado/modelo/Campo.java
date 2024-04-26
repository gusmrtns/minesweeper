package modelo;

import java.util.ArrayList;
import java.util.List;

import excecao.ExplosaoException;

public class Campo {

	private final int linha;
	private final int coluna;
	
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;
	
	private List<Campo> vizinhos = new ArrayList<>();
	
	public Campo(int linha, int coluna) {
		
		this.linha = linha;
		this.coluna = coluna;
		
	}
	
	// a diferença da posição da matriz dos vizinhos é sempre 1 ou 2
	// essa é a logica que iremos usar para delimitar vizinhos
	public boolean adicionarVizinho(Campo vizinho) {
		// descobrindo se o vizinho está numa linha ou coluna diferente ou na diagonal
		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		// Deltas verificadores de autenticidade de vizinhança
		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		// caso o candidato de vizinho não esteja na diagonal, o valor tem que ser igual a 1
		// caso esteja na diagonal esse valor tem que ser 2
		int deltaGeral = deltaColuna + deltaLinha;
		
		// se o delta geral for 1 e ele não está na diagonal, vizinho de linha ou coluna
		if(deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		} // se o delta geral for 2 e esta na diagonal, vizinho de diagonal 
		else if(deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}
		
	}
	
	// tirar ou colocar marcação de bomba num campo
	public void alternarMarcacao() {
		// ao realizar a ação testar se o campo já não está aberto, se não estiver marcar
		if(!aberto) {
			marcado = !marcado;
		}
		
	}
	
	// abrir o campo
	public boolean abrir() {
		// testar se o campo está fechado e não está marcado, se estivar marcado não poderá ser aberto
		if(!aberto && !marcado) {
			aberto = true;
			
			// testar se tem bomba
			if(minado) {
				throw new ExplosaoException();
			}
			
			if(vizinhancaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			
			return true;
			
		} else {
			return false;
		}
	}
	
	boolean vizinhancaSegura() {
		
		return vizinhos.stream().noneMatch(v -> v.minado);
		
	}
	
	public void Minar() {
		
		minado = true;
		
	}
	
	public boolean isMarcado() {
		return marcado;
	}
	
}













