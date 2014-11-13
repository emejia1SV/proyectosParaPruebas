package sv.avantia.agregadores.hilo;

public class GrupoDeHilos extends ThreadGroup {

	public GrupoDeHilos(ThreadGroup parent, String name) {
		super(parent, name);
	}

	public GrupoDeHilos(String name) {
		super(name);
	}
}
