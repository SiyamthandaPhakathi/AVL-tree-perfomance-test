all:
	mkdir -p bin/
	javac -d bin/ src/*.java

docs:
	mkdir -p doc/
	javadoc -d doc/ src/*.java


clean:
	rm -rf bin/ GenericsKB5*.txt doc/ Results* GenericsKB1* GenericsKB2* GenericsKB4* GenericsKB8* GenericsKB3* Queries10* Entries10*

run:
	python3 src/GenericsKBAVLTest.py 

test:
	java -cp bin AVLTreeTest