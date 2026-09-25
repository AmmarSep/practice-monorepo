import java.util.Scanner;

public class Q8_MatrixMultiplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter rows and columns for matrix A: ");
        int r1 = sc.nextInt(), c1 = sc.nextInt();
        System.out.print("Enter rows and columns for matrix B: ");
        int r2 = sc.nextInt(), c2 = sc.nextInt();
        if (c1 != r2) {
            System.out.println("Matrix multiplication not possible");
            return;
        }
        int[][] a = new int[r1][c1], b = new int[r2][c2], c = new int[r1][c2];
        System.out.println("Enter matrix A:");
        for (int i = 0; i < r1; i++)
            for (int j = 0; j < c1; j++)
                a[i][j] = sc.nextInt();
        System.out.println("Enter matrix B:");
        for (int i = 0; i < r2; i++)
            for (int j = 0; j < c2; j++)
                b[i][j] = sc.nextInt();
        for (int i = 0; i < r1; i++)
            for (int j = 0; j < c2; j++)
                for (int k = 0; k < c1; k++)
                    c[i][j] += a[i][k] * b[k][j];
        System.out.println("Result:");
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++)
                System.out.print(c[i][j] + " ");
            System.out.println();
        }
        sc.close();
    }
}
