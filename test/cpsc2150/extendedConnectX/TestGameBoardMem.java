package cpsc2150.extendedConnectX;

import java.util.*;;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameBoardMem {
    private IGameBoard MakeAGameBoard(int r, int c, int numTokens) {
        IGameBoard temp = new GameBoardMem(r,c,numTokens);
        return temp;
    }

    private String printGameBoard(char[][] board){
        String str = "";
        int rowSize = board.length;
        int columnSize = board[0].length;
        // prints header
        for (int i=0; i<columnSize; i++){
            str += " | " +i;
            if (i==columnSize-1){
                str += " | \n";
            }
        }
        // prints contents
        for (int i=rowSize-1; i>=0; i--){
            for (int j=0; j<columnSize; j++){
                // correction for board aesthetics destroying due to double digit header
                if (j < 10) {
                    str += " | " + board[i][j];
                } else {
                    str += " | " + board[i][j] + " ";
                }
                if (j==columnSize-1){
                    str += " | \n";
                }
            }
        }
        return str;
    }

    @Test
    public void IGameBoard_mMax_rowSize_Min_columnSize(){
        int row = 99;
        int column = 3;
        int numTokensToWin = 3;
        IGameBoard method = MakeAGameBoard(row,column,numTokensToWin);
        int expectedRow = row;
        int expectedColumn = column;
        int actualRow = method.getNumRows();
        int actualColumn = method.getNumColumns();
        assertEquals(expectedRow,actualRow);
        assertEquals(expectedColumn,actualColumn);
    }

    @Test
    public void IGameBoard_Min_rowSize_Max_columnSize(){
        int row = 3;
        int column = 99;
        int numTokensToWin = 3;
        IGameBoard method = MakeAGameBoard(row,column,numTokensToWin);
        int expectedRow = row;
        int expectedColumn = column;
        int actualRow = method.getNumRows();
        int actualColumn = method.getNumColumns();
        assertEquals(expectedRow,actualRow);
        assertEquals(expectedColumn,actualColumn);
    }

    @Test
    public void IGameBoard_numOfTokensToWin_One_Less_Than_Lowest_of_ROWorCOLUMN(){
        int row = 15;
        int column = 21;
        int numTokensToWin = 14;
        IGameBoard method = MakeAGameBoard(row,column,numTokensToWin);
        int expectedTokenNumber = numTokensToWin;
        int actualTokenNumber = method.getNumToWin();
        assertEquals(expectedTokenNumber,actualTokenNumber);
    }

    @Test
    public void checkIfFree_One_Space_Left_in_Column(){
        IGameBoard method = MakeAGameBoard(5,5,3);
        char[][] manualBoard = new char[5][5];
        for (int i=0; i<5; i++)
            for (int j=0; j<5; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',1);
        method.placeToken('O',1);
        method.placeToken('X',1);
        method.placeToken('O',1);
        boolean actualFree = method.checkIfFree(1);
        manualBoard[0][1] = 'X';
        manualBoard[1][1] = 'O';
        manualBoard[2][1] = 'X';
        manualBoard[3][1] = 'O';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(actualFree);
    }

    @Test
    public void checkIfFree_One_Space_Left_in_Row(){
        IGameBoard method = MakeAGameBoard(5,5,3);
        char[][] manualBoard = new char[5][5];
        for (int i=0; i<5; i++)
            for (int j=0; j<5; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',1);
        method.placeToken('X',2);
        method.placeToken('O',3);
        boolean actualFree = method.checkIfFree(4);
        manualBoard[0][0] = 'X';
        manualBoard[0][1] = 'O';
        manualBoard[0][2] = 'X';
        manualBoard[0][3] = 'O';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(actualFree);
    }

    @Test
    public void checkIfFree_One_Space_Left_in_Board(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',1);
        method.placeToken('X',0);
        method.placeToken('O',0);
        method.placeToken('X',1);
        method.placeToken('O',1);
        method.placeToken('X',2);
        method.placeToken('O',2);
        boolean actualFree = method.checkIfFree(2);
        manualBoard[2][0] = 'O';
        manualBoard[2][1] = 'O';
        manualBoard[1][2] = 'O';
        manualBoard[1][1] = 'X';
        manualBoard[0][2] = 'X';
        manualBoard[0][1] = 'O';
        manualBoard[0][0] = 'X';
        manualBoard[1][0] = 'X';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(actualFree);
    }

    @Test
    public void checkHorizWin_Insert_Token_at_End(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',0);
        method.placeToken('X',1);
        method.placeToken('O',1);
        method.placeToken('X',2);
        BoardPosition pos = new BoardPosition(0,2);
        boolean actualWin = method.checkHorizWin(pos,'X');
        manualBoard[0][2] = 'X';
        manualBoard[1][0] = 'O';
        manualBoard[0][1] = 'X';
        manualBoard[1][1] = 'O';
        manualBoard[0][0] = 'X';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(actualWin);
    }

    @Test
    public void checkHorizWin_Insert_Token_at_Middle(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',0);
        method.placeToken('X',2);
        method.placeToken('O',2);
        method.placeToken('X',1);
        BoardPosition pos = new BoardPosition(0,0);
        boolean actualWin = method.checkHorizWin(pos,'X');
        manualBoard[0][2] = 'X';
        manualBoard[1][0] = 'O';
        manualBoard[0][1] = 'X';
        manualBoard[1][2] = 'O';
        manualBoard[0][0] = 'X';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(actualWin);
    }

    @Test
    public void checkHorizWin_Insert_Token_Front(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',1);
        method.placeToken('O',1);
        method.placeToken('X',2);
        method.placeToken('O',2);
        method.placeToken('X',0);
        BoardPosition pos = new BoardPosition(0,0);
        boolean actualWin = method.checkHorizWin(pos,'X');
        manualBoard[0][1] = 'X';
        manualBoard[1][1] = 'O';
        manualBoard[0][0] = 'X';
        manualBoard[1][2] = 'O';
        manualBoard[0][2] = 'X';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(actualWin);
    }

    @Test
    public void checkHorizWin_in_Middle_Row(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',0);
        method.placeToken('X',1);
        method.placeToken('O',1);
        method.placeToken('X',0);
        method.placeToken('O',2);
        method.placeToken('X',1);
        method.placeToken('O',2);
        BoardPosition pos = new BoardPosition(1,2);
        boolean actualWin = method.checkHorizWin(pos,'O');
        manualBoard[0][0] = 'X';
        manualBoard[1][0] = 'O';
        manualBoard[0][1] = 'X';
        manualBoard[1][1] = 'O';
        manualBoard[2][0] = 'X';
        manualBoard[0][2] = 'O';
        manualBoard[2][1] = 'X';
        manualBoard[1][2] = 'O';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(actualWin);
    }

    @Test
    public void checkVertWin_Left_of_Board(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',1);
        method.placeToken('X',0);
        method.placeToken('O',1);
        method.placeToken('X',0);
        BoardPosition pos = new BoardPosition(2,0);
        boolean actualWin = method.checkVertWin(pos,'X');
        manualBoard[2][0] = 'X';
        manualBoard[0][1] = 'O';
        manualBoard[1][0] = 'X';
        manualBoard[1][1] = 'O';
        manualBoard[0][0] = 'X';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(actualWin);
    }

    @Test
    public void checkVertWin_Middle_of_Board(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',1);
        method.placeToken('O',0);
        method.placeToken('X',1);
        method.placeToken('O',0);
        method.placeToken('X',1);
        BoardPosition pos = new BoardPosition(2,1);
        boolean actualWin = method.checkVertWin(pos,'X');
        manualBoard[2][1] = 'X';
        manualBoard[0][0] = 'O';
        manualBoard[1][1] = 'X';
        manualBoard[1][0] = 'O';
        manualBoard[0][1] = 'X';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(actualWin);
    }

    @Test
    public void checkVertWin_Right_of_Board(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',2);
        method.placeToken('O',1);
        method.placeToken('X',2);
        method.placeToken('O',1);
        method.placeToken('X',2);
        BoardPosition pos = new BoardPosition(2,2);
        boolean actualWin = method.checkVertWin(pos,'X');
        manualBoard[2][2] = 'X';
        manualBoard[0][1] = 'O';
        manualBoard[1][2] = 'X';
        manualBoard[1][1] = 'O';
        manualBoard[0][2] = 'X';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(actualWin);
    }

    @Test
    public void checkVertWin_Top_of_Board(){
        IGameBoard method = MakeAGameBoard(4,4,3);
        char[][] manualBoard = new char[4][4];
        for (int i=0; i<4; i++)
            for (int j=0; j<4; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',1);
        method.placeToken('X',1);
        method.placeToken('O',0);
        method.placeToken('X',1);
        method.placeToken('O',0);
        method.placeToken('X',1);
        BoardPosition pos = new BoardPosition(3,1);
        boolean actualWin = method.checkVertWin(pos,'X');
        manualBoard[0][0] = 'X';
        manualBoard[0][1] = 'O';
        manualBoard[1][1] = 'X';
        manualBoard[1][0] = 'O';
        manualBoard[2][1] = 'X';
        manualBoard[2][0] = 'O';
        manualBoard[3][1] = 'X';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(actualWin);
    }

    @Test
    public void checkDiagWin_Token_Dropped_at_Top_Right_End(){
        IGameBoard method = MakeAGameBoard(5,5,4);
        char[][] manualBoard = new char[5][5];
        for (int i=0; i<5; i++)
            for (int j=0; j<5; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',1);
        method.placeToken('X',1);
        method.placeToken('O',2);
        method.placeToken('X',2);
        method.placeToken('O',3);
        method.placeToken('X',2);
        method.placeToken('O',3);
        method.placeToken('X',3);
        method.placeToken('O',4);
        method.placeToken('X',3);
        BoardPosition pos = new BoardPosition(3,3);
        boolean actualWin = method.checkDiagWin(pos,'X');
        manualBoard[0][0] = 'X';
        manualBoard[1][1] = 'X';
        manualBoard[2][2] = 'X';
        manualBoard[1][2] = 'X';
        manualBoard[3][3] = 'X';
        manualBoard[2][3] = 'X';
        manualBoard[0][1] = 'O';
        manualBoard[0][2] = 'O';
        manualBoard[0][3] = 'O';
        manualBoard[0][4] = 'O';
        manualBoard[1][3] = 'O';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(actualWin);
    }

    @Test
    public void checkDiagWin_Token_Dropped_at_Middle(){
        IGameBoard method = MakeAGameBoard(5,5,4);
        char[][] manualBoard = new char[5][5];
        for (int i=0; i<5; i++)
            for (int j=0; j<5; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',1);
        method.placeToken('X',1);
        method.placeToken('O',2);
        method.placeToken('X',2);
        method.placeToken('O',3);
        method.placeToken('X',3);
        method.placeToken('O',3);
        method.placeToken('X',3);
        method.placeToken('O',4);
        method.placeToken('X',2);
        BoardPosition pos = new BoardPosition(2,2);
        boolean actualWin = method.checkDiagWin(pos,'X');
        manualBoard[0][0] = 'X';
        manualBoard[1][1] = 'X';
        manualBoard[1][2] = 'X';
        manualBoard[2][2] = 'X';
        manualBoard[3][3] = 'X';
        manualBoard[1][3] = 'X';
        manualBoard[0][1] = 'O';
        manualBoard[0][2] = 'O';
        manualBoard[0][3] = 'O';
        manualBoard[0][4] = 'O';
        manualBoard[2][3] = 'O';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(actualWin);
    }

    @Test
    public void checkDiagWin_Token_Dropped_at_Top_Left_End(){
        IGameBoard method = MakeAGameBoard(5,5,4);
        char[][] manualBoard = new char[5][5];
        for (int i=0; i<5; i++)
            for (int j=0; j<5; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',3);
        method.placeToken('O',2);
        method.placeToken('X',2);
        method.placeToken('O',1);
        method.placeToken('X',1);
        method.placeToken('O',0);
        method.placeToken('X',1);
        method.placeToken('O',0);
        method.placeToken('X',0);
        method.placeToken('O',4);
        method.placeToken('X',0);
        BoardPosition pos = new BoardPosition(3,0);
        boolean actualWin = method.checkDiagWin(pos,'X');
        manualBoard[0][3] = 'X';
        manualBoard[1][2] = 'X';
        manualBoard[1][1] = 'X';
        manualBoard[2][1] = 'X';
        manualBoard[2][0] = 'X';
        manualBoard[3][0] = 'X';
        manualBoard[0][4] = 'O';
        manualBoard[0][2] = 'O';
        manualBoard[0][1] = 'O';
        manualBoard[0][0] = 'O';
        manualBoard[1][0] = 'O';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(actualWin);
    }

    @Test
    public void checkDiagWin_Token_Dropped_at_Bottom_Right_End(){
        IGameBoard method = MakeAGameBoard(5,5,3);
        char[][] manualBoard = new char[5][5];
        for (int i=0; i<5; i++)
            for (int j=0; j<5; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',2);
        method.placeToken('O',1);
        method.placeToken('X',2);
        method.placeToken('O',1);
        method.placeToken('X',1);
        method.placeToken('O',0);
        method.placeToken('X',3);
        BoardPosition pos = new BoardPosition(0,3);
        boolean actualWin = method.checkDiagWin(pos,'X');
        manualBoard[0][3] = 'X';
        manualBoard[0][2] = 'X';
        manualBoard[1][2] = 'X';
        manualBoard[2][1] = 'X';
        manualBoard[0][1] = 'O';
        manualBoard[0][0] = 'O';
        manualBoard[1][1] = 'O';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(actualWin);
    }

    @Test
    public void checkDiagWin_Token_Dropped_at_Bottom_Left_End(){
        IGameBoard method = MakeAGameBoard(5,5,3);
        char[][] manualBoard = new char[5][5];
        for (int i=0; i<5; i++)
            for (int j=0; j<5; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',1);
        method.placeToken('O',2);
        method.placeToken('X',1);
        method.placeToken('O',2);
        method.placeToken('X',2);
        method.placeToken('O',3);
        method.placeToken('X',0);
        BoardPosition pos = new BoardPosition(0,0);
        boolean actualWin = method.checkDiagWin(pos,'X');
        manualBoard[0][0] = 'X';
        manualBoard[0][1] = 'X';
        manualBoard[1][1] = 'X';
        manualBoard[2][2] = 'X';
        manualBoard[0][2] = 'O';
        manualBoard[0][3] = 'O';
        manualBoard[1][2] = 'O';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(actualWin);
    }

    @Test
    public void checkDiagWin_in_Middle_of_Board_Going_to_Top_Right(){
        IGameBoard method = MakeAGameBoard(5,5,3);
        char[][] manualBoard = new char[5][5];
        for (int i=0; i<5; i++)
            for (int j=0; j<5; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',1);
        method.placeToken('O',0);
        method.placeToken('X',1);
        method.placeToken('O',2);
        method.placeToken('X',2);
        method.placeToken('O',3);
        method.placeToken('X',2);
        method.placeToken('O',3);
        method.placeToken('X',4);
        method.placeToken('O',3);
        method.placeToken('X',3);
        BoardPosition pos = new BoardPosition(3,3);
        boolean actualWin = method.checkDiagWin(pos,'X');
        manualBoard[0][1] = 'X';
        manualBoard[1][1] = 'X';
        manualBoard[1][2] = 'X';
        manualBoard[2][2] = 'X';
        manualBoard[0][4] = 'X';
        manualBoard[3][3] = 'X';
        manualBoard[0][0] = 'O';
        manualBoard[0][2] = 'O';
        manualBoard[0][3] = 'O';
        manualBoard[1][3] = 'O';
        manualBoard[2][3] = 'O';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(actualWin);
    }

    @Test
    public void checkDiagWin_in_Middle_of_Board_Going_to_Top_Left(){
        IGameBoard method = MakeAGameBoard(5,5,3);
        char[][] manualBoard = new char[5][5];
        for (int i=0; i<5; i++)
            for (int j=0; j<5; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',3);
        method.placeToken('O',4);
        method.placeToken('X',3);
        method.placeToken('O',2);
        method.placeToken('X',2);
        method.placeToken('O',1);
        method.placeToken('X',2);
        method.placeToken('O',1);
        method.placeToken('X',1);
        method.placeToken('O',0);
        method.placeToken('X',1);
        BoardPosition pos = new BoardPosition(3,1);
        boolean actualWin = method.checkDiagWin(pos,'X');
        manualBoard[0][3] = 'X';
        manualBoard[1][3] = 'X';
        manualBoard[1][2] = 'X';
        manualBoard[1][2] = 'X';
        manualBoard[2][2] = 'X';
        manualBoard[2][1] = 'X';
        manualBoard[3][1] = 'X';
        manualBoard[0][4] = 'O';
        manualBoard[0][2] = 'O';
        manualBoard[0][1] = 'O';
        manualBoard[0][0] = 'O';
        manualBoard[1][1] = 'O';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(actualWin);
    }

    @Test
    public void checkTie_Full_GameBoard_Tie_at_Top_Right(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',1);
        method.placeToken('X',0);
        method.placeToken('O',0);
        method.placeToken('X',2);
        method.placeToken('O',1);
        method.placeToken('X',1);
        method.placeToken('O',2);
        method.placeToken('X',2);
        boolean actualTie = method.checkTie();
        manualBoard[0][0] = 'X';
        manualBoard[1][0] = 'X';
        manualBoard[2][1] = 'X';
        manualBoard[0][2] = 'X';
        manualBoard[2][2] = 'X';
        manualBoard[0][1] = 'O';
        manualBoard[1][1] = 'O';
        manualBoard[1][2] = 'O';
        manualBoard[2][0] = 'O';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(actualTie);
    }

    @Test
    public void checkTie_Not_Full_GameBoard_No_Tie(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',0);
        method.placeToken('X',0);
        boolean actualTie = method.checkTie();
        manualBoard[2][0] = 'X';
        manualBoard[0][0] = 'X';
        manualBoard[1][0] = 'O';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(!actualTie);
    }

    @Test
    public void checkTie_emptyGameBoard_No_Tie(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        boolean actualTie = method.checkTie();
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(!actualTie);
    }

    @Test
    public void checkTie_Full_GameBoard_Tie_at_Top_Left(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',2);
        method.placeToken('O',1);
        method.placeToken('X',2);
        method.placeToken('O',2);
        method.placeToken('X',0);
        method.placeToken('O',1);
        method.placeToken('X',1);
        method.placeToken('O',0);
        method.placeToken('X',0);
        boolean actualTie = method.checkTie();
        manualBoard[0][2] = 'X';
        manualBoard[1][2] = 'X';
        manualBoard[2][1] = 'X';
        manualBoard[2][0] = 'X';
        manualBoard[0][0] = 'X';
        manualBoard[0][1] = 'O';
        manualBoard[1][1] = 'O';
        manualBoard[2][2] = 'O';
        manualBoard[1][0] = 'O';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(actualTie);
    }

    @Test
    public void whatsAtPos_End_of_Full_Row(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',1);
        method.placeToken('X',2);
        BoardPosition pos = new BoardPosition(0,2);
        char actualCharacter = method.whatsAtPos(pos);
        char expectedCharacter = 'X';
        manualBoard[0][0] = 'X';
        manualBoard[0][1] = 'O';
        manualBoard[0][2] = 'X';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertEquals(expectedCharacter,actualCharacter);
    }

    @Test
    public void whatsAtPos_Empty_Board(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        BoardPosition pos = new BoardPosition(0,0);
        char actualCharacter = method.whatsAtPos(pos);
        char expectedCharacter = ' ';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertEquals(expectedCharacter,actualCharacter);
    }

    @Test
    public void whatsAtPos_End_of_Full_Column(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',0);
        method.placeToken('X',0);
        BoardPosition pos = new BoardPosition(2,0);
        char actualCharacter = method.whatsAtPos(pos);
        char expectedCharacter = 'X';
        manualBoard[2][0] = 'X';
        manualBoard[1][0] = 'O';
        manualBoard[0][0] = 'X';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertEquals(expectedCharacter,actualCharacter);
    }

    @Test
    public void whatsAtPos_After_One_Insert_in_Column_0(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',0);
        BoardPosition pos = new BoardPosition(1,0);
        char actualCharacter = method.whatsAtPos(pos);
        char expectedCharacter = 'O';
        manualBoard[0][0] = 'X';
        manualBoard[1][0] = 'O';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertEquals(expectedCharacter,actualCharacter);
    }

    @Test
    public void whatsAtPos_After_One_Insert_in_Middle_Column(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',1);
        method.placeToken('O',1);
        BoardPosition pos = new BoardPosition(1,1);
        char actualCharacter = method.whatsAtPos(pos);
        char expectedCharacter = 'O';
        manualBoard[0][1] = 'X';
        manualBoard[1][1] = 'O';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertEquals(expectedCharacter,actualCharacter);
    }

    @Test
    public void isPlayerAtPos_End_of_Full_Row(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',1);
        method.placeToken('X',2);
        BoardPosition pos = new BoardPosition(0,2);
        char expectedPlayer = 'X';
        boolean playerIsThere = method.isPlayerAtPos(pos,expectedPlayer);
        manualBoard[0][0] = 'X';
        manualBoard[0][1] = 'O';
        manualBoard[0][2] = 'X';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(playerIsThere);
    }

    @Test
    public void isPlayerAtPos_Insert_in_Empty_Board(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        BoardPosition pos = new BoardPosition(0,0);
        char expectedPlayer = ' ';
        boolean playerIsThere = method.isPlayerAtPos(pos,expectedPlayer);
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertTrue(playerIsThere);
    }

    @Test
    public void isPlayerAtPos_End_of_Full_Column(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',0);
        method.placeToken('X',0);
        BoardPosition pos = new BoardPosition(2,0);
        char expectedCharacter = 'X';
        char actualCharacter = method.whatsAtPos(pos);
        boolean playerIsThere = method.isPlayerAtPos(pos,expectedCharacter);
        manualBoard[2][0] = 'X';
        manualBoard[1][0] = 'O';
        manualBoard[0][0] = 'X';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertEquals(expectedCharacter,actualCharacter);
        assertTrue(playerIsThere);
    }

    @Test
    public void isPlayerAtPos_After_One_Insert_in_Middle(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',1);
        method.placeToken('O',1);
        BoardPosition pos = new BoardPosition(1,1);
        char expectedCharacter = 'O';
        char actualCharacter = method.whatsAtPos(pos);
        boolean playerIsThere = method.isPlayerAtPos(pos,expectedCharacter);
        manualBoard[0][1] = 'X';
        manualBoard[1][1] = 'O';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertEquals(expectedCharacter,actualCharacter);
        assertTrue(playerIsThere);
    }

    @Test
    public void isPlayerAtPos_Insert_To_Make_Full_Board(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',0);
        method.placeToken('X',0);
        method.placeToken('O',1);
        method.placeToken('X',1);
        method.placeToken('O',2);
        method.placeToken('X',1);
        method.placeToken('O',2);
        method.placeToken('X',2);
        BoardPosition pos = new BoardPosition(2,2);
        char expectedCharacter = 'X';
        char actualCharacter = method.whatsAtPos(pos);
        boolean playerIsThere = method.isPlayerAtPos(pos,expectedCharacter);
        manualBoard[0][0] = 'X';
        manualBoard[1][0] = 'O';
        manualBoard[2][0] = 'X';
        manualBoard[0][1] = 'O';
        manualBoard[1][1] = 'X';
        manualBoard[0][2] = 'O';
        manualBoard[2][1] = 'X';
        manualBoard[1][2] = 'O';
        manualBoard[2][2] = 'X';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertEquals(expectedCharacter,actualCharacter);
        assertTrue(playerIsThere);
    }

    @Test
    public void placeToken_Almost_Full_Board(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',0);
        method.placeToken('X',0);
        method.placeToken('O',1);
        method.placeToken('X',1);
        method.placeToken('O',1);
        method.placeToken('X',2);
        method.placeToken('O',2);
        method.placeToken('X',2);
        BoardPosition pos = new BoardPosition(2,2);
        char expectedToken = 'X';
        boolean correctToken = method.isPlayerAtPos(pos,expectedToken);
        char actualToken = method.whatsAtPos(pos);
        manualBoard[0][0] = 'X';
        manualBoard[0][1] = 'O';
        manualBoard[0][2] = 'X';
        manualBoard[1][0] = 'O';
        manualBoard[1][1] = 'X';
        manualBoard[1][2] = 'O';
        manualBoard[2][0] = 'X';
        manualBoard[2][1] = 'O';
        manualBoard[2][2] = 'X';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertEquals(expectedToken,actualToken);
        assertTrue(correctToken);
    }

    @Test
    public void placeToken_Almost_Full_Row(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',1);
        method.placeToken('X',2);
        BoardPosition pos = new BoardPosition(0,2);
        char expectedToken = 'X';
        boolean correctToken = method.isPlayerAtPos(pos,expectedToken);
        char actualToken = method.whatsAtPos(pos);
        manualBoard[0][0] = 'X';
        manualBoard[0][1] = 'O';
        manualBoard[0][2] = 'X';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertEquals(expectedToken,actualToken);
        assertTrue(correctToken);
    }

    @Test
    public void placeToken_Almost_Full_Column(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',0);
        method.placeToken('X',0);
        BoardPosition pos = new BoardPosition(2,0);
        char expectedToken = 'X';
        boolean correctToken = method.isPlayerAtPos(pos,expectedToken);
        char actualToken = method.whatsAtPos(pos);
        manualBoard[0][0] = 'X';
        manualBoard[1][0] = 'O';
        manualBoard[2][0] = 'X';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertEquals(expectedToken,actualToken);
        assertTrue(correctToken);
    }

    @Test
    public void placeToken_In_Empty_Board(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        BoardPosition pos = new BoardPosition(0,0);
        char expectedToken = 'X';
        boolean correctToken = method.isPlayerAtPos(pos,expectedToken);
        char actualToken = method.whatsAtPos(pos);
        manualBoard[0][0] = 'X';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertEquals(expectedToken,actualToken);
        assertTrue(correctToken);
    }

    @Test
    public void placeToken_In_Middle_of_Board(){
        IGameBoard method = MakeAGameBoard(3,3,3);
        char[][] manualBoard = new char[3][3];
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                manualBoard[i][j] = ' ';
        method.placeToken('X',0);
        method.placeToken('O',0);
        BoardPosition pos = new BoardPosition(1,0);
        char expectedToken = 'O';
        boolean correctToken = method.isPlayerAtPos(pos,expectedToken);
        char actualToken = method.whatsAtPos(pos);
        manualBoard[0][0] = 'X';
        manualBoard[1][0] = 'O';
        String expectedBoard = printGameBoard(manualBoard);
        String actualBoard = method.toString();
        assertEquals(expectedBoard,actualBoard);
        assertEquals(expectedToken,actualToken);
        assertTrue(correctToken);
    }

}
