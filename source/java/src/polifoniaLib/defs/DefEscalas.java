/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polifoniaLib.defs;

import polifoniaLib.Armonia;
import polifoniaLib.listas.ListArmonia;

/**
 *
 * @author Cristian
 */
public class DefEscalas {

    // Mayor
    public static float[] Mayor = {0, 2, 4, 5, 7, 9, 11};
    public static String[] Ac3EscMay = {"M", "m", "m", "M", "M", "m", "o"};
    public static String[] Ac7EscMay = {"M7", "m7", "m7", "M7", "Mm7", "m7", "o/7"};
//    public static float[] Ac3sn5EscMay = {14, -1, -1, -1, 12, -1, -1};
//    public static float[] Ac7sn5EscMay = {-1, -1, -1, -1, 12, -1, -1};
    // Mayor Armonica
    public static float[] MayorArm = {0, 2, 4, 5, 7, 8, 11};
    public static String[] Ac3EscMayArm = {"M", "o", "m", "m", "M", "+", "o"};
    public static String[] Ac7EscMayArm = {"M7", "o/7", "m7", "M7", "Mm7", "m7", "o7"};
    // Corregir bien los tipos de acordes en las escalas menores
    // Menor natural
    public static float[] MenorNat = {0, 2, 3, 5, 7, 8, 10};
    public static String[] Ac3EscMenNat = {"m", "o", "M", "m", "m", "M", "M"};
    public static String[] Ac7EscMenNat = {"m7", "o/7", "M7", "m7", "m7", "M7", "Mm7"};
//    public static float[] Ac3sn5EscMenNat = {13, -1, -1, -1, 12, -1, -1};
    // Menor armonica
    public static float[] MenorArmonico = {0, 2, 3, 5, 7, 8, 11};
    public static String[] Ac3EscMenArm = {"m", "o", "+", "m", "M", "M", "o"};
    public static String[] Ac7EscMenArm = {"I+", "o/7", "III+", "m7", "Mm7", "M7", "o7"};
    // Menor melodica
    public static float[] MenorMelodico = {0, 2, 3, 5, 7, 9, 11};
    public static String[] Ac3EscMenMel = {"m", "m", "+", "M", "M", "o", "o"};
    public static String[] Ac7EscMenMel = {"I+", "m7", "III+", "Mm7", "Mm7", "o/7", "o/7"};
    public ListArmonia armsList;

    public DefEscalas() {
    }

    public ListArmonia lista(float base, char tipo) {

        armsList = new ListArmonia();
        switch (tipo) {
            case 'M':
                armsList.add(new Armonia(base + 0, "M"));
                armsList.add(new Armonia(base + 2, "m"));
                armsList.add(new Armonia(base + 4, "m"));
                armsList.add(new Armonia(base + 5, "M"));
                armsList.add(new Armonia(base + 7, "M"));
                armsList.add(new Armonia(base + 9, "m"));
                armsList.add(new Armonia(base + 11, "o"));
                // Septimos
                armsList.add(new Armonia(base + 0, "M7"));
                armsList.add(new Armonia(base + 2, "m7"));
                armsList.add(new Armonia(base + 4, "m7"));
                armsList.add(new Armonia(base + 5, "M7"));
                armsList.add(new Armonia(base + 7, "D7"));
                armsList.add(new Armonia(base + 9, "m7"));
                armsList.add(new Armonia(base + 11, "o/7"));
                // Mayor armonica
                armsList.add(new Armonia(base + 2, "o"));
//                armsList.add(new Armonia(base + 9, "+"));
                // Mayor armonicos septimo
//    public static float[] MayorArm = {0,2, 4, 5, 7, 8, 11};
//    public static String[] Ac7EscMayArm = {"M7", "o/7", "m7", "M7", "Mm7", "m7", "o7"};
                armsList.add(new Armonia(base + 11, "o7"));
// Napolitano
//                armsList.add(new Armonia(base + 1, "M"));
// Subdominante armonico
                armsList.add(new Armonia(base + 5, "m"));
// Dominante noveno
                armsList.add(new Armonia(base + 7, "9"));
                armsList.add(new Armonia(base + 7, "DM9*5"));
                armsList.add(new Armonia(base + 7, "Dm9*5"));
                // 9na de Cristian
                armsList.add(new Armonia(base + 7, "7b9"));

// Dominantes alterados
                armsList.add(new Armonia(base + 7, "7b5"));
                armsList.add(new Armonia(base + 7, "7#5"));
                armsList.add(new Armonia(base + 7, "D7*5"));

                break;
            case 'm':
                // Menor natural
//    public static String[] Ac3EscMenNat = {"m", "o", "M", "m", "m", "M", "M"};
                armsList.add(new Armonia(base + 0, "m"));
                armsList.add(new Armonia(base + 2, "o"));
                armsList.add(new Armonia(base + 3, "M"));
                armsList.add(new Armonia(base + 5, "m"));
                armsList.add(new Armonia(base + 7, "m"));
                armsList.add(new Armonia(base + 8, "M"));
                armsList.add(new Armonia(base + 10, "M"));
//    public static String[] Ac7EscMenNat = {"m7", "o/7", "M7", "m7", "m7", "M7", "Mm7"};
                armsList.add(new Armonia(base + 0, "m7"));
//                armsList.add(new Armonia(base + 2, "o/7"));
                armsList.add(new Armonia(base + 3, "M7"));
                armsList.add(new Armonia(base + 5, "m7"));
                armsList.add(new Armonia(base + 7, "m7"));
                armsList.add(new Armonia(base + 8, "M7"));
                armsList.add(new Armonia(base + 10, "D7"));
// Menor armonico
//                armsList.add(new Armonia(base + 3, "+"));
                armsList.add(new Armonia(base + 7, "M"));
//                armsList.add(new Armonia(base + 11, "o"));
// Setpimos
//                armsList.add(new Armonia(base + 0, "I+"));
//                armsList.add(new Armonia(base + 3, "III+"));
                armsList.add(new Armonia(base + 7, "D7"));
                armsList.add(new Armonia(base + 11, "o7"));
// Menor Melódico               
                // Menor melodica
                armsList.add(new Armonia(base + 2, "m"));
//                armsList.add(new Armonia(base + 3, "+"));
                armsList.add(new Armonia(base + 5, "M"));
//                armsList.add(new Armonia(base + 9, "o"));
//                armsList.add(new Armonia(base + 11, "o"));
// Septimos
                armsList.add(new Armonia(base + 2, "m7"));
                armsList.add(new Armonia(base + 5, "D7"));
//                armsList.add(new Armonia(base + 9, "o/7"));
//                armsList.add(new Armonia(base + 11, "o/7"));
// Napolitano
//                armsList.add(new Armonia(base + 1, "M"));
// Dominante noveno
                armsList.add(new Armonia(base + 7, "DM9*5"));
                armsList.add(new Armonia(base + 7, "Dm9*5"));
                armsList.add(new Armonia(base + 7, "D7*5"));
                // 9na de Cristian
                armsList.add(new Armonia(base + 7, "7b9"));


                break;
        }
//        for (int i = 0; i < armsList.size(); i++) {
//            System.out.println("Lista:" + armsList.get(i).getFund12() + ", " + armsList.get(i).getTipoAcStr());
//        }
        return armsList;
    }
}
