package gamesoftitalia.bizbong.entity;

import static java.lang.Math.sqrt;

/**
 * Created by gamesoftitalia on 21/12/2016.
 */

public class SoluzioneSudoBizBong {

    private int max;
    private int[][] sudokuInziale;    //sudoku con la traccia
    private int[][] sudoku;            // sudoku completato dall utente
    private boolean esito;


    public SoluzioneSudoBizBong(int max, int [][] sudoku_inziale, int [][] sudoku){
        this.max=max;
        this.sudokuInziale=sudoku_inziale;
        this.sudoku=sudoku;
        soluzioneSudoku(0,0);
    }

    private void soluzioneSudoku(int riga,int colonna){
        if (riga>max-1){
            if (confrontaSudoku()==true)
                esito=true;
            else
                esito=false;
            return;

        }
        if (sudokuInziale[riga][colonna]!=-1)
            avanza(riga,colonna);
        else{
            int numero;
            for(numero=-1;numero<max;numero++){
                if((controllaRiga(riga,numero)==1)&&(controllaColonna(colonna,numero)==1)&&(controllaQuadrante(riga,colonna,numero)==1)){         /*condizioni riga,colonna e quadrante per vedere se c'è già*/
                    sudokuInziale[riga][colonna]=numero;
                    avanza(riga,colonna);
                }
            }
            sudokuInziale[riga][colonna]=-1;
        }
        return;
    }


    private void avanza(int riga,int colonna){
        if (colonna<max-1)
            soluzioneSudoku(riga,colonna+1);
        else
            soluzioneSudoku(riga+1,0);
        return;
    }

    private int controllaRiga(int riga,int nx){
        int j;
        for(j=0;j<max;j++)
            if (sudokuInziale[riga][j]==nx)
                return 0;
        return 1;
    }

    private int controllaColonna(int colonna,int nx){
        int i;
        for(i=0;i<max;i++)
            if (sudokuInziale[i][colonna]==nx)
                return 0;
        return 1;
    }

    private int controllaQuadrante(int riga,int colonna,int nx){
        int i,j,n;
        n=(int) sqrt(max);
        riga= (riga/n)*n;               /*mettere rad quadrata di max*/
        colonna=(colonna/n)*n;
        for(i=0;i<n;i++)
            for(j=0;j<n;j++)
                if(sudokuInziale[riga+i][colonna+j]==nx)
                    return 0;
        return 1;
    }

    private boolean confrontaSudoku(){
        int i=0;
        while (i<max*max){
            if(sudokuInziale[i/max][i%max]!=sudoku[i/max][i%max])
                return false;
            i++;
        }
        return true;
    }

    public int[][] getSudokuInziale() {
        return sudokuInziale;
    }

    public int getMax() {
        return max;
    }

    public int[][] getSudoku() {
        return sudoku;
    }

    public boolean getEsito(){
        return esito;
    }

    public void setSudokuInziale(int[][] sudokuInziale) {
        this.sudokuInziale = sudokuInziale;
    }

    public void setSudoku(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setEsito(boolean esito) {
        this.esito = esito;
    }
}
