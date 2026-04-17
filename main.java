import java.util.*;

class Main {

    // Arabic letter frequencies (simplified)
    private static final double[] ARABIC_FREQUENCIES = {
            11.6,4.8,3.7,1.1,2.8,2.6,1.1,3.5,1.0,4.7,
            0.9,6.5,3.0,2.9,1.5,1.7,0.7,3.9,1.0,3.0,
            2.7,3.6,5.3,3.1,7.2,2.5,6.0,6.7
    };

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter API Key: ");
        String apiKey = input.nextLine();

        System.out.print("Enter plaintext: ");
        String plaintext = input.nextLine();

        // Step 1: Translate
        String translated = GoogleTranslate.translate(plaintext, apiKey, "ar");
        System.out.println("Translated: " + translated);

        // Step 2: Encrypt
        int shift = 3;
        String encrypted = caesarCipher(translated, shift);
        System.out.println("Encrypted: " + encrypted);

        // Step 3: Frequency Analysis
        frequencyAnalysis(encrypted);
    }

    // Caesar Cipher (basic Unicode shift)
    public static String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            result.append((char)(c + shift));
        }

        return result.toString();
    }

    // Frequency Analysis
    public static void frequencyAnalysis(String text) {
        Map<Character, Integer> freqMap = new HashMap<>();

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            }
        }

        System.out.println("\nFrequency Analysis:");
        for (char c : freqMap.keySet()) {
            System.out.println(c + ": " + freqMap.get(c));
        }
    }
}