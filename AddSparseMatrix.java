import java.io.*;
import java.util.*;

class Node{
    int row, column, value;

    public Node(int row, int column, int value){
        this.row = row;
        this.column = column;
        this.value = value;
    }
}

public class AddSparseMatrix{

    public static void bubblesort(List<Node> nodeList){
        int n = nodeList.size();
        for(int i = 0; i < n-1; i++){
            for(int j = 0; j < n-i-1; j++){
                if(nodeList.get(j).column > nodeList.get(j+1).column){
                    Node tempNode = nodeList.get(j);
                    nodeList.remove(j);
                    nodeList.add(j+1, tempNode);
                }
            }
        }
        return;
    }

    public static List<Node> addSame(List<Node> nodeList){
        List<Node> tempList = new ArrayList<Node>();

        for(int i = 0; i < nodeList.size(); i++){
            if(i < nodeList.size()-1){
                if(nodeList.get(i).column == nodeList.get(i+1).column && i < nodeList.size()-1 && i < nodeList.size()-1){
                    Node tempNode = new Node(nodeList.get(i).row, nodeList.get(i).column, nodeList.get(i).value + nodeList.get(i+1).value);
                    tempList.add(tempNode);
                    i++;
                }else{
                    tempList.add(nodeList.get(i));
                    continue;
                }
            }else{
                tempList.add(nodeList.get(i));
            }
        }

        return tempList;
    }

    public static void main(String[] args) throws Exception {
        List<Node> nodeList = new ArrayList<Node>();

        BufferedReader brReader1 = new BufferedReader(new FileReader(args[0]));
        BufferedReader brReader2 = new BufferedReader(new FileReader(args[1]));
        BufferedWriter bWriter = new BufferedWriter(new FileWriter(args[2], false));

        String input_one = new String();
        String input_two = new String();

        String size = new String();

        while((input_one = brReader1.readLine())!=null && (input_two = brReader2.readLine())!=null){

            String[] array_1 = input_one.split(" ");
            String[] array_2 = input_two.split(" ");
            List<Node> temp = new ArrayList<Node>();
            int row = 0;

            if(input_one.contains(":") == false){
                size = input_one;
                bWriter.write(input_one);
                bWriter.newLine();
                continue;
            }else if(input_one.contains(":")){
                row = Integer.parseInt(array_1[0]);

                for(String i : array_1){
                    int n = i.indexOf(":");
                    if(n > 0){
                        int column = Integer.parseInt(i.substring(0, n));
                        int value = Integer.parseInt(i.substring(n+1, i.length()));
                        Node myNode = new Node(row, column, value);
                        temp.add(myNode);
                    }
                }
            }
            if(input_two.contains(":")){
                for(String i : array_2){
                    int n = i.indexOf(":");
                    if(n > 0){
                        int column = Integer.parseInt(i.substring(0, n));
                        int value = Integer.parseInt(i.substring(n+1, i.length()));
                        Node myNode = new Node(row, column, value);
                        temp.add(myNode);
                    }
                }
            }
            if(temp.size() > 0){
                bubblesort(temp);
                List<Node> finalList = addSame(temp);
                bWriter.write(String.valueOf(row));
                bWriter.write(" ");    
                for(Node i : finalList){
                    bWriter.write(String.valueOf(i.column));
                    bWriter.write(":");
                    bWriter.write(String.valueOf(i.value));
                    bWriter.write(" ");
                }
                bWriter.newLine();
            }else{
                bWriter.write(String.valueOf(row));
                bWriter.write(" :");
                bWriter.newLine();
            }
        }
        brReader1.close();
        brReader2.close();
        bWriter.close();   
    }
}