/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirinto;

/**
 *
 * @author Ultron
 */
public class LeitorArquivo {
    private String[][] array;
    private String fileName;
    
    public LeitorArquivo(String fileName){
        this.setFileName(fileName);
    }
    
    public void setFileName(String fileName) throws Exception{
        if(fileName==null)
            throw new Exception("o nome do arquivo não pode ser nullo");
        if(fileName.contains(".txt"))
            throw new Exception("Somente é aceito aquivos textos");
    }
    
    public arquivoReader(){
        BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(this.fileName);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(FILENAME));

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}
                        //while(br.redy());

		} catch (IOException e) {

			e.printStackTrace();

		}
    }
}
