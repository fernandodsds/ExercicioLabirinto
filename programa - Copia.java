import java.io.*;

public class programa {

	public static void main(String[] args) {
		try {
			Pilha<Coordenada> caminho = null;
			Pilha<Fila<Coordenada>> possibilidades = null;
			Fila<Coordenada> fila  = null;
			Coordenada atual = null;
			Labirinto lab = null;
			

			System.out.println("Digite o nome do arquivo para o labirinto:");

			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
			String arquivo = teclado.readLine();

			//Inicialização
			lab = new Labirinto(arquivo);
			System.out.println(lab);
			int tamanho = lab.retornaTamanho();

			caminho = new Pilha<Coordenada>(tamanho);
			possibilidades = new Pilha<Fila<Coordenada>>(tamanho);
 
			atual = lab.retornarEntrada();
			System.out.println(atual);

			//Busca

			fila = lab.getAdjacentes(atual);

			while(!fila.vazia())
			{
				atual = fila.getUmItem();
				fila.jogueUmItemFora();

				lab.marcaCaminho(atual);

				if(lab.retornaValor(atual) == 'S')
				{
					System.out.println("Saida Wins");
					break;
				}	
				caminho.guardeUmItem(atual);

				possibilidades.guardeUmItem(fila);

				
				fila = lab.getAdjacentes(atual);
				while(fila.vazia() )
				{
					//modo regressivo
					lab.desmarcaCaminho(atual);
					
					caminho.jogueUmItemFora();
					if(!caminho.vazia()){	
						atual = caminho.getUmItem();
					}


					if(!possibilidades.vazia()){
						fila = possibilidades.getUmItem();
						possibilidades.jogueUmItemFora();
					}	
				}

				System.out.println(lab);

			}
			if(lab.retornaValor(atual) != 'S')
				throw new Exception("Não existe uma saida");
			//Busca2
			
			//for(int i = 0; i<y; i++){
			//	if(m[m.length-1][0] == 'E')
			//		atual = new Coordenada(y,0);
			//	}


			//Pilha<Fila<Coordenada>> possibilidades = new Pilha<Fila<Coordenada>>(5*8);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}

	}

					


}
