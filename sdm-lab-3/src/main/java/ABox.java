import java.io.IOException;

public class ABox {

    public static void main(String[] args){
        try {
            String csvFilePath = "/Users/alfredo.leon/Google Drive/Mi unidad/BDMA/2nd Semester/Semantic Data Management/labs/lab3/Lab3SDM/triplets_csv_parsed.csv";
            PaperIterator paperIterator = new PaperIterator(csvFilePath);
            while (paperIterator.hasNext()) {
                PaperRow paper = paperIterator.next();
                System.out.println(paper);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
