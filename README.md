# LZ78
LZ78 compression algorithm can be used by command line.

You must have at least version 11 of Java installed to run it.

 
### PATTERN:
java -jar LZ78.jar (encode/decode) (file-name.txt)


### EXAMPLES:
java -jar LZ78.jar encode example-slides.txt<br/>
java -jar LZ78.jar decode example-slides-encoded_by_LZ78.txt

java -jar LZ78.jar encode example-simple.txt<br/>
java -jar LZ78.jar decode example-simple-encoded_by_LZ78.txt

java -jar LZ78.jar encode example-full.txt<br/>
java -jar LZ78.jar decode example-full-encoded_by_LZ78.txt
