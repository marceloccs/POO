package protocolo;

import bd.BD;
import bd.dbos.Cartela;
import bd.dbos.User;
import programa.Programa;
import server.ServidorNovo;
import server.Sorteio;

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
					Cartela c = (Cartela) proto.getObj();
					ServidorNovo.pausaJogo();
					if(c.verificaNumeros(Sorteio.getNumeros())){
						//Ganhou!
						AcaoResposta acao = AcaoResposta.FinalizaJogo;
						ProtocoloResposta retAll = new ProtocoloResposta(null,AcaoResposta.Ganhador, "Ganhador do jogo foi: "+c.getDono().getNome(), true,null);
						ServidorNovo.enviaMensagemJogando(retAll);
						ret = new ProtocoloResposta (null,acao, "Voce ganhou!", true,proto.getIP());
					}else{
						//Errado!
						AcaoResposta acao = AcaoResposta.VoltaJogo;
						ProtocoloResposta retAll = new ProtocoloResposta(null,acao, "Alarme falso", true,null);
						ServidorNovo.enviaMensagemJogando(retAll);
						ret = new ProtocoloResposta (null,AcaoResposta.Errou, "Voce ainda não ganhou ganhou!", true,proto.getIP());
						ServidorNovo.voltaJogo();
					}
					
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
