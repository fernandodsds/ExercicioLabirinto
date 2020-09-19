import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Pilha<X> // LIFO
{

	private Lista<X> item;
	private int capacidade;
	public Pilha(int capacidade) throws Exception {

		if (capacidade <= 0)
			throw new Exception("Tamanho invalido");
		this.item =  new Lista<X>();
		this.capacidade = capacidade;
	}

	public Pilha(Pilha<X> modelo) throws Exception {
		if (modelo == null)
			throw new Exception("invalido");

		this.item = (Lista<X>)modelo.item.clone();
		this.capacidade = modelo.capacidade;

	}

	public int getCapacidade()
	{
		return this.capacidade;

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
			throw new Exception("N�o cabem mais itens");
		x = retornaItem(x);
		this.item.insiraNoInicio(x);
	}

	public X getUmItem() throws Exception {

		if (this.vazia())
			throw new Exception("A pilha esta vazia");

		return retornaItem(this.item.getPrim());
	}

	public void jogueUmItemFora() throws Exception {

		if (this.vazia())
			throw new Exception("A fila esta vazia");
		this.item.remova(this.item.getPrim());
	}

	public boolean cheia() {

		if (this.capacidade == this.item.getQtd())
			return true;

		return false;
	}

	public boolean vazia() {
		if (this.item.getQtd() == 0 )
			return true;

		return false;
		// verificar se esta vazia;
	}

	public int hashCode() {
		int ret = 666;

		ret = 7 * new Integer(capacidade).hashCode();
		ret = 7 * this.item.hashCode();

		return ret;
	}

	public String toString() {

		return this.item.toString();
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;

		if (this.getClass() != obj.getClass())
			return false;

		Pilha<X> param = null;
		try {
			param = new Pilha<X>((Pilha<X>) obj);
			if (this.capacidade != param.capacidade)
				return false;
		} catch (Exception e) {
		}

		if (!this.item.equals(param.item))
				return false;

		return true;
	}

	public Object clone() {
		Pilha<X> ret = null;
		try {
			ret = new Pilha<X>(this);
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
			System.err.println(erro);
		} catch (InvocationTargetException erro) {
			System.err.println(erro);
		} catch (IllegalAccessException erro) {
			System.err.println(erro);
		}

		return ret;
	}

}