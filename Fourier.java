import java.lang.Math;
import java.util.Arrays;

public class Fourier
{
	public byte[] input;
	public byte[] dft_real;
	public byte[] dft_imaginary;
	public byte[] ift_real;
	public byte[] ift_imaginary;

	public Fourier(byte[] input;)
	{
		this.input = input;
	}

	// Discrete Fourier Transform
	public void dft()
	{
		dft_real = new byte[input.length];
		Arrays.fill(dft_real, 0);
		dft_imaginary = new byte[input.length];
		Arrays.fill(dft_imaginary, 0);
		byte operand;

		for (int i = 0; i < input.length; i++)
		{
			for (int j = 0; j < input.length; j++)
			{
				operand = -2 * Math.PI * i * j * (1 / input.length);
				dft_real[i] += (input[j] * Math.cos(operand)) - (0 * Math.sin(operand));
				dft_imaginary[i] += (0 * Math.cos(operand)) - (input[j] * Math.sin(operand));
			}
			dft_real[i] = dft_real[i] / Math.sqrt(input.length);
			dft_imaginary[i] = dft_imaginary[i] / Math.sqrt(input.length);
		}
	}

	// Inverse Fourier Transform
	public void ift()
	{
		ift_real = new byte[input.length];
		Arrays.fill(ift_real, 0);
		ift_imaginary = new byte[input.length];
		Arrays.fill(ift_imaginary, 0);
		byte operand;

		for (int i = 0; i < input.length; i++)
		{
			for (int j = 0; j < input.length; j++)
			{
				operand = 2 * Math.PI * i * j * (1 / input.length);
				ift_real[i] += (input[j] * Math.cos(operand)) - (0 * Math.sin(operand));
				ift_imaginary[i] += (0 * Math.cos(operand)) - (input[j] * Math.sin(operand));
			}
			ift_real[i] = ift_real[i] / Math.sqrt(input.length);
			ift_imaginary[i] = ift_imaginary[i] / Math.sqrt(input.length);
		}
	}
}
