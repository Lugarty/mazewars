package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.Criatura;
import model.EfeitoStatus;
import model.TipoElemental;

class CriaturaTest {

	@Test
	void testReceberDanoNormal() {
		Criatura c = new Criatura("Dragão", 100, 30, 10, 5, TipoElemental.FOGO);
		c.receberDano(40);
		assertEquals(70, c.getHp());
	}

	@Test
	void testDanoNaoNegativo() {
		Criatura c = new Criatura("Slime", 50, 5, 20, 2, TipoElemental.AGUA);
		c.receberDano(10);
		assertEquals(50, c.getHp());
	}

	@Test
	void testCuraNaoUltrapassaMaxHp() {
		Criatura c = new Criatura("Elfo", 80, 15, 8, 7, TipoElemental.AR);
		c.receberDano(50);
		c.curar(100);
		assertEquals(80, c.getHp());
	}

	@Test
	void testAplicarEfeitoSubstituiPorMaiorDuracao() {
		Criatura c = new Criatura("Golem", 120, 40, 30, 1, TipoElemental.TERRA);
		EfeitoStatus queimadoCurto = new EfeitoStatus(EfeitoStatus.TipoEfeito.QUEIMADO, 2);
		EfeitoStatus queimadoLongo = new EfeitoStatus(EfeitoStatus.TipoEfeito.QUEIMADO, 5);
		c.aplicarEfeito(queimadoCurto);
		c.aplicarEfeito(queimadoLongo);
		assertEquals(1, c.getEfeitosAtivos().size());
		assertEquals(5, c.getEfeitosAtivos().get(0).getDuracao());
	}

	@Test
	void testPodeAgirCongelado() {
		Criatura c = new Criatura("Yeti", 90, 25, 10, 4, TipoElemental.AGUA);
		c.aplicarEfeito(new EfeitoStatus(EfeitoStatus.TipoEfeito.CONGELADO, 2));
		assertFalse(c.podeAgir());

		c.reduzirDuracaoEfeitos();
		assertFalse(c.podeAgir(), "Duração 1: ainda congelado, não pode agir");
	}
}