public class Resolvedor{
    private Pilha<Coordenada> caminho = null;
    private Pilha<Fila<Coordenada>> possibilidades = null;
    private Fila<Coordenada> fila  = null;
    private Coordenada atual = null;
    private Labirinto lab = null;


    public Resolvedor(Labirinto l) throws Exception
    {
            this.lab = new Labirinto(l);

			int tamanho = lab.retornaTamanho();

			caminho = new Pilha<Coordenada>(tamanho);
			possibilidades = new Pilha<Fila<Coordenada>>(tamanho);

            atual = this.lab.retornarEntrada();

            fila = this.lab.getAdjacentes(atual);

            this.modoProgressivo();
    }

    public Labirinto getLabirinto() throws Exception{
        return new Labirinto(this.lab);
    }

    public Pilha<Coordenada> getCaminho() throws Exception{
		Pilha<Coordenada> caminho = (Pilha<Coordenada>)this.caminho.clone();
		Pilha<Coordenada> inverso = new Pilha<Coordenada>(caminho.getCapacidade());

		while(!caminho.vazia())
		{
			inverso.guardeUmItem(caminho.getUmItem());
			caminho.jogueUmItemFora();

		}
        return inverso;
    }

    private void modoProgressivo() throws Exception{
        while(!fila.vazia())
			{
				atual = fila.getUmItem();
				fila.jogueUmItemFora();

				this.lab.marcaCaminho(atual);

				if(this.vitoria())
				{
					return;
				}
				caminho.guardeUmItem(atual);

                possibilidades.guardeUmItem(fila);
                fila = lab.getAdjacentes(atual);

                this.modoRegressivo();
            }
        if (fila.vazia())
            throw new Exception("Tem 2 saidas");
    }

    private void modoRegressivo() throws Exception{

        while(fila.vazia() )
        {
            //modo regressivo
            this.lab.desmarcaCaminho(atual);

            caminho.jogueUmItemFora();
            if(!caminho.vazia()){
                atual = caminho.getUmItem();
            }


            if(!possibilidades.vazia()){
                fila = possibilidades.getUmItem();
                possibilidades.jogueUmItemFora();
            }

            if(caminho.vazia() || possibilidades.vazia())
                throw new Exception("Sem Saida");
        }
    }

    private boolean vitoria(){
        if(lab.retornaValor(atual) == 'S')
        {

            return true;
        };

        return false;
    }

}