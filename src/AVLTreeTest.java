import java.io.IOException;
import java.io.PrintStream;
import java.io.FileOutputStream;
/**
 * Program to test the loading and searching functionality of out knowledge base
 */
public class AVLTreeTest {
    public static void main(String[] args) throws IOException {
        String dataFile = "TestEntries.txt";
        String queryFile = "TestQueries.txt";
        KnowledgeBaseAVL kb = new KnowledgeBaseAVL();

        PrintStream out = new PrintStream(new FileOutputStream("Results"+dataFile));
        System.setOut(out);
        kb.AddEntries(dataFile);
        kb.AddQueries(queryFile);
        kb.SearchAll();
        System.out.println("Number of operations for insertions: "+ kb.getInsertOperationCount());
        System.out.println("Number of operations for search: "+ kb.getSearchOperationCount());
        System.setOut(System.out); 
            
    }
}