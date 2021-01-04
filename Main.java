package nbaProject;

public class Main {
	
	public static final String ADD_PLAYER = "add player"; 
	public static final String ADD_TEAM = "add team";
	public static final String REMOVE_PLAYER = "remove player"; 
	public static final String REMOVE_TEAM = "remove team"; 
	public static final String SIGN_PLAYER = "sign player"; 
	public static final String WAIVE_PLAYER = "waive player";
	public static final String TRADE_PLAYERS = "trade players"; 
	public static final String SHOW_STATS = "show stats for player"; 
	public static final String HOW_MANY_GAMES = "show number of games played for player"; 
	public static final String CAN_PLAYER_DUNK = "can player dunk"; 
	public static final String LIST_PLAYERS_FOR_TEAM = "list players for team"; 
	public static final String LIST_ALL_PLAYERS = "list all players"; 
	public static final String SHOW_STANDINGS = "show standings"; 
	public static final String PLAY_GAME = "play game"; 
	public static final String EXIT = "exit"; 
	
	private static EnhancedScanner scanner = new EnhancedScanner(); 	
	private BasketballLeague nba = new BasketballLeague(); 

	public static void main(String[] args) {
		Main main = new Main();
		main.preloadValues();
		main.start();
	}
	
	private void preloadValues() {
		String preload = scanner.nextLine("Write 1 if you want to preload with two teams of five players each. Just press enter otherwise");
		if (preload.contentEquals("1")) {
			nba.addPlayer("Lebron James", 80, 100, 40);
			nba.addPlayer("Anthony Davis", 82, 104, 30);
			nba.addPlayer("Kyle Kuzma", 81, 100, 30);
			nba.addPlayer("Dennis Shröder", 74, 90, 30);
			nba.addPlayer("Marc Gasol", 84, 105, 15);
			nba.addTeam("Los Angeles Lakers");
			nba.signPlayerToTeam("Lebron James", "Los Angeles Lakers", 30);
			nba.signPlayerToTeam("Anthony Davis", "Los Angeles Lakers", 30);
			nba.signPlayerToTeam("Kyle Kuzma", "Los Angeles Lakers", 10);
			nba.signPlayerToTeam("Dennis Shröder", "Los Angeles Lakers", 20);
			nba.signPlayerToTeam("Marc Gasol", "Los Angeles Lakers", 10);
			nba.addPlayer("Giannis Antetokounmpo", 82, 104, 36);
			nba.addPlayer("Jrue Holiday", 75, 95, 30);
			nba.addPlayer("Khris Middleton", 78, 98, 20);
			nba.addPlayer("Donte Divincenzo", 76, 95, 30);
			nba.addPlayer("Brook Lopez", 84, 105, 15);
			nba.addTeam("Milwaukee Bucks");
			nba.signPlayerToTeam("Giannis Antetokounmpo", "Milwaukee Bucks", 30);
			nba.signPlayerToTeam("Jrue Holiday", "Milwaukee Bucks", 20);
			nba.signPlayerToTeam("Khris Middleton", "Milwaukee Bucks", 20);
			nba.signPlayerToTeam("Donte Divincenzo", "Milwaukee Bucks", 10);
			nba.signPlayerToTeam("Brook Lopez", "Milwaukee Bucks", 10);
			System.out.println("There are two preloaded teams with five players each.");
			System.out.println("");
		} 
	}
	
	private void start() {
		initialize();
		runCommandLoop();
		close();
	}
	
	private void initialize() {
		System.out.println("Welcome!");
		System.out.println();
		printMenu();
	}
	
	private void printMenu() {
		System.out.println("The following commands are available:");
		System.out.println("* " + ADD_PLAYER);
		System.out.println("* " + ADD_TEAM);
		System.out.println("* " + REMOVE_PLAYER);
		System.out.println("* " + REMOVE_TEAM);
		System.out.println("* " + SIGN_PLAYER);
		System.out.println("* " + WAIVE_PLAYER);
		System.out.println("* " + TRADE_PLAYERS);
		System.out.println("* " + SHOW_STATS);
		System.out.println("* " + HOW_MANY_GAMES);
		System.out.println("* " + CAN_PLAYER_DUNK);
		System.out.println("* " + LIST_PLAYERS_FOR_TEAM);
		System.out.println("* " + LIST_ALL_PLAYERS);
		System.out.println("* " + SHOW_STANDINGS);
		System.out.println("* " + PLAY_GAME);
		System.out.println("* " + EXIT);
	}
	
	private void runCommandLoop() {
		String command;
		do {
			command = readCommand();
			handleCommand(command);
		} while (!command.equals(EXIT));
	}
	
	private String readCommand() {
		System.out.println("Command? ");
		return scanner.nextString();
	}

	private void handleCommand(String command) {
		switch (command) {
		case ADD_PLAYER:
			nba.addPlayer();
			break;
		case ADD_TEAM:
			nba.addTeam();
			break;
		case REMOVE_PLAYER:
			nba.removePlayer();
			break;
		case REMOVE_TEAM:
			nba.removeTeam();
			break;
		case SIGN_PLAYER:
			nba.signPlayerToTeam();
			break;
		case WAIVE_PLAYER:
			nba.waivePlayerFromTeam();
			break;
		case TRADE_PLAYERS:
			nba.tradePlayers();
			break;
		case SHOW_STATS:
			nba.showStatsForPlayer();
			break;
		case CAN_PLAYER_DUNK:
			nba.canPlayerDunk();
			break;
		case LIST_PLAYERS_FOR_TEAM:
			nba.listPlayersForTeam();
			break;
		case LIST_ALL_PLAYERS:
			nba.listAllPlayers();
			break;
		case SHOW_STANDINGS:
			nba.showStandings();
			break;
		case PLAY_GAME:
			nba.playGame();
			break;
		case EXIT:
			break;
		case "temp":
			nba.howManyGamesPlayed();
			break;
		default:
			printErrorMessage();
		}
	}
	
	private void printErrorMessage() {
		System.out.println("Incorrect command!");
		System.out.println();
		printMenu();
	}

	private void close() {
		scanner.close();
		System.out.println("The program is closed");
	}
}
