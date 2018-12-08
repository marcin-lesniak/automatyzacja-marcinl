import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Zadanie1 {
    public static void main(String... args) {
        final String HELLO_WORD = "Witaj świecie!";
        final String EXIT_PROMPT = "Aby zamknąć aplikację, trzeba wpisać exit i nacisnąć klawisz enter";
        final String REQUIRED_INPUT_TEXT = "exit";
        String inputText = "";

        System.out.println(HELLO_WORD);
        while(!inputText.equals(REQUIRED_INPUT_TEXT)) {
            System.out.println(EXIT_PROMPT);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                inputText = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
