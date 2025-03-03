package com.hirehub;

import com.hirehub.model.Candidates;
import com.hirehub.model.Interviews;
import com.hirehub.model.Job;
import com.hirehub.model.Users;
import com.hirehub.service.JobService;
import com.hirehub.service.OffersService;
import com.hirehub.service.UserService;
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

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final JobService jobService = new JobService();
    private static final CandidateService candidateService = new CandidateService();
    private static final UserService userService = new UserService();
    private static final InterviewsService interviewsService = new InterviewsService();
    private static final OffersService offersService = new OffersService();

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

    }

    private static void viewAllInterviews() {

    }

    private static void updateInterview() {

    }

    private static void deleteInterview() {

    }

    private static void findInterview() {
    }

    // Application logic

    private static void createApplication() {

    }

    private static void viewAllApplications() {

    }

    private static void updateApplication() {

    }

    private static void deleteApplication() {

    }

    private static void findApplication() {
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
        interviewsService.createInterviews();

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