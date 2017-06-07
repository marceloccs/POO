package bd.dbos;

public class Cartela {
	private String cartela[][] = new String[5][5];
	private String selecionados[] = new String[25];
	private int quantidadeSelecionados=-1;
	
	public Cartela(String[][] cart) throws Exception{
		this.setCartela(cart);
	}

	private void setCartela(String[][] cart) throws Exception {
		if(cart==null)
			throw new Exception("A cartela é nulla");
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				if(cart[i][j]==null)
					throw new Exception("A cartela tem valores nullos");
			}
		}
		
		this.cartela = cart;
	}
	private void seleciona(String valor){
		this.quantidadeSelecionados++;
		selecionados[this.quantidadeSelecionados]=valor;
	}
	private void deSeleciona(String valor){
		//TODO:verifica e remove da array se encontrado
		this.quantidadeSelecionados++;
		selecionados[this.quantidadeSelecionados]=valor;
	}

}
