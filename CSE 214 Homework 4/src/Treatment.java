import java.util.Comparator;

/**
 * @author Ritwik Banerjee
 * @author Daniel Garcia
 */
public class Treatment {

    String name;
    String diseaseTreated;
    double probabilityOfSuccess;
    double pricePerUnit;

    public Treatment(String name, String diseaseTreated, double probabilityOfSuccess, double pricePerUnit) {
        this.name = name;
        this.diseaseTreated = diseaseTreated;
        this.probabilityOfSuccess = probabilityOfSuccess;
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public int hashCode() {
        int  result;
        long temp;
        result = name.hashCode();
        result = 31 * result + diseaseTreated.hashCode();
        temp = Double.doubleToLongBits(probabilityOfSuccess);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pricePerUnit);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return ("Treatment{name=\'" + name + "\', diseaseTreated=\'" + diseaseTreated + "\', probabilityOfSuccess=" + probabilityOfSuccess + ", pricePerUnit=" + pricePerUnit + "}");
    }

    public boolean equals(Treatment o) {
        boolean names = this.name == o.name;
        boolean disease = this.diseaseTreated == o.diseaseTreated;
        boolean price = this.pricePerUnit == o.pricePerUnit;
        boolean probability = this.probabilityOfSuccess == o.probabilityOfSuccess;
        if (names && disease && price && probability) return true;
        else return false;
    }

    protected static class PriceBasedTreatmentComparator implements Comparator<Treatment> {
        @Override
        public int compare(Treatment a, Treatment b) {
            if (b.pricePerUnit > a.pricePerUnit) return 1;
            else if (b.pricePerUnit < a.pricePerUnit) return -1;
            else return 0;
        }
    }

    protected static class SuccessBasedTreatmentComparator implements Comparator<Treatment> {
        @Override
        public int compare(Treatment a, Treatment b) {
            if (a.probabilityOfSuccess > b.probabilityOfSuccess) return 1;
            else if (a.probabilityOfSuccess < b.probabilityOfSuccess) return -1;
            else return 0;
        }
    }

}