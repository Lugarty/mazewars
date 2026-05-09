package service;

import model.Criatura;
import model.Habilidade;
import model.TipoElemental;

public class CalculadoraElemental {
	public static double calcularMultiplicador(TipoElemental ataque, TipoElemental defesa) {
		return ataque.getMultiplicadorContra(defesa);
	}

	public static int calcularDano(Criatura atacante, Criatura defensor, Habilidade habilidade) {
		double mult = calcularMultiplicador(habilidade.getTipo(), defensor.getTipo());
		int danoBruto = atacante.getAtk() + habilidade.getDanoBase() - defensor.getDef();
		int danoFinal = (int) Math.max(0, danoBruto * mult);
		return danoFinal;
	}
}