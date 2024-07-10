package telran.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class CodeCommentsSeparation {

	public static void main(String[] args) throws Exception {
		//args[0] - file path for file containing both Java class code and comments
		//args[1] - result file with only code
		//args[2] -result file with only comments
		// example of args[0] "src/telran/io/test/InputOutputTest.java" 
		//from one file containing code and comments to create two files
		//one with only comments and second with only code
		
		if (args.length < 3) {
			throw new IllegalArgumentException("Usage: java CodeCommentsSeparation <inputFile> "
					+ "<codeOutputFile> <commentsOutputFile>");
		}

		String inputFilePath = args[0];
		String codeOutputFilePath = args[1];
		String commentsOutputFilePath = args[2];

		separateCodeAndComments(inputFilePath, codeOutputFilePath, commentsOutputFilePath);
	}
	
    private static void separateCodeAndComments(String inputFilePath, String codeOutputFilePath, String commentsOutputFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             PrintWriter codeWriter = new PrintWriter(new FileWriter(codeOutputFilePath));
             PrintWriter commentsWriter = new PrintWriter(new FileWriter(commentsOutputFilePath))) {
            reader.lines().forEach(line -> 
            	(line.trim().startsWith("//") ? commentsWriter : codeWriter).println(line));
            System.out.println("Separation of code and comments completed successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

}
