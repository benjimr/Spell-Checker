
public class Matrix
{
	private float[][] elements;

	private int rows;
	private int cols;

	public int getRows()
	{
		return rows;
	}

	public int getCols()
	{
		return cols;
	}

	public Matrix(int rows, int cols)
	{
		this.rows = rows;
		this.cols = cols;
		elements = new float[rows][cols];
	}

	public void setElement(int row, int col, float value)
	{
		elements[row][col] = value;
	}

	public float getElement(int row, int col)
	{
		return elements[row][col];
	}

	public void add(Matrix b)
	{
		for (int row = 0 ; row < rows ; row ++)
		{
			for (int col = 0 ; col < cols ; col ++)
			{
				elements[row][col] += b.elements[row][col];
			}
		}
	}

	public static Matrix add(Matrix a, Matrix b)
	{
		Matrix ret = new Matrix(a.getRows(), a.getCols());

		for (int row = 0 ; row < a.rows; row ++)
		{
			for (int col = 0 ; col < a.cols ; col ++)
			{
				ret.elements[row][col] =
					a.elements[row][col]
					+ b.elements[row][col];

			}
		}
		return ret;
	}
	
	public void identity()
	{
		for(int row = 0 ; row < getRows() ; row ++)
		{
			for (int col = 0 ; col < cols ; col ++)
			{
				if (row == col)
				{
					setElement(row, col, 1);
				}
				else
				{
					setElement(row, col, 0);
				}
			}
		}
	}

	public static Matrix mult(Matrix a, Matrix b) throws MatrixException
	{
		if(a.getCols()!=b.getRows())
		{
			throw new MatrixException("Rows in a must be equal to cols in b");
		}

		Matrix c = new Matrix(a.getRows(), b.getCols());

		for (int row = 0; row < a.getRows(); row++)
		{
			for (int col = 0; col < b.getCols(); col++)
			{
				float sum = 0.0f;
				for (int i = 0; i < a.getCols(); i++)
				{
					sum += a.getElement(row, i) * b.getElement(i, col);
				}
				c.setElement(row, col, sum);
			}
		}
		return c;
	}

	public void mult(Matrix b)
	{
		for (int row = 0; row < getRows(); row++)
		{
			for (int col = 0; col < b.getCols(); col++)
			{
				float sum = 0.0f;
				for (int i = 0; i < getCols(); i++)
				{
					sum += getElement(row, i) * b.getElement(i, col);
				}
				setElement(row, col, sum);
			}
		}
	}

	public static Matrix translation(float px, float py)
	{
		Matrix m = new Matrix(3, 3);
		m.identity();
		m.setElement(0, 2, px);
		m.setElement(1, 2, py);
		return m;
	}

	public static Matrix scaling(float sx, float sy)
	{
		Matrix m = new Matrix(3,3);
		m.identity();
		m.setElement(0, 0, sx);
		m.setElement(1,1, sy);
		return m;
	}

	public Vector transform(Vector v) throws MatrixException
	{
		Matrix temp = new Matrix(3, 1);
		temp.setElement(0, 0, v.x);
		temp.setElement(1, 0, v.y);
		temp.setElement(2, 0, 1);

		Matrix out = Matrix.mult(this, temp);
		return new Vector(out.getElement(0, 0), out.getElement(1, 0));
	}

	public static Matrix rotation(float theta)
	{
		Matrix m = new Matrix(3, 3);
		m.identity();
		m.setElement(0, 0, (float) Math.cos(theta));
		m.setElement(0, 1, (float) -Math.sin(theta));
		m.setElement(1, 0, (float) Math.sin(theta));
		m.setElement(1, 1, (float) Math.cos(theta));
		return m;
	}

	public String toString()
	{
		StringBuffer sb = new StringBuffer();

		for (int row = 0 ; row < this.rows ; row ++)
		{
			for (int col = 0 ; col < this.cols ; col ++)
			{
				sb.append(elements[row][col]);
				sb.append("\t");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
