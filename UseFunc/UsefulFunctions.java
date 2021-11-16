/* 
* UsefulFunctions.java
* Michael Kim
* ICS 4U1
* June 18 2021
* Purpose: to hold useful universal functions
* Methods: getRandomNumber()
*/
package UseFunc;

import java.util.*;

public class UsefulFunctions{
    //Random Number generator with range
    //max is maximum number attainable
    //min is minimum number attainable
    public static int getRandomNumber(int min, int max){ //random num generator
        return (int)((Math.random() * ((max - min) + 1)) + min);
    }
}