package visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import excecao.ExplosaoException;
import excecao.SairException;
import modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();
	}

	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				cicloDoJogo();
				
				System.out.println("Outra partida? (S/N) ");
				String resposta = entrada.nextLine();
				
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else {
					tabuleiro.reiniciar();
				}
				
			}
			
			
		} catch (SairException exit) {
			System.out.println("Fechando jogo!!");
		} finally {
			entrada.close();
		}
		
	}

	private void cicloDoJogo() {
		try {
			
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);
				
				String digitado = capturarValorDigitado("Digite (x, y): "); 
				
				Iterator<Integer> coordenadas = Arrays.stream(digitado.split(","))
					.map(e -> Integer.parseInt(e.trim())).iterator();
				
				digitado = capturarValorDigitado("1 - Abrir ou 2 - alterar marcação (desmarcar ou marcar): ");
				
				if("1".equals(digitado)) {
					tabuleiro.abrir(coordenadas.next(), coordenadas.next());
				}else if("2".equals(digitado)) {
					tabuleiro.alternarMarcacao(coordenadas.next(), coordenadas.next());
				}
				
			}
			
			System.out.println("Você ganhou!!");
		} catch(ExplosaoException explosion) {
			System.out.println("Você perdeu!!");
		}

	}
	
	private String capturarValorDigitado(String texto) {
		System.out.println(texto);
		String digitado = entrada.nextLine();
		
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();		
		}
		
		return digitado;
	}
	
}
