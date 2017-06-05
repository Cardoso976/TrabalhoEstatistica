package estatística.aplicada;

import java.text.DecimalFormat;

public class DadosDiscretosTabelados {
	private DadosContinuos dd[] = new DadosContinuos[100];
	private Tabela tab[] = new Tabela[100];
	private float variancia;
	private float varianciaTotal;
	private float Media;
	private float Mediana;
	private int cont = 0;

	public void setDados(int dado, int n) {
		boolean aff = false;
		dd[cont] = new DadosContinuos();
		if (cont == 0) {
			dd[cont].setDado(dado);
			cont++;
		} else {
			for (int i = 0; i < cont; i++) {
				if (dd[i].getDado() == dado) {
					dd[i].setF();
					aff = true;
				}
			}
			if (aff != true) {
				dd[cont].setDado(dado);
				cont++;
			}
		}
	}

	public void getDados() {
		for (int i = 0; i < cont; i++) {
			System.out.println(dd[i].getDado());
		}
	}

	public void montarTabela() {
		for (int i = 0; i < cont; i++) {
			tab[i] = new Tabela();
			tab[i].setXi(dd[i].getDado());
			tab[i].setFi(dd[i].getF());
		}
	}

	public void setFac() {
		for (int i = 0; i < cont; i++) {
			if (i == 0) {
				tab[i].setFac(tab[i].getFi());
			} else {
				tab[i].setFac(tab[i - 1].getFac() + tab[i].getFi());
			}
		}
	}

	public void setMedia(int n) {
		for (int i = 0; i < cont; i++) {
			tab[i].setXifi((float) (tab[i].getXi() * tab[i].getFi()));
			Media += (tab[i].getXifi());
		}
		Media = Media / n;
	}

	public float getMedia() {
		return Media;
	}

	public void setVariancia(int n) {
		variancia = 0;
		for (int i = 0; i < cont; i++) {
			tab[i].setVariancia(((tab[i].getXi() - this.Media) * (tab[i].getXi() - this.Media)) * tab[i].getFi());
			varianciaTotal += ((tab[i].getXi() - this.Media) * (tab[i].getXi() - this.Media)) * tab[i].getFi();
		}
		variancia = varianciaTotal / n;
	}

	public float desvioPadrao() {
		return (float) Math.sqrt(variancia);
	}

	public void setMediana(int n) {
		float j = (float) (n + 1) / 2;
		for (int i = 0; i < cont; i++) {
			if (j <= tab[i].getFac() && i != 0) {
				Mediana = tab[i].getXi();
				break;
			}
		}
	}

	public float getMediana() {
		return Mediana;
	}

	public void getTabela(int n) {
		System.out.println("  Xi  |   Fi   |   Fac  |  (Xi-Media)²*Fi  ");
		for (int i = 0; i < cont; i++) {
			if (tab[i].getXi() >= 100) {
				DecimalFormat df = new DecimalFormat("#00.0");
				System.out.print(df.format(tab[i].getXi()));
			} else {
				DecimalFormat df = new DecimalFormat("#00.00");
				System.out.print(df.format(tab[i].getXi()));
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
		DecimalFormat df = new DecimalFormat("#00.00");
		System.out
				.println("Total |        |  " + df.format(n) + " |     " + df.format(varianciaTotal));

	}

}
