import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;
import java.io.*;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
 class search {
    public void searchId(){
               try (CSVReader reader = new CSVReader(new FileReader("criminal updated.csv"))) {
            String[] nextLine;
            String[] idArray = new String[0];
            String[] rowDataArray = new String[0];
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length > 0) {
                    String id = nextLine[0];
                    String row = String.join(",", nextLine); // Store the entire row as a CSV string

                    // Create new arrays with increased size
                    String[] newIdArray = new String[idArray.length + 1];
                    String[] newDataRowArray = new String[rowDataArray.length + 1];
                    System.arraycopy(idArray, 0, newIdArray, 0, idArray.length);
                    System.arraycopy(rowDataArray, 0, newDataRowArray, 0, rowDataArray.length);
                    newIdArray[idArray.length] = id;
                    newDataRowArray[rowDataArray.length] = row;
                    idArray = newIdArray;
                    rowDataArray = newDataRowArray;
                }

            }

            // Sort the array of IDs using Quick Sort
            quickSort(idArray, rowDataArray, 0, idArray.length - 1);


            // Perform a binary search for a specific ID
            Scanner scn = new Scanner(System.in);
            System.out.println("enter the id you want to search");
            String targetId = scn.nextLine(); 
            int resultIndex = binarySearch(idArray, targetId);

            if (resultIndex != -1) {
                String rowData = rowDataArray[resultIndex];
                System.out.println("Data corresponding to " + targetId + ":");
                System.out.println("ID,Name,Area,State,Type of Crime,tDate of Case Registration,Lawyer Assigned,Age,Case Status,Case ID,Decision");
                System.out.println(rowData);
            } else {
                System.out.println(targetId + " not found in the list.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(CsvValidationException c){
            c.printStackTrace();
            }
    }
    public static void quickSort(String[] arr, String[] rows, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, rows, low, high);
            quickSort(arr, rows, low, pivotIndex - 1);
            quickSort(arr, rows, pivotIndex + 1, high);
        }
    }

    public static int partition(String[] arr, String[] rows, int low, int high) {
        String pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) < 0) {
                i++;
                // Swap arr[i] and arr[j]
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                // Swap corresponding rows
                temp = rows[i];
                rows[i] = rows[j];
                rows[j] = temp;
            }
        }
        // Swap arr[i+1] and arr[high] (the pivot)
        String temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        // Swap corresponding rows
        temp = rows[i + 1];
        rows[i + 1] = rows[high];
        rows[high] = temp;
        return i + 1;
    }

    public static int binarySearch(String[] arr, String target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int comparison = target.compareTo(arr[mid]);

            if (comparison == 0) {
                return mid; // Element found
            } else if (comparison < 0) {
                right = mid - 1; // Adjust the right boundary
            } else {
                left = mid + 1; // Adjust the left boundary
            }
        }

        return -1; // Element not found
    }
    // -----------------------------------------------search id end-----------------------------------------------------------------
    public void searchname(){
        try (CSVReader reader = new CSVReader(new FileReader("criminal updated.csv"))) {
            String[] nextLine;
            Scanner scn = new Scanner(System.in);
            System.out.println("enter the name you want to search");
            String targetName = scn.nextLine();
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length > 0) {
                    String name = nextLine[1];
                    if (name.equalsIgnoreCase(targetName)) {
                        // Name found, display the entire row
                        System.out.println("Row corresponding to " + targetName + ":");
                        System.out.println("ID\tName\tArea\tState\tType of Crime\tDate of Case Registration\tLawyer Assigned\tAge\tCase Status\tCase ID\tDecision\r\n" + //
                                "");
                        String row = String.join("\t", nextLine);
                        System.out.println(row);
                        return;
                    }

                }
            }
            // If the loop completes and no match is found
            System.out.println(targetName + " not found ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(CsvValidationException c){
            c.printStackTrace();
            }
    }
    // -----------------------------------------------------------------------search name ends here ----------------------------------------------------------
     public void searcharea(){
                 try (CSVReader reader = new CSVReader(new FileReader("criminal updated.csv"))) {
            String[] nextLine;
            Scanner scn = new Scanner(System.in);
            boolean areaFound = false; // Track if the area is found
            System.out.println("enter the area you want to search");
            String targetArea = scn.nextLine();
            System.out.println("ID\tName\tArea\tState\tType of Crime\tDate of Case Registration\tLawyer Assigned\tAge\tCase Status\tCase ID\tDecision\r\n" + //
                                "");
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length > 0) {
                    String area = nextLine[2];
                    if (area.equalsIgnoreCase(targetArea)) {
                        // Area found, display the entire row
                        // System.out.println("Row corresponding to area " + targetArea + ":");
                        String row = String.join("\t", nextLine);
                        System.out.println(row);
                        areaFound = true;
                    }
                }
            }

            // If the loop completes and no match is found
            if (!areaFound) {
                System.out.println("No records found for area " + targetArea + ".");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(CsvValidationException c){
            c.printStackTrace();
            }
     }
     // -----------------------------------------------------------------------search name ends here ----------------------------------------------------------
 public void searchtype(){
    try (CSVReader reader = new CSVReader(new FileReader("criminal updated.csv"))) {
            String[] nextLine;
            Scanner scn = new Scanner(System.in);
            boolean crimeFound = false; // Track if the area is found
            System.out.println("enter the type of crime you want to search");
            String targetType = scn.nextLine();
            System.out.println("ID\tName\tArea\tState\tType of Crime\tDate of Case Registration\tLawyer Assigned\tAge\tCase Status\tCase ID\tDecision\r\n" + //
                                "");
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length > 0) {
                    // Assuming the type of crime is in the fourth column (adjust the index if needed)
                    String typeOfCrime = nextLine[4];
                    if (typeOfCrime.equalsIgnoreCase(targetType)) {
                        // Type of crime found, display the entire row
                        String row = String.join(",", nextLine);
                        System.out.println(row);
                        crimeFound = true;
                    }
                }
            }

            // If the loop completes and no match is found
            if (!crimeFound) {
                System.out.println("No records found for type of crime " + targetType + ".");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }       
        catch(CsvValidationException c){
            c.printStackTrace();
            }
 }
    }
 class data {
   private String id;
   private String name;
   private String area;
   private String state;
   private String type;
   private String date;
   private String lawassign;
   private String age;
   private String status;
   private String cid;
   private String decision;
    // inputing in private variable
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setLawassign(String lawassign) {
        this.lawassign = lawassign;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setCid(String cid) {
        this.cid = cid;
    }
    public void setDecision(String decision) {
        this.decision = decision;
    }

    // retrieving the data
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getArea() {
        return area;
    }
    public String getState() {
        return state;
    }
    public String getType() {
        return type;
    }
    public String getDate() {
        return date;
    }
    public String getLawassign() {
        return lawassign;
    }
    public String getAge() {
        return age;
    }
    public String getStatus() {
        return status;
    }
    public String getCid() {
        return cid;
    }
    public String getDecision() {
        return decision;
    }
}
//adding the data in table
class add extends data{
        void add_data(int i){
         Scanner scn = new Scanner(System.in);
        System.out.println("enter details of criminal to be added");
        // Specify the path to your CSV file
        String csvFilePath = "criminal updated.csv";
        // Create a list of data to write to the CSV file
        List<String> data = new ArrayList<>();
        System.out.println("\nplease enter the data in format mentioned below\n");
        for (int j = 0; j < i; j++) {
            System.out.println("Enter details of "+(j+1)+" criminals");
            System.out.print("ID: ");
            super.setId(scn.nextLine());

            System.out.print("Criminal Name: ");
            super.setName(scn.nextLine());

            System.out.print("Area: ");
            super.setArea(scn.nextLine());

            System.out.print("State: ");
            super.setState(scn.nextLine());

            System.out.print("Type of Crime: ");
            super.setType(scn.nextLine());

            System.out.print("Date of Case Registration: ");
            super.setDate(scn.nextLine());

            System.out.print("Lawyer Assigned: ");
            super.setLawassign(scn.nextLine());

            System.out.print("Age: ");
            super.setAge(scn.nextLine());

            System.out.print("Case Status: ");
            super.setStatus(scn.nextLine());

            System.out.print("Case ID: ");
            super.setCid(scn.nextLine());

            System.out.print("Decision: ");
            super.setDecision(scn.nextLine());
            //adding the data in the array list created above 
            data.add(super.getId());
            data.add(super.getName());
            data.add(super.getArea());
            data.add(super.getState());
            data.add(super.getType());
            data.add(super.getDate());
            data.add(super.getLawassign());
            data.add(super.getAge());
            data.add(super.getStatus());
            data.add(super.getCid());
            data.add(super.getDecision());
            // Initialize the CSVWriter in append mode to not to overwrite the data 
            try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath, true))) {
            // Writing  the data to the CSV file
            writer.writeNext(data.toArray(new String[0]));
        } 
            catch (IOException e) {
            e.printStackTrace();//catches error the in case any exception occured during appending in csv file of criminal file
        }
        System.out.println("\nCriminal Data of"+j+1+"Added\n");
        }



}
}
class view{
    void viewall(){
                try (CSVReader reader = new CSVReader(new FileReader("criminal updated.csv"))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // Process each row of data here
                for (String cell : nextLine) {
                    System.out.print( "|"+ cell +"|"+ "\t");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    }
class police{
    void pol(){
        Scanner scn = new Scanner(System.in);
        System.out.println("this is police portal");
        System.out.println("what do you want to do");
        System.out.println("enter \n 1)view all \n2)searching \n3)add new data \n4) exit");
        view v=new view();
        add b=new add();
        search s=new search();
        int a=scn.nextInt();
        switch (a) {
            case 1:
            v.viewall();//calling the view function         
                break;
            case 2:
            System.out.println("what do you want to search");
            System.out.println("enter \n1 for Id\n 2 for name\n 3 for area \n for type of crime");
            int sear=scn.nextInt();
            switch (sear) {
                case 1:
                    s.searchId();
                    break;
                case 2:
                    s.searchname();
                    break;
                case 3:
                    s.searcharea();
                    break;
                case 4:
                    s.searchtype();
                default:
                    break;
            }
            break;
            case 3:
            System.out.println("enter no. of data you want to be added");
            int n=scn.nextInt();
            if(n!=0){
                 b.add_data(n);
            }
            else{
                System.out.println("need to add minimum 1 data");
            }
           
            break;
            case 4:
                System.out.println("Exiting the program.");
                System.exit(0);
                break;
            default:
                break;
        }
    }
}
class lawyer{
    void law(){
        Scanner scn = new Scanner(System.in);
        System.out.println("\nthis is lawyer portal");
        System.out.println("what do you want to do");
        System.out.println("enter \n 1)view all \n 2)searching 3)exit");
        view v=new view();
        add b=new add();
        search s=new search();
        int a=scn.nextInt();
        switch (a) {
            case 1:
            v.viewall();//calling the view function         
                break;
            case 2:
            System.out.println("what do you want to search");
            System.out.println("enter \n1 for Id\n 2 for name\n 3 for area \n for type of crime");
            int sear=scn.nextInt();
            switch (sear) {
                case 1:
                    s.searchId();
                    break;
                case 2:
                    s.searchname();
                    break;
                case 3:
                    s.searcharea();
                case 4:
                    s.searchtype();
                default:
                    break;
            }
            break;
            case 3:
                System.out.println("Exiting the program.");
                System.exit(0);
                break;
            default:
                System.out.println("invalid choice");
                break;
        }
    }
}


class authoriselaw{
    private static final String LAWYER_DATA_FILE = "lawyer.csv";
        void authlaw() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Sign In System for lawyers");
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    signIn();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void signIn() {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(LAWYER_DATA_FILE)).build()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter email address: ");
            String emailAddress = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            lawyer l=new lawyer();
            String[] nextLine;
            boolean found = false;
    
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length == 5 &&
                        nextLine[3].equals(emailAddress) &&
                        nextLine[4].equals(password)) {
                    found = true;
                    System.out.println("Sign in successful.");
                    System.out.println("\nlawyer details");
                    System.out.println("Name: " + nextLine[0]);
                    System.out.println("Lawyer ID: " + nextLine[1]);
                    System.out.println("Court Name: " + nextLine[2]);
                    System.out.println("Email Address: " + nextLine[3]);
                    l.law();
                    break;
                }
            }
    
            if (!found) {
                System.out.println("Sign in failed. Invalid email address or password.");
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private void signUp() {
        try (FileWriter fileWriter = new FileWriter(LAWYER_DATA_FILE, true)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter lawyer ID: ");
            String lawyerID = scanner.nextLine();
            System.out.print("Enter court name: ");
            String courtName = scanner.nextLine();
            System.out.print("Enter email address: ");
            String emailAddress = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            String newUser = name + "," + lawyerID + "," + courtName + "," + emailAddress + "," + password;
            fileWriter.write(newUser + "\n");
            System.out.println("Sign up successful.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class authorisepolice{
    private static final String LAWYER_DATA_FILE = "policedata.csv";
        void authpol() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Sign In System for police");
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    signIn();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void signIn() {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(LAWYER_DATA_FILE)).build()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter email address: ");
            String emailAddress = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            police p=new police();
            String[] nextLine;
            boolean found = false;
    
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length == 7 &&
                        nextLine[3].equals(emailAddress) &&
                        nextLine[4].equals(password)) {
                    found = true;
                    System.out.println("Sign in successful.");
                    System.out.println("\n police details");
                    System.out.println("Name: " + nextLine[0]);
                    System.out.println("Lawyer ID: " + nextLine[1]);
                    System.out.println("police station area " + nextLine[2]);
                    System.out.println("Email Address: " + nextLine[3]);
                    System.out.println("police station id"+nextLine[5]);
                    System.out.println("post in police"+nextLine[6]);
                    p.pol();
                    break;
                }
            }
    
            if (!found) {
                System.out.println("Sign in failed. Invalid email address or password.");
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private void signUp() {
        try (FileWriter fileWriter = new FileWriter(LAWYER_DATA_FILE, true)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter police ID: ");
            String policeID = scanner.nextLine();
            System.out.print("Enter area of police station: ");
            String Area = scanner.nextLine();
            System.out.print("Enter email address: ");
            String emailAddress = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            System.out.print("Enter police station ID ");
            String psid = scanner.nextLine();
            System.out.print("Enter your post in police ");
            String post = scanner.nextLine();
            String newUser = name + "," + policeID + "," + Area + "," + emailAddress + "," + password+","+psid+","+post;
            fileWriter.write(newUser + "\n");
            System.out.println("Sign up successful.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
class documenttracking{
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("welcome to documenttracking");
        System.out.println("please enter which document you want to access");
        System.out.println("enter 1 for police");
        System.out.println("enter 2 for lawyer ");
        int a=scn.nextInt();
        police crim=new police();
        lawyer l=new lawyer();
        authoriselaw al=new authoriselaw();
        authorisepolice ap=new authorisepolice();
        switch (a) {
            case 1:
                ap.authpol();
                break;
            case 2:
                al.authlaw();
                break;
        
            default:
                System.out.println("exiting");
                break;
        }


    }
}