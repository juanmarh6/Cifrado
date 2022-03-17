package PaqCifrado;
import java.io.Serializable;
import java.util.Random;

public class Alfabeto implements Serializable {
    public char [] alf = new char[256];

    public Alfabeto(){  //constructor automático
        char [] toCode = new char[256];
        for (int i = 1; i < 257; i++) //rellena toCode con ascii
            toCode[i-1] = (char) i;
        for (int j = 0; j < 256; j++){
            Random rs = new Random();
            int c = rs.nextInt(0, toCode.length-j); //random int desde 0 hasta toCode.length
            alf[j] = toCode[c]; //va rellenando alf con el random toCode
            for (int k = c; k < toCode.length-1-j; k++) //echa atras los toCode que siguen al random tocado
                toCode[k] = toCode[k+1];
        }
    }
    public String toString(){
        String salida = new String("");
        for (int i = 0; i < alf.length; i++)
            salida += i + "-> " + alf[i] + "\n";
        return salida;
    }
    public void copiar(Alfabeto b){
        this.alf = b.alf;
    }
}
