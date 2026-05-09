package test;

import org.junit.jupiter.api.Test;

import game.CalculadoraElemental;
import game.Criatura;
import game.Habilidade;
import game.TipoElemental;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraElementalTest {
	@Test
	void testVantagemFogoContraAr() {
		assertEquals(1.5, CalculadoraElemental.calcularMultiplicador(TipoElemental.FOGO, TipoElemental.AR), 0.001);
	}

	@Test
	void testDesvantagemFogoContraAgua() {
		assertEquals(0.7, CalculadoraElemental.calcularMultiplicador(TipoElemental.FOGO, TipoElemental.AGUA), 0.001);
	}

	@Test
	void testNeutroFogoContraFogo() {
		assertEquals(1.0, CalculadoraElemental.calcularMultiplicador(TipoElemental.FOGO, TipoElemental.FOGO), 0.001);
	}

	@Test
	void testDanoCalculadoComVantagem() {
		Criatura atacante = new Criatura("A", 100, 20, 5, 5, TipoElemental.FOGO);
		Criatura defensor = new Criatura("B", 100, 15, 5, 4, TipoElemental.AR);
		Habilidade hab = new Habilidade("Lança de Fogo", TipoElemental.FOGO, 10, null, 0);
		int dano = CalculadoraElemental.calcularDano(atacante, defensor, hab);
		assertEquals(37, dano);
	}

	@Test
	void testDanoNaoUltrapassaZeroComDefesaAlta() {
		Criatura atacante = new Criatura("A", 100, 10, 5, 5, TipoElemental.AGUA);
		Criatura defensor = new Criatura("B", 100, 5, 50, 1, TipoElemental.FOGO);
		Habilidade hab = new Habilidade("Bolha", TipoElemental.AGUA, 15, null, 0);
		int dano = CalculadoraElemental.calcularDano(atacante, defensor, hab);
		assertEquals(0, dano);
	}
}