package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        
    }

    @Test
    public void negatiivinenVarausLuoTyhjanVaraston() {
        Varasto turha = new Varasto(-7);
        assertEquals(0,turha.paljonkoMahtuu(),vertailuTarkkuus);
    }
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void tilavuudenAsettaminenToimii() {
        Varasto var = new Varasto(5,0);
        assertEquals(var.getTilavuus(),5,vertailuTarkkuus);
    }
    
    @Test
    public void negatiivivinenTilavuusTuleeNollaksi() {
        Varasto var = new Varasto(-5,0);
        assertEquals(var.getTilavuus(),0,vertailuTarkkuus);
    }

    @Test
    public void negatiiviinenAlkusaldoTuleeNollaksi() {
        Varasto var = new Varasto(5,-1);
        assertEquals(var.getSaldo(),0,vertailuTarkkuus);
    }
    
    @Test 
    public void josMahtuuNiinOikeaSaldo() {
        Varasto var = new Varasto(5,3);
        assertEquals(var.paljonkoMahtuu(),2,vertailuTarkkuus);
    }
    
    @Test
    public void laitetaanKonstruktoriinLiikaa() {
        Varasto var = new Varasto(5,7);
        assertEquals(var.getSaldo(),4,vertailuTarkkuus);
    }
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0",varasto.toString());
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisataanNegatiivista() {
        varasto.lisaaVarastoon(-1);
        assertEquals(10,varasto.paljonkoMahtuu(),vertailuTarkkuus);
    }
    
    @Test
    public void lisataanLiikaa() {
        varasto.lisaaVarastoon(100);
        assertEquals(0,varasto.paljonkoMahtuu(),vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void yritetaanOttaaLiikaa() {
        varasto.lisaaVarastoon(8);
        
        double saatuMaara = varasto.otaVarastosta(10);
        assertEquals(8,saatuMaara,vertailuTarkkuus);
    }
    
    @Test
    public void yritetaanOttaaNegatiivinenMaara() {
        varasto.lisaaVarastoon(5);
        
        double saatuMaara = varasto.otaVarastosta(-5);
        assertEquals(0,saatuMaara,vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}