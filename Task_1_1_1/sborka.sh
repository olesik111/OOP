javadoc -d build/docs/javadoc -sourcepath src/main/java -subpackages ru.nsu.kataeva

javac src/main/java/ru/nsu/kataeva/HeapSort.java -d ./build

java -cp ./build ru.nsu.kataeva.HeapSort