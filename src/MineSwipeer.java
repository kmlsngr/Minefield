import java.util.*;

public class MineSwipeer {

    int row;
    int column;
    int userInpR,userInpC;
    String [][] mayinTarlasi;
    String [][] gameTable;
    boolean condition = true;
    Scanner input = new Scanner(System.in);
    MineSwipeer(int row, int column){
        this.row=row;
        this.column=column;
        this.mayinTarlasi = mayinTarlasi(); //return mayinTarlasi matrix
        this.gameTable = gameTable(this.row,this.column);

    }
    public void run() {
        System.out.println("Mayınların Konumu");
        printMayinTarlasi();
        System.out.println("===================================");
        System.out.println("Mayın Tarlası Oyununa Hoşgeldiniz !");
        printGameTable();

        while (condition){

            userInput();
            controlTable(this.mayinTarlasi,this.gameTable ,this.userInpR,this.userInpC);

        }
    }

    private void userInput() {
        boolean isValid = true;
        while (isValid){
            System.out.print("Enter row number: ");
            this.userInpR= input.nextInt()-1;
            System.out.print("Enter column number: ");
            this.userInpC= input.nextInt()-1;
            System.out.println("====================================");
            if(userInpR<0 || userInpR>this.row-1||userInpC<0||userInpC>this.column-1){
                System.out.println("Hatalı Giriş, tekrar dene! Oyunun dışına çıkma!");
            }
            else {
                isValid=false;
            }
        }

    }


    public String[][]gameTable (int row,int column){
        String [][] gameTable = new String[row][column];
        for(int i = 0;i<row;i++){
            for (int j =0;j<column;j++ ){
                gameTable[i][j]= " - ";
            }

        }
        return gameTable;

    }


    public String[][] mayinTarlasi(){
        this.mayinTarlasi = new String[row][column];
        String [] tempArray = new String[row*column];
        String y = " * ";
        String t = " - ";


        for(int k = 0;k<row*column;k++){
            while (k<(row*column/4)){
                tempArray[k]=y;
                k++;
            }
            tempArray[k]=t;

        }
        List<String> intList = Arrays.asList(tempArray);
        Collections.shuffle(intList);
        intList.toArray(tempArray);
        int nextIndex=0;
        for(int i = 0;i<this.mayinTarlasi.length;i++){
            for(int j=0;j<this.mayinTarlasi[i].length;j++){
                this.mayinTarlasi[i][j]=tempArray[nextIndex++];
            }
        }
        return this.mayinTarlasi;

    }
    public void controlTable(String [][] arr, String [][]gameTable , int inputRow , int inputColumn){
        int count =0;
        if(arr[inputRow][inputColumn].equals(" * ")){
            System.out.println("===========KAYBETTİNİZ==============");
            printMayinTarlasi();
            condition=false;

        }
        else {
        for(int i = inputRow-1;i<=inputRow+1 ;i++){
            for(int j = inputColumn-1;j<=inputColumn+1;j++){
                if(i<0||j<0||i>=arr.length||j>=arr[0].length||(i==inputRow && j==inputColumn)){
                    continue;
                }
                else {
                    if(arr[i][j]==" * "){
                        count ++;
                    }
                }
            }

        }

        if(count>0){
            String s =  Integer.toString(count);
            arr[inputRow][inputColumn]=" "+s+" ";
            gameTable[inputRow][inputColumn]=" "+s+" ";
            if(!isWin()){
                printGameTable();
            }
        }
        else {
            String s =  Integer.toString(count);
            arr[inputRow][inputColumn]=" "+0+" ";
            gameTable[inputRow][inputColumn]=" "+0+" ";
            if(!isWin()){
                printGameTable();
            }
        }



        }


    }

    public boolean isWin(){
        int emptyCount=0;
        for(int i = 0; i<this.mayinTarlasi.length; i++){
            for(int j = 0; j< mayinTarlasi[i].length; j++){
                if(mayinTarlasi[i][j]==" - "){

                    emptyCount ++;
                }
            }

        }
        if(emptyCount== 0){
            System.out.println("====OYUNU KAZANDINIZ, TEBRİKLER!====");
            System.out.println("====================================");
            printGameTable();
            condition = false;
            return true;
        }

        return false;

    }

    public void printGameTable(){
        for (String[] row : this.gameTable) {
            for (String column : row) {
                System.out.print(column);
            }
            System.out.println("");
        }

    }
    private void printMayinTarlasi() {
        for (String[] row : this.mayinTarlasi) {
            for (String column : row) {
                System.out.print(column);
            }
            System.out.println("");
        }
    }
}
