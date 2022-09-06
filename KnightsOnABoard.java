import java.io.*;
import java.util.Scanner;

public class KnightsOnABoard {
    static Scanner scan = new Scanner(System.in);

    public static File validateFile(File inputFile) {
        boolean isValid = inputFile.exists();
        while (!isValid) {
            System.out.println("File does not exist, enter valid file.");
            String file = scan.nextLine();
            inputFile = new File(file);
            isValid = inputFile.exists();
        }
        return inputFile;
    }

    public static boolean validateData(File inputFile) throws IOException {
        Scanner fileScan = new Scanner(inputFile);
        int countLine = 0;
        while (fileScan.hasNextLine()) {
            countLine++;
            String line = fileScan.nextLine();
            if (line.length() != 15) {
                System.out.println("Not 8 by 8");
                return false;
            }
        }
        if (countLine != 8) {
            System.out.println("Not 8 by 8");
            return false;
        }

        fileScan = new Scanner(inputFile);
        while (fileScan.hasNext()) {
            if (fileScan.hasNext() && !fileScan.hasNextInt()) {
                System.out.println("Invalid Data");
                return false;
            } else
                fileScan.next();

        }
        return true;
    }

    public static int[][] populateBoard(File inputFile) throws IOException {
        int[][] nums = new int[8][8];
        Scanner fileScan = new Scanner(inputFile);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                nums[i][j] = fileScan.nextInt();

                if (nums[i][j] > 1)
                    nums[i][j] = 1;
                else if (nums[i][j] < 0)
                    nums[i][j] = 0;
            }
        }

        return nums;

    }

    public static boolean cannotCapture(int[][] chessBoard) {
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                if (chessBoard[i][j] == 1) {
                    if (i - 2 >= 0) {
                        if (j - 1 >= 0 && chessBoard[i - 2][j - 1] == 1)
                            return false;

                        if (j + 1 < chessBoard[i].length && chessBoard[i - 2][j + 1] == 1)
                            return false;
                    }

                    if (i - 1 >= 0) {
                        if (j - 2 >= 0 && chessBoard[i - 1][j - 2] == 1)
                            return false;

                        if (j + 2 < chessBoard[i].length && chessBoard[i - 1][j + 2] == 1)
                            return false;
                    }

                    if (i + 2 < chessBoard.length) {
                        if (j - 1 >= 0 && chessBoard[i + 2][j - 1] == 1)
                            return false;

                        if (j + 1 < chessBoard[i].length && chessBoard[i + 2][j + 1] == 1)
                            return false;
                    }

                    if (i + 1 < chessBoard.length) {
                        if (j - 2 >= 0 && chessBoard[i + 1][j - 2] == 1)
                            return false;

                        if (j + 2 < chessBoard[i].length && chessBoard[i + 1][j + 2] == 1)
                            return false;
                    }
                }
            }

        }
        return true;

    }

    public static void printBoard(int[][] chessBoard) {
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                System.out.print(chessBoard[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter file name.");
        String fileName = input.nextLine();
        File file = new File(fileName);
        File validFile = validateFile(file);
        int[][] chessBoard;
        while (!validateData(validFile)) {
            System.out.println("Enter file name with valid data");
            String name = input.nextLine();
            File tempFile = new File(name);
            validFile = validateFile(tempFile);
        }
        chessBoard = populateBoard(validFile);
        System.out.println("This is how the chessboard looks.");
        printBoard(chessBoard);

        if (cannotCapture(chessBoard))
            System.out.println("No knights can capture any other knights");
        else
            System.out.println("At least one knight can capture another ght");
    }
}