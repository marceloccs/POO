/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirinto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

/**
 *Classe que possui como objetivo ler o arquivo e reproduzir o arqui em multiplos formatos de String
 * 
 * @author Ultron
 */
public class LeitorArquivo {
    private String fileName;
    private Vector<String> conteudo;
    
    public LeitorArquivo(String fileName) throws Exception{
        this.setFileName(fileName);
    }
    /**
     * Passe o caminho do arquivo em uma String com o caminho do arquivo
     * 
     * @param String fileName
     * @throws Exception nome nullo
     */
    public void setFileName(String fileName) throws Exception{
        if(fileName==null)
            throw new Exception("o nome do arquivo não pode ser nullo");
        /*if(fileName.contains(".txt"))
            throw new Exception("Somente é aceito aquivos textos");*/
        this.fileName=fileName;
        this.arquivoRead();
    }
    
    /**
     * Le cada linha do arquivo e coloca num vector de String
     * 
     * @throws Exception Erro ao encontrar arquivo
     */
    
    public void arquivoRead() throws Exception{
            BufferedReader br = null;
            FileReader fr = null;
            Vector <String> ret;

		try {

			fr = new FileReader(this.fileName);
			br = new BufferedReader(fr);

			String sCurrentLine;

			//br = new BufferedReader(new FileReader(this.fileName));
                        int i =0;
                        ret = new Vector<String>();
			while ((sCurrentLine = br.readLine()) != null) {
				ret.add(sCurrentLine); //(sCurrentLine);
                                i++;
			}
                        //while(br.redy());

		} catch (IOException e) {

                    throw new Exception("não foi possivel ler o arquivo!");

		}
        this.conteudo = ret;
    }
    
    /**
     * Uma função que retorna um vetor de string com as linhas do textos
     * 
     * @return Vector <string> com o retorno completo
     */
    public Vector getConteudo(){
        return this.conteudo;
    }
    /**
     * Uma função que retorna em formato String[] o valor de cada linha do arquivo lido
     * 
     * @return String[] com os valores de cada linha
     */
    public String[] getConteudoVectorString(){
        String[] ret = new String [this.conteudo.size()];
        
        for (int i =0;i<this.conteudo.size();i++){
            ret[i]=this.conteudo.get(i);
        }
        
        return ret;
    }
    /**
     * retorna uma string corrida intdentica com o arquivo lido
     * 
     * @return String com tudo lido no arquivo
     */
    public String getConteudoString(){
        String ret = "";
        
        for (int i =0;i<this.conteudo.size();i++){
            ret+="\n"+this.conteudo.get(i);
        }
        
        return ret;
    }
    
    public String toString(){
        return "Local do arquivo: "+this.fileName+"\n"
        + "Numero de linhas: "+this.conteudo.size()+"\n"
        + "Arquivo Lido: "+this.getConteudoString();
    }
    
    public int hashCode(){
        int ret=666;
        ret = ret * 7 + this.conteudo.hashCode();
        ret = ret * 7 + this.fileName.hashCode();
        return ret;
    }
    
    public boolean equals(Object obj){
        if(obj ==null)
            return false;
        if(obj==this)
            return false;
        if(obj.getClass() != this.getClass())
            return false;
        LeitorArquivo aux = (LeitorArquivo)obj;
        if(aux.conteudo.equals(aux.conteudo))
            return false;
        if(aux.fileName.equals(aux.fileName))
            return false;
        
        return true;
    }
    
    public LeitorArquivo clone(){
        LeitorArquivo ret =null;
        try{
            ret = new LeitorArquivo(this);
        }catch(Exception e){}
        
        return ret;
    }
    
    public LeitorArquivo(LeitorArquivo lei)throws Exception{
        if(lei==null)
            throw new Exception("objeto não pode ser nulo");
        this.conteudo=lei.conteudo;
        this.fileName=lei.fileName;
    }
}
