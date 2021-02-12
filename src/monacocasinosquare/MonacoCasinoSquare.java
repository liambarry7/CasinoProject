package monacocasinosquare;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class MonacoCasinoSquare {

    public static Scanner input = new Scanner (System.in);
    public static Random rand = new Random();
    public static ArrayList<Login> loginList = new ArrayList<>();
    public static boolean loggedIn = false;
    public static int reference = 0;

    
    public static void main(String[] args) {
        loginList = FileHandling.readFile();
        reference = loginList.size();
        newMenu();
    }
    
    public static void newMenu() {
        while (loggedIn == false) {
            try {
                System.out.println("1 - Sign Up");
                System.out.println("2 - Login");
                System.out.println("0 - Exit");
                int start = input.nextInt();
                
                if (start == 1) {
                    signUp();
                } else if (start == 2) {
                    login();
                } else if (start == 0) {
                    FileHandling.writeFile(loginList);
                    System.exit(start);
                }
                
            } catch (Exception e) {
                System.out.println("Error occured: " + e);
                input.nextLine();
            }            
        }
        
        mainMenu(); 
    }
    

    public static void signUp() {
        try {
            
            System.out.println("Please enter a username."); 
            String username = input.next();

            System.out.println("Please enter a password.");
            String password = input.next();


            int accountNumber = reference;
            
            reference = reference + 1;
            
            
            
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("Account Number: " + accountNumber);
 
            int money = 50;
            int moneyWon = 0;
            int moneyLost = 0;
            int gamesPlayed = 0;

            Login newLogin = new Login(username, password, accountNumber, money, moneyWon, moneyLost, gamesPlayed);
            loginList.add(newLogin);
            

            
        } catch (Exception e) {
            System.out.println("Error occured: " + e);
            System.out.println("Try again.");
            input.nextLine();
            
        }

    }

    public static void login() {
        try {
            int index = getAccNum();
            if (index != -1) {
                reference = index;
                
                
                System.out.println("What is your username?");
                String username = input.next();

                if (username.equals(loginList.get(index).getUsername())) {

                    System.out.println("What is your password?");
                    String password = input.next();
                    
                    if (password.equals(loginList.get(index).getPassword())) {
                        System.out.println("Password accepted.");
                        
                        if (username.equals("Admin") && password.equals("Admin1")) {
                            adminPowers();
                        } else {
                            loggedIn = true;
                            FileHandling.writeFile(loginList);
                        }
                        
                    } else {
                        System.out.println("Login details incorrect.");
                    }

                } else {
                    System.out.println("Login details incorrect.");
                }

            } else {
                System.out.println("Account number invalid.");
            }

        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
            System.out.println("Please try again.");
            input.nextInt();
        }
        

    }

    public static int getAccNum() {
        System.out.println("Please type in your account Number");
        int accountNumber = input.nextInt();
        
        if (!loginList.isEmpty()) {
            for (int i = 0; i < loginList.size(); i++) {
                if (accountNumber == (loginList.get(i).getAccountNumber())) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void view() {
        for (int i = 0; i < loginList.size(); i++) {
            System.out.println(loginList.get(i).toString());
        }
    }

    public static void editUsers() {
        try {
            view();
            System.out.println("Enter Account Number:");
            int editAcc = input.nextInt();
            
            System.out.println("1 - Change Username");
            System.out.println("2 - Change Password");
            System.out.println("3 - Add Coins");
            System.out.println("4 - Delete User");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter new Username");
                    String newUsername = input.next();
                    loginList.get(editAcc).setUsername(newUsername);
                    break;

                case 2:
                    System.out.println("Enter new password");
                    String newPassword = input.next();
                    loginList.get(editAcc).setPassword(newPassword);
                    break;

                case 3:
                    System.out.println("Enter amount of Coins");
                    int moreCoins = input.nextInt();
                    loginList.get(editAcc).setMoney( loginList.get(editAcc).getMoney() + moreCoins); //adds  new money to previous amount
                    break;
                    
                case 4:
                    System.out.println("Are you sure you want to do this? \nEnter 'confirm' to delete account.");                    
                    String delete = input.next();
                    if (delete.equals("confirm")) {
                        loginList.remove(editAcc);
                    } else {
                        System.out.println("Account will not be deleted.");
                    }                    
                    break;
                    
            }

        } catch (Exception e) {
            System.out.println("Error occured!");
        }

    }

    public static void adminPowers() { //Admin, Admin1, 0
        try {
            while (true) {
                System.out.println("\nAdmin:");
                System.out.println("1 - View Users");
                System.out.println("2 - Edit Users");
                System.out.println("0 - Log Out");
                String choice = input.next();

                if (choice.equals("1")) {
                    view();
                } else if (choice.equals("2")) {
                    editUsers();
                } else if (choice.equals("0")) {
                    System.out.println("Logging Out...\n");
                    break;
                } else {
                    System.out.println("Please try again!");
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred.");
            input.next();
        }
    }

    
    

    public static int getMoney() {
        int money = loginList.get(reference).getMoney();
        return money;
    }
    
    public static int getMoneyWon() {
        int moneyWon = loginList.get(reference).getMoneyWon();
        return moneyWon;
    }
    
    public static int getmoneyLost() {
        int moneyLost = loginList.get(reference).getMoneyLost();
        return moneyLost;
    }
    
    public static int getGamesPlayed() {
        int gamesPlayed = loginList.get(reference).getGamesPlayed();
        return gamesPlayed;
    }
    

    

    
    public static void mainMenu() {
        loginList = FileHandling.readFile();//read file to get updated money after each game
        
        
        
        System.out.println("\nWelcome to the Casino.");
        while (true) {
            System.out.println("\nWhat game would you like to play?");
            System.out.println("1 - Blackjack"); 
            System.out.println("2 - Higher or Lower"); 
            System.out.println("3 - Roulette"); 
            System.out.println("4 - View Balance");
            System.out.println("5 - Rules");
            System.out.println("0 - Exit");
            int gameChoice = input.nextInt();
            
            switch (gameChoice) {
                case 0:
                    System.out.println("Money left: " + getMoney() );
                    System.exit(gameChoice);
                case 1:
                    blackjack();
                    FileHandling.writeFile(loginList);
                    break;
                case 2:
                    higherLower();
                    FileHandling.writeFile(loginList);
                    break;
                case 3:
                    rouletteWheel();
                    FileHandling.writeFile(loginList);
                    break;
                case 4:
                    balance();
                    break;
                case 5:
                    rules();
                    break;
                default:
                    System.out.println("Please enter a valid number");
                    
            }
        }

    }
    
    public static void balance() {
        int money = getMoney();
        int moneyWon = getMoneyWon();
        int moneyLost = getmoneyLost();
        int gamesPlayed = getGamesPlayed();
        
        System.out.println("Balance: " + money);
        System.out.println("Money Won: " + moneyWon);
        System.out.println("Money Lost: " + moneyLost);
        System.out.println("Games Played: " + gamesPlayed);
        
    }
    
    public static void rules() {
        System.out.println("When the fun stops you stop.");
        System.out.println("You start at 50 coins.");
        System.out.println("If you run out of coins, you loose, you do not pass go, you do not collect $200, and your account is terminated");
        System.out.println("Please enter all bets as integers.");
    }
    
    
    public static void blackjack() {
        
        System.out.println("\nWelcome to Blackjack"); 
        
        boolean run = true;
        while (run == true) {
            int cardTotal = 0;
            int bet = 0;
            boolean bust = false;
            
            
            int card = randomNumbers(1,13);
            
            int realValue = blackjackValues(card);
           
            String displayCard = displayCardCorrector(card);
            
            int cardTwo = randomNumbers(1,13);
           
            int realValueTwo = blackjackValues(cardTwo);
            
            String displayCardTwo = displayCardCorrector(cardTwo);
            
            cardTotal = realValue + realValueTwo;
            
            while (true) {
                bet = betting();
                if (bet == 0) {
                    System.out.println("Please try again");
                } else {
                    System.out.print("");
                    break;
                }
            }
            
            System.out.println("\nYou first cards are:");
            System.out.println(displayCard + "\n" + displayCardTwo);
            System.out.println("Total: " + cardTotal);
            
            while (true) {
                System.out.println("\nWould you like to double down? (y/n)"); //check thus works
                String doubleBet = input.next();
                if (doubleBet.equals("y")) {
                    bet = bet * 2;
                    System.out.println("Bet: " + bet);
                    break;
                } else if (doubleBet.equals("n")) {
                    System.out.println("Bet: " + bet);
                    break;
                } else {
                    System.out.println("Please try again.");
                }
            }
            
            
            while (true) {
                System.out.println("\nWould you like to stick or twist (s/t)");
                String choice = input.next();

                if (choice.equals("s")) {
                    System.out.println("You have chosen to stick");
                    break;
                    
                } else if (choice.equals("t")) {
                    System.out.println("You have chosen to twist");
                    int twistCard = randomNumbers(1, 13);
                    int twistRealValue = blackjackValues(twistCard);
                    String twistDisplay = displayCardCorrector(twistCard);
                    System.out.println("You next card is: \n" + twistDisplay);
                    cardTotal = cardTotal + twistRealValue;
                    System.out.println("Total: " + cardTotal);

                    if (cardTotal > 21) {
                        System.out.println("You went bust!");
                        bust = true;
                        break;
                    } else if (cardTotal == 21) {
                        //System.out.println("You have 21!");
                        break;
                    }
                }
            }

            System.out.println("\nYour total is: " + cardTotal);

            int dealerTotal = dealerCards();   
            System.out.println("The dealer has a total of: " + dealerTotal);
            
            if (bust == true) {
                System.out.println("You lost!");
                
                loginList.get(reference).setMoney(loginList.get(reference).getMoney() - bet); //takes away betted money from total balance
                loginList.get(reference).setMoneyLost(loginList.get(reference).getMoneyLost() + bet); //adds losing bet to total loosings
                
            } else if (dealerTotal < cardTotal) {
                System.out.println("You won!");
                
                loginList.get(reference).setMoney(loginList.get(reference).getMoney() +bet); //takes away betted money from total balance
                loginList.get(reference).setMoneyWon(loginList.get(reference).getMoneyWon() + bet); //adds losing bet to total loosings
                
                
            } else if (dealerTotal == cardTotal) {
                System.out.println("You lost!");
               
                loginList.get(reference).setMoney(loginList.get(reference).getMoney() - bet); //takes away betted money from total balance
                loginList.get(reference).setMoneyLost(loginList.get(reference).getMoneyLost() + bet); //adds losing bet to total loosings
                
            } else if (dealerTotal > cardTotal){
                System.out.println("You lost!");
                
                loginList.get(reference).setMoney(loginList.get(reference).getMoney() - bet); //takes away betted money from total balance
                loginList.get(reference).setMoneyLost(loginList.get(reference).getMoneyLost() + bet); //adds losing bet to total loosings
            }
            
            loginList.get(reference).setGamesPlayed(loginList.get(reference).getGamesPlayed() + 1); //add 1 to total games played
            run = false;
            //endGame(); //runs method to see if users is bankrupt
        }
        
    }
    
    public static int dealerCards() {
        int dealerTotal = 0;
        for (int i = 0; i < 2; i++) {
            int dealerCard = randomNumbers(2,11);
            dealerTotal = dealerTotal + dealerCard;
        }
        
        while (dealerTotal < 17) { //makes it so that dealer always has a total below 21 and above 17 to give unfair advantage
                int dif = 21 - dealerTotal;
                int houseAdvantage = randomNumbers(1, dif);
                dealerTotal = dealerTotal + houseAdvantage;
            }
        
        return dealerTotal;
    }    
    
    public static int blackjackValues(int card) {
        
        int cardValue = 0; //determinds value of cards according to rules of blackjack
        if (card == 13) {
            cardValue = 10;
        } else if (card == 12) { //picture cards are worth 10
            cardValue = 10;
        } else if (card == 11) {
            cardValue = 10;
        } else if (card == 1) { //aces are equal to 11            
            cardValue = 11;
        } else {
            cardValue = card;
        }        
        return cardValue;
    }



    
    
    public static void higherLower() {
        
        try {

            int streak = 0;
            int bet = 0;

            System.out.println("\nWelcome to Higher or Lower.");
            boolean run = true;
            while (run == true) {
                int min = 1;
                int max = 13;
                int card = randomNumbers(min, max);
                String displayCard = displayCardCorrector(card);

                int cardTwo = randomNumbers(min, max);
                String displayCardTwo = displayCardCorrector(cardTwo);

                System.out.println("The card is: " + displayCard);
                System.out.println("Will the next card be:");
                System.out.println("1 - Higher");
                System.out.println("or");
                System.out.println("2 - Lower");
                int userGuess = input.nextInt();

                while (true) {
                    bet = betting();
                    if (bet == 0) {
                        System.out.println("Please try again");
                    } else {
                        System.out.print("");
                        break;
                    }
                }

                

                if (userGuess == 1 && cardTwo > card) {
                    System.out.println("You Won!");
                    loginList.get(reference).setMoney(loginList.get(reference).getMoney() + bet); 
                    loginList.get(reference).setMoneyWon(loginList.get(reference).getMoneyWon() + bet); 
                    System.out.println("You won " + bet + " money\n");
                    streak = streak + 1;

                } else if (userGuess == 2 && cardTwo < card) {
                    System.out.println("You Won!");
                    loginList.get(reference).setMoney(loginList.get(reference).getMoney() + bet); 
                    loginList.get(reference).setMoneyWon(loginList.get(reference).getMoneyWon() + bet); 
                    System.out.println("You won " + bet + " money\n");
                    streak = streak + 1;

                } else if (card == cardTwo) {
                    System.out.println("Cards were the same.");
                    System.out.println("No money lost or won.");

                } else {
                    System.out.println("You lost!\n");
                    streak = 0; //resets answer streak
                    loginList.get(reference).setMoney(loginList.get(reference).getMoney() - bet); //takes away betted money from total balance
                    loginList.get(reference).setMoneyLost(loginList.get(reference).getMoneyLost() + bet); //adds losing bet to total loosings
                    System.out.println("You lost " + bet + " money");
                    run = false;
                }

                System.out.println("The card was " + displayCardTwo);
                System.out.println("Money: " + getMoney()); //displays the current amount of money you have so user knows how much they can bet
                System.out.println("");
                loginList.get(reference).setGamesPlayed(loginList.get(reference).getGamesPlayed() + 1); //add 1 to total games played

            }
            //endGame();

        } catch (Exception e) {
            System.out.println("Error occured");
        }

    }

    public static void rouletteWheel() {
        try {

            System.out.println("Welcome to Roulette");
            boolean winning = true;
            while (winning == true) {
                int betNum = 0;

                while (true) {
                    System.out.println("What number do you want to bet on (0-36)");
                    betNum = input.nextInt();
                    if (betNum > 36 || betNum <= 0) {
                        System.out.println("Please re-enter number!");
                    } else {
                        break;
                    }
                }

                int bet = betting();
                int wheel = randomNumbers(1, 36);
                boolean odd = false;

                
                System.out.println("\nSpinning...\n");

                int wheelNumChecker = (wheel % 2);
                int userNumChecker = (betNum % 2);

           
                if ((userNumChecker == 0) && (wheelNumChecker == 1)) {
                    odd = true;
                } else if ((userNumChecker == 1) && (wheelNumChecker == 0)) {
                    odd = true;
                } else if (userNumChecker == wheelNumChecker) {
                    odd = false;
                }

                System.out.println(odd);

                if (odd == true && betNum != wheel) {
                    loginList.get(reference).setMoney(loginList.get(reference).getMoney() - bet); 
                    loginList.get(reference).setMoneyLost(loginList.get(reference).getMoneyLost() + bet); 
                    System.out.println("You lost " + bet + " money");
                    winning = false;

                } else if (odd == false && betNum != wheel) {
                    System.out.println("You got the right colour!");
                    loginList.get(reference).setMoney(loginList.get(reference).getMoney() + bet); 
                    loginList.get(reference).setMoneyWon(loginList.get(reference).getMoneyWon() + bet); 
                    System.out.println("You won " + bet + " money\n");

                } else if (betNum == wheel) {
                    System.out.println("You won jackpot: 10000 chips!");
                    loginList.get(reference).setMoney(loginList.get(reference).getMoney() + 10000); 
                    loginList.get(reference).setMoneyWon(loginList.get(reference).getMoneyWon() + 10000);
                }
                System.out.println("The ball landed on: " + wheel);
                System.out.println("Money: " + getMoney());
                loginList.get(reference).setGamesPlayed(loginList.get(reference).getGamesPlayed() + 1); //add 1 to total games played

                break;

            }
            //endGame();

        } catch (Exception e) {
            System.out.println("Error occured");
            input.next();
        }



    }


    public static int betting() {
        int bet = 0;
        
        try {
            System.out.println("How much do you want to bet?");
            String baseBet = input.next();
            bet = Integer.parseInt(baseBet);

            if (bet > 100 || bet < 0) {
                System.out.println("Bet is not acceptable. Please enter a bet that's lower than 100 Money.");
                bet = 0;
            } else {
                System.out.println("You have bet: " + bet);
            }
            
        } catch (Exception e) {
            System.out.println("Error occured");
        }

        return bet;
    }
    
    public static String suitSelector() {
        String suit = "";
        int cardSuits = randomNumbers(1, 4);
        if (cardSuits == 1) {
            suit = " of Diamonds";
        } else if (cardSuits == 2) {
            suit = " of Spades";
        } else if (cardSuits == 3) {
            suit = " of Clubs";
        } else if (cardSuits == 4) {
            suit = " of Hearts";
        } 
        return suit;
    
    }
    
    public static int randomNumbers(int min, int max) {
        return rand.nextInt((max-min)+1)+min;
    }
 

    
    public static String displayCardCorrector(int card) {
        String displayCard = Integer.toString(card);
        if (card == 13) {
            displayCard = royaltyCard(13);
        } else if (card == 12) {
            displayCard = royaltyCard(12);
        } else if (card == 11) {
            displayCard = royaltyCard(11);
        } else if (card == 1) {
            displayCard = royaltyCard(1);
        } else {
            displayCard = displayCard + suitSelector();
        }

        return displayCard;
    }

    public static String royaltyCard(int card) {
        String superCard = "";
        if (card == 13) {
            superCard = "King" + suitSelector();
        } else if (card == 12) {
            superCard = "Queen" + suitSelector();
        } else if (card == 11) {
            superCard = "Jack" + suitSelector();
        } else if (card == 1) {
            superCard = "Ace" + suitSelector();
        }
        return superCard;
    }

//work in progress
//    public static void endGame() {
//        int money = getMoney();
//        if (money == 0) {
//            System.out.println("Yuu have out of coins.\nYou loose.\nYou do not pass go.\nYou do not collect $200.\nYour account will be terminated.");
//            loginList.remove(reference);
//            System.exit(money);
//        } else {
//            System.out.print("");
//        }
//    }
    

}


