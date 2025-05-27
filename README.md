This project explores the performance of an AVL tree in managing a large dataset of general statements from GenericsKB. The goal is to measure how efficiently the AVL tree handles inserting new data and searching for existing data as the size of the dataset grows.

## What the Program Does
The program uses a set of Java classes to build and manipulate AVL trees. It allows you to:

* Load general statements from a file (like GenericsKB.txt) into an AVL tree. Each statement is stored as an Entry object with a term, statement, and confidence score.
* Load search queries from a separate file.
* Measure the time and operations required to:
* Add new entries to the tree.
* Search for specific terms in the tree.
* Run automated experiments with varying dataset sizes to collect performance data.
* Generate graphs (using Python's matplotlib) to visualize the insertion and search performance, demonstrating the logarithmic time complexity of an AVL tree.
## How to Use It
- Compile the program: Open your terminal and run make.
- Run a basic test: To ensure everything is working correctly, execute make test. This will run a small test with pre-defined entries and queries.
- Run the main experiment and generate graphs: To run the full performance experiment and see the graphs, type make run. This will use the default GenericsKB.txt for entries and GenericsKB-queries.txt for queries.
* Run with your own files: If you want to use different data files, you can manually run the main application:
  Bash\
  java -cp bin GenericsKbAVLApp your_data_file.txt your_query_file.txt your_inserts_file.txt\
(Replace your_data_file.txt, your_query_file.txt, and your_inserts_file.txt with your actual file names.)
* Install Matplotlib: If you're running make run and graphs aren't appearing, you might need to install matplotlib: pip install matplotlib.
* The program is designed to automate most of the testing and analysis, making it easy to see how AVL trees perform under different conditions.
