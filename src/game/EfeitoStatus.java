package game;

public class EfeitoStatus {
	public enum TipoEfeito {
		QUEIMADO, CONGELADO, ENVENENADO, ATORDOADO
	}

	private TipoEfeito tipo;
	private int duracao;

	public EfeitoStatus(TipoEfeito tipo, int duracao) {
		this.tipo = tipo;
		this.duracao = duracao;
	}

	public TipoEfeito getTipo() {
		return tipo;
	}

	public int getDuracao() {
		return duracao;
	}

	public void reduzirDuracao() {
		if (duracao > 0)
			duracao--;
	}

	public boolean isAtivo() {
		return duracao > 0;
	}

	public void aplicarEfeito(Criatura alvo) {
		switch (tipo) {
		case QUEIMADO:
			int dano = (int) (alvo.getMaxHp() * 0.05);
			alvo.receberDano(dano);
			break;
		case ENVENENADO:
			alvo.receberDano(8);
			break;
		case CONGELADO:
		case ATORDOADO:
			break;
		}
	}
}