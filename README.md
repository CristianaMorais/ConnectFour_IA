# Connect Four AI 🔴🟡
## Artificial Intelligence | 2018

## 👥 Authors
> Cristiana Silva (up201505454)

> Sara Sousa (up201504217)

## 📖 Project Overview
This project is a Java-based implementation of the classic Connect Four game, featuring a computer opponent powered by adversarial search algorithms. The objective is to be the first to align four pieces vertically, horizontally, or diagonally.

## 🧠 AI Algorithms
Players can choose between two fundamental AI strategies to challenge:

> Minimax: A decision-making algorithm used for minimizing the possible loss for a worst-case scenario.

> Alpha-Beta Pruning: An optimization of the Minimax algorithm that significantly reduces the number of nodes evaluated in the search tree.

## 🛠️ Usage Instructions
### Compilation and Execution
Open your terminal, navigate to the project directory, and run:

```bash
$ javac *.java && java ConnectFour
```

### Gameplay Example
Upon starting, you will be prompted to configure the match:

1. Select Algorithm: Choose between (1) Minimax or (2) Alpha-Beta.
2. Set Search Depth: Define how many moves ahead the AI should calculate (e.g., 5).
3. Turn Order: Choose if you want to play first (s) or second (n).

### Sample Interface:

```Plaintext
Select the algorithm:
1 - MiniMax
2 - Alpha-Beta
> 2

Enter the maximum search depth: 5
Do you want to play first? [s/n] s

Empty Board:
0 1 2 3 4 5 6
- - - - - - - 
- - - - - - - 
- - - - - - - 
- - - - - - - 
- - - - - - - 
- - - - - - - 

Enter your move (column 0-6):
```
