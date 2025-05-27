import java.math.*;

/**
 * Represents a knowledge entry with a term, statement, and confidence score.
 * Implements the Comparable interface to allow for sorting based on the term.
 */

public class Entry implements Comparable<Entry>{

    private String term;
    private String statement;
    private double confidenceScore;
    /**
     * Constructs a new Entry object.
     *
     * @param term The term associated with the entry.
     * @param statement The statement associated with the entry.
     * @param confidenceScore The confidence score associated with the entry.
     */
   
	public Entry(String term,String statement,double confidenceScore){
        this.term = term;
        this.statement = statement;
        this.confidenceScore =confidenceScore;

    }

    //Getters
    public String getTerm(){return this.term;}
    
    public String getStatement(){return this.statement;}
    
    //Return a confidence score rounded of to 3 decimal places without changing its actual value
    public double getConfidenceScore(){return (Math.round(this.confidenceScore*1000.0)/1000.0);}
    
    @Override
    //Compare entries using their terms
    public int compareTo(Entry otherEntry){return this.term.compareTo(otherEntry.term);}

    public String toString(){
        return term + " " + statement + " " + this.getConfidenceScore();
    }

}
