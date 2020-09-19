import java.io.*;


/**Classe para objetos do tipo Coordenada que arzena os valores de X, Y no labirinto.

 * @author Fernando Silva

 * @version 1.0


 */

public class Labirinto{
    char m[][] = null;

    int x = 0;
    int y = 0;
    /** Construtor da classe
     * @author Fernando Silva

     * @param  arq String  - Local onde esta o arquivo que contem o labirinto, pode lançar alguma excessão caso os valores de X e Y
     * não estejam contidos no arquivo.
     */

    public Labirinto(String arq) throws Exception{
        BufferedReader entrada =
        new BufferedReader (
        new FileReader (
            arq));
         try{
         this.y = Integer.parseInt(entrada.readLine());
         this.x = Integer.parseInt(entrada.readLine());
	 	}catch(Exception e)
	 	{
			throw new Exception("Passe os dados do tamanho do labirinto.");
		}
         this.m = new char[y][x];
        for (int i = 0; i<y; i++)
        {
            String linha = entrada.readLine();
            for (int j = 0; j<x; j++)
            {
                m[i][j] = linha.charAt(j);
            }

        }

        entrada.close();

    }
    /** Construtor de copia
     * @author Fernando Silva

     * @param  l Labirinto - passa os atributos de c para this
     */
    public Labirinto(Labirinto l) throws Exception{
        if(l == null)
            throw new Exception("Passe um modelo");
        this.x = l.x;
        this.y = l.y;
        this.m = new char[this.y][this.x];
        for (int i = 0; i<l.y; i++)
		   {
		       for (int j = 0; j<l.x; j++)
		          {
		              this.m[i][j] = l.m[i][j];
		           }

        }
    }

    /**Retorna o tamanho do labirinto mutiplicando X*Y

     * @author Fernando Silva

     * @return int - Tamanho

     */
    public int retornaTamanho(){
        return x*y;
    }

    /**Retorna a coordenada em que a entrada esta localizada pode lançar exceção caso não haja nenhuma entrada

     * @author Fernando Silva

     * @return Coordenanda - Entrada

     */
    public Coordenada retornarEntrada() throws Exception{
		Coordenada ret = null;
		for(int i = 0; i<y; i++){
			if(this.m[i][0] == 'E')

                 return new Coordenada(i,0);


			}

		for(int i = 0; i<x; i++){
				if(this.m[0][i] == 'E')
				//
                return new Coordenada(0,i);


			}

		for(int i = 0; i<y; i++){
				if(this.m[i][m[0].length-1] == 'E')
				//
                return new Coordenada(i,m[0].length-1);


				}

		for(int i = 0; i<x; i++){
				if(this.m[m.length-1][i] == 'E')
					//
                    return new Coordenada(m.length-1,i);
				}
		throw new Exception("Entrada Inexistente!!");
	}


    public String toString(){
        String ret = "";
        for (int i = 0; i<y; i++)
        {
            for (int j = 0; j<x; j++)
            {
                ret += m[i][j];

            }
            ret+="\r\n";

        }
        return ret;
    }

    /**Retorna Fila de coordenadas validas adjacentes a coordenada passada por paremetro.

     * @author Fernando Silva
     * @param Coordenada c
     * @return Fila<Coordenada> - adjacentes

     */
    public Fila<Coordenada> getAdjacentes(Coordenada c) throws Exception{
        Fila<Coordenada> ret= new Fila<Coordenada>(3);
        try{

        if((this.m[c.getY()+1][c.getX()]==  ' ') || (this.m[c.getY()+1][c.getX()]== 'S'))
            ret.guardeUmItem(new Coordenada(c.getY()+1, c.getX()));

        }
        catch(Exception e){

            System.err.println(e);
        }



        try{
        if((this.m[c.getY()-1][c.getX()] ==  ' ') || (this.m[c.getY()-1][c.getX()] ==  'S'))
            ret.guardeUmItem(new Coordenada(c.getY()-1, c.getX()));

        }
                catch(Exception e){}



        try{
        if((this.m[c.getY()][c.getX()+1]==  ' ') || (this.m[c.getY()][c.getX()+1]==  'S'))
            ret.guardeUmItem(new Coordenada(c.getY(), c.getX()+1));

        }
                catch(Exception e){}

        try{
        if((this.m[c.getY()][c.getX()-1] ==  ' ') ||( this.m[c.getY()][c.getX()-1] ==  'S'))
            ret.guardeUmItem(new Coordenada(c.getY(), c.getX()-1));

        }
        catch(Exception e){}

    return ret;
    }

    /**Retorna a informação contida na posição de uma coordenada

     * @author Fernando Silva
     * @param Coordenada c
     * @return char - Informação

     */
    public char retornaValor(Coordenada c){

            return this.m[c.getY()][c.getX()];
        }
    /**Marca caracter (*) na posição da coordenada passada por parametro

     * @author Fernando Silva
     * @param Coordenada c
     */
    public void marcaCaminho(Coordenada c){
        if(this.retornaValor(c) != 'S')
        {
            this.m[c.getY()][c.getX()] = '*';
        }
    }
    /**Desmarca caracter (*) na posição da coordenada passada por parametro

     * @author Fernando Silva
     * @param Coordenada c
     */
    public void desmarcaCaminho(Coordenada c){
        if(this.retornaValor(c) == '*')
        {
            this.m[c.getY()][c.getX()] = ' ';
        }
    }
}