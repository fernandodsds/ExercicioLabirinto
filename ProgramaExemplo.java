import java.io.*;

public class ProgramaExemplo
{
    public static void main (String[] args)
    {
		try
		{
			PrintWriter saida =
			new PrintWriter (
			new FileWriter (
			"teste.txt"));

			saida.println ("Caprichem no ");
			saida.println ("meu trabalho ");
			saida.println ("pestezinhas ");
			saida.println ("amadas!!!");

			saida.close ();

			BufferedReader entrada =
			new BufferedReader (
			new FileReader (
			"teste.txt"));

			while (entrada.ready())
			{
				String linha = entrada.readLine();

				for (int i=0; i<linha.length(); i++)
				    System.out.print (linha.charAt(i));
			}

			entrada.close();

			System.out.println ();
			System.out.println ();
		}
		catch (Exception erro)
		{}
    }
}