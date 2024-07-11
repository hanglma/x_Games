package MenschAergereDichNicht;
import static MenschAergereDichNicht.Spielfigur.anzahlFiguren;
import java.util.Map;
import java.util.HashMap;


public class GameLogic
{

    Wuerfel wuerfel = new Wuerfel();
    private SpielfigurRot[] figurenRot = new SpielfigurRot[4];
    private SpielfigurGelb[] figurenGelb = new SpielfigurGelb[4];
    private SpielfigurGruen[] figurenGruen = new SpielfigurGruen[4];
    private SpielfigurBlau[] figurenBlau = new SpielfigurBlau[4];
    private int anzahlRot;
    private int anzahlGelb;
    private int anzahlGruen;
    private int anzahlBlau;
    private int rotpos, gelbpos, gruenpos, blaupos, i;
    private int mitzaehlen = 0;
    public Map<Integer, Integer> spielfeldAll = new HashMap<Integer, Integer>();

    public void Spielfeldeintragen(int Feldall, String farb)
    {

        int Farbnum;
        int i, l, pos, farbp;


            if(farb == "rot")
            {
                Farbnum = 1;
            }
            if(farb == "blau")
            {
                Farbnum = 2;
            }
            if(farb == "gelb")
            {
                Farbnum = 3;
            }
            if(farb == "gruen")
            {
                Farbnum = 4;
            }else
            {
                Farbnum = 0;
            }
            spielfeldAll.put(Feldall, Farbnum);

    }

    public String Farbauslesung(int Feld)
    {
        String farbe;
        int Feldnummer;
        Feldnummer = spielfeldAll.get(Feld);

        if(Feldnummer == 1)
        {
            farbe = "rot";
        }
        if(Feldnummer == 2)
        {
            farbe = "blau";
        }
        if(Feldnummer == 3)
        {
            farbe = "gelb";
        }
        if(Feldnummer == 4)
        {
            farbe = "gruen";
        }else
        {
            farbe = "leer";
        }
        return farbe;
    }


    public void spielzugAusfuehren(String farbe)
    {
        Spielfeld spielfeldall = new Spielfeld(mitzaehlen, farbe);

       if (farbe=="rot")
           {
                if (anzahlRot==0)
                {
                    figurenRot[anzahlRot]=new SpielfigurRot();
                    anzahlRot++;

                   /* if ("Gegner auf Feld")                          //noch zu bearbeiten
                    {
                       // "schmeißen"
                    }
                    else
                    {
                        spielzugAusfuehren(farbe);
                    }*/
                }
           }
       if (farbe=="gelb")
           {
               if (anzahlGelb==0)
               {
                    figurenGelb[anzahlGelb]=new SpielfigurGelb();
                    anzahlGelb++;
                 /*   if ("Gegner auf Feld")                          //noch zu bearbeiten
                    {
                        "schmeißen"
                    }
                    else
                    {
                        spielzugAusfuehren(farbe);
                    }*/
               }
           }
       if (farbe=="gruen")
           {
               if (anzahlGruen==0)
               {
                   figurenGruen[anzahlGruen]=new SpielfigurGruen();
                   anzahlGruen++;
                   /*if ("Gegner auf Feld")                          //noch zu bearbeiten
                   {
                       "schmeißen"
                   }
                   else
                   {
                       spielzugAusfuehren(farbe);
                   }*/
               }

           }
       if (farbe=="blau")
           {
               if (anzahlBlau==0)
               {
                   figurenBlau[anzahlBlau]=new SpielfigurBlau();
                   anzahlBlau++;
                  /* if ("Gegner auf Feld")                          //noch zu bearbeiten
                   {
                       "schmeißen"
                   }
                   else
                   {
                       spielzugAusfuehren(farbe);
                   }*/
               }


           }




    }

    public void Zug(String Farbe, int Anzahl, int Wuerfelzahl){

        //int Wuerfelzahl = wuerfel.wuerfeln();

        switch (Farbe)
        {

            case "rot":


                if (Wuerfelzahl == 6)
                    {
                        Wuerfelzahl = wuerfel.wuerfeln() + Wuerfelzahl;
                        /*ausgewählte Figur*/figurenRot[Anzahl].ziehen(Wuerfelzahl);     //noch falsch
                        rotpos = figurenRot[Anzahl].getPositionFeld();
                    }
                else
                    {
                        /*ausgewählte Figur*/figurenRot[Anzahl].ziehen(Wuerfelzahl);     //noch falsch
                        rotpos = figurenRot[Anzahl].getPositionFeld();
                    }

                Spielfeldeintragen(rotpos,"rot");
                break;

            case "blau":

                if (Wuerfelzahl == 6)
                {
                    Wuerfelzahl = wuerfel.wuerfeln() + Wuerfelzahl;
                    /*ausgewählte Figur*/figurenBlau[Anzahl].ziehen(Wuerfelzahl);     //noch falsch
                    blaupos = figurenBlau[Anzahl].getPositionFeld();
                }
                else
                {
                    /*ausgewählte Figur*/figurenBlau[Anzahl].ziehen(Wuerfelzahl);     //noch falsch
                    blaupos = figurenBlau[Anzahl].getPositionFeld();
                }

                Spielfeldeintragen(blaupos,"blau");
                break;

            case "gelb":

                if (Wuerfelzahl == 6)
                {
                    Wuerfelzahl = wuerfel.wuerfeln() + Wuerfelzahl;
                    /*ausgewählte Figur*/figurenGelb[Anzahl].ziehen(Wuerfelzahl);     //noch falsch
                    gelbpos = figurenGelb[Anzahl].getPositionFeld();
                }
                else
                {
                    /*ausgewählte Figur*/figurenGelb[Anzahl].ziehen(Wuerfelzahl);     //noch falsch
                    gelbpos = figurenGelb[Anzahl].getPositionFeld();
                }

                Spielfeldeintragen(gelbpos,"gelb");
                break;

            case "gruen":

                if (Wuerfelzahl == 6)
                {
                    Wuerfelzahl = wuerfel.wuerfeln() + Wuerfelzahl;
                    /*ausgewählte Figur*/figurenGruen[Anzahl].ziehen(Wuerfelzahl);     //noch falsch
                    gruenpos = figurenGruen[Anzahl].getPositionFeld();
                }
                else
                {
                    /*ausgewählte Figur*/figurenGruen[Anzahl].ziehen(Wuerfelzahl);     //noch falsch
                    gruenpos = figurenGruen[Anzahl].getPositionFeld();
                }

                Spielfeldeintragen(gruenpos,"gruen");
                break;

            }

        }

    }


}