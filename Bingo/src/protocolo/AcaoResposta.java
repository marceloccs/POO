package protocolo;

public enum AcaoResposta {
	NewUser(1),
	FinalizaJogo(2),
	Ganhador(3),
	Abortar(4),
	IniciarJogo(5),
	Ath(6),
	JogoAcabou(7),
	PegarTabela(8),
	Erro(0);
	
	public int numero;
	AcaoResposta(int sta){
		this.numero = sta;
	}
}