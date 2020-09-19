import java.io.*;

public class programa {

	public static void main(String[] args) {
		try {

			System.out.println("Digite o nome do arquivo para o labirinto:");

			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
			String arquivo = teclado.readLine();

			//Inicialização
			Labirinto lab = new Labirinto(arquivo);
			Resolvedor  r = new Resolvedor(lab);

			System.out.println("Resolvendo Labirinto.........");

			PrintWriter saida =
			new PrintWriter (
			new FileWriter (
			"saida.txt"));
			saida.println("------------------LABIRINTO SEM RESOLVER------------------");
			saida.println(lab);
			saida.println("------------------LABIRINTO RESOLVIDO---------------------");
			saida.println(r.getLabirinto());
			saida.println("CAMINHO PERCORRIDO:");
			saida.println(r.getCaminho());
			saida.close();

			System.out.println("O labirinto foi resolvido verifique o caminho percorrido no arquivo - saida.txt");
			Process pro = Runtime.getRuntime().exec("cmd.exe /c saida.txt");
			pro.waitFor();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}

	}




}
