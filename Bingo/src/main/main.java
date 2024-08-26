package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import cards.Card;

public class main {

    private static int              threads;

    private static int              bingoNum;

    private static int              numCol;

    private static int              numRow;

    private static int              numDiag;

    private static ArrayList<Card>  finished;

    private static LinkedList<Card> cards;

    private static Queue<Integer>   calls;

    public static void main ( final String[] args ) {
        calls = new ConcurrentLinkedQueue<Integer>();
        threads = 1000000;

        cards = new LinkedList<Card>();
        finished = new ArrayList<Card>();

        for ( int i = 0; i < threads; i++ ) {
            cards.addLast( new Card() );
        }

        final ArrayList<Integer> rolls = new ArrayList<Integer>();
        for ( int i = 1; i < 76; i++ ) {
            rolls.add( i );
        }
        while ( finished.isEmpty() ) {
            bingoNum = rolls.remove( (int) ( Math.random() * rolls.size() ) );
            calls.add( bingoNum );
            for ( final Card c : cards ) {
                c.pulledNumber( bingoNum );
                if ( c.checkCols() ) {
                    finished.add( c );
                    numCol++;
                }
                else if ( c.checkDiags() ) {
                    finished.add( c );
                    numDiag++;
                }
                else if ( c.checkRows() ) {
                    finished.add( c );
                    numRow++;
                }
            }

        }

        for ( final Card c : finished ) {
            System.out.println( c );
        }
        System.out.printf( "Rows: %5d|%d%%\nCols: %5d|%d%%\nDiag: %5d|%d%%\n", numRow,
                numRow * 100 / ( numRow + numCol + numDiag ), numCol, numCol * 100 / ( numRow + numCol + numDiag ),
                numDiag, numDiag * 100 / ( numRow + numCol + numDiag ) );
        for ( int i = 0; i < calls.size(); i++ ) {
            System.out.print( calls.remove() + " " );
        }

    }

}
