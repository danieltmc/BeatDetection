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
	public static double fft(byte[] input)
	{
		int n = input.length;
		
		if (n == 1)
		{
			return(input);
		}
		if (n % 2 != 0)
		{
			// Remove the last byte
			n = n - 1;
		}
		
		double[] converted = new double[n];
		for (int i = 0; i < n; i++)
		{
			converted[i] = ((double) input[i]) / 256.0;
		}
		
		double[] even = new double[n/2];
		for (int i = 0; i < n/2; i++)
		{
			even[i] = converted[2 * i];
		}
		double[] even_transformed = fft(even);
		
		double[] odd = new double[n/2];
		for (int i = 0; i < n/2; i++)
		{
			odd[i] = converted[2 * i + 1];
		}
		double[] odd_transformed = fft(odd);
		
		double[] output = new double[n];
		for (int i = 0; i < n/2; i++)
		{
			double mult = 2 * i * Math.PI / n;
			double real = Math.cos(mult);
			double imaginary = Math.sin(mult);
			output[i] = even_transformed + (real * odd_transformed[i] - imaginary * 0);
			output[i + n / 2] = even_transformed - (real * odd_transformed[i] - imaginary * 0);
		}
		return(output);
	}
}
