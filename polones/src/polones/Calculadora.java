/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polones;
import static java.lang.Math.exp;
import java.util.StringTokenizer;
import polones.Pilha;
import polones.Fila;

/**
 *
 * @author Ultron
 */
public class Calculadora {
    private Pilha <String> pil;//pilha de organização
    private Pilha <Double> calculadora;//pilha de calculo
    private Fila <String> fil;//good
    private double resultado;
    private String [] pedacos;
    static Tabela tabela = new Tabela();
    static Operadores calculador = new Operadores();
    private String valorPassado;
    private String filaOrganizada;
    
    public Calculadora(String metodoInfixo) throws Exception{
        if((metodoInfixo==null)||(metodoInfixo.equals(" ")))
            throw new Exception("A equação não é compativel");
        this.valorPassado = metodoInfixo;
        String [] partes;
        //TODO refatorar o tokenizar para tomar cuidado com strings do teclado que não são numeros
        StringTokenizer quebrador = new StringTokenizer (metodoInfixo, "+-*/^()", true);
        pedacos = new String [quebrador.countTokens()];
        pil = new Pilha (quebrador.countTokens());
        fil = new Fila (quebrador.countTokens());
        calculadora = new Pilha (quebrador.countTokens());
        int i = 0;
        do{
            pedacos[i] = quebrador.nextToken();
            i++;
        }while(quebrador.hasMoreTokens());
        this.organizar();
    }
    private void organizar() throws Exception{
        for(int i =0; i<pedacos.length;i++){
            try{
                Double aux = Double.parseDouble(pedacos[i]); // verifica se é numero
                fil.guarde(aux.toString());
            }catch(NumberFormatException e){// caso contrario é um simbulo
                
                if(!this.calculador.isValid(pedacos[i]))
                    throw new Exception("O operador "+pedacos[i]+" não é compativel");
                
                if(pil.getValor()==null) // Primeiro pedaço
                    pil.guarde(pedacos[i]); // guarda o maligno
                
                else{
                    String aux = pedacos[i];//armazena o pedaco em uma variavel auxiliar
                    boolean flagParentes = false;
                    if(aux.equals(")")){
                        //TODO: Verificar se tem o ( na fila
                        do{
                           if(pil.getValor().equals("(")){
                                flagParentes=true;
                                pil.jogueFora();
                           }else{
                                fil.guarde(pil.jogueFora());
                           }
                        }while(flagParentes!=true);
                        aux = pil.jogueFora();
                    }
                    do{
                        if(pil.getValor()!=null){
                            if(tabela.getValor(pil.getValor(), aux)){ //se for verdade na tabela
                                 fil.guarde(pil.jogueFora());
                            }else{
                                pil.guarde(aux);
                                aux=null;
                            }
                        }else{
                            pil.guarde(aux);
                            aux=null;
                        }
                    }while (aux !=null);
                }
            }
        }
        if(pil.hasValore()){//esvasia pilha e poem na fila
            while(pil.hasValore()==true){
                fil.guarde(pil.jogueFora());
            }
        }
        this.filaOrganizada = fil.imprimir();
        calcular();
    }
    private void calcular() throws Exception{
    //inicia os calculos com a tabela organizada
    double a=0;
    double b=0;
    String operador;
    
        do{
           //TODO: desenfilierar até achar um operador e fazer o calculo
           try{
                double aux = Double.parseDouble(this.fil.getValor()); // verifica se é numero
                this.calculadora.guarde(aux);
                this.fil.jogueFora();
           }catch(NumberFormatException e){
                try{
                b=this.calculadora.jogueFora();
                a=this.calculadora.jogueFora();

                double resultado = this.calculador.calcular(a, fil.jogueFora(), b);
                this.calculadora.guarde(resultado);
               }
               catch(NullPointerException ne){
                   throw new Exception("A equação não é compativel");
               }
           }
        }while(fil.getNumeroValores()>0);
        if(fil.getValor()!=null)
            throw new Exception("A equação não é compativel");
        if(fil.getNumeroValores()!=0)
            throw new Exception("A equação não é compativel");
        if(calculadora.getNumeroValores()!=1)
            throw new Exception("A equação não é compativel");
        
        this.resultado=this.calculadora.getValor();
    }
    
    public String getPedacosGruped(){
        String texto = "";
        for(int i=0;i<pedacos.length;i++)
            texto +="\n".concat(pedacos[i]);
        
        return texto;
    }
    
    public String getResultado(){
        String texto = "";
        texto+= this.resultado;
        return texto;
    }
    
    public String toString(){
        String texto = "";
        texto+= "Resultado final: "+this.resultado +"\n String recebida: "+this.valorPassado+"\n Valor pós: "+this.filaOrganizada;
        return texto;
    }
    public Fila clone(){}
     
    public Integer compareTo(){}
     
     public boolean equals(Object obj){
        if(obj==null)
            return false;
        if(this==obj)
            return true;
        if(this.getClass()!=obj.getClass())
            return false;
        return true;
    }
     
    public int hashCode(){
        //int ret = super.hashCode()// quando herdar de alguma classe
        int ret = 666; //qualquer numero, não zero e intero, desde que sua classe não herde de nenhuma classe
        
        ret = 7 * ret + this.pil.hashCode();
        ret = 7 * ret + this.calculadora.hashCode();
        ret = 7 * ret + this.fil.hashCode();
        ret = 7 * ret + new Double (this.resultado).hashCode();
        ret = 7 * ret + this.pedacos.hashCode();
        ret = 7 * ret + this.tabela.hashCode();
        ret = 7 * ret + this.calculador.hashCode();
        ret = 7 * ret + this.valorPassado.hashCode();
        ret = 7 * ret + this.filaOrganizada.hashCode();
        return ret;
    }
    
    public String teste() throws Exception{
        String texto = "";
        texto+= this.resultado;
        return texto;
    }
}
