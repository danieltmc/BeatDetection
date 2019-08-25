public class Complex
{
	public double real;
	public double imaginary;
	
	public Complex(double real, double imaginary)
	{
		this.real = real;
		this.imaginary = imaginary;
	}
	
	public Complex times(Complex other)
	{
		double real = this.real * other.real - this.imaginary * other.imaginary;
		double imaginary = this.real * other.imaginary + this.imaginary * other.real;
		return(new Complex(real, imaginary));
	}
	
	public Complex plus(Complex other)
	{
		double real = this.real + other.real;
		double imaginary = this.imaginary + other.imaginary;
		return(new Complex(real, imaginary));
	}
}
