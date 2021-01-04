package nbaProject;

import java.util.ArrayList;
import java.text.NumberFormat;

public class Team {
	
	private static final int SALARY_CAP = 100000000;
	private static NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
	private int remainingCapSpace;
	private String name;
	private ArrayList<Player> players = new ArrayList<>();
	private int wins;
	private int losses;
	private double winningPercentage;
	
	public static int getSalaryCap() {
		return SALARY_CAP;
	}

	public Team(String name) {
		this.name = name;
		this.remainingCapSpace = SALARY_CAP;
	}
	
	public boolean addPlayer(Player player) { // Adds a player. 
		if (player.getYearlySalary() <= remainingCapSpace) {
			remainingCapSpace -= player.getYearlySalary();
			players.add(player);
			player.setTeam(this);
			return true; // To indicate success. 
		}
		return false; // To indicate failure. 		
	}
	
	public boolean removePlayer(Player player) { // Waiving a player means removing him. 
		if (players.contains(player)) {
			players.remove(player);
			remainingCapSpace += player.getYearlySalary();
			player.setTeam(null);
			return true; // To indicate success. 
		}
		return false; // To indicate failure. 		
	}
	
	public void emptyTheRoster() {
		for (Player player: players) {
			player.setTeam(null);
		}
		players.clear();
	}
	
	public void listPlayers() {
		if (players.isEmpty()) {
			System.out.println("This team has no players.");
		} else {
			sortPlayers();
			System.out.println("The " + name + " has the following players: ");
			for (Player player: players) {
				System.out.println("  " + player + ", who makes " + defaultFormat.format(player.getYearlySalary()) + " yearly.");
			}
		}
	}
	
	private void sortPlayers() { // Uses selection sort. 
		Player highestPaidPlayer = null; 
		for (int i=0; i<players.size(); i++) { 
			highestPaidPlayer = players.get(i); 
			for (int j=i; j<players.size(); j++) { 
				highestPaidPlayer = findHighestPaidPlayer(highestPaidPlayer, players.get(j)); 
			} 
			players.remove(highestPaidPlayer);
			players.add(i, highestPaidPlayer); 
		}
	}
	
	private Player findAlphabeticalPlayer(Player playerA, Player playerB) {
		if (playerA.getName().toLowerCase().compareTo(playerB.getName().toLowerCase()) <= 0) {
        	return playerA;
        } else {
        	return playerB;
        }
	}
	
	private Player findHighestPaidPlayer(Player playerA, Player playerB) { // Finds dog with shortest tail between two dogs. 
		if (playerA.getYearlySalary() > playerB.getYearlySalary()) {
			return playerA;
		}
		else if (playerA.getYearlySalary() < playerB.getYearlySalary()) {
			return playerB;
		}
		else {
			return findAlphabeticalPlayer(playerA, playerB);
		} 
	}
	
	public int countPlayers() {
		return players.size();
	}
	
	public void addWin() {
		wins++;
		winningPercentage = ((double) wins)/((double)(wins+losses));
	}

	public void addLoss() {
		losses++;
		winningPercentage = ((double) wins)/((double)(wins+losses));
	}
	
	public int getRemainingCapSpace() {
		return remainingCapSpace;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Player> getPlayers() {
		return new ArrayList<>(players);
	}
	
	public int getWins() {
		return wins;
	}

	public int getLosses() {
		return losses;
	}

	public double getWinningPercentage() {
		return winningPercentage;
	}

	@Override
	public String toString() {
		return name;
	}
}
