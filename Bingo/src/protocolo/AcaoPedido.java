package protocolo;

public enum AcaoPedido {
	NewUser(1),
	IniciarJogo(2),
	Ath(3),
	Ganhou(4),
	PegarTabelaJogos(5);
	
	public int numero;
	
	AcaoPedido(int sta){
		this.numero = sta;
	}
	
	AcaoPedido(AcaoPedido ap){
		this.numero = ap.getNumero();
	}
	
	public int getNumero(){
		return this.numero;
	}
	
}
