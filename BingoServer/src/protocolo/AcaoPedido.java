package protocolo;

import bd.BD;
import bd.dbos.User;

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
		this.numero = ap.getnumero();
	}
	
	public int getnumero(){
		return this.numero;
	}
	public Object acionarAcao(Object obj){
		Object ret = null;
		try{
			switch(this.numero){
				case 1:
					try{
						AcaoResposta acao = AcaoResposta.NewUser;
						User u = new User(obj);
						BD.USERS.incluir(u);
						ret = new ProtocoloResposta(null,acao, "sucesso ao inserir usaurio", true);
					}catch(Exception e){
						AcaoResposta acao = AcaoResposta.Erro;
						ret = new ProtocoloResposta(null,acao, e.getMessage(), false);
					}
					break;
				case 2:
					break;
				case 3:
					User u1 = new User(obj);
					User aux;
					try{
						AcaoResposta acao = AcaoResposta.Ath;
						aux = BD.USERS.auth(u1.getEmail(), u1.getPassword());
						ret = new ProtocoloResposta(aux,acao, "sucesso ao atentificar usaurio", true);
					}catch(Exception e){
						AcaoResposta acao = AcaoResposta.Erro;
						ret = new ProtocoloResposta(null,acao, e.getMessage(), false);
					}
					break;
				case 4:
					break;
				case 5:
					try{
						AcaoResposta acao = AcaoResposta.PegarTabela;
						ret = new ProtocoloResposta(BD.JOGOS.pegarUltimoMes(),acao,"Sucesso ao recuperar a lista",true);
					}catch(Exception e){
						AcaoResposta acao = AcaoResposta.Erro;
						ret = new ProtocoloResposta(null,acao, e.getMessage(), false);
					}
					break;
			}
			
		}catch(Exception e){
			AcaoResposta acao = AcaoResposta.Erro;
			ret = new ProtocoloResposta(null,acao, e.getMessage(), false);
		}
		return ret;
	}
	
}