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
		this.x = x; 
		this.y = y;
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
	 * @return 
	 * @return new Vector
	 */
	public Vector sub(Vector v)
	{
		return new Vector(this.x - v.getX(), this.y - v.getY());
	}
	
	/**
	 * Multiplies a Scalar with this Vector
	 * @param s Scalar
	 * @return new Vector
	 */
	public Vector mul(double s)
	{
		return new Vector(this.x * s,this.y * s);
	}
	/**
	 * Divides this Vector with a Scalar 
	 * @param s Scalar
	 * @return new Vector
	 */
	public Vector div(double s)
	{
		return new Vector(this.x / s,this.y / s);
	}
	
	/**
	 * Turns this Vector at a given angle
	 * @param deg  angle in degree
	 * @return 
	 * @return new Vector
	 */
    public Vector turn(int a)
    {
        double x=this.x;
        double y= this.y;
        
        double xn;
        double yn;

        xn=(float)(x*Math.cos(a*Math.PI/180)-y*Math.sin(a*Math.PI/180));
        yn=(float)(x*Math.sin(a*Math.PI/180)+y*Math.cos(a*Math.PI/180));
        
        return new Vector(xn,yn);

    }
	
	public double getAngle(){
		double a = 0;
		if(this.getX() == this.getY() && this.getX() == 0){
			return 0;
		}
		
		if(this.getX() != 0){
			a = Math.atan(this.getY()/this.getX())*180/Math.PI;
		}
		else {
			if(y > 0) a = 90;
			else a = 270;
		}
		
		if(x < 0) a += 180;
		if(a < 0) a += 360;
		a = a%360;
		
		return a;
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

	public Vector mul(Vector vector) {
		
		return new Vector(this.getX()*vector.getX(),this.getY()*this.getY());
	}
	
	public Vector div(Vector vector)
	{
		return new Vector(this.getX()/vector.getX(),this.getY()/this.getY());
	}
	
	
}
