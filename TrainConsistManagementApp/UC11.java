import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrainApp {

    public static void main(String[] args) {
        System.out.println("=== Train Consist Management App ===");

        String trainIdPattern = "TRN-\\d{4}";
        String cargoCodePattern = "PET-[A-Z]{2}";

        Pattern trainPattern = Pattern.compile(trainIdPattern);
        Pattern cargoPattern = Pattern.compile(cargoCodePattern);

        String[] testTrainIds = {"TRN-1234", "TRAIN12", "TRN-123", "TRN-12345"};
        String[] testCargoCodes = {"PET-AB", "PET-ab", "PET123", "AB-PET"};

        System.out.println("\nValidating Train IDs:");
        for (String id : testTrainIds) {
            Matcher matcher = trainPattern.matcher(id);
            if (matcher.matches()) {
                System.out.println(id + " : VALID");
            } else {
                System.out.println(id + " : INVALID");
            }
        }

        System.out.println("\nValidating Cargo Codes:");
        for (String code : testCargoCodes) {
            Matcher matcher = cargoPattern.matcher(code);
            if (matcher.matches()) {
                System.out.println(code + " : VALID");
            } else {
                System.out.println(code + " : INVALID");
            }
        }
    }
}
