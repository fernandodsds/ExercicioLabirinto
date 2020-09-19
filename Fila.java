import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.sql.rowset.spi.TransactionalWriter;

public class Fila<X> implements Cloneable // FIFO
{

	private Lista<X> item;
	private int capacidade;


	public Fila(int capacidade) throws Exception {
		if (capacidade <= 0)
			throw new Exception("Tamanho invalido");
		this.capacidade = capacidade;	
		this.item = new Lista<X>();
	}

	public Fila(Fila<X> modelo) throws Exception {
		if (modelo == null)
			throw new Exception("invalido");
		this.capacidade = modelo.capacidade;
		this.item = new Lista<X>(modelo.item);

	}

	private X retornaItem(X x) {
		if (x instanceof Cloneable)
			return meuCloneDeX(x);
		return x;

	}

	public void guardeUmItem(X x) throws Exception {
		if (x == null)
			throw new Exception("Invalido");
		if (this.cheia())
			throw new Exception("Não cabem mais itens");
			
		this.item.insiraNoFim(retornaItem(x));
	}

	public X getUmItem() throws Exception {
		if (this.vazia())
			throw new Exception("A fila esta vazia");

		return (X)retornaItem(this.item.getPrim());
		// validar se existem coisas no vetor (topo = -1?)
		// retornar o primeiro valor guardado
	}

	public void jogueUmItemFora() throws Exception {
		if (this.vazia())
			throw new Exception("A fila esta vazia");

		this.item.remova(this.getUmItem());
		
		// validar se existe algo guardado em Item
		// remover de Item o primeiro valor guardado
	}

	public boolean cheia() {
		if (this.item.getQtd() == this.capacidade)
			return true;

		return false;

	}

	public boolean vazia() {
		if (this.item.getQtd() == 0)
			return true;

		return false;
	}

	public String toString() {
		
		return this.item.toString();
	}

	public int hashCode() {
		int ret = 666;

		ret = 7 * new Integer(capacidade).hashCode();
		ret = 7 * this.item.hashCode();
		return ret;

	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;

		if (this.getClass() != obj.getClass())
			return false;

		Fila<X> param = null;
		try {
			param = new Fila<X>((Fila<X>) obj);
			if (this.capacidade != param.capacidade)
				return false;
		} catch (Exception e) {
		}
		if (!this.item.equals(param.item))
			return false;

		return true;
	}

	public Object clone() {
		Fila<X> ret = null;
		try {
			ret = new Fila<X>(this);
		} catch (Exception e) {

		}
		return ret;

	}

	private X meuCloneDeX(X x) {
		X ret = null;
		try {
			Class<?> classe = x.getClass();
			Class<?>[] tipoDoParametroFormal = new Class<?>[0];
			Method metodo = classe.getMethod("clone", tipoDoParametroFormal);
			Object[] parametroReal = new Object[0];
			ret = (X) metodo.invoke(x, parametroReal);
		} catch (NoSuchMethodException erro) {
		} catch (InvocationTargetException erro) {
		} catch (IllegalAccessException erro) {
		}

		return ret;
	}
}