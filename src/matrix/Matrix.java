package matrix;
import java.util.Scanner;
public class Matrix {

    public double[]data;
    public int rows;
    public int cols;

    public Matrix(int rows, int cols){

        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
        java.util.Arrays.fill(data,0);

    }

    public Matrix(double[][] d)
    {
        int max=0;

        this.rows = d.length;


        for(int i=0; i<d.length;i++)
        {
            if(d[i].length>max)
                max=d[i].length;
        }

        this.cols=max;
        data = new double[rows*cols];

        for(int i=0; i<d.length;i++)
        {
            for(int j=0;j<this.cols;j++)
            {
                if(j<d[i].length)
                {
                    data[i*cols+j] = d[i][j];
                }
                else
                {
                    data[i*cols+j] =0;
                }

            }
        }
    }



    public double[][] asArray()
    {
        double [][] array = new double [rows][cols];
        for(int i=0;i<this.rows;i++)
        {
            for(int j=0;j<this.cols;j++)
            {
                array[i][j]=data[i*cols+j];
            }
        }

        return array;
    }


    public double get(int r,int c)
    {
        return data[cols*r+c];
    }

    public void set (int r,int c, double value)
    {
        data[cols*r+c]=value;
    }

    public String toString(){
        int k=0;
        StringBuilder buf = new StringBuilder();
        for(int i=0;i<rows;i++){
            buf.append("[");
            for(int j=0; j<cols;j++)
            {
                buf.append(data[k]);
                if(j%cols!=cols-1)
                    buf.append(" , ");
                k++;
            }
            buf.append("]");
            buf.append("\n");
        }

        return buf.toString();
    }

    public void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));
        else
        {
            rows=newRows;
            cols=newCols;
        }

    }

    public Matrix add(Matrix m)
    {
        if(this.rows!=m.rows || this.cols!=m.rows)
            throw new RuntimeException(String.format(" Matrixes can't be added\n"));
        else
        {
            Matrix x=new Matrix(rows,cols);

            for(int i=0; i<data.length;i++)
                x.data[i]=this.data[i]+m.data[i];
            return x;
        }

    }

    public Matrix sub(Matrix m)
    {
        if(this.rows!=m.rows || this.cols!=m.rows)
            throw new RuntimeException(String.format("Dimensions of matrixes aren't the same!\n"));
        else
        {
            Matrix x=new Matrix(rows,cols);

            for(int i=0; i<data.length;i++)
                x.data[i]=this.data[i]-m.data[i];
            return x;
        }

    }

    public Matrix mul(Matrix m)
    {
        if(this.rows!=m.rows || this.cols!=m.rows)
            throw new RuntimeException(String.format(" Dimensions of matrixes aren't the same!\n"));
        else
        {
            Matrix x=new Matrix(rows,cols);
            for(int i=0; i<data.length;i++)
                x.data[i]=this.data[i]*m.data[i];
            return x;
        }

    }

    public Matrix div(Matrix m)
    {
        if(this.rows!=m.rows || this.cols!=m.rows)
            throw new RuntimeException(String.format("Dimensions of matrixes aren't the same!\n"));
        else
        {
            Matrix x=new Matrix(rows,cols);
            for(int i=0; i<data.length;i++) {

                if(m.data[i]==0)
                    throw new RuntimeException(String.format("Dividing by zero is undefined! !\n"));
                else
                    x.data[i] = this.data[i] / m.data[i];


            }
            return x;
        }

    }


    public Matrix addval(double w)
    {
        for(int i=0; i<data.length;i++)
            data[i]+=w;

        return this;
    }

    public Matrix subval(double w)
    {
        for(int i=0; i<data.length;i++)
            data[i]-=w;

        return this;
    }

    public Matrix mulval(double w)
    {
        for(int i=0; i<data.length;i++)
            data[i]*=w;

        return this;
    }

    public Matrix divval(double w)
    {
        if(w==0)
            throw new RuntimeException(String.format("Dividing by zero is undefined!\n"));

        for(int i=0; i<data.length;i++)
            data[i]/=w;

        return this;
    }

    public Matrix dot(Matrix m)
    {
        int l;

        if(this.cols!=m.rows)
            throw new RuntimeException(String.format("Dimensions of matrixes aren't the same!\n"));
        else
        {
            Matrix x=new Matrix(this.rows,m.cols);
            java.util.Arrays.fill(x.data,0);

            for (int i = 0; i < x.rows; i++)
                for (int j = 0; j < x.cols; j++)
                    for (int k = 0; k < this.cols; k++)
                        x.data[i*x.cols+j] += (this.data[this.cols*i+k] * m.data[m.cols*k+j]);

            return x;
        }

    }

    public double frobenius()
    {
        double sum=0;

        for(int i=0; i<rows*cols;i++)
            sum+=this.data[i]*this.data[i];

        return sum;
    }

    /////////GRUPA C////////////////

    public Matrix sumRows()
    {
        Matrix x=new Matrix(this.rows,1);
        java.util.Arrays.fill(x.data,0);

        for(int i=0; i<this.rows;i++)
        {
            for(int j=0; j<this.cols;j++)
            {
                x.data[i]+=this.data[i*this.cols+j];
            }
        }

        return x;
    }

    ///////////////////////////////////////


    public static void main(String[] args)
    {
        /*double[][] m = new double[][] { {3},{1,2}, { 4, 5, 6 } };
        double[][] n = new double [][] {{2,4,5},{6,7},{2,8}};
        double[][] o = new double [][]{{1,1},{0,1},{2,3},{1,2}};
        double[][] p =new double[][]{{1,2},{0,1}};
        double[][] s= new double[][]{{2,1,1},{0,1,0},{3,5,0}};
        double[][] t= new double[][]{{1},{2},{3}};
        Matrix mat1 = new Matrix(m);
        Matrix mat2 = new Matrix(n);
        String x = mat1.toString();
        String y = mat2.toString();
        System.out.print(x);
        System.out.println("\n");
        System.out.print(y);
        System.out.println("\n");
        Matrix r=mat1.add(mat2);
        String z = r.toString();
        System.out.print(z);
        System.out.println("\n");
        Matrix mat3=new Matrix(o);
        Matrix mat4=new Matrix(p);
        Matrix mul=mat3.dot(mat4);
        String k = mul.toString();
        System.out.print(k);
        System.out.println("\n");*/

        Matrix m = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix col = m.sumRows();
        String x=col.toString();
        System.out.print(x);

    }


}


