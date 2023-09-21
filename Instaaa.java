import java.util.*;
import java.util.Random;

// Define the Instaa class.
class Instaa {
    static Scanner sc = new Scanner(System.in);  // Create a static Scanner for user input.
    private String user = "Giri";  // Default user name.
    private String password = "Giri@1234567890";  // Default password.
    private String gmail;
    private String gmailPassword;

    // Method to set the user name.
    void setval(String user) {
        this.user = user;
    }

    // Method to set the password.
    void setval1(String password) {
        this.password = password;
    }

    // Method to get the user name.
    String getuser() {
        return user;
    }

    // Method to get the password.
    String getpassword() {
        return password;
    }

    // Method to set the Gmail address.
    void setGmail(String gmail) {
        this.gmail = gmail;
    }

    // Method to set the Gmail password.
    void setGmailPassword(String gmailPassword) {
        this.gmailPassword = gmailPassword;
    }

    // Method to get the Gmail address.
    String getGmail() {
        return gmail;
    }

    // Method to get the Gmail password.
    String getGmailPassword() {
        return gmailPassword;
    }
}

// Define the User class, which extends the Instaa class.
class User extends Instaa {
    private static final int MAX_LOGIN_ATTEMPTS = 3;
    private int usernameAttempts = 0;
    private int passwordAttempts = 0;
    private boolean loggedIn = false;  // Indicates whether a user is currently logged in.
    private String loggedInUser = "";  // Stores the user who is currently logged in.

    // Main method, the entry point of the program.
    public static void main(String[] args) {
        User obj = new User();  // Create an instance of the User class.
        boolean registered = false;  // Flag to track whether a user is registered.

        System.out.println(" => => => => WELCOME TO INSTAA <= <= <= <=");

        while (true) {  // Main program loop, continues until the program exits.
            if (obj.loggedIn) {  // If a user is logged in.
                System.out.println("Logged in as:  -> " + obj.loggedInUser + " <- ");
                // Adding a 4-second delay before displaying the menu after login
                delaySeconds(4);
                System.out.println("6. Log Out");
            } else {
                // Adding a 3-second delay before displaying menu options
                delaySeconds(3);

                System.out.println("1. Sign Up");
                System.out.println("2. Log In");
                System.out.println("3. Reset Username");
                System.out.println("4. Reset Password");
                System.out.println("5. Exit");
            }
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();  // Read the user's choice.

            switch (choice) {
                case 1:
                    signUp(obj);  // Call the signUp method to register a new user.
                    registered = true;  // Set registered flag to true.
                    break;
                case 2:
                    if (!obj.loggedIn) {
                        login(obj);  // Call the login method to log in.
                    } else {
                        System.out.println(" -> You are already logged in as: " + obj.loggedInUser);
                    }
                    break;
                case 3:
                    if (registered) {
                        resetUsername(obj);  // Call the resetUsername method to reset the username.
                    } else {
                        System.out.println("    ****    You are not registered. Please sign up first.    ****    ");
                    }
                    break;
                case 4:
                    if (registered) {
                        resetPassword(obj);  // Call the resetPassword method to reset the password.
                    } else {
                        System.out.println("    ****    You are not registered. Please sign up first.    ****    ");
                    }
                    break;
                case 5:
                    System.out.println(" -> -> Exiting the program. <- <-");
                    System.exit(0);  // Exit the program.
                    break;
                case 6:
                    if (obj.loggedIn) {
                        if (logOutConfirmation()) {
                            obj.logOut();  // Call the logOut method to log out the user.
                            // Adding a 4-second delay after displaying "Logged out successfully"
                            delaySeconds(4);
                        } else {
                            System.out.println("    ****    Logout canceled.    ****    ");
                        }
                    } else {
                        System.out.println("    ****    You are not logged in.    ****    ");
                    }
                    break;
                default:
                    System.out.println("    ****    Invalid choice. Please enter a valid option.    ****    ");
            }
        }
    }

    // Method to handle user sign-up.
    static void signUp(User obj) {
        System.out.print("Enter your User Name: ");
        String enteredUser = sc.next();
        obj.setval(enteredUser);  // Set the user name using the setval method.

        System.out.print("Enter your User Password: ");
        String enteredPassword = sc.next();
        obj.setval1(enteredPassword);  // Set the password using the setval1 method.

        System.out.print("Enter your Gmail: ");
        String enteredGmail = sc.next();
        obj.setGmail(enteredGmail);  // Set the Gmail address using the setGmail method.

        System.out.print("Enter your Gmail Password: ");
        String enteredGmailPassword = sc.next();
        obj.setGmailPassword(enteredGmailPassword);  // Set the Gmail password using the setGmailPassword method.

        // Adding a 5-second delay
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Handle the InterruptedException, but for simplicity, this code does nothing.
            // You can add appropriate error handling here if needed.
        }

        System.out.println("    $$$$    Registration Successful!    $$$$    ");
    }

    // Method to handle user login.
    static void login(User obj) {
        if (obj.usernameAttempts < MAX_LOGIN_ATTEMPTS) {
            System.out.print("Enter your User Name: ");
            String enteredUser = sc.next();

            if (obj.getuser().equals(enteredUser)) {
                System.out.print("Enter your User Password: ");
                String enteredPassword = sc.next();

                if (obj.getpassword().equals(enteredPassword)) {
                    obj.loggedIn = true;
                    obj.loggedInUser = enteredUser;
                    // Adding a 4-second delay for displaying "Login Success!" message
                    delaySeconds(4);
                    System.out.println("    @@@@    Login Success!    @@@@    ");
                    return;
                } else {
                    obj.passwordAttempts++;
                    int remainingAttempts = MAX_LOGIN_ATTEMPTS - obj.passwordAttempts;
                    if (remainingAttempts > 0) {
                        System.out.println(" !!!! Invalid Password. You have " + remainingAttempts + " more attempts. !!!! ");
                        login(obj); // Recursive call to retry entering the password immediately.
                    } else {
                        System.out.println("    ****    You've exceeded the maximum number of login attempts for the password.    ****    ");
                        resetPassword(obj);  // Call the resetPassword method if max login attempts for password are reached.
                    }
                }
            } else {
                obj.usernameAttempts++;
                int remainingAttempts = MAX_LOGIN_ATTEMPTS - obj.usernameAttempts;
                if (remainingAttempts > 0) {
                    System.out.println(" !!!! Invalid User Name. You have " + remainingAttempts + " more attempts. !!!! ");
                    login(obj); // Recursive call to retry entering the username immediately.
                } else {
                    System.out.println("    ****    You've exceeded the maximum number of login attempts for the username.    ****    ");
                    resetPassword(obj);  // Call the resetPassword method if max login attempts for username are reached.
                }
            }
        } else {
            System.out.println("    ****    You've exceeded the maximum number of login attempts for both username and password. Please sign up again.    ****    ");
            obj.usernameAttempts = 0;
            obj.passwordAttempts = 0;
        }
    }

    // Method to handle resetting the username.
    static void resetUsername(User obj) {
        boolean otpSent = false;
        int remainingGmailAttempts = 3;

        while (remainingGmailAttempts > 0) {
            System.out.print("Enter your Gmail: ");
            String enteredGmail = sc.next();

            if (obj.getGmail().equals(enteredGmail)) {
                System.out.print("Enter your Gmail Password: ");
                String enteredGmailPassword = sc.next();

                if (obj.getGmailPassword().equals(enteredGmailPassword)) {
                    String otp = generateOTP();  // Generate OTP
                    System.out.println(" -> -> Your OTP for username reset is: " + otp);
                    otpSent = true;

                    while (true) {
                        System.out.print(" -> -> Enter OTP to confirm username reset (or 'R' to resend OTP): ");
                        String enteredOTP = sc.next();

                        if (otp.equals(enteredOTP)) {
                            // Adding a 3-second delay after OTP matches
                            delaySeconds(3);

                            System.out.print("Enter your new User Name: ");
                            String newUsername = sc.next();
                            obj.setval(newUsername);  // Set the new user name.
                            System.out.println("    ****    Username reset successful.    ****    ");
                            obj.usernameAttempts = 0;
                            return;
                        } else if (enteredOTP.equalsIgnoreCase("R")) {
                            otp = generateOTP();  // Resend OTP
                            System.out.println(" => => Resending OTP: " + otp);
                        } else {
                            System.out.println("    ****    Invalid OTP. Username reset failed.    ****    ");
                        }
                    }
                } else {
                    System.out.println(" !!!! Invalid Gmail Password. You have " + remainingGmailAttempts + " more attempts. !!!! ");
                    remainingGmailAttempts--;
                }
            } else {
                System.out.println(" !!!!Invalid Gmail. You have " + remainingGmailAttempts + " more attempts. !!!! ");
                remainingGmailAttempts--;
            }
        }
        if (!otpSent) {
            System.out.println("    ****    Failed to send OTP. Username reset failed.    ****    ");
        }
    }

    // Method to handle resetting the password.
    static void resetPassword(User obj) {
        boolean otpSent = false;
        int remainingGmailAttempts = 3;

        while (remainingGmailAttempts > 0) {
            System.out.print("Enter your Gmail: ");
            String enteredGmail = sc.next();

            if (obj.getGmail().equals(enteredGmail)) {
                System.out.print("Enter your Gmail Password: ");
                String enteredGmailPassword = sc.next();

                if (obj.getGmailPassword().equals(enteredGmailPassword)) {
                    String otp = generateOTP();  // Generate OTP
                    System.out.println(" -> -> Your OTP for password reset is: " + otp);
                    otpSent = true;

                    while (true) {
                        System.out.print(" -> -> Enter OTP to confirm password reset (or 'R' to resend OTP): ");
                        String enteredOTP = sc.next();

                        if (otp.equals(enteredOTP)) {
                            // Adding a 3-second delay after OTP matches
                            delaySeconds(3);

                            System.out.print("Enter your new User Password: ");
                            String newPassword = sc.next();
                            obj.setval1(newPassword);  // Set the new password.
                            System.out.println("    ****    Password reset successful.    ****    ");
                            obj.passwordAttempts = 0;
                            return;
                        } else if (enteredOTP.equalsIgnoreCase("R")) {
                            otp = generateOTP();  // Resend OTP
                            System.out.println(" => => Resending OTP: " + otp);
                        } else {
                            System.out.println("    ****    Invalid OTP. Password reset failed.    ****    ");
                        }
                    }
                } else {
                    System.out.println(" !!!! Invalid Gmail Password. You have " + remainingGmailAttempts + " more attempts. !!!! ");
                    remainingGmailAttempts--;
                }
            } else {
                System.out.println(" !!!! Invalid Gmail. You have " + remainingGmailAttempts + " more attempts. !!!! ");
                remainingGmailAttempts--;
            }
        }
        if (!otpSent) {
            System.out.println("    ****    Failed to send OTP. Password reset failed.    ****    ");
        }
    }

    // Method to handle user logout.
    void logOut() {
        loggedIn = false;
        loggedInUser = "";
        // Adding a 4-second delay after displaying "Logged out successfully"
        delaySeconds(4);
        System.out.println("    ****    Logged out successfully.    ****    ");
    }

    // Method to generate a random OTP (One-Time Password).
    static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(999999);

        // Adding a 3-second delay
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // Handle the InterruptedException, but for simplicity, this code does nothing.
            // You can add appropriate error handling here if needed.
        }

        return Integer.toString(otp);
    }

    // Method to introduce a delay in seconds.
    static void delaySeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            // Handle the InterruptedException, but for simplicity, this code does nothing.
            // You can add appropriate error handling here if needed.
        }
    }

    // Method to confirm user logout.
    static boolean logOutConfirmation() {
        System.out.print("!!!! Are you sure you want to log out? (Y/N): ");
        String choice = sc.next().toLowerCase();
        return choice.equals("y");  // Return true if the user wants to log out.
    }
}

