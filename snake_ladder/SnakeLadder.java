package snake_ladder;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

class BoardException extends Exception{
    public BoardException(String message)
    {
        super(message);
    }
}

abstract class SpecialEntity{
    // you basically can't create an object of this class
    private int start;
    private int end;
    
    public SpecialEntity(int start , int end)
    {
        this.start = start;
        this.end   = end;
    }
    public int getStartPosition()
    {
        return this.start;
    }
    public int getEndPosition()
    {
        return this.end;
    }
    
   public abstract String getId();
    
}

class Ladder extends SpecialEntity{
    public Ladder(int start, int end) {
        super(start, end);
    }
    
    @Override
    public String  getId()
    {
        return "L"+this.getStartPosition();
    }
    
}

class Snake extends SpecialEntity{
    public Snake(int start, int end)
    {
        super(start,end);
    }
    
    @Override
    public String getId()
    {
        return "S"+ this.getStartPosition();
    }
}


class Board{
    int dimension;
    HashMap<Integer,SpecialEntity> specialentities;
    
    public Board(int dimension)
    {
        this.dimension = dimension;
        this.specialentities = new HashMap<Integer, SpecialEntity>();
    }
    
    public int getTotalCells()
    {
        return this.dimension * this.dimension;
    }
    
    public void addSpecialEntity(SpecialEntityFactory specialEntityFactory, int start, int end) throws BoardException{
        SpecialEntity entity = specialEntityFactory.createSpecialEntity(start, end);
        
        if(start<=0 || end<=0 || start>getTotalCells() || end > getTotalCells())
           throw new BoardException("Invalid position for entity place it inside the board");
           
        if (entity instanceof Ladder && start >= end) {
        throw new BoardException("Ladder's end position must be greater than its start position.");
        }
        if(entity instanceof Snake && end>= start)
         throw new BoardException("Snake start posiiton should be higher then its end position");
        
        this.specialentities.put(start,entity); 
    }
    
    public boolean hasSpecialEntity(int posn)
    {
        return this.specialentities.containsKey(posn);
    }
    
    public SpecialEntity getSpecialEntity(int posn)
    {
        if(hasSpecialEntity(posn))
          return this.specialentities.get(posn);
        
        return null;  
    }
}
enum GameStatus {
    NOT_STARTED,
    RUNNING,
    FINISHED
}

class Player{
    private String name;
    private int position;
    
    
    Player(String name)
    {
        this.name = name;
        this.position = 0;
    }
    
    public void setPosition(int pos)
    {
        this.position = pos;
    }
    
    public int  getPosition()
    {
        return position;
    }
    
    public String getName()
    {
        return name;
    }
}
/*
game 
-- players list
-- board
---  startGame() this will keep on running until and unless we are able to 
--- movePlayer()
*/
class Game{
    Queue<Player> players;
    Board board;
    GameStatus status;
    Dice dice; 
    
    Game(Queue<Player> players,Board board, Dice dice)
    {
        this.players = players;
        this.board = board;
        this.dice = dice;
    }
    
    
    void startGame()
    {
       /* you start with the first player  
       
       you roll this dice
       you update the position
       you check if its at a position of an special entity if so update it
       also if position exceeds final box ,don't update
       
       in case the game is over print the winner
       
       */
       status  = GameStatus.RUNNING;
       while(players.size()>1)
       {
           Player player = players.poll();
          
           
           int newPos = makeMove(player);
           int winningPosition = board.getTotalCells();
           
           if(newPos!=winningPosition)
             players.add(player); // add for processing of the next turn
           else
            System.out.println(player.getName()+" has completed the game");
       }
       
       this.status = GameStatus.FINISHED;
       System.out.println("THe game has been finished it's over "+players.size());
    }
    
    int makeMove(Player player)
    {    
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
        
        int steps = this.dice.roll();
        
        int posn = player.getPosition();
         
        int newPos = steps + posn;
        System.out.println("This is the new Position for you "+ newPos);
        int winningPosition = board.getTotalCells();
        if(newPos<=winningPosition)
        {
            if(board.hasSpecialEntity(newPos))
            {
                newPos  = board.getSpecialEntity(newPos).getEndPosition();
                player.setPosition(newPos);
                return newPos;
            }else{
                 player.setPosition(newPos);
                return newPos;
            }
            
        }
        return posn; // we dont update the position if it surpasses the the board
    }
     
}


class Dice {
    private int maxVal;

    public Dice(int maxVal) {
        this.maxVal = maxVal;
    }

    int roll() {
        return (int) Math.floor(Math.random() * maxVal + 1);
    }
}

interface SpecialEntityFactory {
    SpecialEntity createSpecialEntity(int startPos, int endPos);
}

class SnakeFactory implements SpecialEntityFactory {
    @Override
    public SpecialEntity createSpecialEntity(int startPos, int endPos) {
        return new Snake(startPos, endPos);
    }
}

class LadderFactory implements SpecialEntityFactory {
    @Override
    public SpecialEntity createSpecialEntity(int startPos, int endPos)
    {
        return new Ladder(startPos, endPos);
    }
}

class SnakeLadder {
    
    public static void main(String[] args) {
        try{
            System.out.println("Try programiz.pro");
          Board board = new Board(3);
          
          SpecialEntityFactory snakeFactory = new SnakeFactory();
          SpecialEntityFactory ladderFactory = new LadderFactory();
  
         
          
          board.addSpecialEntity(snakeFactory , 7,5);
          board.addSpecialEntity(ladderFactory, 3,9);
          Player player1 = new Player("p1");
          Player player2 = new Player("p2");
          Player player3 = new Player("p3");
          
          Queue<Player> players = new LinkedList<Player>();
          players.add(player1);
          players.add(player2);
          players.add(player3);
          Dice dice = new Dice(6);
          Game game = new Game(players, board,dice);
          game.startGame();     
          }catch(Exception e)
          {
              System.out.println(e.getMessage());
          }
    }
     
}
