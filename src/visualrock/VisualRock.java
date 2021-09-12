package visualrock;

import java.util.Scanner;

public class VisualRock {

    public static final double ACC = -9.81;

    public static String makeline(String s, int len) {
        String ans = " ";
        for (int n = 1; n <= len; n++) {
            ans += s;
        }
        return ans;
    }

    public static void main(String[] args) {
        double highest = 0;
        double farthest = 0;
        double maxTime = 0;
        int thetaDegrees;
        double theta;
        double v0;
        double xpos;
        double ypos;
        double halfTime;
        double totalTime = 0;
        double maxHeight = 0;
        double maxDist = 0;
        double t;
        int lineno;
        int charno;
        double vx;
        double vy;
        Scanner gdk = new Scanner(System.in);
        final int nlines = 20;
        final int nchars = 50;
        String lines[] = new String[nlines];
        Rock rocks[] = new Rock[3];
        for (int n = 0; n < lines.length; n++) {
            lines[n] = makeline(" ", nchars);
        }
        for (int n = 0; n < 3; n++) {
            System.out.print("Can you give me an angle in degrees (10 to 80): ");
            thetaDegrees = gdk.nextInt();
            theta = Math.PI * (thetaDegrees / 180.0);
            System.out.print("Can you give me an initial velocity (10 to 100): ");
            v0 = gdk.nextDouble();
            rocks[n] = new Rock(thetaDegrees, v0);
            System.out.println("Rock Number " + n + rocks[n].toString());
            highest = Math.max(highest, rocks[n].maxHeight());
            farthest = Math.max(farthest, rocks[n].maxDis());
            maxTime = Math.max(maxTime, rocks[n].totalTime());
        }
        System.out.println(String.format("Highest = %.2f Farthest = %.2f", highest, farthest));
        for (t = 0; t < maxTime; t += maxTime / nchars) {
            for (int n = 0; n < 3; n++) {
                xpos = rocks[n].xpos(t);
                ypos = rocks[n].ypos(t);
                lineno = (int) Math.round((ypos / highest) * (nlines - 1));
                charno = (int) Math.round((xpos / farthest) * (nchars - 1));
                if (0 <= lineno && lineno < nlines && 0 <= charno && charno < nchars) {
                    lines[lineno] = lines[lineno].substring(0, charno) + "*" + lines[lineno].substring(charno + 1, nchars);
                }
                if (rocks[n].ypos(t) > 0) {
                LineChart.addPoint("Rocks " + n, t, rocks[n].ypos(t));
                }
            }
        }

        //String topborder = makeline("-", nchars + 2);
        //String bottomborder = makeline("-", nchars - 2);
        //System.out.println(topborder);
        for (int n = 0; n < 1; n++) {
            LineChart.makeGraphic(lines[n]);
        //    System.out.println("|" + lines[n] + "|");
        }
        //System.out.println(bottomborder);
    }
}