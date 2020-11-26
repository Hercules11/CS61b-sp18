public class NBody{
    public static double readRadius(String file_name) {
        In in = new In(file_name);
        int first_int = in.readInt();
        return in.readDouble();
    }

    /**
     * @sourse https://github.com/seriouszyx/cs61b/blob/master/proj0/NBody.java#L15
     * @param file_name given a file name of data
     * @return an array of planets.
     */
    public static Planet[] readPlanets(String file_name) {
        In in = new In(file_name);
        int first_int = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[first_int]; // built_in data types is the same as users defined.
        int num = 0;
        while(num < first_int) {
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets[num++] = new Planet(xp, yp, xv, yv, m, img);
        }
        return planets;
    }

    /**
     * .\StdDraw.java:1219: 错误: 编码 GBK 的不可映射字符 (0x93)
     * 解决办法： javac NBody.java  -Xlint:deprecation -encoding UTF8
     * @param args
     */
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Planet p : planets) {
            p.draw();
        }

        /**
         * Animations
         * @sourse https://github.com/seriouszyx/cs61b/blob/master/proj0/NBody.java
         */
        StdDraw.enableDoubleBuffering();

        double time = 0;
        int lengthOfPlanets = planets.length;
        while(time <= T) {
            double[] xForces = new double[lengthOfPlanets];
            double[] yForces = new double[lengthOfPlanets];
            for(int j=0; j<lengthOfPlanets; j++) {
                xForces[j] = planets[j].calcNetForceExertedByX(planets);
                yForces[j] = planets[j].calcNetForceExertedByY(planets);
            }
            for(int i=0; i<lengthOfPlanets; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg"); // every loop, re-depict.
            for(Planet p:planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

        StdOut.printf("%d\n", lengthOfPlanets);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < lengthOfPlanets; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }
}