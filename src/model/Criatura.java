package model;

import java.util.ArrayList;
import java.util.List;

public class Criatura {
	private String nome;
	private int hp;
	private int maxHp;
	private int atk;
	private int def;
	private int velocidade;
	private TipoElemental tipo;
	private List<Habilidade> habilidades;
	private List<EfeitoStatus> efeitosAtivos;
	private List<Item> itens;

	public Criatura(String nome, int maxHp, int atk, int def, int velocidade, TipoElemental tipo) {
		this.nome = nome;
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.atk = atk;
		this.def = def;
		this.velocidade = velocidade;
		this.tipo = tipo;
		this.habilidades = new ArrayList<>();
		this.efeitosAtivos = new ArrayList<>();
		this.itens = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public int getHp() {
		return hp;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getAtk() {
		return atk;
	}

	public int getDef() {
		return def;
	}

	public int getVelocidade() {
		return velocidade;
	}

	public TipoElemental getTipo() {
		return tipo;
	}

	public List<Habilidade> getHabilidades() {
		return habilidades;
	}

	public List<EfeitoStatus> getEfeitosAtivos() {
		return efeitosAtivos;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public void adicionarHabilidade(Habilidade h) {
		habilidades.add(h);
	}

	public void adicionarItem(Item i) {
		itens.add(i);
	}

	public void receberDano(int dano) {
		int danoFinal = Math.max(0, dano - def);
		hp -= danoFinal;
		if (hp < 0)
			hp = 0;
	}

	public void curar(int quantidade) {
		hp = Math.min(maxHp, hp + quantidade);
	}

	public void aplicarEfeito(EfeitoStatus efeito) {
		for (EfeitoStatus e : efeitosAtivos) {
			if (e.getTipo() == efeito.getTipo()) {
				if (efeito.getDuracao() > e.getDuracao()) {
					efeitosAtivos.remove(e);
					efeitosAtivos.add(efeito);
				}
				return;
			}
		}
		efeitosAtivos.add(efeito);
	}

	public void processarEfeitos() {
		for (EfeitoStatus e : efeitosAtivos) {
			if (e.isAtivo()) {
				e.aplicarEfeito(this);
			}
		}
		efeitosAtivos.removeIf(e -> !e.isAtivo());
	}

	public void reduzirDuracaoEfeitos() {
		efeitosAtivos.forEach(EfeitoStatus::reduzirDuracao);
	}

	public boolean estaVivo() {
		return hp > 0;
	}

	public boolean podeAgir() {
		for (EfeitoStatus e : efeitosAtivos) {
			if (e.isAtivo() && (e.getTipo() == EfeitoStatus.TipoEfeito.CONGELADO
					|| e.getTipo() == EfeitoStatus.TipoEfeito.ATORDOADO)) {
				return false;
			}
		}
		return true;
	}
}