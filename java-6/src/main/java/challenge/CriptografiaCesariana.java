package challenge;

public class CriptografiaCesariana implements Criptografia {



    @Override
    public String criptografar(String texto) {
        String cifrado = "";
        int zCode = 122;
        int key = 3;
        int initalLetter = 96;
        int codeLetterCypher;

        texto = texto.toLowerCase();
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("Passe um texto para criptografa.");
        }
        for (int i = 0 ; i < texto.length(); i++) {
            char caracterAtual = texto.charAt(i);
            if (caracterAtual >= 'a' && caracterAtual <= 'z') {
                if ((char)(caracterAtual + key) > 'z') {
                    codeLetterCypher =  (int)(caracterAtual) + key - zCode + initalLetter ;
                    cifrado += (char)(codeLetterCypher);
                } else {
                    cifrado += (char)(caracterAtual + key);
                }
            } else {
                cifrado += caracterAtual;
            }
        }
        return cifrado;
    }

    @Override
    public String descriptografar(String texto) {
        int codeLetterCypher;
        int lastLetter = 123;
        int key = 3;
        int aCode = 97;

        String decifrado = "";

        texto = texto.toLowerCase();
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("Passe um texto para descriptografa.");
        }
        for (int i = 0; i < texto.length(); i++) {
            char caracterAtual = texto.charAt(i);
            if (caracterAtual >= 'a' && caracterAtual <= 'z') {
                if ((char)(caracterAtual - key) < 'a') {
                    codeLetterCypher = lastLetter - (key - ((int)(caracterAtual) - aCode));
                    decifrado += (char)(codeLetterCypher);
                } else {
                    decifrado += (char)(caracterAtual - key);
                }
            } else {
                decifrado += caracterAtual;
            }
        }

        return decifrado;
    }
}
