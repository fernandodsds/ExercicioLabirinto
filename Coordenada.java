
/**Classe para objetos do tipo Coordenada que arzena os valores de X, Y no labirinto.

 * @author Fernando Silva

 * @version 1.0


 */

public class Coordenada /*implements Cloneable*/ {
	private int X,Y;

    /** Construtor da classe
     * @author Fernando Silva

     * @param  x int  - posição no eixo X
	 * @param  y int  - posição no eixo Y
     */

	public Coordenada(int x, int y) {
		X = y;
		Y = x;
	}
    /** Construtor de copia
     * @author Fernando Silva

     * @param  c Coordenada - passa os atributos de c para this
     */
	public Coordenada(Coordenada c) {
		this.X = c.X;
		this.Y = c.Y;
	}

	@Override
	public String toString() {
		return  String.format("(%d , %d)", this.Y, this.X);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + X;
		result = prime * result + Y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordenada other = (Coordenada) obj;
		if (X != other.X)
			return false;
		if (Y != other.Y)
			return false;
		return true;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}
	
	/*public Object clone() {
		Coordenada ret = null;
		try {
			ret = new Coordenada(this);
		} catch (Exception e) {

		}
		return ret;

	}*/
	

}
