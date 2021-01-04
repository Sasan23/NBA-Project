package nbaProject;

import java.text.NumberFormat;
import java.util.ArrayList;

public class BasketballLeague {
	
	private static EnhancedScanner scanner = new EnhancedScanner();
	private static NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
	private ArrayList<Team> teams = new ArrayList<>();
	private ArrayList<Player> players = new ArrayList<>();
	
	public void addPlayer(Player player) {
		addPlayer(player.getName(), player.getHeight(), player.getStandingReach(), player.getVerticalLeap());
	}
	
	public void addPlayer(String name, int height, int standingReach, int verticalLeap) {
		Player player = new Player(name, height, standingReach, verticalLeap);
		if (players.contains(player)) {
			System.out.println("That player already exists!");
		} else {
			players.add(player);
		}
	}
	
	public void addPlayer() {
		String name = scanner.nextLine("Enter the name of the player");
		int height = scanner.nextInt("Enter the height of the player in inches");
		int standingReach = scanner.nextInt("Enter the standing reach of the player in inches");
		int verticalLeap = scanner.nextInt("Enter the vertical leap of the player in inches");
		addPlayer(name, height, standingReach, verticalLeap);
	}
	
	public void addTeam(String name) {
		Team team = new Team(name);
		if (findTeam(name) != null) {
			System.out.println("That team already exists!");
		} else {
			teams.add(team);
		}
	}
	
	public void addTeam() {
		String name = scanner.nextLine("Enter the name of the team");
		addTeam(name);
	}
	
	public void removePlayer() {
		String name = scanner.nextLine("Enter the name of the player");
		Player player = findPlayer(name);
		if (player == null) {
			System.out.println("There is no such player!");
		} else {
			players.remove(player);
			Team team = player.getTeam();
			if (team != null) {
				team.removePlayer(player);
				player.setYearlySalary(0); // If the player was on a team, the contract is now void. 
			}
		}
	}
	
	public void removeTeam() {
		String name = scanner.nextLine("Enter the name of the team");
		Team team = findTeam(name);
		if (team == null) {
			System.out.println("There is no such team!");
		} else {
			team.emptyTheRoster();
			teams.remove(team);
		}
	}
	
	public void signPlayerToTeam() {
		String playerName = scanner.nextLine("Enter the name of the player");
		Player player = findPlayer(playerName);
		if (player == null) {
			System.out.println("No such player exists!");
			return;
		} 
		String teamName = scanner.nextLine("Enter the name of the team");
		Team team = findTeam(teamName);
		if (team == null) {
			System.out.println("No such team exists!");
			return;
		}
		if (player.getTeam() != null) {
			System.out.println("The player is already signed to a team!");
		}
		int salary = scanner.nextInt("Enter the yearly salary in millions");
		player.setYearlySalary(salary*1000000); // We preliminarily set the salary. 
		if (team.addPlayer(player)) {
			System.out.println(playerName + " signed to the " + teamName);
		} else {
			System.out.println(teamName + " does not have enough salary left to pay this player!");
			System.out.println("They have " + defaultFormat.format(team.getRemainingCapSpace()) + " left.");
			player.setYearlySalary(0); // We make the salary 0 again because the player did not sign the contract. 
		}
	}
	
	public void signPlayerToTeam(String playerName, String teamName, int salaryInMillions) {
		Player player = findPlayer(playerName);
		player.setYearlySalary(salaryInMillions*1000000);
		Team team = findTeam(teamName);
		team.addPlayer(player);
	}
	
	public void waivePlayerFromTeam() {
		String playerName = scanner.nextLine("Enter the name of the player");
		Player player = findPlayer(playerName);
		if (player == null) {
			System.out.println("No such player exists!");
			return;
		} 
		Team team = player.getTeam();
		if (player.getTeam() == null) {
			System.out.println("This player is a free agent, not signed to a team!");
		}
		team.removePlayer(player);
		player.setYearlySalary(0); // The player does not have a contract anymore, hence 0 in salary. 
		System.out.println(playerName + " waived from the " + team.getName());
	}
	
	public void showStatsForPlayer() {
		String playerName = scanner.nextLine("Enter the name of the player");
		Player player = findPlayer(playerName);
		if (player == null) {
			System.out.println("No such player exists!");
			return;
		} 
		player.showStats();
	}
	
	public void tradePlayers() { 
		String firstTeamName = scanner.nextLine("Enter the name of the first team");
		String secondTeamName = scanner.nextLine("Enter the name of the second team");
		Team firstTeam = findTeam(firstTeamName);
		Team secondTeam = findTeam(secondTeamName);
		if (firstTeam == null || secondTeam == null || firstTeam == secondTeam) {
			System.out.println("Either one of those teams does not exist or you entered the same team twice");
			return;
		}
		executeTrade(firstTeam, secondTeam);
	}
	
	private void executeTrade(Team firstTeam, Team secondTeam) {
		ArrayList<Player> playersFirstTeam = tradePlayersLoop(firstTeam); 
		ArrayList<Player> playersSecondTeam = tradePlayersLoop(secondTeam); 
		int firstTeamRemainingSalary = firstTeam.getRemainingCapSpace(); // Remaining salary. 
		int secondTeamRemainingSalary = secondTeam.getRemainingCapSpace();
		int firstTeamPlayersTotalSalary = 0;
		int secondTeamPlayersTotalSalary = 0;
		for (Player player: playersFirstTeam) {
			firstTeamPlayersTotalSalary += player.getYearlySalary(); // Total salary of the players being traded from the first team. 
		}
		for (Player player: playersSecondTeam) {
			secondTeamPlayersTotalSalary += player.getYearlySalary(); // Total salary of the players being traded from the second team. 
		}
		int difference = Math. abs(firstTeamPlayersTotalSalary - secondTeamPlayersTotalSalary);
		firstTeamRemainingSalary += firstTeamPlayersTotalSalary; // Remove salaries of players traded from team A, hence adding to remaining salary.
		secondTeamRemainingSalary += secondTeamPlayersTotalSalary; // Remove salaries of players traded from Team B, add to remaining salary. 
		firstTeamRemainingSalary -= secondTeamPlayersTotalSalary; // Add salaries of players traded to team A (from B), hence reduce remaining. 
		secondTeamRemainingSalary -= firstTeamPlayersTotalSalary; // Add salaries of players traded to team B (from A), hence reduce remaining. 
		if (firstTeamRemainingSalary < 0 || secondTeamRemainingSalary < 0 || difference > 5000000) { 
			// If either team goes over the salary cap or if the salaries differ by more than 5 million.
			System.out.println("Either the salaries do not match or one of teams is going over the cap. This trade is not doable.");
			System.out.println(firstTeam.getName() + " has " + defaultFormat.format(firstTeam.getRemainingCapSpace()) + " left.");
			System.out.println(secondTeam.getName() + " has " + defaultFormat.format(secondTeam.getRemainingCapSpace()) + " left.");
			return;
		}
		for (Player player: playersFirstTeam) {
			firstTeam.removePlayer(player);
		}
		for (Player player: playersSecondTeam) {
			secondTeam.removePlayer(player);
		}
		for (Player player: playersFirstTeam) {
			secondTeam.addPlayer(player);
		}
		for (Player player: playersSecondTeam) {
			firstTeam.addPlayer(player);
		}
		System.out.println("The trade is officially executed!");
		// The add loops need to be separate from the remove ones because we need to remove all players before we start adding. 
		// If we add a player before we remove one, we may go over the cap even when the trade is doable. 
	}
	
	private ArrayList<Player> tradePlayersLoop(Team team) {
		ArrayList<Player> players = new ArrayList<>();
		int exit = 0;
		do {
			String playerName = scanner.nextLine("Enter the name of a player to trade from " + team.getName());
			Player player = findPlayer(playerName);
			if (player == null || player.getTeam() != team) {
				System.out.println("This player either does not exist, or is not part of the " + team.getName());
				continue;
			}
			players.add(player);
			exit = scanner.nextInt("Press 1 if you want to add another player to trade from " + team.getName() + " and 2 otherwise");
		} while (exit != 2);
		return players;
	}
	
	public boolean canPlayerDunk() {
		String playerName = scanner.nextLine("Enter the name of the player");
		Player player = findPlayer(playerName);
		if (player == null) {
			System.out.println("No such player exists!");
			return false;
		}
		if (player.canPlayerDunk()) {
			System.out.println(playerName + " can dunk!");
			return true;
		} else {
			System.out.println(playerName + " cannot dunk!");
			return false;
		}
	}
	
	public void listPlayersForTeam() {
		String teamName = scanner.nextLine("Enter the name of the team");
		Team team = findTeam(teamName);
		if (team == null) {
			System.out.println("No such team exists!");
		} else {
			team.listPlayers();
		}
	}
	
	public void listAllPlayers() {
		if (players.isEmpty()) {
			System.out.println("There are no players currently.");
		}
		for (Player player: players) {
			if (player.getTeam() == null) {
				System.out.println(player.getName() + " is a free agent.");
			} 
		}
		System.out.println();
		for (Team team: teams) {
			team.listPlayers();
			System.out.println();
		}
	}
	
	public void showStandings() { // List teams. 
		sortTeams();
		for (Team team: teams) {
			System.out.println(team + ". Wins: " + team.getWins() + ". Losses: " + team.getLosses() + ". W/L: " + 
					String.format("double : %.2f", team.getWinningPercentage()));
		}
	}
	
	public void playGame() {
		String homeTeamName = scanner.nextLine("Enter the name of the home team");
		String awayTeamName = scanner.nextLine("Enter the name of the road team");
		Team homeTeam = findTeam(homeTeamName);
		Team awayTeam = findTeam(awayTeamName);
		while (homeTeam == null) {
			homeTeamName = scanner.nextLine("No such team. Enter a valid name for the home team");
		}
		while (awayTeam == null) {
			awayTeamName = scanner.nextLine("No such team. Enter a valid name for the away team");
		}
		while (awayTeam.equals(homeTeam)) {
			awayTeamName = scanner.nextLine("You entered the same team twice. Enter a different road team");
			awayTeam = findTeam(awayTeamName);
		}
		if (homeTeam.countPlayers() < 5 || awayTeam.countPlayers() < 5) {
			System.out.println("One or both teams do not have the required number of players to play a game (5).");
			return;
		}
		Game game = new Game(homeTeam, awayTeam);
		game.playGame();
		game.showBoxScore();
	}
	
	public void howManyGamesPlayed() {
		String playerName = scanner.nextLine("Enter the name of the player");
		Player player = findPlayer(playerName);
		if (player == null) {
			System.out.println("No such player exists!");
		} else {
			System.out.println(player.getNumberOfGames());
		}
	}
	
	private void sortTeams() { // Uses selection sort. 
		Team winnigestTeam = null; 
		for (int i=0; i<teams.size(); i++) { 
			winnigestTeam = teams.get(i); 
			for (int j=i; j<teams.size(); j++) { 
				winnigestTeam = findWinningestTeam(winnigestTeam, teams.get(j)); 
			} 
			teams.remove(winnigestTeam);
			teams.add(i, winnigestTeam); 
		}
	}
	
	private Team findAlphabeticalTeam(Team teamA, Team teamB) {
		if (teamA.getName().toLowerCase().compareTo(teamB.getName().toLowerCase()) <= 0) {
        	return teamA;
        } else {
        	return teamB;
        }
	}
	
	private Team findWinningestTeam(Team teamA, Team teamB) { // Finds dog with shortest tail between two dogs. 
		if (teamA.getWinningPercentage() > teamB.getWinningPercentage()) {
			return teamA;
		}
		else if (teamA.getWinningPercentage() < teamB.getWinningPercentage()) {
			return teamB;
		}
		else {
			return findAlphabeticalTeam(teamA, teamB);
		} 
	}
	
	private Player findPlayer(String playerName) {
		for (Player player: players) {
			if (playerName.equalsIgnoreCase(player.getName())) {
				return player;
			}
		}
		return null;
	}
	
	private Team findTeam(String teamName) {
		for (Team team: teams) {
			if (teamName.equalsIgnoreCase(team.getName())) {
				return team;
			}
		}
		return null;
	}

}
