package protocolo;

import bd.BD;
import bd.dbos.User;
import programa.IniciadorJogo;
import programa.Programa;

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
	public ProtocoloResposta acionarAcao(ProtocoloPedido proto){
		ProtocoloResposta ret = null;
		try{
			switch(proto.getAcao().getnumero()){
				case 1:
					try{
						AcaoResposta acao = AcaoResposta.NewUser;
						User u = new User((User) proto.getObj());
						BD.USERS.incluir(u);
						ret = new ProtocoloResposta(null,acao, "sucesso ao inserir usuario", true,proto.getIP());
					}catch(Exception e){
						AcaoResposta acao = AcaoResposta.Erro;
						ret = new ProtocoloResposta(null,acao, e.getMessage(), false,proto.getIP());
					}
					break;
				case 2:
					try{
						//IniciadorJogo jogo = new IniciadorJogo();
						//int porta = jogo.iniciaJogo();
						AcaoResposta acao = AcaoResposta.IniciarJogo;
						ret = new ProtocoloResposta(null,acao, "JogoIniciando", true,proto.getIP());
					}catch(Exception e){
						AcaoResposta acao = AcaoResposta.Erro;
						ret = new ProtocoloResposta(null,acao, e.getMessage(), false,proto.getIP());
					}
					break;
				case 3:
					User u1 = new User(proto.getObj());
					User aux;
					try{
						AcaoResposta acao = AcaoResposta.Ath;
						aux = BD.USERS.auth(u1.getEmail(), u1.getPassword());
						ret = new ProtocoloResposta(aux,acao, "sucesso ao atentificar usaurio", true,proto.getIP());
					}catch(Exception e){
						AcaoResposta acao = AcaoResposta.Erro;
						ret = new ProtocoloResposta(null,acao, e.getMessage(), false,proto.getIP());
					}
					break;
				case 4:
					break;
				case 5:
					try{
						AcaoResposta acao = AcaoResposta.PegarTabela;
						ret = new ProtocoloResposta(BD.JOGOS.pegarUltimoMes(),acao,"Sucesso ao recuperar a lista",true,proto.getIP());
					}catch(Exception e){
						AcaoResposta acao = AcaoResposta.Erro;
						ret = new ProtocoloResposta(null,acao, e.getMessage(), false,proto.getIP());
					}
					break;
			}
			
		}catch(Exception e){
			AcaoResposta acao = AcaoResposta.Erro;
			ret = new ProtocoloResposta(null,acao, e.getMessage(), false,proto.getIP());
		}
		return ret;
	}
	
}
