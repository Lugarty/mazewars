package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import game.Criatura;
import game.EfeitoStatus;
import game.GerenciadorEfeitos;
import game.RegistroBatalhaInterface;
import game.TipoElemental;

class GerenciadorEfeitosTest {

	@Mock
	private RegistroBatalhaInterface registro;

	private GerenciadorEfeitos gerenciador;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		gerenciador = new GerenciadorEfeitos();
	}

	@Test
	void testQueimaduraCausaDanoERegistra() {
		Criatura c = new Criatura("Fenix", 100, 20, 0, 6, TipoElemental.FOGO);
		c.aplicarEfeito(new EfeitoStatus(EfeitoStatus.TipoEfeito.QUEIMADO, 3));
		int hpAntes = c.getHp(); // 100

		gerenciador.processarEfeitos(c, registro);

		assertEquals(hpAntes - 5, c.getHp(), "HP deveria cair 5 pontos");
		verify(registro, atLeastOnce()).registrar(contains("sofreu"));
	}

	@Test
	void testCriaturaMorreDuranteEfeito() {
		Criatura c = new Criatura("Moribundo", 5, 5, 0, 2, TipoElemental.TREVAS);
		c.aplicarEfeito(new EfeitoStatus(EfeitoStatus.TipoEfeito.ENVENENADO, 2));

		gerenciador.processarEfeitos(c, registro);

		assertFalse(c.estaVivo(), "Criatura deveria estar morta após veneno");
		verify(registro).registrar(contains("derrotado"));
	}

	@Test
	void testEfeitoExpiradoNaoAplicaDano() {
		Criatura c = new Criatura("Pedra", 80, 10, 0, 1, TipoElemental.TERRA);
		EfeitoStatus veneno = new EfeitoStatus(EfeitoStatus.TipoEfeito.ENVENENADO, 1);
		c.aplicarEfeito(veneno);

		gerenciador.processarEfeitos(c, registro);
		int hpAposPrimeiro = c.getHp();

		gerenciador.processarEfeitos(c, registro);
		assertEquals(hpAposPrimeiro, c.getHp(), "HP não deve mudar no segundo processamento");

		verify(registro, never()).registrar(contains("derrotado"));
	}
}