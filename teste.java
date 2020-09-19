import java.io.*;

public class teste
{
    public static void main (String[] args)
    {
        try {
           Labirinto lab = new Labirinto("teste.txt");
          if( lab.retornaValor(new Coordenada(1,2)) == ' ')
           System.out.println("Vazio");
           if( lab.retornaValor(new Coordenada(1,1)) == ' ') 
           System.out.println("Vazio");

           System.out.println(lab.getAdjacentes(new Coordenada(2,1)));



        } catch (Exception e) {
            System.err.println(e);
        }
       
    }
}