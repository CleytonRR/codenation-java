package challenge;

public class CriptografiaCesariana implements Criptografia {
    private  int key = 3;
    @Override
    public String criptografar(String texto) {
        String cifrado = "";
        int zCode = 122;
        int initalLetter = 96;
        int codeLetterCypher;

        texto = texto.toLowerCase();
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("Passe um texto para criptografa.");
        }
        for (int i = 0 ; i < texto.length(); i++) {
            char caracterAtual = texto.charAt(i);
            if (caracterAtual >= 'a' && caracterAtual <= 'z') {
                if ((char)(caracterAtual + this.key) > 'z') {
                    codeLetterCypher =  (int)(caracterAtual) + this.key - zCode + initalLetter ;
                    cifrado += (char)(codeLetterCypher);
                } else {
                    cifrado += (char)(caracterAtual + this.key);
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
        int aCode = 97;

        String decifrado = "";

        texto = texto.toLowerCase();
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("Passe um texto para descriptografa.");
        }
        for (int i = 0; i < texto.length(); i++) {
            char caracterAtual = texto.charAt(i);
            if (caracterAtual >= 'a' && caracterAtual <= 'z') {
                if ((char)(caracterAtual - this.key) < 'a') {
                    codeLetterCypher = lastLetter - (this.key - ((int)(caracterAtual) - aCode));
                    decifrado += (char)(codeLetterCypher);
                } else {
                    decifrado += (char)(caracterAtual - this.key);
                }
            } else {
                decifrado += caracterAtual;
            }
        }

        return decifrado;
    }
}
