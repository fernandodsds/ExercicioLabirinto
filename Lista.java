public class Lista <X> implements Cloneable
{
    private class No
    {
        private X  info;
        private No prox;

        public No (X i, No p)
        {
            this.info = i;
            this.prox = p;
        }

        public X getInfo ()
        {
            return this.info;
        }

        public No getProx ()
        {
            return this.prox;
        }

		public void setInfo (X i)
		{
			this.info = i;
		}

		public void setProx (No novo)
        {
	    	this.prox = novo;
		}
    }

	public Lista(){}
    private No prim=null;

	public X getPrim(){
		return this.prim.getInfo();
	}
    public void insiraNoInicio (X i) throws Exception
    {
		//if (this.tem(i))
		//	throw new Exception ("Informacao repetida");

        this.prim = new No (i,this.prim);
    }

    public void insiraNoFim (X i) throws Exception
    {
        No novo = new No (i, null);

        if (this.prim==null)
        {
            this.prim=novo;
            return;
        }

		No atual = this.prim;

		while (atual.getProx() != null)
		{
			if (i.equals(atual.getInfo()))
				throw new Exception ("Informacao repetida");

			atual = atual.getProx();
		}

		if (i.equals(atual.getInfo()))
			throw new Exception ("Informacao repetida");

		atual.setProx (novo);
    }

    public boolean tem (X i)
    {
        No atual=this.prim;

        while (atual!=null)
        {
            if (i.equals(atual.getInfo()))
                return true;

            atual = atual.getProx();
        }

        return false;
    }

	public void remova (X i) throws Exception
	{
		if (i==null)
			throw new Exception ("Informacao ausente");

		if (this.prim==null)
		    throw new Exception ("Informacao inexistente");

		if (i.equals(this.prim.getInfo()))
			this.prim = this.prim.getProx();
		else
		{
			No atual=this.prim;

			while (atual.getProx()!=null &&
			       !i.equals(atual.getProx().getInfo()))
				atual=atual.getProx();

			if (atual.getProx()==null)
			    throw new Exception ("Informacao inexistente");

			atual.setProx (atual.getProx().getProx());
		}
	}

    public void invertaSe ()
    {
		if (this.prim==null)
			return;

		if (this.prim.getProx()==null)
			return;

		No anterior=null, atual=this.prim, seguinte=this.prim.getProx();

		while (seguinte!=null)
		{
			atual.setProx(anterior);
			anterior=atual;
			atual   =seguinte;
			seguinte=seguinte.getProx();
		}

		atual.setProx(anterior);
		this.prim = atual;
	}

    public int getQtd ()
    {
		int ret=0;

        No atual=this.prim;
        while (atual!=null)
        {
            ret++;
            atual= atual.getProx();
        }

        return ret;
	}

    public String toString ()
    {
        String ret="";

        No atual=this.prim;
        while (atual!=null)
        {
            ret += atual.getInfo()+" ";
            atual= atual.getProx();

        }

        return ret;
    }

    public boolean equals (Object obj)
    {
		if (this==obj)
		    return true;

		if (obj==null)
		    return false;

		if (this.getClass()!=obj.getClass())
		    return false;

		Lista<X> lis=(Lista<X>)obj;

		No atualThis=this.prim, atualLis=lis.prim;

		while (atualThis!=null && atualLis!=null)
		{
			if (!atualThis.getInfo().equals(atualLis.getInfo()))
				return false;

			atualThis=atualThis.getProx();
			atualLis =atualLis .getProx();
		}

		if (atualThis!=null)
			return false;

		if (atualLis!=null)
			return false;

		return true;
	}

	public int hashCode ()
	{
		int ret=666;

		No atual=this.prim;
		while (atual!=null)
		{
			ret = ret*7 + atual.getInfo().hashCode();
			atual= atual.getProx();
        }

		return ret;
	}

	public Lista (Lista<X> modelo) throws Exception
	{
		if (modelo==null)
			throw new Exception ("Modelo ausente");

		if (modelo.prim==null)
		{
			this.prim=null;
			return;
		}

		this.prim = new No (modelo.prim.getInfo(),null);

		if (modelo.prim.getProx()==null)
			return;

		No atualThis=this.prim, atualModelo=modelo.prim;

		while (atualModelo.getProx()!=null)
		{
			atualThis.setProx (new No (atualModelo.getProx().getInfo(),null));

			atualThis  =atualThis  .getProx();
			atualModelo=atualModelo.getProx();
		}
	}

	public Object clone ()
	{
		Lista<X> ret=null;

		try
		{
			ret=new Lista<X> (this);
		}
		catch (Exception erro)
		{}

		return ret;
	}
}