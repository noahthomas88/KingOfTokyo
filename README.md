CSSE 376 Team A
King Of Tokyo Definition of Done:
By John Gear, Noah Thomas, Joshua Warning, Fisher Shen
TODO: Add in as many edge cases as we can think of

Game Set-Up:
There may be 2 to 6 players. We will not allow a game to begin with less than or more than this amount. 

Each player will begin with 10 health, 0 victory points, 0 energy, and 0 power cards.

A player cannot have a negative amount of victory points health, power cards or energy.

If there are 5 to 6 players then there are 3 places on the board: outside Tokyo, Tokyo city, and Tokyo bay.

If there are currently 2 to 4 players There are only 2 places on the board, Tokyo City, and outside of Tokyo.

Three power cards are revealed from the deck.

Each player rolls the six dice and whoever has the most attacks on their role is first and when there are multiple players who tie, repeat the process.

The game progresses clockwise around the board from that point.


Dice Mechanics:
The game begins with 6 dice for each player to roll.

Each die has 6 sides, with faces that are (1,2, or 3 attack), energy, and health.

If a player has a special power card they permanently roll with one additional die. These cards can be stacked for a stronger effect. However there are only 3 cards that allow this which leads to a max of 9 dice to roll.

Players have the option to re-roll selected dice 2 additional times. If they chose to re roll no dice we will ask if they are pleased with their current roll. If so we will accept the current roll of the dice.

The numbers add victory points to the player, if you roll 3 of a kind of either 1, 2 or 3 the player gains as many victory points as the number that was duplicated 3 times. Any additional numbers of the same face add 1 more victory point to the player.
For each attack face that is rolled, players outside of the current players location will lose one health point. However, if the other players reach 0 health their health will not continue to decrease from attacks.

For each heal face that is rolled, the player gains one health point. (Health cannot exceed 10 points or go below 0 points).

For each energy face that is rolled, the player gains one energy point (there is no upper limit to the amount of energy a player can have but he must have and integer greater than -1).
Once a players turn is over, they will pass the dice to the next player.

Tokyo City/Tokyo Bay Rules:
If no one is in Tokyo City, a player must enter Tokyo City. This includes the first turn of the game. The same is true for Tokyo Bay if there are more than 5 players. If both are open, the player will prioritize moving into tokyo city over tokyo bay.

A player earns 1 victory point when they enter Tokyo City or Tokyo Bay.
If a player starts their turn in Tokyo City or Tokyo Bay then they gain 2 victory points.

Players in Tokyo City or Tokyo Bay cannot heal from dice rolls.

You can only leave Tokyo after losing health from another player. If you choose to do so, that player must take the position.

Once the game has 4 or fewer players, the player who occupies Tokyo Bay must move out. If Tokyo City is empty, the player must move there.

If a player dies and the number of players drops to 4 players, then he uses a power card that gives him one more life, Tokyo bay will continue to be available to players on the board.

End of Turn:
Resolve any power up cards that activate on the end of the turn. Users will cease to be able to perform any action but to pass the “turn” to the next player.

Power Up Cards:
Energy points can be spent to purchase any of the three face up cards and the face up card will be replaced with one from the deck after purchase.

Each power card from the original deck will be implemented correctly.

A player can choose to spend 2 energy points to “sweep” the three visible cards off of the board and reveal three new power cards from the deck. The deck will not repeat identical power cards in the three remaining cards unless we have already seen the entire deck of cards. If so, the deck will be “reshuffled” and 3 more cards will be put out on display. 

Power cards have three different properties, either they will take effect immediately after purchase, they will be kept by a player and will cause a permanent change, or they are kept by a player until the conditions of the card have been met and then discarded.

When a Player Reaches 0 health.

When a player reaches 0 health they lose.The only exception to this rule is if a player possesses the power card that gives them an extra life. They will never possess negative health.

If a player reaches 0 health and 20 victory points at the same time they still lose, unless they have the power card that gives them an extra life.

All cards and energy points are discarded after a player has lost.

End of Game:
When a player accrues 20 victory points and health is not zero, They win. If a player gains victory points in his turn that would make his total greater than 20 victory points, he will still end his turn with 20 victory points.

When only one player is the only player left alive, They win.

If all players die at the same time every player loses.

If a game wishes to be replayed, users must restart the program.


Helpful link:
http://www.iellogames.com/downloads/KOT2-rulebook_EN.pdf
