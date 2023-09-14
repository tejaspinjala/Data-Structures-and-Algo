//import java.io.*;
//import java.text.*;
//import java.util.*;
//
//public class clinicTree {
//
//    private static final String FILE_NAME = "patient.txt";
//    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
//
//    private TreeNode root;
//
//    public clinicTree() {
//        root = null;
//    }
//
//    public static void loadDatabase(String fileName) throws ParseException, FileNotFoundException {
//        File file = new File(fileName);
//        Scanner scanner = new Scanner(file);
//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//            String[] parts = line.split(",");
//            String name = parts[0];
//            String doctor = parts[1];
//            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//            Date date = null;
//            if (!parts[2].trim().isEmpty()) {
//                date = dateFormat.parse(parts[2]);
//            }
//            Date nextAppointment = null;
//            if (parts.length > 3 && !parts[3].trim().isEmpty()) {
//                nextAppointment = dateFormat.parse(parts[3]);
//            }
//            ArrayList<Patient> patients = new ArrayList<Patient>();
//            patients.add(new Patient(name, doctor, date, nextAppointment));
//        }
//        scanner.close();
//    }
//    
//    public void addPatient() throws ParseException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the patient's name (first last): ");
//        String name = scanner.nextLine();
//        System.out.println("Enter the doctor's name: ");
//        String doctor = scanner.nextLine();
//        System.out.println("Enter the appointment date (MM/DD/YYYY): ");
//        Date date = DATE_FORMAT.parse(scanner.nextLine());
//        insert(new Patient(name, doctor, date, null));
//        System.out.println("Patient added successfully!");
//    }
//    
//    public void deletePatient() throws ParseException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the patient's name (first last): ");
//        String name = scanner.nextLine();
//        Patient patient = search(name);
//        if (patient == null) {
//            System.out.println("Patient not found.");
//            return;
//        }
//        delete(patient);
//        System.out.println("Patient deleted successfully!");
//    }
//    
//    public void editAppointment() throws ParseException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the patient's name (first last): ");
//        String name = scanner.nextLine();
//        Patient patient = search(name);
//        if (patient == null) {
//            System.out.println("Patient not found.");
//            return;
//        }
//        System.out.println("Enter the new appointment date (MM/DD/YYYY): ");
//        Date date = DATE_FORMAT.parse(scanner.nextLine());
//        patient.appointmentDate = date;
//        System.out.println("Appointment updated successfully!");
//    }
//    
//    public void searchNextWeekByDoctor() throws ParseException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the doctor's name: ");
//        String doctor = scanner.nextLine();
//        List<Patient> patients = getNextWeekByDoctor(doctor);
//        if (patients.isEmpty()) {
//            System.out.println("No patients found.");
//            return;
//        }
//        if (patients.size() == 1) {
//            System.out.println("Patient found: " + patients.get(0));
//            return;
//        }
//        System.out.println("Multiple patients found:");
//        for (int i = 0; i < patients.size(); i++) {
//            System.out.println((i+1) + ": " + patients.get(i));
//        }
//        System.out.println("Enter the number of the patient to select: ");
//        int selection = scanner.nextInt();
//        System.out.println("Patient selected: " + patients.get(selection-1));
//    }
//    
//    private void searchPatient() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter patient name: ");
//        String name = scanner.nextLine();
//        
//        ArrayList<Patient> patients = searchPatientsByName(name);
//        
//        if (patients.isEmpty()) {
//            System.out.println("No patients found with that name.");
//            return;
//        }
//        
//        if (patients.size() == 1) {
//            Patient patient = patients.get(0);
//            System.out.println("Patient information:");
//            System.out.println("Name: " + patient.name);
//            System.out.println("Doctor: " + patient.doctorName);
//            System.out.println("Appointment date: " + DATE_FORMAT.format(patient.appointmentDate));
//            System.out.println("Annual date: " + (patient.annualDate == null ? "Not set" : DATE_FORMAT.format(patient.annualDate)));
//        } else {
//            System.out.println(patients.size() + " patients found with that name. Please select one:");
//            for (int i = 0; i < patients.size(); i++) {
//                System.out.println((i + 1) + ": " + patients.get(i).name);
//            }
//            int choice = Integer.parseInt(scanner.nextLine());
//            Patient patient = patients.get(choice - 1);
//            System.out.println("Patient information:");
//            System.out.println("Name: " + patient.name);
//            System.out.println("Doctor: " + patient.doctorName);
//            System.out.println("Appointment date: " + DATE_FORMAT.format(patient.appointmentDate));
//            System.out.println("Annual date: " + (patient.annualDate == null ? "Not set" : DATE_FORMAT.format(patient.annualDate)));
//        }
//    }
//    
//    private ArrayList<Patient> searchPatientsByName(String name) {
//    	ArrayList<Patient> patients = new ArrayList<Patient>();
//    	searchPatientsByName(root, name, patients);
//    	return patients;
//    }
//    
//    private void searchPatientsByName(TreeNode node, String name, ArrayList<Patient> patients) {
//        if (node == null) {
//            return;
//        }
//
//        int compare = node.patient.name.compareToIgnoreCase(name);
//
//        if (compare == 0) {
//            patients.add(node.patient);
//        }
//
//        searchPatientsByName(node.left, name, patients);
//        searchPatientsByName(node.right, name, patients);
//    }
//
//    private Patient search(String name) {
//        TreeNode node = search(root, name);
//        if (node == null) {
//            return null;
//        } else {
//            return node.patient;
//        }
//    }
//
//    private TreeNode search(TreeNode node, String name) {
//        if (node == null) {
//            return null;
//        }
//
//        int compare = node.patient.name.compareToIgnoreCase(name);
//
//        if (compare == 0) {
//            return node;
//        } else if (compare > 0) {
//            return search(node.left, name);
//        } else {
//            return search(node.right, name);
//        }
//    }
//
//    private void delete(Patient patient) {
//        root = delete(root, patient);
//    }
//    
//    private void insert(Patient patient) {
//    	root = insert(root, patient);
//    }
//
//    private TreeNode delete(TreeNode node, Patient patient) {
//        if (node == null) {
//            return null;
//        }
//
//        int compare = patient.compareTo(node.patient);
//
//        if (compare < 0) {
//            node.left = delete(node.left, patient);
//        } else if (compare > 0) {
//            node.right = delete(node.right, patient);
//        } else {
//            if (node.left == null) {
//                return node.right;
//            } else if (node.right == null) {
//                return node.left;
//            } else {
//                TreeNode temp = node.right;
//                while (temp.left != null) {
//                    temp = temp.left;
//                }
//                node.patient = temp.patient;
//                node.right = delete(node.right, temp.patient);
//            }
//        }
//
//        return node;
//    }
//
//    private List<Patient> getNextWeekByDoctor(String doctor) {
//        List<Patient> patients = new ArrayList<Patient>();
//        getNextWeekByDoctor(root, doctor, patients);
//        return patients;
//    }
//
//    private void getNextWeekByDoctor(TreeNode node, String doctor, List<Patient> patients) {
//        if (node == null) {
//            return;
//        }
//
//        getNextWeekByDoctor(node.left, doctor, patients);
//
//        if (node.patient.doctorName.equalsIgnoreCase(doctor)) {
//            Date today = new Date();
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(today);
//            calendar.add(Calendar.DAY_OF_YEAR, 7);
//            Date nextWeek = calendar.getTime();
//            if (node.patient.appointmentDate != null && node.patient.appointmentDate.after(today) && node.patient.appointmentDate.before(nextWeek)) {
//                patients.add(node.patient);
//            }
//        }
//
//        getNextWeekByDoctor(node.right, doctor, patients);
//    }
//
//    private void inOrderTraversal(TreeNode node) {
//        if (node != null) {
//            inOrderTraversal(node.left);
//            System.out.println(node.patient);
//            inOrderTraversal(node.right);
//        }
//    }
//
//    private TreeNode insert(TreeNode node, Patient patient) {
//        if (node == null) {
//            return new TreeNode(patient);
//        }
//
//        if (patient.compareTo(node.patient) < 0) {
//            node.left = insert(node.left, patient);
//        } else {
//            node.right = insert(node.right, patient);
//        }
//
//        return node;
//    }
//
//    private static class TreeNode {
//        Patient patient;
//        TreeNode left;
//        TreeNode right;
//
//        public TreeNode(Patient patient) {
//            this.patient = patient;
//            this.left = null;
//            this.right = null;
//        }
//    }
//
//    private static class Patient implements Comparable<Patient> {
//        String name;
//        String doctorName;
//        String appointmentDate;
//        String annualDate;
//
//        public Patient(String name, String doctorName, String appointmentDate, String annualDate) {
//            this.name = name;
//            this.doctorName = doctorName;
//            this.appointmentDate = appointmentDate;
//            this.annualDate = annualDate;
//        }
//
//        public int compareTo(Patient other) {
//            return name.compareTo(other.name);
//        }
//    	
//    	public String getPatient() {
//    		return name;
//    	}
//    	
//    	public String getDoctor() {
//    		return doctorName;
//    	}
//    	
//    	public String getAppointment() {
//    		return appointmentDate;
//    	}
//    	
//    	public String getAnnualDate() {
//    		return annualDate;
//    	}
//
//    }
//
//    public static void main(String[] args) {
//        clinicTree database = new clinicTree();
//        try {
//            database.loadDatabase(FILE_NAME);
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found: " + FILE_NAME);
//        } catch (ParseException e) {
//            System.out.println("Error parsing date in file: " + FILE_NAME);
//        }
//    }
//
//
//}
