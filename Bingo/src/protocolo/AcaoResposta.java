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
	NumeroSortiado(9),
	ParaJogo(10),
	ImpossivelEntrarJogo(11),
	AlarmeFalso(12),
	Errou(13),
	VoltaJogo(14),
	Erro(0);
	
	public int numero;
	AcaoResposta(int sta){
		this.numero = sta;
	}
	
}
