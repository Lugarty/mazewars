package model;

import service.BatalhaService;
import service.GerenciadorEfeitos;
import service.RegistroBatalhaInterface;

public class Main {
	public static void main(String[] args) {

		// ==============================================
		// 1. CRIAÇÃO DAS CRIATURAS
		// Parâmetros: nome, HP, ATK, DEF, velocidade, tipo elemental
		// ==============================================
		Criatura dragao = new Criatura("Dragão", 120, 25, 10, 5, TipoElemental.FOGO);
		Criatura sereia = new Criatura("Sereia", 100, 20, 5, 7, TipoElemental.AGUA);

		// ==============================================
		// 2. ADIÇÃO DE HABILIDADES ESPECIAIS
		// Parâmetros: nome da habilidade, tipo elemental, dano base,
		// efeito de status (tipo e duração em turnos),
		// chance de aplicar o efeito (0.0 a 1.0)
		// ==============================================
		dragao.adicionarHabilidade(new Habilidade("Sopro de Fogo", TipoElemental.FOGO, 20,
				new EfeitoStatus(EfeitoStatus.TipoEfeito.QUEIMADO, 3), // 3 turnos
				0.4 // 40% de chance de queimar
		));

		sereia.adicionarHabilidade(new Habilidade("Jato d'Água", TipoElemental.AGUA, 15,
				new EfeitoStatus(EfeitoStatus.TipoEfeito.CONGELADO, 2), // 2 turnos
				0.3 // 30% de chance de congelar
		));

		// ==============================================
		// 3. ADIÇÃO DE ITENS AO INVENTÁRIO
		// Parâmetros: nome, tipo do item, valor do efeito
		// Tipos: CURA (recupera HP), BUFF_DEFESA (aumenta DEF temporariamente)
		// ==============================================
		dragao.adicionarItem(new Item("Poção de Cura", Item.TipoItem.CURA, 20));
		sereia.adicionarItem(new Item("Elixir de Defesa", Item.TipoItem.BUFF_DEFESA, 5));

		// ==============================================
		// 4. CRIAÇÃO DO REGISTRADOR DE BATALHA
		// Aqui usamos uma implementação simples que apenas imprime no console.
		// Em um sistema real, poderia gravar em banco de dados ou arquivo de log.
		// ==============================================
		RegistroBatalhaInterface registro = new RegistroBatalhaInterface() {
			@Override
			public void registrar(String evento) {
				System.out.println("[LOG] " + evento);
			}
		};

		// ==============================================
		// 5. INICIALIZAÇÃO DOS SERVIÇOS DE BATALHA
		// - GerenciadorEfeitos: processa dano de status entre turnos
		// - BatalhaService: controla o fluxo da batalha
		// ==============================================
		GerenciadorEfeitos gerenciador = new GerenciadorEfeitos();
		BatalhaService batalha = new BatalhaService(registro, gerenciador);

		// ==============================================
		// 6. INÍCIO DA BATALHA
		// O sistema resolve automaticamente:
		// - Iniciativa (qual criatura ataca primeiro)
		// - Cálculo de dano com vantagem elemental
		// - Aplicação de efeitos de status
		// - Uso de itens (em versões futuras)
		// - Verificação de vitória/derrota
		// ==============================================
		System.out.println("=== MAZE WARS - BATALHA ===");
		Criatura vencedor = batalha.iniciarBatalha(dragao, sereia);

		// Exibe o grande vencedor!
		System.out.println("🏆 Vencedor: " + vencedor.getNome());
	}
}