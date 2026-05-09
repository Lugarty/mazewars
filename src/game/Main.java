package game;

public class Main {
	public static void main(String[] args) {
		Criatura dragao = new Criatura("Dragão", 120, 25, 10, 5, TipoElemental.FOGO);
		Criatura sereia = new Criatura("Sereia", 100, 20, 5, 7, TipoElemental.AGUA);

		dragao.adicionarHabilidade(new Habilidade("Sopro de Fogo", TipoElemental.FOGO, 20,
				new EfeitoStatus(EfeitoStatus.TipoEfeito.QUEIMADO, 3), 0.4));
		sereia.adicionarHabilidade(new Habilidade("Jato d'Água", TipoElemental.AGUA, 15,
				new EfeitoStatus(EfeitoStatus.TipoEfeito.CONGELADO, 2), 0.3));

		dragao.adicionarItem(new Item("Poção de Cura", Item.TipoItem.CURA, 20));
		sereia.adicionarItem(new Item("Elixir de Defesa", Item.TipoItem.BUFF_DEFESA, 5));

		RegistroBatalhaInterface registro = new RegistroBatalhaInterface() {
			@Override
			public void registrar(String evento) {
				System.out.println("[LOG] " + evento);
			}
		};
		GerenciadorEfeitos gerenciador = new GerenciadorEfeitos();
		BatalhaService batalha = new BatalhaService(registro, gerenciador);

		System.out.println("=== MAZE WARS - BATALHA ===");
		Criatura vencedor = batalha.iniciarBatalha(dragao, sereia);
		System.out.println("🏆 Vencedor: " + vencedor.getNome());
	}
}