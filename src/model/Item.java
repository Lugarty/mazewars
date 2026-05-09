package model;

public class Item {
	public enum TipoItem {
		CURA, BUFF_ATAQUE, BUFF_DEFESA
	}

	private String nome;
	private TipoItem tipo;
	private int valor;

	public Item(String nome, TipoItem tipo, int valor) {
		this.nome = nome;
		this.tipo = tipo;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public TipoItem getTipo() {
		return tipo;
	}

	public int getValor() {
		return valor;
	}

	public void usar(Criatura criatura) {
		switch (tipo) {
		case CURA:
			criatura.curar(valor);
			break;
		case BUFF_ATAQUE:
			criatura.setAtk(criatura.getAtk() + valor);
			break;
		case BUFF_DEFESA:
			criatura.setDef(criatura.getDef() + valor);
			break;
		}
	}
}