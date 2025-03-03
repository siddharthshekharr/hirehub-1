package com.hirehub;

import com.hirehub.model.Candidates;
import com.hirehub.model.Interviews;
import com.hirehub.model.Job;
import com.hirehub.model.Users;
import com.hirehub.model.Applications;
import com.hirehub.service.JobService;
import com.hirehub.service.OffersService;
import com.hirehub.service.UserService;
import com.hirehub.service.ApplicationService;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import com.hirehub.service.CandidateService;
import com.hirehub.service.InterviewsService;
import com.hirehub.dao.CandidatesDAO;
import com.hirehub.dao.CandidatesDAOIMPL;
import com.hirehub.dao.JobDAO;
import com.hirehub.dao.JobDAOIMPL;
import com.hirehub.model.Enums;
import java.util.Calendar;
import java.math.BigDecimal;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final JobService jobService = new JobService();
    private static final CandidateService candidateService = new CandidateService();
    private static final UserService userService = new UserService();
    private static final InterviewsService interviewsService = new InterviewsService();
    private static final OffersService offersService = new OffersService();
    private static final ApplicationService applicationService = new ApplicationService();

    // displays main menu where user can choose section to manage
    public static void main(String[] args) {
        System.out.println("Welcome to Hirehub!");
        boolean running = true;

        while (running) {
            displayMainMenu();

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> handleCandidateOperations();
                case 2 -> handleJobOperations();
                case 3 -> handleInterviewOperations();
                case 4 -> handleApplicationOperations();
                case 5 -> handleUserOperations();
                case 6 -> handleOfferOperations();
                case 7 -> {
                    System.out.println("Thank you for using Hirehub!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // String input, to get a string from the user
    private static String getStringInput(String prompt) {
        System.out.print(prompt);

        // Handle the case where we're coming from a getIntInput call
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.isEmpty() && scanner.hasNextLine()) {
                input = scanner.nextLine();
            }
            return input.trim();
        } else {
            scanner.next(); // Consume any non-newline token
            return scanner.nextLine().trim();
        }
    }

    // integer input, to ensure valid integer or prompts if invalid
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.next(); // Clear the invalid input
        }
        return scanner.nextInt();
    }

    // main menu options to the console
    private static void displayMainMenu() {
        System.out.println("HireHub Main Menu");
        System.out.println("1. Candidate Management");
        System.out.println("2. Job Management");
        System.out.println("3. Interview Management");
        System.out.println("4. Application Management");
        System.out.println("5. User Management");
        System.out.println("6. Offer Management");
        System.out.println("7. Exit");
    }

    // Job operations
    private static void handleJobOperations() {
        while (true) {
            System.out.println("\n === Job Management ===");
            System.out.println("1. Create New Job");
            System.out.println("2. View all Jobs");
            System.out.println("3. View by ID");
            System.out.println("4. Update Job");
            System.out.println("5. Delete Job");
            System.out.println("6. Back to Main menu");

            int choice = getIntInput("Enter your choice: ");
            try {
                switch (choice) {
                    // CRUD operations, methods handle actions
                    case 1 -> createJob();
                    case 2 -> viewAllJobs();
                    case 3 -> viewById();
                    case 4 -> updateJob();
                    case 5 -> deleteJob();
                    case 6 -> {
                        return;
                    } // Exit job management
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                e.printStackTrace(); // Or some other logging method
            }
        }
    }

    // Candidate operations
    private static void handleCandidateOperations() {
        while (true) {
            System.out.println("\n === Candidate Management ===");
            System.out.println("1. Register Candidate");
            System.out.println("2. View all Candidates");
            System.out.println("3. Update Candidates");
            System.out.println("4. Delete Candidates");
            System.out.println("5. Find Candidate by ID");
            System.out.println("6. Return to Main Menu");

            int choice = getIntInput("Enter your choice: ");
            try {
                switch (choice) {
                    case 1 -> createCandidate();
                    case 2 -> viewAllCandidates();
                    case 3 -> updateCandidate();
                    case 4 -> deleteCandidate();
                    case 5 -> findById();
                    case 6 -> {
                        return;
                    } // Exit candidate management
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                e.printStackTrace(); // Or some other logging method
            }
        }
    }

    // User operations
    private static void handleUserOperations() {
        while (true) {
            System.out.println("\n === User Management ===");
            System.out.println("1. Create User");
            System.out.println("2. View all Users");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Find User by ID");
            System.out.println("6. Return to Main Menu");

            int choice = getIntInput("Enter your choice: ");
            try {
                switch (choice) {
                    case 1 -> createUser();
                    case 2 -> viewAllUsers();
                    case 3 -> updateUser();
                    case 4 -> deleteUser();
                    case 5 -> findUserById();
                    case 6 -> {
                        return;
                    } // Exit user management
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                e.printStackTrace(); // Or some other logging method
            }
        }
    }

    // Interview operations
    private static void handleInterviewOperations() {
        while (true) {
            System.out.println("\n === Interview Management ===");
            System.out.println("1. Create Interview");
            System.out.println("2. View all Interviews");
            System.out.println("3. Update Interviews");
            System.out.println("4. Delete Interview");
            System.out.println("5. Find Interview");
            System.out.println("6. Return to Main Menu");

            int choice = getIntInput("Enter your choice: ");
            try {
                switch (choice) {
                    case 1 -> createInterview();
                    case 2 -> viewAllInterviews();
                    case 3 -> updateInterview();
                    case 4 -> deleteInterview();
                    case 5 -> findInterview();
                    case 6 -> {
                        return;
                    } // Exit interview management
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                e.printStackTrace(); // Or some other logging method
            }
        }
    }

    // Application operations
    private static void handleApplicationOperations() {
        while (true) {
            System.out.println("\n === Application Management ===");
            System.out.println("1. Create Application");
            System.out.println("2. View all Applications");
            System.out.println("3. Update Application");
            System.out.println("4. Delete Application");
            System.out.println("5. Find Application");
            System.out.println("6. Return to Main Menu");

            int choice = getIntInput("Enter your choice: ");
            try {
                switch (choice) {
                    case 1 -> createApplication();
                    case 2 -> viewAllApplications();
                    case 3 -> updateApplication();
                    case 4 -> deleteApplication();
                    case 5 -> findApplication();
                    case 6 -> {
                        return;
                    } // Exit application management
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                e.printStackTrace(); // Or some other logging method
            }
        }
    }

    // Offer operations
    private static void handleOfferOperations() {
        while (true) {
            System.out.println("\n === Offer Management ===");
            System.out.println("1. Create Offer");
            System.out.println("2. View all Offers");
            System.out.println("3. Update Offer");
            System.out.println("4. Delete Offer");
            System.out.println("5. Find Offer");
            System.out.println("6. Return to Main Menu");

            int choice = getIntInput("Enter your choice: ");
            try {
                switch (choice) {
                    case 1 -> createOffer();
                    case 2 -> viewAllOffers();
                    case 3 -> updateOffer();
                    case 4 -> deleteOffer();
                    case 5 -> findOffer();
                    case 6 -> {
                        return;
                    } // Exit application management
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                e.printStackTrace(); // Or some other logging method
            }
        }
    }

    // Job logic

    private static void createJob() {
        String title = getStringInput("Enter job title: ");
        String description = getStringInput("Enter job description: ");
        String requirements = getStringInput("Enter job requirements: ");

        System.out.println("Enter posting date (YYYY-MM-DD): ");
        String postingDateStr = scanner.nextLine();
        Date postingDate = null;
        try {
            postingDate = java.sql.Date.valueOf(postingDateStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format. Using current date.");
            postingDate = new Date();
        }

        System.out.println("Enter closing date (YYYY-MM-DD): ");
        String closingDateStr = scanner.nextLine();
        Date closingDate = null;
        try {
            closingDate = java.sql.Date.valueOf(closingDateStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format. Using date 30 days from now.");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 30);
            closingDate = new Date(calendar.getTimeInMillis());
        }

        System.out.println("Select job status:");
        System.out.println("1. DRAFT");
        System.out.println("2. OPEN");
        System.out.println("3. CLOSED");
        System.out.println("4. ON_HOLD");

        int statusChoice = getIntInput("Enter your choice: ");
        Enums.jobStatus status = switch (statusChoice) {
            case 1 -> Enums.jobStatus.DRAFT;
            case 2 -> Enums.jobStatus.OPEN;
            case 3 -> Enums.jobStatus.CLOSED;
            case 4 -> Enums.jobStatus.ON_HOLD;
            default -> Enums.jobStatus.DRAFT;
        };

        Job job = new Job();
        job.setTitle(title);
        job.setDescription(description);
        job.setRequirements(requirements);
        job.setPostingDate(postingDate);
        job.setClosingDate(closingDate);
        job.setStatus(status);

        JobDAO jobDAO = new JobDAOIMPL();
        jobDAO.add(job);

        System.out.println("Job created successfully! ID: " + job.getId());
    }

    private static void viewAllJobs() {
        JobDAO jobDAO = new JobDAOIMPL();
        List<Job> jobList = jobDAO.getAll();

        if (jobList.isEmpty()) {
            System.out.println("No jobs found.");
            return;
        }

        System.out.println("\n=== All Jobs ===");
        System.out.printf("%-5s %-30s %-15s %-15s %-10s%n",
                "ID", "Title", "Posting Date", "Closing Date", "Status");
        System.out.println("-------------------------------------------------------------------------");

        for (Job job : jobList) {
            System.out.printf("%-5d %-30s %-15s %-15s %-10s%n",
                    job.getId(),
                    job.getTitle(),
                    job.getPostingDate(),
                    job.getClosingDate(),
                    job.getStatus());
        }
    }

    private static void viewById() {
        int id = getIntInput("Enter job ID to find: ");

        JobDAO jobDAO = new JobDAOIMPL();
        Job job = jobDAO.getId(id);

        if (job == null) {
            System.out.println("Job not found with ID: " + id);
            return;
        }

        System.out.println("\n=== Job Details ===");
        System.out.printf("ID: %d%n", job.getId());
        System.out.printf("Title: %s%n", job.getTitle());
        System.out.printf("Description: %s%n", job.getDescription());
        System.out.printf("Requirements: %s%n", job.getRequirements());
        System.out.printf("Posting Date: %s%n", job.getPostingDate());
        System.out.printf("Closing Date: %s%n", job.getClosingDate());
        System.out.printf("Status: %s%n", job.getStatus());
    }

    private static void updateJob() {
        int id = getIntInput("Enter job ID to update: ");

        JobDAO jobDAO = new JobDAOIMPL();
        Job job = jobDAO.getId(id);

        if (job == null) {
            System.out.println("Job not found with ID: " + id);
            return;
        }

        System.out.println("Current details:");
        System.out.printf(
                "ID: %d%nTitle: %s%nDescription: %s%nRequirements: %s%nPosting Date: %s%nClosing Date: %s%nStatus: %s%n",
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getRequirements(),
                job.getPostingDate(),
                job.getClosingDate(),
                job.getStatus());

        // Get updated information
        String title = getStringInput("Enter new title (or press Enter to keep current): ");
        if (!title.isEmpty()) {
            job.setTitle(title);
        }

        String description = getStringInput("Enter new description (or press Enter to keep current): ");
        if (!description.isEmpty()) {
            job.setDescription(description);
        }

        String requirements = getStringInput("Enter new requirements (or press Enter to keep current): ");
        if (!requirements.isEmpty()) {
            job.setRequirements(requirements);
        }

        String postingDateStr = getStringInput(
                "Enter new posting date (YYYY-MM-DD) (or press Enter to keep current): ");
        if (!postingDateStr.isEmpty()) {
            try {
                Date postingDate = java.sql.Date.valueOf(postingDateStr);
                job.setPostingDate(postingDate);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Keeping current posting date.");
            }
        }

        String closingDateStr = getStringInput(
                "Enter new closing date (YYYY-MM-DD) (or press Enter to keep current): ");
        if (!closingDateStr.isEmpty()) {
            try {
                Date closingDate = java.sql.Date.valueOf(closingDateStr);
                job.setClosingDate(closingDate);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid date format. Keeping current closing date.");
            }
        }

        System.out.println("Select new status:");
        System.out.println("1. DRAFT");
        System.out.println("2. OPEN");
        System.out.println("3. CLOSED");
        System.out.println("4. ON_HOLD");
        System.out.println("5. Keep current status");

        int statusChoice = getIntInput("Enter your choice: ");
        if (statusChoice >= 1 && statusChoice <= 4) {
            Enums.jobStatus newStatus = switch (statusChoice) {
                case 1 -> Enums.jobStatus.DRAFT;
                case 2 -> Enums.jobStatus.OPEN;
                case 3 -> Enums.jobStatus.CLOSED;
                case 4 -> Enums.jobStatus.ON_HOLD;
                default -> job.getStatus();
            };
            job.setStatus(newStatus);
        }

        jobDAO.update(job);
        System.out.println("Job updated successfully!");
    }

    private static void deleteJob() {
        int id = getIntInput("Enter job ID to delete: ");

        JobDAO jobDAO = new JobDAOIMPL();
        Job job = jobDAO.getId(id);

        if (job == null) {
            System.out.println("Job not found with ID: " + id);
            return;
        }

        System.out.println("Are you sure you want to delete this job?");
        System.out.printf("ID: %d%nTitle: %s%nStatus: %s%n",
                job.getId(),
                job.getTitle(),
                job.getStatus());

        // Clear the scanner buffer
        scanner.nextLine();

        System.out.print("Type 'YES' to confirm deletion: ");
        String confirmation = scanner.nextLine().trim();

        if (confirmation.equalsIgnoreCase("YES")) {
            jobDAO.delete(id);
            System.out.println("Job deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    // Candidate logic

    private static void createCandidate() {
        scanner.nextLine(); // Clear buffer
        System.out.println("Enter Candidate first name");
        String firstName = scanner.nextLine();
        System.out.println("Enter Candidate last name");
        String lastName = scanner.nextLine();
        System.out.println("Enter Candidate email address");
        String email = scanner.nextLine();
        System.out.println("Enter Candidate phone number");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter Candidate resume URL (optional)");
        String resumeUrl = scanner.nextLine();

        Candidates candidate = new Candidates(firstName, lastName, email, phoneNumber);
        if (!resumeUrl.isEmpty()) {
            candidate.setresumeURL(resumeUrl);
        }

        candidateService.createCandidate(candidate);
        System.out.println("Candidate created successfully! ID: " + candidate.getId());
    }

    private static void viewAllCandidates() {
        CandidatesDAO candidatesDAO = new CandidatesDAOIMPL();
        List<Candidates> candidatesList = candidatesDAO.getAll();

        if (candidatesList.isEmpty()) {
            System.out.println("No candidates found.");
            return;
        }

        System.out.println("\n=== All Candidates ===");
        System.out.printf("%-5s %-15s %-15s %-25s %-15s %-10s%n",
                "ID", "First Name", "Last Name", "Email", "Phone", "Status");
        System.out.println("-------------------------------------------------------------------------");

        for (Candidates candidate : candidatesList) {
            System.out.printf("%-5d %-15s %-15s %-25s %-15s %-10s%n",
                    candidate.getId(),
                    candidate.getfirstName(),
                    candidate.getlastName(),
                    candidate.getemailAddress(),
                    candidate.getphoneNumber(),
                    candidate.getStatus());
        }
    }

    private static void updateCandidate() {
        int id = getIntInput("Enter candidate ID to update: ");

        CandidatesDAO candidatesDAO = new CandidatesDAOIMPL();
        Candidates candidate = candidatesDAO.getId(id);

        if (candidate == null) {
            System.out.println("Candidate not found with ID: " + id);
            return;
        }

        System.out.println("Current details:");
        System.out.printf("ID: %d%nName: %s %s%nEmail: %s%nPhone: %s%nStatus: %s%n",
                candidate.getId(),
                candidate.getfirstName(),
                candidate.getlastName(),
                candidate.getemailAddress(),
                candidate.getphoneNumber(),
                candidate.getStatus());

        // Get updated information
        String firstName = getStringInput("Enter new first name (or press Enter to keep current): ");
        if (!firstName.isEmpty()) {
            candidate.setfirstName(firstName);
        }

        String lastName = getStringInput("Enter new last name (or press Enter to keep current): ");
        if (!lastName.isEmpty()) {
            candidate.setlastName(lastName);
        }

        String email = getStringInput("Enter new email (or press Enter to keep current): ");
        if (!email.isEmpty()) {
            candidate.setemailAddress(email);
        }

        String phone = getStringInput("Enter new phone number (or press Enter to keep current): ");
        if (!phone.isEmpty()) {
            candidate.setphoneNumber(phone);
        }

        String resumeURL = getStringInput("Enter new resume URL (or press Enter to keep current): ");
        if (!resumeURL.isEmpty()) {
            candidate.setresumeURL(resumeURL);
        }

        System.out.println("Select new status:");
        System.out.println("1. ACTIVE");
        System.out.println("2. INACTIVE");
        System.out.println("3. BLACKLISTED");
        System.out.println("4. Keep current status");

        int statusChoice = getIntInput("Enter your choice: ");
        if (statusChoice >= 1 && statusChoice <= 3) {
            Candidates.CandidateStatus newStatus = switch (statusChoice) {
                case 1 -> Candidates.CandidateStatus.ACTIVE;
                case 2 -> Candidates.CandidateStatus.INACTIVE;
                case 3 -> Candidates.CandidateStatus.BLACKLISTED;
                default -> candidate.getStatus();
            };
            candidate.setStatus(newStatus);
        }

        candidatesDAO.update(candidate);
        System.out.println("Candidate updated successfully!");
    }

    private static void deleteCandidate() {
        int id = getIntInput("Enter candidate ID to delete: ");

        CandidatesDAO candidatesDAO = new CandidatesDAOIMPL();
        Candidates candidate = candidatesDAO.getId(id);

        if (candidate == null) {
            System.out.println("Candidate not found with ID: " + id);
            return;
        }

        System.out.println("Are you sure you want to delete this candidate?");
        System.out.printf("ID: %d%nName: %s %s%nEmail: %s%n",
                candidate.getId(),
                candidate.getfirstName(),
                candidate.getlastName(),
                candidate.getemailAddress());

        // Clear the scanner buffer
        scanner.nextLine();

        System.out.print("Type 'YES' to confirm deletion: ");
        String confirmation = scanner.nextLine().trim();

        if (confirmation.equalsIgnoreCase("YES")) {
            candidatesDAO.delete(id);
            System.out.println("Candidate deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private static void findById() {
        int id = getIntInput("Enter candidate ID to find: ");

        CandidatesDAO candidatesDAO = new CandidatesDAOIMPL();
        Candidates candidate = candidatesDAO.getId(id);

        if (candidate == null) {
            System.out.println("Candidate not found with ID: " + id);
            return;
        }

        System.out.println("\n=== Candidate Details ===");
        System.out.printf("ID: %d%n", candidate.getId());
        System.out.printf("First Name: %s%n", candidate.getfirstName());
        System.out.printf("Last Name: %s%n", candidate.getlastName());
        System.out.printf("Email: %s%n", candidate.getemailAddress());
        System.out.printf("Phone: %s%n", candidate.getphoneNumber());
        System.out.printf("Resume URL: %s%n", candidate.getresumeURL());
        System.out.printf("Status: %s%n", candidate.getStatus());
        System.out.printf("Registration Date: %s%n", candidate.getregistrationDate());
    }

    // User logic

    private static void createUser() {

    }

    private static void viewAllUsers() {

    }

    private static void updateUser() {

    }

    private static void deleteUser() {

    }

    private static void findUserById() {
    }

    // Interview logic

    private static void createInterview() {
        System.out.println("\n=== Create New Interview ===");

        // Get application ID
        int applicationId = getIntInput("Enter Application ID: ");

        // Get interview date
        System.out.println("Enter Interview Date (format: YYYY-MM-DD): ");
        String dateStr = getStringInput("");

        // Get interview time
        System.out.println("Enter Interview Time (format: HH:MM): ");
        String timeStr = getStringInput("");

        // Parse date and time
        Date interviewDate = null;
        try {
            Calendar calendar = Calendar.getInstance();
            String[] dateParts = dateStr.split("-");
            String[] timeParts = timeStr.split(":");

            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]) - 1; // Calendar months are 0-based
            int day = Integer.parseInt(dateParts[2]);
            int hour = Integer.parseInt(timeParts[0]);
            int minute = Integer.parseInt(timeParts[1]);

            calendar.set(year, month, day, hour, minute, 0);
            interviewDate = calendar.getTime();
        } catch (Exception e) {
            System.out.println("Invalid date or time format. Please try again.");
            return;
        }

        // Get feedback (optional)
        String feedback = getStringInput("Enter Feedback (optional): ");

        // Get status
        System.out.println("Select Interview Status:");
        System.out.println("1. SCHEDULED");
        System.out.println("2. COMPLETED");
        System.out.println("3. CANCELLED");
        System.out.println("4. NO_SHOW");

        int statusChoice = getIntInput("Enter your choice: ");
        Enums.interviewStatus status;

        switch (statusChoice) {
            case 1 -> status = Enums.interviewStatus.SCHEDULED;
            case 2 -> status = Enums.interviewStatus.COMPLETED;
            case 3 -> status = Enums.interviewStatus.CANCELLED;
            case 4 -> status = Enums.interviewStatus.NO_SHOW;
            default -> {
                System.out.println("Invalid choice. Setting status to SCHEDULED.");
                status = Enums.interviewStatus.SCHEDULED;
            }
        }

        // Create the interview
        try {
            Interviews interview = interviewsService.createInterview(applicationId, interviewDate, feedback, status);
            System.out.println("Interview created successfully with ID: " + interview.getinterviewID());
        } catch (Exception e) {
            System.out.println("Error creating interview: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void viewAllInterviews() {
        System.out.println("\n=== All Interviews ===");

        List<Interviews> interviews = interviewsService.getAllInterviews();

        if (interviews.isEmpty()) {
            System.out.println("No interviews found.");
            return;
        }

        System.out.println("ID | Application ID | Date | Status | Feedback");
        System.out.println("--------------------------------------------------");

        for (Interviews interview : interviews) {
            System.out.printf("%d | %d | %s | %s | %s%n",
                    interview.getinterviewID(),
                    interview.getapplicationID(),
                    interview.getinterviewDate(),
                    interview.getstatus(),
                    interview.getfeedback() != null ? interview.getfeedback() : "N/A");
        }
    }

    private static void updateInterview() {
        System.out.println("\n=== Update Interview ===");

        int interviewId = getIntInput("Enter Interview ID to update: ");

        // Retrieve the interview
        Interviews interview = interviewsService.getInterviewById(interviewId);

        if (interview == null) {
            System.out.println("Interview not found with ID: " + interviewId);
            return;
        }

        System.out.println("Current Interview Details:");
        System.out.printf("ID: %d | Application ID: %d | Date: %s | Status: %s | Feedback: %s%n",
                interview.getinterviewID(),
                interview.getapplicationID(),
                interview.getinterviewDate(),
                interview.getstatus(),
                interview.getfeedback() != null ? interview.getfeedback() : "N/A");

        // Update application ID
        System.out.println("Enter new Application ID (or press Enter to keep current): ");
        String applicationIdStr = getStringInput("");
        if (!applicationIdStr.isEmpty()) {
            try {
                int applicationId = Integer.parseInt(applicationIdStr);
                interview.setapplicationID(applicationId);
            } catch (NumberFormatException e) {
                System.out.println("Invalid application ID. Keeping current value.");
            }
        }

        // Update interview date
        System.out.println("Enter new Interview Date (format: YYYY-MM-DD) or press Enter to keep current: ");
        String dateStr = getStringInput("");

        if (!dateStr.isEmpty()) {
            System.out.println("Enter new Interview Time (format: HH:MM) or press Enter to keep current: ");
            String timeStr = getStringInput("");

            if (!timeStr.isEmpty()) {
                try {
                    Calendar calendar = Calendar.getInstance();
                    String[] dateParts = dateStr.split("-");
                    String[] timeParts = timeStr.split(":");

                    int year = Integer.parseInt(dateParts[0]);
                    int month = Integer.parseInt(dateParts[1]) - 1; // Calendar months are 0-based
                    int day = Integer.parseInt(dateParts[2]);
                    int hour = Integer.parseInt(timeParts[0]);
                    int minute = Integer.parseInt(timeParts[1]);

                    calendar.set(year, month, day, hour, minute, 0);
                    interview.setinterviewDate(calendar.getTime());
                } catch (Exception e) {
                    System.out.println("Invalid date or time format. Keeping current value.");
                }
            }
        }

        // Update feedback
        System.out.println("Enter new Feedback (or press Enter to keep current): ");
        String feedback = getStringInput("");
        if (!feedback.isEmpty()) {
            interview.setfeedback(feedback);
        }

        // Update status
        System.out.println("Select new Interview Status (or press Enter to keep current):");
        System.out.println("1. SCHEDULED");
        System.out.println("2. COMPLETED");
        System.out.println("3. CANCELLED");
        System.out.println("4. NO_SHOW");
        System.out.println("5. Keep current status");

        int statusChoice = getIntInput("Enter your choice: ");

        if (statusChoice >= 1 && statusChoice <= 4) {
            Enums.interviewStatus status;
            switch (statusChoice) {
                case 1 -> status = Enums.interviewStatus.SCHEDULED;
                case 2 -> status = Enums.interviewStatus.COMPLETED;
                case 3 -> status = Enums.interviewStatus.CANCELLED;
                case 4 -> status = Enums.interviewStatus.NO_SHOW;
                default -> status = interview.getstatus();
            }
            interview.setstatus(status);
        }

        // Update the interview
        boolean success = interviewsService.updateInterview(interview);

        if (success) {
            System.out.println("Interview updated successfully.");
        } else {
            System.out.println("Failed to update interview.");
        }
    }

    private static void deleteInterview() {
        System.out.println("\n=== Delete Interview ===");

        int interviewId = getIntInput("Enter Interview ID to delete: ");

        // Confirm deletion
        System.out.println("Are you sure you want to delete this interview? (y/n): ");
        String confirm = getStringInput("");

        if (confirm.equalsIgnoreCase("y")) {
            boolean success = interviewsService.deleteInterview(interviewId);

            if (success) {
                System.out.println("Interview deleted successfully.");
            } else {
                System.out.println("Failed to delete interview.");
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private static void findInterview() {
        System.out.println("\n=== Find Interview ===");

        int interviewId = getIntInput("Enter Interview ID to find: ");

        Interviews interview = interviewsService.getInterviewById(interviewId);

        if (interview == null) {
            System.out.println("Interview not found with ID: " + interviewId);
            return;
        }

        System.out.println("Interview Details:");
        System.out.printf("ID: %d%n", interview.getinterviewID());
        System.out.printf("Application ID: %d%n", interview.getapplicationID());
        System.out.printf("Date: %s%n", interview.getinterviewDate());
        System.out.printf("Status: %s%n", interview.getstatus());
        System.out.printf("Feedback: %s%n", interview.getfeedback() != null ? interview.getfeedback() : "N/A");
    }

    // Application logic

    private static void createApplication() {
        System.out.println("\n=== Create New Application ===");

        // Get job ID
        int jobId = getIntInput("Enter Job ID: ");

        // Get candidate ID
        int candidateId = getIntInput("Enter Candidate ID: ");

        // Get application date (default to current date)
        Date applicationDate = new Date();

        // Get current salary
        System.out.println("Enter Current Salary (optional, press Enter to skip): ");
        String currentSalaryStr = getStringInput("");
        BigDecimal currentSalary = null;
        if (!currentSalaryStr.isEmpty()) {
            try {
                currentSalary = new BigDecimal(currentSalaryStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary format. Setting to null.");
            }
        }

        // Get expected salary
        System.out.println("Enter Expected Salary (optional, press Enter to skip): ");
        String expectedSalaryStr = getStringInput("");
        BigDecimal expectedSalary = null;
        if (!expectedSalaryStr.isEmpty()) {
            try {
                expectedSalary = new BigDecimal(expectedSalaryStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary format. Setting to null.");
            }
        }

        // Get notice period
        System.out.println("Enter Notice Period in days (optional, press Enter to skip): ");
        String noticePeriodStr = getStringInput("");
        Integer noticePeriod = null;
        if (!noticePeriodStr.isEmpty()) {
            try {
                noticePeriod = Integer.parseInt(noticePeriodStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid notice period format. Setting to null.");
            }
        }

        // Get cover letter
        String coverLetter = getStringInput("Enter Cover Letter (optional): ");

        // Create the application object
        try {
            Applications application = new Applications(jobId, candidateId, applicationDate, "APPLIED", 0);

            if (currentSalary != null) {
                application.setCurrentSalary(currentSalary);
            }

            if (expectedSalary != null) {
                application.setExpectedSalary(expectedSalary);
            }

            if (noticePeriod != null) {
                application.setNoticePeriod(noticePeriod);
            }

            if (!coverLetter.isEmpty()) {
                application.setcoverLetter(coverLetter);
            }

            applicationService.createApplication(application);
            System.out.println("Application created successfully with ID: " + application.getapplicationID());
        } catch (Exception e) {
            System.out.println("Error creating application: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void viewAllApplications() {
        System.out.println("\n=== All Applications ===");

        List<Applications> applications = applicationService.getAllApplications();

        if (applications.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }

        System.out.println("ID | Job ID | Candidate ID | Date | Status");
        System.out.println("--------------------------------------------------");

        for (Applications application : applications) {
            System.out.printf("%d | %d | %d | %s | %s%n",
                    application.getapplicationID(),
                    application.getjobID(),
                    application.getcandidateID(),
                    application.getapplicationDate(),
                    application.getStatus());
        }
    }

    private static void updateApplication() {
        System.out.println("\n=== Update Application ===");

        int applicationId = getIntInput("Enter Application ID to update: ");

        // Retrieve the application
        Applications application = applicationService.getApplicationById(applicationId);

        if (application == null) {
            System.out.println("Application not found with ID: " + applicationId);
            return;
        }

        System.out.println("Current Application Details:");
        System.out.printf("ID: %d | Job ID: %d | Candidate ID: %d | Date: %s | Status: %s%n",
                application.getapplicationID(),
                application.getjobID(),
                application.getcandidateID(),
                application.getapplicationDate(),
                application.getStatus());

        // Update job ID
        System.out.println("Enter new Job ID (or press Enter to keep current): ");
        String jobIdStr = getStringInput("");
        if (!jobIdStr.isEmpty()) {
            try {
                int jobId = Integer.parseInt(jobIdStr);
                application.setjobID(jobId);
            } catch (NumberFormatException e) {
                System.out.println("Invalid job ID. Keeping current value.");
            }
        }

        // Update candidate ID
        System.out.println("Enter new Candidate ID (or press Enter to keep current): ");
        String candidateIdStr = getStringInput("");
        if (!candidateIdStr.isEmpty()) {
            try {
                int candidateId = Integer.parseInt(candidateIdStr);
                application.setcandidateID(candidateId);
            } catch (NumberFormatException e) {
                System.out.println("Invalid candidate ID. Keeping current value.");
            }
        }

        // Update status
        System.out.println("Select new Application Status (or press Enter to keep current):");
        System.out.println("1. APPLIED");
        System.out.println("2. SCREENING");
        System.out.println("3. SHORTLISTED");
        System.out.println("4. INTERVIEWING");
        System.out.println("5. OFFERED");
        System.out.println("6. HIRED");
        System.out.println("7. REJECTED");
        System.out.println("8. Keep current status");

        int statusChoice = getIntInput("Enter your choice: ");

        if (statusChoice >= 1 && statusChoice <= 7) {
            Enums.applicationStatus status;
            switch (statusChoice) {
                case 1 -> status = Enums.applicationStatus.APPLIED;
                case 2 -> status = Enums.applicationStatus.SCREENING;
                case 3 -> status = Enums.applicationStatus.SHORTLISTED;
                case 4 -> status = Enums.applicationStatus.INTERVIEWING;
                case 5 -> status = Enums.applicationStatus.OFFERED;
                case 6 -> status = Enums.applicationStatus.HIRED;
                case 7 -> status = Enums.applicationStatus.REJECTED;
                default -> status = application.getStatus();
            }
            application.setstatus(status);
        }

        // Update current salary
        System.out.println("Enter new Current Salary (or press Enter to keep current): ");
        String currentSalaryStr = getStringInput("");
        if (!currentSalaryStr.isEmpty()) {
            try {
                BigDecimal currentSalary = new BigDecimal(currentSalaryStr);
                application.setCurrentSalary(currentSalary);
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary format. Keeping current value.");
            }
        }

        // Update expected salary
        System.out.println("Enter new Expected Salary (or press Enter to keep current): ");
        String expectedSalaryStr = getStringInput("");
        if (!expectedSalaryStr.isEmpty()) {
            try {
                BigDecimal expectedSalary = new BigDecimal(expectedSalaryStr);
                application.setExpectedSalary(expectedSalary);
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary format. Keeping current value.");
            }
        }

        // Update notice period
        System.out.println("Enter new Notice Period in days (or press Enter to keep current): ");
        String noticePeriodStr = getStringInput("");
        if (!noticePeriodStr.isEmpty()) {
            try {
                Integer noticePeriod = Integer.parseInt(noticePeriodStr);
                application.setNoticePeriod(noticePeriod);
            } catch (NumberFormatException e) {
                System.out.println("Invalid notice period format. Keeping current value.");
            }
        }

        // Update cover letter
        System.out.println("Enter new Cover Letter (or press Enter to keep current): ");
        String coverLetter = getStringInput("");
        if (!coverLetter.isEmpty()) {
            application.setcoverLetter(coverLetter);
        }

        // Update the application
        try {
            applicationService.updateApplication(application);
            System.out.println("Application updated successfully.");
        } catch (Exception e) {
            System.out.println("Error updating application: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void deleteApplication() {
        System.out.println("\n=== Delete Application ===");

        int applicationId = getIntInput("Enter Application ID to delete: ");

        // Confirm deletion
        System.out.println("Are you sure you want to delete this application? (y/n): ");
        String confirm = getStringInput("");

        if (confirm.equalsIgnoreCase("y")) {
            try {
                applicationService.deleteApplication(applicationId);
                System.out.println("Application deleted successfully.");
            } catch (Exception e) {
                System.out.println("Error deleting application: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private static void findApplication() {
        System.out.println("\n=== Find Application ===");

        int applicationId = getIntInput("Enter Application ID to find: ");

        Applications application = applicationService.getApplicationById(applicationId);

        if (application == null) {
            System.out.println("Application not found with ID: " + applicationId);
            return;
        }

        System.out.println("Application Details:");
        System.out.printf("ID: %d%n", application.getapplicationID());
        System.out.printf("Job ID: %d%n", application.getjobID());
        System.out.printf("Candidate ID: %d%n", application.getcandidateID());
        System.out.printf("Application Date: %s%n", application.getapplicationDate());
        System.out.printf("Status: %s%n", application.getStatus());
        System.out.printf("Current Salary: %s%n",
                application.getCurrentSalary() != null ? application.getCurrentSalary() : "N/A");
        System.out.printf("Expected Salary: %s%n",
                application.getExpectedSalary() != null ? application.getExpectedSalary() : "N/A");
        System.out.printf("Notice Period: %s days%n",
                application.getnoticePeriod() != null ? application.getnoticePeriod() : "N/A");
        System.out.printf("Cover Letter: %s%n",
                application.getcoverLetter() != null ? application.getcoverLetter() : "N/A");
    }

    // Offer logic

    private static void createOffer() {

    }

    private static void viewAllOffers() {

    }

    private static void updateOffer() {

    }

    private static void deleteOffer() {

    }

    private static void findOffer() {
    }

    private static void addJob() {
        System.out.println("Create new job");
        System.out.println("Enter job title: ");
        String title = scanner.nextLine();
        System.out.println("Requirements needed for Job");
        String requirements = scanner.nextLine();
        System.out.println("Job Description");
        String description = scanner.nextLine();

        Job job = new Job(title, description, requirements, new Date(), null, "Open");
        jobService.createJob(job);
        System.out.println("Job created successfully!" + job.getId());

    }

    private static void addCandidate() {
        scanner.nextLine(); // Clear buffer
        System.out.println("Add new Candidate");
        System.out.println("Enter Candidate first name");
        String firstName = scanner.nextLine();
        System.out.println("Enter Candidate last name");
        String lastName = scanner.nextLine();
        System.out.println("Enter Candidate email address");
        String email = scanner.nextLine();
        System.out.println("Enter Candidate phone number");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter Candidate resume URL (optional)");
        String resumeUrl = scanner.nextLine();

        Candidates candidate = new Candidates(firstName, lastName, email, phoneNumber);
        if (!resumeUrl.isEmpty()) {
            candidate.setresumeURL(resumeUrl);
        }

        candidateService.createCandidate(candidate);
        System.out.println("Candidate created successfully! ID: " + candidate.getId());
    }

    private static void addUsers() {
        System.out.println("Add new User");
        System.out.println("Enter Username: ");
        String userName = scanner.nextLine();

        System.out.println("Enter user Role");
        String role = scanner.nextLine();

        System.out.println("Enter user email address");
        String email = scanner.nextLine();

        System.out.println("Enter User ID");
        int userId = scanner.nextInt();

        System.out.println("Enter Password");
        String password = scanner.nextLine();

        Users user = new Users(userId, userName, password, email, role);
        userService.createUsers(user);

        System.out.println("User created successfully!" + user.getuserId());

    }

    private static void addInterviews() {
        System.out.println("Add new Interview");

        System.out.println("Enter Interview ID");
        int interviewID = scanner.nextInt();

        System.out.println("Enter Application ID");
        int applicationID = scanner.nextInt();

        System.out.println("Enter Interview Date");
        int interviewDate = scanner.nextInt();

        System.out.println("Enter Interview Feedback");
        String feedback = scanner.nextLine();

        Interviews interview = new Interviews(interviewID, applicationID, null, feedback, feedback);
        interviewsService.createInterview(applicationID, new Date(), feedback, Enums.interviewStatus.SCHEDULED);

        // job CRUD
        JobService jobService = new JobService();

        // Create a new job
        Job newJob = new Job("Software Developer", "Develops application", "Java", new Date(), new Date(), "Open");
        jobService.createJob(newJob);
        System.out.println("New job created with ID: " + newJob.getId());

        // get all jobs
        List<Job> allJobs = jobService.getAllJobs();
        System.out.println("All jobs");

        for (Job job : allJobs) {
            System.out.println(job);
        }

        // get a specific job and store it in Job type in variable called retrievedJob
        Job retrievedJob = jobService.getJobById(newJob.getId());
        System.out.println("Retrieved job: " + retrievedJob);

        // Delete the job
        jobService.deleteJob(newJob.getId());

    }
}