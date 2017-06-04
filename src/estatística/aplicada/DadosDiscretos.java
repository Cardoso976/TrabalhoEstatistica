package estatística.aplicada;

public class DadosDiscretos {

	private int tam;
	private float vet[] = new float[100];

	public void criaVetor() {
		tam = -1;
	}

	public boolean Vazia() {
		return tam == -1;
	}

	public boolean Cheia() {
		return tam == vet.length - 1;
	}

	public void ordenar(int n) {
		int aux;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (vet[j] > vet[j + 1] && j + 1 < n) {
					aux = (int) vet[j];
					vet[j] = vet[j + 1];
					vet[j + 1] = aux;
				}

			}
		}
	}

	public boolean adicionaDados(int num) {
		if (!Cheia()) {
			tam++;
			vet[tam] = num;
			return true;
		} else
			return false;

	}

	public double calculaMedia(int n) {
		double media = 0.00;
		for (int i = 0; i < this.vet.length; i++) {
			media += this.vet[i];
		}

		return media / n;
	}

	public float calculaMediana(int n) {
		this.ordenar(n);
		float mediana;
		if (n % 2 != 0) {
			return this.vet[n / 2];
		} else {
			int a, b;

			a = (n / 2) - 1;

			b = (n / 2);

			mediana = (float) (this.vet[a] + this.vet[b]) / 2;

			return mediana;
		}
	}

	public float calculaDesvio(int n) {
		float aux = 0;
		float x = (float) this.calculaMedia(n);
		for (int i = 0; i < n; i++) {
			vet[i] = (float) (vet[i] - x);
			vet[i] = vet[i] * vet[i];
			aux += vet[i];
		}
		return aux / (n - 1);

	}

}
