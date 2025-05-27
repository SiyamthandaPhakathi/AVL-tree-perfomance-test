import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/** 
 * 
 * Program that tests the number of operations done when loading and searching a tree
 * @author Siyamthanda Phakathi
 * 
*/
public class GenericsKbAVLApp{
    
     public static void main(String[] args) throws IOException {
        String dataFile = args[0];
        String queryFile = args[1];
        String inserts = args[2];
        KnowledgeBaseAVL kb = new KnowledgeBaseAVL();

        PrintStream out = new PrintStream(new FileOutputStream("Results"+dataFile));
        System.setOut(out);
        kb.AddEntries(dataFile);
        kb.AddQueries(queryFile);
        kb.SearchAll();
        kb.appendEntries(inserts);
        System.out.println("Number of operations for insertions: "+ kb.getInsertOperationCount());
        System.out.println("Number of operations for search: "+ kb.getSearchOperationCount());
        
        System.setOut(System.out);  

            
    }
    
    
}