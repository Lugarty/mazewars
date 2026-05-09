package game;

public enum TipoElemental {
	FOGO, AGUA, TERRA, AR, LUZ, TREVAS;

	public double getMultiplicadorContra(TipoElemental defesa) {
		switch (this) {
		case FOGO:
			switch (defesa) {
			case AR:
				return 1.5;
			case TREVAS:
				return 1.3;
			case AGUA:
				return 0.7;
			case TERRA:
				return 0.8;
			default:
				return 1.0;
			}
		case AGUA:
			switch (defesa) {
			case FOGO:
				return 1.5;
			case TERRA:
				return 1.3;
			case AR:
				return 0.7;
			case TREVAS:
				return 0.8;
			default:
				return 1.0;
			}
		case TERRA:
			switch (defesa) {
			case AGUA:
				return 1.3;
			case LUZ:
				return 1.5;
			case FOGO:
				return 0.8;
			case AR:
				return 0.7;
			default:
				return 1.0;
			}
		case AR:
			switch (defesa) {
			case TERRA:
				return 1.5;
			case AGUA:
				return 1.3;
			case FOGO:
				return 0.7;
			case LUZ:
				return 0.8;
			default:
				return 1.0;
			}
		case LUZ:
			switch (defesa) {
			case TREVAS:
				return 2.0;
			case FOGO:
				return 1.3;
			case TERRA:
				return 0.7;
			case AGUA:
				return 0.8;
			default:
				return 1.0;
			}
		case TREVAS:
			switch (defesa) {
			case LUZ:
				return 1.5;
			case AGUA:
				return 1.3;
			case FOGO:
				return 0.7;
			case AR:
				return 0.8;
			default:
				return 1.0;
			}
		default:
			return 1.0;
		}
	}
}