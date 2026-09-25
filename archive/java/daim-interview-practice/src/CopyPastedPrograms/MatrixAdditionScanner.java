package CopyPastedPrograms;

import java.util.Scanner;

public class MatrixAdditionScanner
{
    public static void main(String args[])
    {
        int i, j;
        int mat1[][] = new int[3][3];
        int mat2[][] = new int[3][3];
        int res[][] = new int[3][3];

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter matrix 1 elements : ");
        for(i=0; i<3; i++){
            for(j=0; j<3; j++){
                mat1[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Enter matrix 2 elements : ");
        for(i=0; i<3; i++){
            for(j=0; j<3; j++){
                mat2[i][j] = scanner.nextInt();
            }
        }

        for(i=0; i<3; i++){
            for(j=0; j<3; j++){
                res[i][j] = mat1[i][j] + mat2[i][j];
            }
        }

        System.out.print("The new matrix is :\n");
        for(i=0; i<3; i++){
            for(j=0; j<3; j++){
                System.out.print(res[i][j]+ " ");
            }
            System.out.println();
        }
    }
}
