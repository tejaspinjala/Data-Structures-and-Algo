import java.util.*;

public class dijkstra {
    
    static int state = 48;
    static int[][] distanceMatrix = new int[state][state];
    
    static class vertex {
        public boolean known;
        public int distance;
        public int previousvertex;
    }
    
    public static void main(String[] args) {
        // initialize distance matrix
        distanceMatrix [0][4] = 755;
        distanceMatrix [0][1] = 129;
        distanceMatrix [0][2] = 153;
        distanceMatrix [1][4] = 713;
        distanceMatrix [1][5] = 534;
        distanceMatrix [1][6] = 541;
        distanceMatrix [1][2] = 663;
        distanceMatrix [2][6] = 441;
        distanceMatrix [2][3] = 160;
        distanceMatrix [3][6] = 619;
        distanceMatrix [4][7] = 476;
        distanceMatrix [4][5] = 652;
        distanceMatrix [5][8] = 488;
        distanceMatrix [5][9] = 435;
        distanceMatrix [5][6] = 338;
        distanceMatrix [6][9] = 727;
        distanceMatrix [6][10] = 438;
        distanceMatrix [7][11] = 697;
        distanceMatrix [7][12] = 585;
        distanceMatrix [7][8] = 355;
        distanceMatrix [8][12] = 626;
        distanceMatrix [8][13] = 536;
        distanceMatrix [8][14] = 486;
        distanceMatrix [8][9] = 100;
        distanceMatrix [9][14] = 444;
        distanceMatrix [9][15] = 455;
        distanceMatrix [9][10] = 675;
        distanceMatrix [10][15] = 742;
        distanceMatrix [10][16] = 624;
        distanceMatrix [11][17] = 430;
        distanceMatrix [11][18] = 504;
        distanceMatrix [11][12] = 388;
        distanceMatrix [12][18] = 337;
        distanceMatrix [12][19] = 416;
        distanceMatrix [12][13] = 293;
        distanceMatrix [13][19] = 204;
        distanceMatrix [13][14] = 165;
        distanceMatrix [14][19] = 343;
        distanceMatrix [14][20] = 187;
        distanceMatrix [14][15] = 392;
        distanceMatrix [15][20] = 490;
        distanceMatrix [15][21] = 404;
        distanceMatrix [15][16] = 215;
        distanceMatrix [16][21] = 435;
        distanceMatrix [17][22] = 150;
        distanceMatrix [17][18] = 416;
        distanceMatrix [18][22] = 253;
        distanceMatrix [18][26] = 344;
        distanceMatrix [18][19] = 340;
        distanceMatrix [19][26] = 453;
        distanceMatrix [19][27] = 562;
        distanceMatrix [19][23] = 192;
        distanceMatrix [19][20] = 255;
        distanceMatrix [20][23] = 291;
        distanceMatrix [20][24] = 279;
        distanceMatrix [20][21] = 244;
        distanceMatrix [21][24] = 258;
        distanceMatrix [22][25] = 242;
        distanceMatrix [22][26] = 392;
        distanceMatrix [23][27] = 415;
        distanceMatrix [23][28] = 190;
        distanceMatrix [23][24] = 249;
        distanceMatrix [24][29] = 614;
        distanceMatrix [25][30] = 205;
        distanceMatrix [25][31] = 160;
        distanceMatrix [25][26] = 282;
        distanceMatrix [26][31] = 255;
        distanceMatrix [26][36] = 530;
        distanceMatrix [26][32] = 597;
        distanceMatrix [26][27] = 203;
        distanceMatrix [27][32] = 532;
        distanceMatrix [27][33] = 197;
        distanceMatrix [27][34] = 186;
        distanceMatrix [27][28] = 145;
        distanceMatrix [28][34] = 175;
        distanceMatrix [28][29] = 244;
        distanceMatrix [29][34] = 237;
        distanceMatrix [30][31] = 252;
        distanceMatrix [31][35] = 212;
        distanceMatrix [31][36] = 434;
        distanceMatrix [32][36] = 156;
        distanceMatrix [32][37] = 129;
        distanceMatrix [32][33] = 302;
        distanceMatrix [33][37] = 397;
        distanceMatrix [33][38] = 354;
        distanceMatrix [33][34] = 160;
        distanceMatrix [34][38] = 425;
        distanceMatrix [35][36] = 200;
        distanceMatrix [37][39] = 62;
        distanceMatrix [37][38] = 103;
        distanceMatrix [38][39] = 124;
        distanceMatrix [38][40] = 127;
        distanceMatrix [38][41] = 268;
        distanceMatrix [39][40] = 108;
        distanceMatrix [40][41] = 193;
        distanceMatrix [41][43] = 111;
        distanceMatrix [41][44] = 165;
        distanceMatrix [41][42] = 153;
        distanceMatrix [42][44] = 236;
        distanceMatrix [42][45] = 106;
        distanceMatrix [43][46] = 72;
        distanceMatrix [43][44] = 101;
        distanceMatrix [44][46] = 45;
        distanceMatrix [44][45] = 68;
        distanceMatrix [45][47] = 139;
        

        // initialize vertices
        vertex[] vertexArray = new vertex[state];
        for (int i = 0; i < state; i++) {
            vertexArray[i] = new vertex();
            vertexArray[i].known = false;
            vertexArray[i].distance = 9999; // infinity
            vertexArray[i].previousvertex = 0;
        }
        vertexArray[0].distance = 0; // set source vertex distance to 0
        
        // run Dijkstra's algorithm
        for (int i = 0; i < state-1; i++) {
            int minDistance = 9999;
            int currentvertex = 0;
            for (int j = 0; j < state; j++) {
                if (!vertexArray[j].known && vertexArray[j].distance < minDistance) {
                    currentvertex = j;
                    minDistance = vertexArray[j].distance;
                }
            }
            vertexArray[currentvertex].known = true;
            for (int k = 0; k < state; k++) {
                if (!vertexArray[k].known && distanceMatrix[currentvertex][k] != 0 && vertexArray[currentvertex].distance + distanceMatrix[currentvertex][k] < vertexArray[k].distance) {
                    vertexArray[k].distance = vertexArray[currentvertex].distance + distanceMatrix[currentvertex][k];
                    vertexArray[k].previousvertex = currentvertex;
                }
            }
        }
        
        // print shortest distances
        for (int i = 0; i < state; i++) {
            System.out.println("The shortest distance between state[0] and state[" + i + "] is: " + vertexArray[i].distance);
        }
    }
}
