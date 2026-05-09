package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import game.BatalhaService;
import game.Criatura;
import game.EfeitoStatus;
import game.GerenciadorEfeitos;
import game.Habilidade;
import game.RegistroBatalhaInterface;
import game.TipoElemental;

class BatalhaServiceTest {

	@Mock
	private RegistroBatalhaInterface registro;

	@Mock
	private GerenciadorEfeitos gerenciadorEfeitos;

	private BatalhaService batalhaService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		batalhaService = new BatalhaService(registro, gerenciadorEfeitos);
	}

	@Test
	void testCriaturaMaisRapidaAtacaPrimeiro() {
		Criatura rapida = new Criatura("Rápida", 50, 20, 5, 10, TipoElemental.AR);
		rapida.adicionarHabilidade(new Habilidade("Golpe Rápido", TipoElemental.AR, 10, null, 0));
		Criatura lenta = new Criatura("Lenta", 60, 25, 10, 3, TipoElemental.TERRA);
		lenta.adicionarHabilidade(new Habilidade("Pancada", TipoElemental.TERRA, 15, null, 0));

		doNothing().when(gerenciadorEfeitos).processarEfeitos(any(Criatura.class), eq(registro));

		Criatura vencedor = batalhaService.iniciarBatalha(rapida, lenta);

		assertEquals(rapida, vencedor);
		verify(registro, atLeastOnce()).registrar(contains("Vencedor"));
	}

	@Test
	void testBatalhaTerminaQuandoUmMorrePorEfeito() {
		Criatura a = new Criatura("A", 50, 30, 5, 8, TipoElemental.FOGO);
		a.adicionarHabilidade(new Habilidade("Fogo", TipoElemental.FOGO, 20, null, 0));
		Criatura b = new Criatura("B", 40, 20, 2, 5, TipoElemental.AGUA);
		b.adicionarHabilidade(new Habilidade("Água", TipoElemental.AGUA, 15, null, 0));

		doAnswer(invocation -> {
			Criatura criatura = invocation.getArgument(0);
			if (criatura == b) {
				criatura.receberDano(999);
			}
			return null;
		}).when(gerenciadorEfeitos).processarEfeitos(any(Criatura.class), eq(registro));

		Criatura vencedor = batalhaService.iniciarBatalha(a, b);

		assertFalse(b.estaVivo());
		assertEquals(a, vencedor, "A deveria vencer pois B morreu por efeito");
		verify(registro).registrar(contains("derrotado"));
	}

	@Test
	void testCriaturaCongeladaNaoAge() {
		Criatura a = new Criatura("A", 60, 15, 5, 7, TipoElemental.LUZ);
		a.adicionarHabilidade(new Habilidade("Luz", TipoElemental.LUZ, 20, null, 0));
		Criatura b = new Criatura("B", 70, 20, 8, 4, TipoElemental.TREVAS);
		b.adicionarHabilidade(new Habilidade("Sombra", TipoElemental.TREVAS, 20, null, 0));

		b.aplicarEfeito(new EfeitoStatus(EfeitoStatus.TipoEfeito.CONGELADO, 2));

		GerenciadorEfeitos realGerenciador = new GerenciadorEfeitos();
		batalhaService = new BatalhaService(registro, realGerenciador);

		Criatura vencedor = batalhaService.iniciarBatalha(a, b);

		assertEquals(60, a.getHp());
		assertEquals(a, vencedor);
		verify(registro, atLeastOnce()).registrar(contains("impossibilitado"));
	}
}