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

    public String getAllGenes(String dna) {
        StorageResource sr = new StorageResource();
        String gene = findGene(dna);
        sr.add(gene);

        while (!gene.equals("")) {
            int newStart = dna.indexOf(gene) + gene.length();
            String newDna = dna.substring(newStart);
            gene = findGene(newDna);
            sr.add(gene);
        }
        String geneList = "";
        for (String s : sr.data()){
            geneList = geneList + s;
        }
        return geneList;
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
}