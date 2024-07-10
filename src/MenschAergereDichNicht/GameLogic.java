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


    public class Spielfeld
    {
        public Map<Integer, Integer> spielfeldAll;

        public boolean feststellen;

        public int Feldall, Farbnum;
        public int i, l, pos, farbp;
        public String farb;

        public Spielfeld(int Feldall, String farb)
        {
            this.farb = farb;
            this.Feldall = Feldall;

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


       for(i = 0; i < 4; i++)
       {
           if (i == 0)
           {
               int Wuerfelzahl = wuerfel.wuerfeln();
               if (Wuerfelzahl == 6)
                {
                    figurenRot[anzahlRot]=new SpielfigurRot();
                    /*ausgewählte Figur*/figurenRot[anzahlRot].ziehen(Wuerfelzahl);             //noch falsch
                    rotpos = figurenRot[anzahlRot].getPositionFeld();
                }
               else
                {
                    /*ausgewählte Figur*/.ziehen(Wuerfelzahl);
                }
               spielfeldall = spielfeldall(rotpos, "rot");

           }

       }

    }

}