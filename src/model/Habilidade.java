package model;

public class Habilidade {
	private String nome;
	private TipoElemental tipo;
	private int danoBase;
	private EfeitoStatus efeito;
	private double chanceEfeito;

	public Habilidade(String nome, TipoElemental tipo, int danoBase, EfeitoStatus efeito, double chanceEfeito) {
		this.nome = nome;
		this.tipo = tipo;
		this.danoBase = danoBase;
		this.efeito = efeito;
		this.chanceEfeito = chanceEfeito;
	}

	public String getNome() {
		return nome;
	}

	public TipoElemental getTipo() {
		return tipo;
	}

	public int getDanoBase() {
		return danoBase;
	}

	public EfeitoStatus getEfeito() {
		return efeito;
	}

	public double getChanceEfeito() {
		return chanceEfeito;
	}

	public boolean ativarEfeito() {
		return Math.random() < chanceEfeito;
	}
}