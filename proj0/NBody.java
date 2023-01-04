public class NBody{
    
    public static double readRadius(String filename){
        In in = new In(filename);

		int n = in.readInt();
		double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int n = in.readInt();
        double radious = in.readDouble();
        Planet[] allPlanets = new Planet[n];
        for(int i=0; i<n; i++){
            Planet p = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
            allPlanets[i] = p;
        }
        return allPlanets;
    }

    public static void main(String[] args){
        double T = Double. parseDouble(args[0]);
        double dt = Double. parseDouble(args[1]);
        String filename = args[2];

        double radius = NBody.readRadius(filename);
        Planet[] allPlanets = NBody.readPlanets(filename);

        /** drawing the background */
        String background = "images/starfield.jpg";
        /** Sets up the universe so it goes from 
		  * -radius, -radius up to radius, radius */
		StdDraw.setScale(-radius, radius);

		/* Clears the drawing window. */
		StdDraw.clear();

		StdDraw.picture(0, 0, background);
	
		/* Shows the drawing to the screen, and waits 2000 milliseconds. */
		StdDraw.show();
		StdDraw.pause(1000);

        /** drawing planets */
        for(int i = 0; i<allPlanets.length; i++){
            allPlanets[i].draw();
        }

        StdDraw.enableDoubleBuffering();

        for(double time = 0; time<=T; time+=dt){
            double[] xForces = new double[allPlanets.length];
            double[] yForces = new double[allPlanets.length];

            for(int i = 0; i<allPlanets.length; i++){
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
            }

            for(int i = 0; i<allPlanets.length; i++){
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.setScale(-radius, radius);

            /* Clears the drawing window. */
            StdDraw.clear();

            StdDraw.picture(0, 0, background);
        
            /* Shows the drawing to the screen, and waits 2000 milliseconds. */
            StdDraw.show();
            StdDraw.pause(1000);

            /** drawing planets */
            for(int i = 0; i<allPlanets.length; i++){
                allPlanets[i].draw();
            }
        }
        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for(int i = 0; i<allPlanets.length; i++){
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", 
                allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel, 
                allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
        }
    }
}