/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polifoniaLib.defs;

import polifoniaLib.ArmoniaDef;
import polifoniaLib.listas.ListArmoniaDef;
import polifoniaLib.listas.ListFloat;

/**
 */
public class DefAcordesJazz {

    ListArmoniaDef arms;
//    public static String aS_mayor = "M";
//    public static float[] a_mayor = {0, 4, 7};
//    public static String bS_menor = "m";
//    public static float[] b_menor = {0, 3, 7};
//    public static String cS_dim = "o";
//    public static float[] c_dim = {0, 3, 6};
//    public static String dS_aum = "+";
//    public static float[] d_aum = {0, 4, 8};
//    public static String eS_M7 = "M7";
//    public static float[] e_M7 = {0, 4, 7, 11};
//    public static String fS_Mm7 = "Mm7";
//    public static float[] f_Mm7 = {0, 4, 7, 10};
//    public static String gS_m7 = "m7";
//    public static float[] g_m7 = {0, 3, 7, 10};
//    public static String hS_semiDim7 = "o/7";
//    public static float[] h_semiDim7 = {0, 3, 6, 10};
//    public static String iS_dim7 = "o7";
//    public static float[] i_dim7 = {0, 3, 6, 9};
//    // Acordes que surgen del menor arm y mel
//    public static String jS_ImenVIIdim = "I+";//"o7"
//    public static float[] j_ImenVIIdim = {0, 3, 7, 11};
//    public static String kS_IIImenVIIaum = "III+";//"o7"
//    public static float[] k_IIImenVIIaum = {0, 4, 8, 11};
//    public static String lS_D7_sin5 = "D7*5";//Septimo incompleto sin quinta
//    public static float[] l_D7_sin5 = {0, 4, 10};
//    // Alteraciones de acordes
//    public static String S_nap = "IIb1";
//    public static float[] nap = {0, 4, 7};
//    // Dominantes alterados
//    public static String S_VM7p5 = "VM7#5";
//    public static float[] VM7p5 = {0, 4, 8, 10};
//    //Dominante novena
//    public static String S_V9Ms5 = "V9Ms5";
//    public static float[] V9Ms5 = {0, 2, 4, 10};
////    public static float[] V9Ms5 = {0, 4, 10, 2};
//    public static String S_V9ms5 = "V9ms5";
//    public static float[] V9ms5 = {0, 1, 4, 10};

    public DefAcordesJazz() {
        arms = new ListArmoniaDef();
        arms.add(new ArmoniaDef("M", new ListFloat(0, 4, 7)));
        arms.add(new ArmoniaDef("m", new ListFloat(0, 3, 7)));
        arms.add(new ArmoniaDef("o", new ListFloat(0, 3, 6)));
        arms.add(new ArmoniaDef("+", new ListFloat(0, 4, 8)));
        arms.add(new ArmoniaDef("M7", new ListFloat(0, 4, 7, 11)));
        arms.add(new ArmoniaDef("D7", new ListFloat(0, 4, 7, 10)));
        arms.add(new ArmoniaDef("m7", new ListFloat(0, 3, 7, 10)));
        arms.add(new ArmoniaDef("o/7", new ListFloat(0, 3, 6, 10)));
        arms.add(new ArmoniaDef("o7", new ListFloat(0, 3, 6, 9)));
        arms.add(new ArmoniaDef("mM7", new ListFloat(0, 3, 7, 11)));
        arms.add(new ArmoniaDef("D7*5", new ListFloat(0, 4, 10)));
        arms.add(new ArmoniaDef("DM9*5", new ListFloat(0, 2, 4, 10)));
        arms.add(new ArmoniaDef("Dm9*5", new ListFloat(0, 1, 4, 10)));

        
        arms.add(new ArmoniaDef("5", new ListFloat(0,7)));
        arms.add(new ArmoniaDef("Sus4", new ListFloat(0,5,7)));
        arms.add(new ArmoniaDef("Sus2", new ListFloat(0,2,7)));
        //arms.add(new ArmoniaDef("6", new ListFloat(0,4,7,9))); Se confunde con A7 en [CM]
        //arms.add(new ArmoniaDef("m6", new ListFloat(0,3,7,9)));
        arms.add(new ArmoniaDef("9", new ListFloat(0,2,4,7,10)));
        arms.add(new ArmoniaDef("m9", new ListFloat(0,2,3,7,10)));
        arms.add(new ArmoniaDef("M9", new ListFloat(0,2,4,7,11)));
        arms.add(new ArmoniaDef("mM9", new ListFloat(0,2,3,7,11)));
        arms.add(new ArmoniaDef("11", new ListFloat(0,2,4,5,7,10)));
        arms.add(new ArmoniaDef("m11", new ListFloat(0,2,3,5,7,10)));
        arms.add(new ArmoniaDef("M11", new ListFloat(0,2,4,5,7,11)));
        arms.add(new ArmoniaDef("mM11", new ListFloat(0,2,3,5,7,11)));
        arms.add(new ArmoniaDef("13", new ListFloat(0,2,4,7,9,10)));
        arms.add(new ArmoniaDef("m13", new ListFloat(0,2,3,7,9,10)));
        arms.add(new ArmoniaDef("M13", new ListFloat(0,2,4,7,9,11)));
        arms.add(new ArmoniaDef("mM13", new ListFloat(0,2,3,7,9,11)));
        arms.add(new ArmoniaDef("add9", new ListFloat(0,2,4,7)));
        arms.add(new ArmoniaDef("Madd9", new ListFloat(0,2,3,7)));
        arms.add(new ArmoniaDef("6add9", new ListFloat(0,2,4,7,9)));
        arms.add(new ArmoniaDef("m6add9", new ListFloat(0,2,3,7,9)));
        arms.add(new ArmoniaDef("D7add11", new ListFloat(0,4,5,7,10)));
        arms.add(new ArmoniaDef("M7add11", new ListFloat(0,4,5,7,11)));
        arms.add(new ArmoniaDef("m7add11", new ListFloat(0,3,5,7,10)));
        arms.add(new ArmoniaDef("mM7add11", new ListFloat(0,3,5,7,11)));
        arms.add(new ArmoniaDef("D7add13", new ListFloat(0,4,7,9,11)));
        arms.add(new ArmoniaDef("M7add13", new ListFloat(0,4,7,9,11)));
        arms.add(new ArmoniaDef("m7add13", new ListFloat(0,3,7,9,10)));
        arms.add(new ArmoniaDef("mM7add13", new ListFloat(0,3,7,9,11)));
        arms.add(new ArmoniaDef("7b5", new ListFloat(0,4,6,10)));
        arms.add(new ArmoniaDef("7#5", new ListFloat(0,4,8,10)));
        arms.add(new ArmoniaDef("7b9", new ListFloat(0,1,4,7,10)));
        arms.add(new ArmoniaDef("7#9", new ListFloat(0,3,4,7,10)));
        arms.add(new ArmoniaDef("7#5b9", new ListFloat(0,1,4,8,10)));
        arms.add(new ArmoniaDef("m7#5", new ListFloat(0,3,8,10)));
        arms.add(new ArmoniaDef("m7b9", new ListFloat(0,1,3,7,10)));
        arms.add(new ArmoniaDef("9#11", new ListFloat(0,2,4,6,7,11)));
        arms.add(new ArmoniaDef("9b13", new ListFloat(0,2,4,7,8,11)));
        arms.add(new ArmoniaDef("6sus4", new ListFloat(0,5,7,9)));
        
        arms.add(new ArmoniaDef("7sus4", new ListFloat(0,5,7,10)));
        arms.add(new ArmoniaDef("M7sus4", new ListFloat(0,5,7,11)));
        arms.add(new ArmoniaDef("9sus4", new ListFloat(0,2,5,7,10)));
        arms.add(new ArmoniaDef("M9sus4", new ListFloat(0,2,5,7,11)));
        
        arms.add(new ArmoniaDef("2m", new ListFloat(0,1)));
        arms.add(new ArmoniaDef("2M", new ListFloat(0,2)));
        arms.add(new ArmoniaDef("3m", new ListFloat(0,3)));
        arms.add(new ArmoniaDef("3M", new ListFloat(0,4)));
        arms.add(new ArmoniaDef("4P", new ListFloat(0,5)));
        arms.add(new ArmoniaDef("6+", new ListFloat(0,6)));
        arms.add(new ArmoniaDef("5P", new ListFloat(0,7)));
        arms.add(new ArmoniaDef("6m", new ListFloat(0,8)));
        arms.add(new ArmoniaDef("6M", new ListFloat(0,9)));
        arms.add(new ArmoniaDef("7m", new ListFloat(0,10)));
        arms.add(new ArmoniaDef("7M", new ListFloat(0,11)));
        


    }

    public ListArmoniaDef getArms() {
        return arms;
    }

    public float[] getArreglo(int m) {
        ListFloat temp = getArms().get(m).getNotas();
        float[] estrA = new float[temp.size()];
        for (int j = 0; j < temp.size(); j++) {
            estrA[j] = temp.get(j);
        }
        return estrA;
    }
}
