package game;

public class BatalhaService {
	private RegistroBatalhaInterface registro;
	private GerenciadorEfeitos gerenciadorEfeitos;

	public BatalhaService(RegistroBatalhaInterface registro, GerenciadorEfeitos gerenciadorEfeitos) {
		this.registro = registro;
		this.gerenciadorEfeitos = gerenciadorEfeitos;
	}

	public Criatura iniciarBatalha(Criatura a, Criatura b) {
		registro.registrar("Início da batalha entre " + a.getNome() + " e " + b.getNome());
		while (a.estaVivo() && b.estaVivo()) {
			gerenciadorEfeitos.processarEfeitos(a, registro);
			if (!a.estaVivo()) {
				registro.registrar(a.getNome() + " foi derrotado!");
				break;
			}
			gerenciadorEfeitos.processarEfeitos(b, registro);
			if (!b.estaVivo()) {
				registro.registrar(b.getNome() + " foi derrotado!");
				break;
			}

			Criatura[] ordem = { a, b };
			if (b.getVelocidade() > a.getVelocidade()) {
				ordem[0] = b;
				ordem[1] = a;
			}

			for (Criatura agente : ordem) {
				if (!agente.estaVivo())
					continue;
				if (!agente.podeAgir()) {
					registro.registrar(agente.getNome() + " está impossibilitado de agir.");
					continue;
				}

				Criatura alvo = (agente == a) ? b : a;
				if (!agente.getHabilidades().isEmpty()) {
					Habilidade hab = agente.getHabilidades().get(0);
					int dano = CalculadoraElemental.calcularDano(agente, alvo, hab);
					alvo.receberDano(dano);
					registro.registrar(agente.getNome() + " usou " + hab.getNome() + " em " + alvo.getNome()
							+ " causando " + dano + " de dano.");

					if (hab.getEfeito() != null && hab.ativarEfeito()) {
						alvo.aplicarEfeito(new EfeitoStatus(hab.getEfeito().getTipo(), hab.getEfeito().getDuracao()));
						registro.registrar(alvo.getNome() + " recebeu efeito " + hab.getEfeito().getTipo());
					}

					if (!alvo.estaVivo()) {
						registro.registrar(alvo.getNome() + " foi derrotado!");
						break;
					}
				}
			}
			registro.registrar(
					"Fim do turno. HP " + a.getNome() + ": " + a.getHp() + ", HP " + b.getNome() + ": " + b.getHp());
		}

		Criatura vencedor = a.estaVivo() ? a : b;
		registro.registrar("Vencedor: " + vencedor.getNome());
		return vencedor;
	}
}