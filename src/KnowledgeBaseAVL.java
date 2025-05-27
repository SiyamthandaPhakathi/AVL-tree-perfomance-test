import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Program responsible for initializing out AVLTree and populating our data base
 * @author Siyamthanda Phakathi
 */
public class KnowledgeBaseAVL {

    private AVLTree<Entry> entries;
    private AVLTree<String> queries;
  
    /**
     * Null constructor that creates empty trees for queries and entries
     * @param fileName The path to the file containing knowledge base entries
     */
    public KnowledgeBaseAVL(){
        entries = new AVLTree<Entry>();
        queries = new AVLTree<String>();
    }

    /**
     * Function to populate AVL tree with entries from a text file
     * 
     * @param fileName The path to the file containing the knowledge base entries
     * 
     */
    public void AddEntries(String fileName) {
        try{

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String entry;
            //Iterate through each line in the text file and make an entry object thereafter insert it to the tree.
            while((entry = br.readLine()) != null){
                StringTokenizer st = new StringTokenizer(entry,"\t");
                Entry newEntry = new Entry(st.nextToken(),st.nextToken(),Double.parseDouble(st.nextToken()));
                this.entries.insert(newEntry);
            }
            br.close(); 

        }
        catch(IOException e){
            System.out.println("Error loading file. Ensure file name or file path is correct.");
        }
        
    }

    /**
     * Adds new entries to an already populated knowledge base
     * @param newFileName additional insertions
     * @throws IOException if file not found
     */
    public void appendEntries(String newFileName) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(newFileName));
        String entry;
        entries.resetCount();

        while ((entry = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(entry, "\t");
            Entry newEntry = new Entry(st.nextToken(), st.nextToken(), Double.parseDouble(st.nextToken()));

            entries.insert(newEntry);
        }
        br.close();

    }
    /**
     * Method to populate queries tree from text file
     * 
     * @param fileName The path file or path name to ther file containing queries
     */
    public void AddQueries(String fileName)throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String query;
        while((query = br.readLine()) != null){
            queries.insert(query);
        }
        br.close();
    }


    /**
     * Searches for a single query within the tree
     * 
     * @param query you wish to look for in the entries tree
     */
    public void Search(String query){
        BinaryTreeNode<Entry> entry = entries.find(new Entry(query,"",0));
        
        if( entry == null){
            System.out.println("Term not found:"+query);
        }
        else{
            System.out.println(query+": "+entry.data.getStatement()+"("+entry.data.getConfidenceScore()+")");
        }
        
    }
    
    /**
     * Sets the first query to search from as the root query
     */
    public void SearchAll(){
        SearchAll(queries.root);
    }

    /**
     * Method to recusrively search for all the queries in the entries tree
     * @param query The first query to search from
     */
    private void SearchAll(BinaryTreeNode<String> query){
        if(query != null){
            this.Search(query.data);
            SearchAll(query.getLeft());
            SearchAll(query.getRight());
        }
    }
    
    //returns number of comparisons from populating a tree
    public int getInsertOperationCount(){
        return this.entries.insertCounts;
    }

    //return number of comparisons for searching for a term in a tree
    public int getSearchOperationCount(){
        return this.entries.findCounts;
    }


   

   
    
}
