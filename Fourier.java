import java.lang.Math;
import java.util.Arrays;

public class Fourier
{
	public byte[] input_real;
	public byte[] input_imaginary;
	public byte[] dft_real;
	public byte[] dft_imaginary;
	public byte[] ift_real;

	public Fourier(byte[] input)
	{
		this.input_real = input
		this.input_imaginary = new byte[input.length];
		Arrays.fill(this.input_imaginary, 0);
		this.dft_real = new byte[input.length];
		this.dft_imaginary = new byte[input.length];
		this.ift_real = new byte[input.length];
	}

	// Fast Fourier Transform
	public void fft()
	{
		
	}
}
