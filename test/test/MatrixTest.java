package test;
import matrix.Matrix;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class MatrixTest {
    @Test
    public void Matrix() throws Exception {

        //Testy do 1 konstruktora://

        Matrix m1=new Matrix(2,2);
        int x1=m1.data.length;
        assertEquals(x1,m1.rows*m1.cols);

        Matrix m2=new Matrix(4,5);
        int x2=m2.data.length;
        assertEquals(x2,m2.rows*m2.cols);

        //Testy do 2 konstruktora://

        double[][] x3 = new double[][] { {3},{1,2}, { 4, 5, 6 } };
        Matrix m3 = new Matrix(x3);
        double [][] y3 = m3.asArray();
        Matrix res3 = new Matrix(y3);
        assertArrayEquals(m3.data,res3.data,.1);
        assertEquals(m3.rows,res3.rows);


        double[][] x4 = new double [][]{{1,1},{0,1},{2,3},{1,2}};
        Matrix m4 = new Matrix(x4);
        double [][] y4 = m4.asArray();
        Matrix res4 = new Matrix(y4);
        assertArrayEquals(m4.data,res4.data,.1);
        assertEquals(m4.rows,res4.rows);

    }


    @Test
    public void asArray() throws Exception {


        Matrix m1=new Matrix(2,2);
        m1.data = new double[] {3,0,1,1};
        double[][] x1 = new double [][] {{3,0},{1,1}};
        double[][] x2 = m1.asArray();

        assertArrayEquals(x1[0],x2[0],.1);
        assertArrayEquals(x1[1],x2[1],.1);

        Matrix m2=new Matrix(2,3);
        m2.data = new double[] {3,0,0,4,5,6};
        double[][] y1 = new double [][] {{3,0,0},{4,5,6}};
        double[][] y2 = m2.asArray();

        assertArrayEquals(y1[0],y2[0],.1);
        assertArrayEquals(y1[1],y2[1],.1);

        Matrix m3=new Matrix(3,3);
        m3.data = new double[] {3,0,0,4,5,0,1,2,3};
        double[][] z1 = new double [][] {{3,0,0},{4,5,0},{1,2,3}};
        double[][] z2 = m3.asArray();

        assertArrayEquals(z1[0],z2[0],.1);
        assertArrayEquals(z1[1],z2[1],.1);
        assertArrayEquals(z1[2],z2[2],.1);

    }

    @Test
    public void get() throws Exception {
        double[][] m = new double[][] { {3},{1,2}, { 4, 5, 6 } };
        Matrix mat1=new Matrix(m);
        assertEquals(mat1.get(0,0),3,1.);
        assertEquals(mat1.get(0,2),0,1.);
    }

    @Test
    public void set() throws Exception {
        Matrix m=new Matrix(2,5);
        m.set(0,3,2);
        assertEquals(m.data[0*m.cols+3],2,1.);

        m.set(1,1,5);
        assertEquals(m.data[1*m.cols+1],5,1.);
    }

    @Test
    public void testtoString() throws Exception {

        int r1=0;
        int r2=0;
        int cols=0;

        double[][] p = new double[][]{{1,2},{0,1}};
        Matrix m = new Matrix(p);
        String buf=m.toString();

        for(int i=0; i<buf.length();i++)
        {
            if(buf.charAt(i)=='[')
                r1++;
            if(buf.charAt(i)==']')
                r2++;
            if(buf.charAt(i)==',')
                cols++;
        }

        assertEquals(r1,r2);
        assertEquals(r1,m.rows);
        assertEquals(cols/m.rows,m.cols-1);

        r1=0;
        r2=0;
        cols=0;

        double[][] s= new double[][]{{2,1,1},{0,1,0},{3,5,0}};
        Matrix m2 = new Matrix(s);
        String buf2=m2.toString();

        for(int i=0; i<buf2.length();i++)
        {
            if(buf2.charAt(i)=='[')
                r1++;
            if(buf2.charAt(i)==']')
                r2++;
            if(buf2.charAt(i)==',')
                cols++;
        }

        assertEquals(r1,r2);
        assertEquals(r1,m2.rows);
        assertEquals(cols/m2.rows,m2.cols-1);
    }


    @Test(expected = RuntimeException.class)
    public void WrongArgumentsInReshapeFunction() {
        Matrix m=new Matrix(4,6);
        m.reshape(2, 5);

        Matrix k=new Matrix(2,4);
        k.reshape(1, 3);

    }

    @Test(expected = RuntimeException.class)
    public void WrongArgumentsInAddMulSubDivFunctions() {
        double[][] n = new double [][]{{1,1},{0,1},{2,3},{1,2}};
        double[][] o = new double [][] {{2,4,5},{6,7},{2,8}};
        Matrix m1=new Matrix(n);
        Matrix m2=new Matrix(o);
        Matrix m3=m1.add(m2);
        Matrix m4=m1.sub(m2);
        Matrix m5=m1.mul(m2);
        Matrix m6=m1.div(m2);
    }

    @Test(expected = RuntimeException.class)
    public void DividingbyZeroinDivFunction() {
        double[][] p =new double[][]{{1,2},{3,1}};
        Matrix m1=new Matrix(p);
        double[][] t =new double[][]{{1,2},{3,0}};
        Matrix m2=new Matrix(t);
        Matrix m3=m1.div(m2);
    }

    @Test(expected = RuntimeException.class)
    public void WrongSizesofMatrixesInDotFunction() {

        Matrix m1=new Matrix(4,6);
        Matrix m2=new Matrix(2,5);
        Matrix m3=m1.dot(m2);
    }

    @Test
    public void add() throws Exception {
        double[][] x =new double[][]{{1,2},{0,1}};
        double[][] y =new double[][]{{2,3},{1,1}};
        double[][] b=new double[][]{{3,5},{1,2}};
        Matrix m1=new Matrix(x);
        Matrix m2=new Matrix(y);
        Matrix m3=m1.add(m2);
        double[][] z=m3.asArray();
        assertArrayEquals(z[0],b[0],1.);
        assertArrayEquals(z[1],b[1],1.);
    }

    @Test
    public void sub() throws Exception {
        double[][] x =new double[][]{{1,2},{0,1}};
        double[][] y =new double[][]{{2,3},{1,1}};
        double[][] b=new double[][]{{-1,-1},{-1,0}};
        Matrix m1=new Matrix(x);
        Matrix m2=new Matrix(y);
        Matrix m3=m1.sub(m2);
        double[][] z=m3.asArray();
        assertArrayEquals(z[0],b[0],1.);
        assertArrayEquals(z[1],b[1],1.);
    }

    @Test
    public void mul() throws Exception {
        double[][] x =new double[][]{{1,2},{0,1}};
        double[][] y =new double[][]{{2,3},{1,1}};
        double[][] b=new double[][]{{2,6},{0,1}};
        Matrix m1=new Matrix(x);
        Matrix m2=new Matrix(y);
        Matrix m3=m1.mul(m2);
        double[][] z=m3.asArray();
        assertArrayEquals(z[0],b[0],1.);
        assertArrayEquals(z[1],b[1],1.);
    }

    @Test
    public void div() throws Exception {
        double[][] x =new double[][]{{1,2},{0,1}};
        double[][] y =new double[][]{{2,3},{1,1}};
        double[][] b=new double[][]{{0.5,2/3},{0,1}};
        Matrix m1=new Matrix(x);
        Matrix m2=new Matrix(y);
        Matrix m3=m1.div(m2);
        double[][] z=m3.asArray();
        assertArrayEquals(z[0],b[0],1.);
        assertArrayEquals(z[1],b[1],1.);
    }

    @Test
    public void dot() throws Exception {

        double[][] o = new double [][]{{1,1},{0,1},{2,3},{1,2}};
        double[][] p =new double[][]{{1,2},{0,1}};
        double[][] t=new double [][]{{1,3},{0,1},{2,7},{1,4}};
        Matrix mat1=new Matrix(o);
        Matrix mat2=new Matrix(p);
        Matrix mul=mat1.dot(mat2);
        double [][] s=mul.asArray();
        assertArrayEquals(s[0],t[0],1.);
        assertArrayEquals(s[1],t[1],1.);
        assertArrayEquals(s[2],t[2],1.);
        assertArrayEquals(s[3],t[3],1.);

        double[][] m = new double[][] { {3},{1,2}, { 1, 2, 2 } };
        double[][] n= new double[][]{{2,1,1},{0,1,0},{3,5,0}};
        double[][] k=new double [][]{{6,3,3},{2,3,1},{8,13,1}};
        Matrix mat3=new Matrix(m);
        Matrix mat4=new Matrix(n);
        Matrix mul2=mat3.dot(mat4);
        double [][] d=mul2.asArray();
        assertArrayEquals(d[0],k[0],1.);
        assertArrayEquals(d[1],k[1],1.);
        assertArrayEquals(d[2],k[2],1.);

        double[][] g = new double[][] {{1,2},{0,4}};
        double[][] h= new double[][]{{3,1},{2,0}};
        double[][] j=new double [][]{{7,1},{8,0}};
        Matrix mat5=new Matrix(g);
        Matrix mat6=new Matrix(h);
        Matrix mul3=mat5.dot(mat6);
        double [][] i=mul3.asArray();
        assertArrayEquals(i[0],j[0],1.);
        assertArrayEquals(i[1],j[1],1.);
    }


    @Test
    public void frobenius() throws Exception {

        double[][] j=new double [][]{{7,1},{8,0}};
        Matrix m1=new Matrix(j);
        assertEquals(m1.frobenius(),114,.1);

        double[][] m = new double[][] { {3},{1,2}, { 1, 2, 2 } };
        Matrix m2=new Matrix(m);
        assertEquals(m2.frobenius(),23,.1);
    }
    //KARTKÓWKA2 - GRUPA C//

    ////Test wyrzucania wyjatku, gdy sumR ma zle parametry//

    @Test(expected = RuntimeException.class)
    public void WrongShapeofSumR() {
        int m=10;
        int n=5;
        Matrix mat=new Matrix(m,n);
        Matrix sumR=new Matrix(9,2);
        sumR=mat;
    }


    ////FUNKCJA SPRAWDZAJĄCA SAMO SUMOWANIE//

    @Test
    public void sumRows() throws Exception{


        int  m= new  Random().nextInt(100)+1;
        int n= new  Random().nextInt(100)+1;
        double delta = new Random().nextDouble();
        Matrix mat=new Matrix(m,n);
        double wiersz=0;

        for(int i=0; i<mat.rows;i++)
        {
            for(int j=0; j<mat.cols;j++)
            {
                mat.data[i*mat.cols+j]+=delta;
            }
        }

        Matrix sumR= mat.sumRows();

        if(sumR.rows!=mat.rows || sumR.cols!=1)
            throw new RuntimeException(String.format("wrong size of matrix!"));

        for(int i=0; i<mat.rows;i++)
        {
            wiersz=((mat.data[i*mat.cols+0]+mat.data[i*mat.cols+(mat.rows-1)])*mat.rows)/2;
            assertEquals(sumR.data[i],wiersz, .1);
            wiersz=0;
        }
    }

    //////////


}