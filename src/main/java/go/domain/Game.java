package go.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by moby on 30/09/15.
 */
    @Entity
    public class Game {

        @Id
        @GeneratedValue
        private Long gameId;

        @OneToOne(mappedBy="game", cascade= CascadeType.ALL)
        @JsonManagedReference
        public Board board;

        public char playerTurn; // whos turn is it? 'B' or 'W'
        public String playerBlackId;
        public String playerWhiteId;
        public int playerBlackPoints;
        public int playerWhitePoints;

        protected Game(){};

        @Transient
        public int myStatusCode;

        @Transient
        public volatile String player;

        public Game(int boardSize) {
            board = new Board(this, boardSize);
            playerTurn = 'B';
            generatePlayerIds();
        }

        public int play(String playerId, int x, int y) {
            char player;
            if( playerBlackId.equals(playerId) ){
                player = 'B';
            } else if( playerWhiteId.equals(playerId) ){
                player = 'W';
            } else{
                myStatusCode = -6; // unknown playerId
                return myStatusCode;
            }

            if( playerTurn != player ) {
                myStatusCode = -4; // not your turn
                return myStatusCode;
            }

            int result = board.play(player, x, y);
            if (result >= 0) {
                if (playerTurn == 'B') {
                    playerBlackPoints += result;
                } else {
                    playerWhitePoints += result;
                }
                playerTurn = (playerTurn=='B') ? 'W':'B';
                //save();
                System.out.println(player + " played! ( " + x + ", " + y + ")");
                return result;
            }
            myStatusCode = -5; //illlegal move.
            return myStatusCode;
        }

        public String otherPlayerId( String playerId ){
            if( playerBlackId.equals(playerId) ){
                return( playerWhiteId );
            } else if( playerWhiteId.equals(playerId) ){
                return( playerBlackId );
            } else{
                return( null ); // invalid playerId
            }
        }

        public void generatePlayerIds() {
            Calendar c = Calendar.getInstance();
            playerBlackId = "" + c.getTimeInMillis() + "B";
            playerWhiteId = "" + c.getTimeInMillis() + "W";
        }

    public Long getId() {
        return gameId;
    }
}
