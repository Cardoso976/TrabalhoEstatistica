package estatística.aplicada;

import java.text.DecimalFormat;

public class Classes {
	private DadosContinuos dados[] = new DadosContinuos[100];
	private float c;
	private float K;
	private Tabela tab[] = new Tabela[100];
	private int At;
	private int maiorValor;
	private int menorValor;
	private int cont = 0;
	private float Media;
	private float Mediana;
	private int faca;
	private float variancia;
	private float varianciaTotal;

	public void setMaior(int n) {
		maiorValor = dados[0].getDado();
		for (int i = 0; i < cont; i++) {
			if (maiorValor < dados[i].getDado()) {
				maiorValor = dados[i].getDado();
			}
		}
	}

	public int getMaior() {
		return maiorValor;
	}

	public void setMenor(int n) {
		menorValor = dados[0].getDado();
		for (int i = 0; i < cont; i++) {
			if (menorValor > dados[i].getDado()) {
				menorValor = dados[i].getDado();
			}
		}
	}

	public int getMenor() {
		return menorValor;
	}

	public int getAt() {
		return At;
	}

	public void setAt() {
		At = maiorValor - menorValor;
	}

	public void comprimentoClasse(int n) {
		c = (float) (At / this.getK());
		int parteInteira = (int) c;
		float parteFracionaria = c - parteInteira;
		if (parteFracionaria > 0.5) {
			c = (int) c + 1;
		} else {
			c = parteInteira;
		}
	}

	public void setK(int n) {
		K = (int) Math.sqrt(n);
	}

	public float getK() {
		return K;
	}

	public void setDados(int dado, int n) {
		boolean aff = false;
		dados[cont] = new DadosContinuos();
		if (cont == 0) {
			dados[cont].setDado(dado);
			cont++;
		} else {
			for (int i = 0; i < cont; i++) {
				if (dados[i].getDado() == dado) {
					dados[i].setF();
					aff = true;
				}
			}
			if (aff != true) {
				dados[cont].setDado(dado);
				cont++;
			}
		}
	}

	public int getCont() {
		return cont;
	}

	public void montarTabela() {
		int aux = menorValor;
		for (int i = 0; i <= (int) K; i++) {
			tab[i] = new Tabela();
			if (i == 0) {
				tab[i].setLi(aux);
				tab[i].setLs(aux + (int) c);
			} else {
				aux += c;
				tab[i].setLi(aux);
				tab[i].setLs(aux + (int) c);
			}

		}
	}

	public void setXi() {
		for (int i = 0; i <= (int) K; i++) {
			tab[i].setXi((float) (tab[i].getLs() + tab[i].getLi()) / 2);

		}
	}

	public void setFac() {
		for (int i = 0; i <= (int) K; i++) {
			if (i == 0) {
				tab[i].setFac(tab[i].getFi());
			} else {
				tab[i].setFac(tab[i - 1].getFac() + tab[i].getFi());
			}
		}
	}

	public void setFi() {
		int aux = 0;
		for (int i = 0; i <= (int) K; i++) {
			for (int j = 0; j < cont; j++) {
				if (dados[j].getDado() >= tab[i].getLi() && dados[j].getDado() < tab[i].getLs()) {
					aux += dados[j].getF();
				}
			}
			tab[i].setFi(aux);
			aux = 0;
		}

	}

	public void getFi() {
		for (int i = 0; i < K; i++) {
			System.out.println(tab[i].getFi());
		}
	}

	public void setMedia(int n) {
		for (int i = 0; i <= (int) K; i++) {
			tab[i].setXifi((float) (tab[i].getXi() * tab[i].getFi()));
			Media += (tab[i].getXifi());
		}
		Media = Media / n;
	}
	
	public float getMedia(){
		return Media;
	}

	public void setMediana(int n) {
		float j = (float) n / 2;
		int aux = 0;
		for (int i = 0; i <= (int) K; i++) {
			if (j <= tab[i].getFac() && i != 0) {
				faca = tab[i - 1].getFac();
				aux = i;
				break;
			}
		}

		Mediana = (tab[aux].getLi()) + ((j - faca) * c) / tab[aux].getFi();
	}

	public float getMediana() {
		return Mediana;
	}

	public void setVariancia(int n) {
		variancia = 0;
		for (int i = 0; i <= (int) K; i++) {
			tab[i].setVariancia(((tab[i].getXi() - this.Media) * (tab[i].getXi() - this.Media)) * tab[i].getFi());
			varianciaTotal += ((tab[i].getXi() - this.Media) * (tab[i].getXi() - this.Media)) * tab[i].getFi();
		}
		variancia = varianciaTotal / n;
	}

	public float desvioPadrao() {
		return (float) Math.sqrt(variancia);
	}

	public void getTabela(int n) {
		System.out.println("    Dados     |   Xi   |   Fi   |   Fac  |  (Xi-Media)²*Fi  ");
		for (int i = 0; i <= (int) K; i++) {
			if (tab[i].getLi() >= 100) {
				DecimalFormat df = new DecimalFormat("#00.0");
				System.out.print(" " + df.format(tab[i].getLi()) + "|-" + df.format(tab[i].getLs()));
			} else {
				DecimalFormat df = new DecimalFormat("#00.00");
				System.out.print(" " + df.format(tab[i].getLi()) + "|-" + df.format(tab[i].getLs()));
			}
			if (tab[i].getXi() >= 100) {
				DecimalFormat df = new DecimalFormat("#00.0");
				System.out.print(" |  " + df.format(tab[i].getXi()));
			} else {
				DecimalFormat df = new DecimalFormat("#00.00");
				System.out.print(" |  " + df.format(tab[i].getXi()));
			}
			if (tab[i].getFi() >= 100) {
				DecimalFormat df = new DecimalFormat("#00.0");
				System.out.print(" |  " + df.format(tab[i].getFi()));
			} else {
				DecimalFormat df = new DecimalFormat("#00.00");
				System.out.print(" |  " + df.format(tab[i].getFi()));
			}
			if (tab[i].getFac() >= 100) {
				DecimalFormat df = new DecimalFormat("#00.0");
				System.out.print(" |  " + df.format(tab[i].getFac()));
			} else {
				DecimalFormat df = new DecimalFormat("#00.00");
				System.out.print(" |  " + df.format(tab[i].getFac()));
			}
			if (tab[i].getVariancia() >= 100) {
				DecimalFormat df = new DecimalFormat("#00.0");
				System.out.print(" |     " + df.format(tab[i].getVariancia()) + "\n");
			} else {
				DecimalFormat df = new DecimalFormat("#00.00");
				System.out.print(" |     " + df.format(tab[i].getVariancia()) + "\n");
			}

		}
		DecimalFormat df = new DecimalFormat("#00.0");
		System.out.println("    Total     |        |  "+df.format(n)+"  |        |     "+df.format(varianciaTotal));

	}

}
