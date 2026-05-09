package game;

public class GerenciadorEfeitos {
	public void processarEfeitos(Criatura criatura, RegistroBatalhaInterface registro) {
		if (!criatura.estaVivo())
			return;
		for (EfeitoStatus e : criatura.getEfeitosAtivos()) {
			if (e.isAtivo()) {
				int hpAntes = criatura.getHp();
				e.aplicarEfeito(criatura);
				int hpDepois = criatura.getHp();
				if (hpDepois < hpAntes) {
					registro.registrar(
							criatura.getNome() + " sofreu " + (hpAntes - hpDepois) + " de dano por " + e.getTipo());
				}
				if (!criatura.estaVivo()) {
					registro.registrar(criatura.getNome() + " foi derrotado!");
					break;
				}
			}
		}
		criatura.reduzirDuracaoEfeitos();
		criatura.getEfeitosAtivos().removeIf(e -> !e.isAtivo());
	}
}