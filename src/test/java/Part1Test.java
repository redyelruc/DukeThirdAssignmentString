import org.junit.*;
import edu.duke.*;

public class Part1Test {
    Part1 part1;
    String dna;
    String stopCodon;
    String startCodon;
    int startIndex;

    @Before
    public void setup() {
        part1 = new Part1();
        startCodon = "ATG";

    }

    @Test
    public void testFindStopCodonFirstOccurrenceIsValidReturns10() {
        dna = "AATGGTCCCATAAATT";
        stopCodon = "TAA";
        startIndex = dna.indexOf(startCodon);
        int expected = 10;
        Assert.assertEquals(expected, part1.findStopCodon(dna, startIndex, stopCodon));
    }

    @Test
    public void testFindStopCodonSecondOccurrenceIsValidReturns16() {
        dna = "AATGGTSGTSGTAATCTAAGTC";
        stopCodon = "TAA";
        startIndex = dna.indexOf(startCodon);
        int expected = 16;
        Assert.assertEquals(expected, part1.findStopCodon(dna, startIndex, stopCodon));
    }

    @Test
    public void testFindStopCodonNoOccurrencesReturnsDnaLength() {
        dna = "AATGTGTGTGATGATGATGGT";
        stopCodon = "TAA";
        startIndex = dna.indexOf(startCodon);
        int expected = dna.length();
        Assert.assertEquals(expected, part1.findStopCodon(dna, startIndex, stopCodon));
    }

    @Test
    public void testFindGeneNoValidStartIndexReturnsEmptyString() {
        dna = "AACGTGTAATCTCTAG";
        String expected = "";
        Assert.assertEquals(expected, part1.findGene(dna));
    }

    @Test
    public void testFindGeneOneValidStopCodonReturnsGene() {
        dna = "AATGTGTAATGTCTAG";
        String expected = "ATGTGTAATGTCTAG";
        Assert.assertEquals(expected, part1.findGene(dna));
    }

    @Test
    public void testFindGeneThreeValidStopCodonsReturnsGene() {
        dna = "AATGTAATGATAGATCATC";
        String expected = "ATGTAA";
        Assert.assertEquals(expected, part1.findGene(dna));

    }

//    @Test
//    public void testGetAllGenesFinds3Genes() {
//        dna = "AATGTAATGATGTGATAGATGTAGCATC";
//        String expected = "ATGTAA";
//        expected = expected + "ATGTGA";
//        expected = expected + "ATGTAG";
//        Assert.assertEquals(expected, part1.getAllGenes(dna));
//    }

    @Test
    public void testCgRatioContainsBothReturns0point5() {
        dna = "ATGCCATACG";
        double expected = 0.5;
        Assert.assertEquals(expected, part1.cgRatio(dna), 0.0001);
    }

    @Test
    public void testCountCTGReturns0() {
        dna = "ATGATAATGTAATAGATGCCCATAA";
        int expected = 0;
        Assert.assertEquals(expected, part1.countCTG(dna));
    }

    @Test
    public void testCountCTGReturns3() {
        dna = "ACTGATAACTGTAATAGACTGCCCATAA";
        int expected = 3;
        Assert.assertEquals(expected, part1.countCTG(dna));
    }

    @Test
    public void testCountCTGinEmptyStringReturns0() {
        dna = "";
        int expected = 0;
        Assert.assertEquals(expected, part1.countCTG(dna));
    }

}