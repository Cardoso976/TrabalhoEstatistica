package estatística.aplicada;

import java.util.Scanner;

public class Principal {
	public static Scanner leia;

	public static void main(String[] args) {
		leia = new Scanner(System.in);
		int key=0;

		do {
			System.out.println("1. Dados Discretos Não Tabelados.\n2. Dados Discretos Tabelados\n3. Dados Contínuos.\n4. Sair.");
			key = 0;
			key = leia.nextInt();

			switch (key) {
			case 1:
				DadosDiscretos d = new DadosDiscretos();
				d.criaVetor();

				System.out.println("Digite o Nº de entradas: ");
				int n = leia.nextInt();
				System.out.println("Digite os numeros ->");

				for (int i = 0; i < n; i++) {
					d.adicionaDados(leia.nextInt());
				}

				System.out.printf("Média = %.2f\n", d.calculaMedia(n));

				System.out.printf("Mediana = %.2f\n", d.calculaMediana(n));

				System.out.printf("Desvio Padrão = %.2f\n", d.calculaDesvio(n));

				break;
				
			case 2:
				
				System.out.println("Digite o numero de entradas ->");
				int n2 = leia.nextInt();
				System.out.println("Digite os dados ->");
				int dado1;
				DadosDiscretosTabelados ddt = new DadosDiscretosTabelados();
				for(int i = 0; i<n2; i++){
					dado1 = leia.nextInt();
					ddt.setDados(dado1, n2);
				}
				leia.nextLine();
				
				
				ddt.montarTabela();
				ddt.setFac();
				ddt.setMedia(n2);
				ddt.setVariancia(n2);
				ddt.setMediana(n2);
				ddt.getTabela(n2);
				System.out.printf("Media -> %.2f\n", ddt.getMedia());
				System.out.printf("Mediana -> %.2f\n", ddt.getMediana());
				System.out.printf("Desvio Padrão -> %.2f\n", ddt.desvioPadrao());
				
				break;

			case 3:

				System.out.println("Digite o numero de entradas ->");
				int n1 = leia.nextInt();
				System.out.println("Digite os dados ->");
				int dado;
				Classes cl = new Classes();
				for (int i = 0; i < n1; i++) {
					dado = leia.nextInt();					
					cl.setDados(dado, n1);
				}
				leia.nextLine();
				cl.setK(n1);
				cl.setMaior(n1);
				cl.setMenor(n1);
				cl.setAt();
				cl.comprimentoClasse(n1);
				cl.montarTabela();
				cl.setFi();
				cl.setXi();
				cl.setFac();
				cl.setMedia(n1);
				cl.setMediana(n1);
				cl.setVariancia(n1);
				cl.getTabela(n1);
				System.out.println();
				System.out.printf("Media -> %.2f\n", cl.getMedia());
				System.out.printf("Mediana -> %.2f\n", cl.getMediana());
				System.out.printf("Desvio Padrão -> %.2f\n", cl.desvioPadrao());
				System.out.println();
				break;
			}
		} while (key != 4);

	}

}
