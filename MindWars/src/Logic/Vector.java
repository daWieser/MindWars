package Logic;

public class Vector {
	
	private double x;
	private double y;
	
	//Create new Vector from existing Vector
	
	/**
	 * Creates new Vector from existing Vector
	 * @param v existing Vector
	 */
	public Vector(Vector v)
	{
		this.x= v.getX();
		this.y= v.getY();
	}
	
	/**
	 * Creates a Vector from two floats
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	public Vector(double x,double y)
	{
		this.x= x; 
		this.y=y;
	}
	
	/**
	 * Adds a Vector to this Vector
	 * @param v Vector to add
	 * @return new Vector
	 */
	public Vector add(Vector v)
	{
		return new Vector(this.x + v.getX(), this.y + v.getY());
	}
	
	/**
	 * Subtracts a Vector to this Vector
	 * @param v Vector to subtract
	 * @return new Vector
	 */
	public void sub(Vector v)
	{
		this.x-= v.getX();
		this.y-= v.getY();
	}
	
	/**
	 * Multiplies a Scalar with this Vector
	 * @param s Scalar
	 * @return new Vector
	 */
	public Vector mul(double s)
	{
		return new Vector(this.x*s,this.y*s);
	}
	/**
	 * Divides this Vector with a Scalar 
	 * @param s Scalar
	 * @return new Vector
	 */
	public Vector div(double s)
	{
		return new Vector(this.x*s,this.y*s);
	}
	
	/**
	 * Turns this Vector at a given angle
	 * @param deg  angle in degree
	 * @return new Vector
	 */
	public Vector turn(float deg)
	{
		return new Vector(this.x*Math.sin(deg*Math.PI/180),this.y*Math.cos(deg*Math.PI/180));
	}
	
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Vector other = (Vector) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
}
