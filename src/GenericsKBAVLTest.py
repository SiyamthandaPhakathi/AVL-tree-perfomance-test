import random
import matplotlib.pyplot as plt
import subprocess
import numpy as np

#Generate random data sets of different sizes
def CreateSubset(inputFile, subsetFile, subsetSize):
    infile = open(inputFile, 'r')
    lines = infile.readlines()
    infile.close()
    subset = random.sample(lines, subsetSize)
    outfile = open(subsetFile, 'w')
    outfile.writelines(subset)
    outfile.close()

#Runs my java Test  and extracts the count values at the bottom
def RunJavaTest(entriesSubset, testQueries, testEntries):
    
    try:
        #The run function allows you to input commands to the terminal. This line will run make all and compile all 
        #java files. The check = True is just in case of error or if the process is halted abnormally
        subprocess.run(["make", "all"], check=True) 

        #This runs the command to run AVLTreeTest.java with the two files being passed in as args to the main method
        #capture output lets you acces std output so you can read your output, text decodes from bytes to string and check is for error handling
        subprocess.run(["java", "-cp", "bin", "GenericsKbAVLApp", entriesSubset, testQueries, testEntries],  capture_output=True,text=True,check=True,)
    

        output = open("Results"+entriesSubset,'r')
        lines = output.readlines()
        print(lines[-1])
        insert_count = int(lines[-2].split()[-1])  # Extract insert count
        search_count = int(lines[-1].split()[-1])
        output.close()  # Extract search count
        return insert_count, search_count
    except Exception as e:
        print(f"Error: {e}")


def DrawGraph(datasetSize,bestCaseInsert,worstCaseInsert,averageCaseInsert,bestCaseSearch,worstCaseSearch,averageCaseSearch):

    plt.plot(datasetSize,bestCaseInsert, label = "Best case", marker = "o")
    plt.plot(datasetSize,worstCaseInsert, label = "Worst case", marker = "o")
    plt.plot(datasetSize,averageCaseInsert, label = "Average case", marker = "o")
    plt.plot(datasetSize,logN, label = "logN", marker = "o")
    plt.xlabel("Dataset size (N)")
    plt.ylabel("Number of operations")
    plt.grid(True)
    plt.title("Number of insert operations in relation to dataset size")
    plt.legend()
    plt.show()
    plt.figure()
    
    plt.plot(datasetSize,bestCaseSearch, label = "Best case", marker = "o")
    plt.plot(datasetSize,worstCaseSearch, label = "Worst case", marker = "o")
    plt.plot(datasetSize,averageCaseSearch, label = "Average case", marker = "o")
    plt.plot(datasetSize,logN, label = "logN", marker = "o")
    plt.xlabel("Dataset size (N)")
    plt.ylabel("Number of operations")
    plt.grid(True)
    plt.title("Number of search operations in relation to dataset size")
    plt.legend()
    plt.show()


if __name__ == "__main__":
    allEntries = input("Enter the file name of where all entries are stored: ")
    allQueries = input("Enter the file name for all queries: ")
    resultsFile = "Results.txt"
    #Creates constant query and test sub files for every single run of the test
    queriesSubset = "Queries10.txt"
    CreateSubset(allQueries,queriesSubset,10)
    testEntriesSubset = "Entries10.txt"
    CreateSubset("GenericsKB-additional.txt",testEntriesSubset,10)
    

    numberOfRuns = int(input("How many times would you like to run this experiment:"))

    datasetSize = [ 1, 4, 15, 56, 214, 818, 3129, 11956, 45722, 50000]
    logN = []
    for data in datasetSize:
        logN.append(np.ceil((np.log(data))*10))
    #Each element is an array that stores r tuples containing the (insert count[dataSize.length], search count[dataSize.length])
    results = []
    #results[i] = a tuple with the runs
    #results[i][0] = array of insert counts for run i
    #results[i][1] = array of search counts for run i
    file = open(resultsFile,'w')

    
    for run in range(numberOfRuns):
        searchCounts = []
        insertCounts = []
        file.write("Results for run "+str(run)+":\n")
        for size in datasetSize:
            entriesSubset = f"GenericsKB{size}.txt"
            CreateSubset(allEntries, entriesSubset, size)
        
            insertCount, searchCount = RunJavaTest(entriesSubset,queriesSubset,testEntriesSubset)
            insertCounts.append(insertCount)
            searchCounts.append(searchCount)
            file.write("n = "+ str(size)+"\n")
            file.write("---------------------------------------\n")
            file.write("Search count: "+str(searchCount)+"\n")
            file.write("Insert count: "+str(insertCount)+"\n")
            file.write("---------------------------------------\n")
        results.append([insertCounts,searchCounts])
    file.close()
        
        
    
    
    # Calculating best case, worst case, and average case\
    #Initialize best case with infinities and worst case with 0s
    bestCaseSearch = [float('inf')] * len(datasetSize) 
    worstCaseSearch = [0] * len(datasetSize) 
    averageCaseSearch = [0] * len(datasetSize)

    bestCaseInsert = [float('inf')] * len(datasetSize)
    worstCaseInsert = [0] * len(datasetSize)
    averageCaseInsert = [0] * len(datasetSize)

    for run in range(numberOfRuns): 
        for i, size in enumerate(datasetSize):
            #results[i][0] = array of insert counts for run i
            #results[i][1] = array of search counts for run i
            searchCounts = results[run][1]
            insertCounts = results[run][0]

            #Finds the best case by choosing the smallest value for each n, worst case by doing the oppise and
            #adds every result from n to average case to be calculated later
            bestCaseSearch[i] = min(bestCaseSearch[i], searchCounts[i])
            worstCaseSearch[i] = max(worstCaseSearch[i], searchCounts[i])
            averageCaseSearch[i] += searchCounts[i]

            bestCaseInsert[i] = min(bestCaseInsert[i], insertCounts[i])
            worstCaseInsert[i] = max(worstCaseInsert[i], insertCounts[i])
            averageCaseInsert[i] += insertCounts[i]

    averageCaseSearch = [sumAverage / numberOfRuns for sumAverage in averageCaseSearch]
    averageCaseInsert = [sumAverage / numberOfRuns for sumAverage in averageCaseInsert]

    DrawGraph(datasetSize,bestCaseInsert,worstCaseInsert,averageCaseInsert,bestCaseSearch,worstCaseSearch,averageCaseSearch)




    







    

   


  