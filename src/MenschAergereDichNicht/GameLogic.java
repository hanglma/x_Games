package MenschAergereDichNicht;
import static MenschAergereDichNicht.Spielfigur.anzahlFiguren;


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

    public void spielzugAusfuehren(String farbe)
    {

       int Wuerfelzahl = wuerfel.wuerfeln();
       if (Wuerfelzahl==6)
       {
           if (farbe=="rot")
           {
                if (anzahlRot==0)
                {
                    figurenRot[anzahlRot]=new SpielfigurRot();
                    anzahlRot++;
                    if ("Gegner auf Feld")                          //noch zu bearbeiten
                    {
                        "schmeißen"
                    }
                    else
                    {
                        spielzugAusfuehren(farbe);
                    }
                }
           }
           if (farbe=="gelb")
           {
               if (anzahlGelb==0)
               {
                    figurenGelb[anzahlGelb]=new SpielfigurGelb();
                    anzahlGelb++;
                    if ("Gegner auf Feld")                          //noch zu bearbeiten
                    {
                        "schmeißen"
                    }
                    else
                    {
                        spielzugAusfuehren(farbe);
                    }
               }
           }
           if (farbe=="gruen")
           {
               if (anzahlGruen==0)
               {
                   figurenGruen[anzahlGruen]=new SpielfigurGruen();
                   anzahlGruen++;
                   if ("Gegner auf Feld")                          //noch zu bearbeiten
                   {
                       "schmeißen"
                   }
                   else
                   {
                       spielzugAusfuehren(farbe);
                   }
               }

           }
           if (farbe=="blau")
           {
               if (anzahlBlau==0)
               {
                   figurenBlau[anzahlBlau]=new SpielfigurBlau();
                   anzahlBlau++;
                   if ("Gegner auf Feld")                          //noch zu bearbeiten
                   {
                       "schmeißen"
                   }
                   else
                   {
                       spielzugAusfuehren(farbe);
                   }
               }
               else
               {

               }

           }
       }

       else
       {

       }
    }
}