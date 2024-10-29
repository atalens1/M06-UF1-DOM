import java.io.IOException;
import java.io.RandomAccessFile;

public class EscriureAleatori {

    public static void main(String[] args) throws IOException {

        try (RandomAccessFile raf1 = new RandomAccessFile("EmpleatRAF.dat", "rw")) {

            int id[] = {1,2,3};
            String nomsEmpleats[] = {"Pere Gil","Josep Lopez","Marti Garrido"};
            int departaments[] = {10,25,15};
            Double salari[] = {1200.00,2300.25,750.32};

            //Utilitzarem un StringBuffer per tractar l'array de noms d'empleats, el qual
            //inicialitzarem tot seguit:
            StringBuffer sbf = null; 

            //Com els arrays tenen tots la mateixa mida, prenem el primer per fer la iteració
            for (int i = 0; i<id.length; i++) {
                raf1.writeInt(id[i]);
                sbf = new StringBuffer(nomsEmpleats[i]);
            //Longitud de caràcters màxima
                sbf.setLength(20);
            //No escrivim amb UTF i si caràcter a caràcter per assegurar 
            //la longitud màxima del buffer
                raf1.writeChars(sbf.toString());
            //Escrivim ara departament i salari
                raf1.writeInt(departaments[i]);
                raf1.writeDouble(salari[i]);
            }
        }
    }
}