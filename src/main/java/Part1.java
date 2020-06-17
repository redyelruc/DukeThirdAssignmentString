import edu.duke.*;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 != 0) {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            } else {
                break;
            }
        }
        if (currIndex == -1) {
            currIndex = dna.length();
        }
        return currIndex;
    }

    public String findGene(String dna) {
        String gene = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex != -1) {
            int stopIndexTAA = findStopCodon(dna, startIndex, "TAA") + 3;
            int stopIndexTAG = findStopCodon(dna, startIndex, "TAG") + 3;
            int stopIndexTGA = findStopCodon(dna, startIndex, "TGA") + 3;
            int stopIndex = Math.min(Math.min(stopIndexTAA, stopIndexTAG), stopIndexTGA);
            if (stopIndex <= dna.length()) {
                gene = dna.substring(startIndex, stopIndex);
            }
        }
        return gene;
    }

    public StorageResource getAllGenes(String dna) {
        StorageResource sr = new StorageResource();
        String gene = findGene(dna);
        sr.add(gene);
        String newDna = dna;

        while (!gene.equals("")) {
            int newStart = newDna.indexOf(gene) + gene.length();
            newDna = newDna.substring(newStart);
            gene = findGene(newDna);
            sr.add(gene);
        }
//        String geneList = "";
//        for (String s : sr.data()){
//            geneList = geneList + s;
//        }
        return sr;
    }

    public int findStringAinStringB(String stringA, String stringB){
        int occurrences = 0;
        int startIndex = stringB.indexOf(stringA);
        while (startIndex != -1){
            occurrences++;
            startIndex = stringB.indexOf(stringA, startIndex + stringA.length());
        }
        return occurrences;
    }

    public double cgRatio(String dna){
        double ceesAndGees = findStringAinStringB("C", dna) + findStringAinStringB("G", dna);
        double ratio = ceesAndGees/dna.length();
        return ratio;
    }

    public int countCTG(String dna){
        int occurrences = findStringAinStringB("CTG", dna);
        return occurrences;
    }

    public void processGenes(StorageResource sr){
        //print all the strings in sr longer than 9 characters
        int count = 0;
        int cgCount = 0;
        int longest = 0;
        for (String s : sr.data()){
            StorageResource geneList = new StorageResource();
            geneList = getAllGenes(s);
            for(String gene : geneList.data()) {
                System.out.println(gene);
                if (gene.length() > longest) {
                    longest = gene.length();
                }
                if (gene.length() > 5){
                    System.out.println(gene);
                    count ++;
                }
                if (cgRatio(gene) > 0.35){
                    System.out.println("higher than 0.35 = " + gene);
                    cgCount ++;
                }
            }
        }
        //print the number of Strings longer than 9 characters
        System.out.println("number of genes  = " + count);

        //print strings whose CG ratio is higher than 0.35
        System.out.println("number of genes higher than 0.35 = " + cgCount);
        //print the length of the longest gene
        System.out.println("longest gene has = " + longest + ("characters"));

    }

    public static void main(String[] args){
        StorageResource testSR = new StorageResource();

        String testDna = "";
        testSR.add(testDna.toUpperCase());
        Part1 part1 = new Part1();
        part1.processGenes(testSR);
    }
}