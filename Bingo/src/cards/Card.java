package cards;

import java.util.ArrayList;

public class Card {
    private final int[][] numbers;
    private final int[][] copy;

    public Card () {
        numbers = new int[5][5];
        final int[] col1 = randomRange( 1, 15 );
        final int[] col2 = randomRange( 16, 30 );
        final int[] col3 = randomRange( 31, 45 );
        final int[] col4 = randomRange( 46, 60 );
        final int[] col5 = randomRange( 61, 75 );
        for ( int i = 0; i < 5; i++ ) {
            for ( int j = 0; j < 5; j++ ) {
                if ( i == 0 ) {
                    numbers[j][i] = col1[j];
                }
                else if ( i == 1 ) {
                    numbers[j][i] = col2[j];
                }
                else if ( i == 2 ) {
                    numbers[j][i] = col3[j];
                }
                else if ( i == 3 ) {
                    numbers[j][i] = col4[j];
                }
                else {
                    numbers[j][i] = col5[j];
                }
            }
        }
        copy = numbers;
        numbers[2][2] = 0;
    }

    public boolean pulledNumber ( final int num ) {

        if ( num < 16 ) {
            for ( int i = 0; i < 5; i++ ) {
                if ( num == numbers[i][0] ) {
                    numbers[i][0] = 0;
                    return true;
                }
            }
        }
        else if ( num < 31 ) {
            for ( int i = 0; i < 5; i++ ) {
                if ( num == numbers[i][1] ) {
                    numbers[i][1] = 0;
                    return true;
                }
            }
        }
        else if ( num < 46 ) {
            for ( int i = 0; i < 5; i++ ) {
                if ( num == numbers[i][2] ) {
                    numbers[i][2] = 0;
                    return true;
                }
            }
        }
        else if ( num < 61 ) {
            for ( int i = 0; i < 5; i++ ) {
                if ( num == numbers[i][3] ) {
                    numbers[i][3] = 0;
                    return true;
                }
            }
        }
        else {
            for ( int i = 0; i < 5; i++ ) {
                if ( num == numbers[i][4] ) {
                    numbers[i][4] = 0;
                    return true;
                }
            }
        }
        return false;
    }

    private int[] randomRange ( final int min, final int max ) {
        final int[] retArr = new int[5];
        final ArrayList<Integer> possible = new ArrayList<Integer>();
        for ( int i = min; i < max + 1; i++ ) {
            possible.add( i );
        }
        for ( int i = 0; i < 5; i++ ) {
            retArr[i] = possible.remove( (int) ( Math.random() * ( possible.size() ) ) );
        }
        return retArr;
    }

    public boolean checkRows () {
        for ( int i = 0; i < 5; i++ ) {
            for ( int j = 0; j < 5; j++ ) {
                if ( numbers[i][j] != 0 ) {
                    break;
                }
                if ( j == 4 ) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkCols () {
        for ( int j = 0; j < 5; j++ ) {
            for ( int i = 0; i < 5; i++ ) {
                if ( numbers[i][j] != 0 ) {
                    break;
                }
                if ( i == 4 ) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkDiags () {
        for ( int i = 0; i < 5; i++ ) {
            if ( numbers[i][i] != 0 ) {
                break;
            }
            if ( i == 4 ) {
                return true;
            }
        }
        for ( int i = 0; i < 5; i++ ) {
            if ( numbers[4 - i][i] != 0 ) {
                break;
            }
            if ( i == 4 ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString () {
        String retStr = " ";
        for ( int i = 0; i < 14; i++ ) {
            retStr += "_";
        }
        retStr += "\n";
        for ( int i = 0; i < 5; i++ ) {
            retStr += String.format( "|%2d|%2d|%2d|%2d|%2d|\n", numbers[i][0], numbers[i][1], numbers[i][2],
                    numbers[i][3], numbers[i][4] );
        }
        return retStr;
    }

    public int[] getRow ( final int row ) {
        return copy[row];
    }

    public int[] getCol ( final int col ) {
        final int[] retCol = new int[5];
        for ( int i = 0; i < 5; i++ ) {
            retCol[i] = copy[i][col];
        }
        return retCol;
    }

    public boolean checkAll () {
        return checkRows() || checkCols() || checkDiags();
    }
}
