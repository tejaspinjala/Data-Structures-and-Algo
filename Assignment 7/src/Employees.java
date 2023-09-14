import java.util.*;

//Employees class
public class Employees {
	private static class EmployeeRecord implements Comparable<EmployeeRecord> {
		final int employeeNumber;
		final double hourlyRate;

		//constructor
		public EmployeeRecord(int employeeNumber, double hourlyRate) {
			this.employeeNumber = employeeNumber;
			this.hourlyRate = hourlyRate;
		}

		
		public int getEmployeeNumber() {
        	return employeeNumber;
		}

        
		public double getHourlyRate() {
			return hourlyRate;	
		}
		
		@Override
		public int compareTo(EmployeeRecord other) {
			return Integer.compare(employeeNumber, other.employeeNumber);
		}

		@Override
		public String toString() {
			return employeeNumber + ": " + hourlyRate;
		}
	}
    
	//main method
	public static void main(String[] args) {
		
		// initialize heap with employee records
		PriorityQueue<EmployeeRecord> heap = new PriorityQueue<>();
        
		//scanner
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter employee records (employee number and hourly rate, separated by spaces): ");
		String input = scanner.nextLine();
        
		//separates the input into employee number and hourly rate
		while (!input.isEmpty()) {
			String[] tokens = input.split(" ");
			int employeeNumber = Integer.parseInt(tokens[0]);
			double hourlyRate = Double.parseDouble(tokens[1]);
			heap.offer(new EmployeeRecord(employeeNumber, hourlyRate));
			System.out.print("Enter another employee record (or press enter to continue): ");
			input = scanner.nextLine();	
		}

		//allow user to insert or delete records
		while (true) {
			System.out.print("Enter 'i' to insert a record, 'd' to delete a record, or 'q' to quit: ");
			String action = scanner.nextLine();
			if (action.equals("i")) {
				System.out.print("Enter employee number and hourly rate (separated by a space): ");
				String[] tokens = scanner.nextLine().split(" ");
				int employeeNumber = Integer.parseInt(tokens[0]);
				double hourlyRate = Double.parseDouble(tokens[1]);
				heap.offer(new EmployeeRecord(employeeNumber, hourlyRate));
			} else if (action.equals("d")) {
				System.out.print("Enter employee number to delete: ");
				int employeeNumber = Integer.parseInt(scanner.nextLine());
				heap.removeIf(record -> record.getEmployeeNumber() == employeeNumber);
			} else if (action.equals("q")) {
				break;
			}
		}

		//sort updated list using heap sort
		List<EmployeeRecord> sortedRecords = new ArrayList<>(heap.size());
		while (!heap.isEmpty()) {
			sortedRecords.add(heap.poll());
		}

		//display sorted list
		System.out.println("Employee records (sorted by employee number):");
		for (EmployeeRecord record : sortedRecords) {
			System.out.println(record);
		}
	}
}