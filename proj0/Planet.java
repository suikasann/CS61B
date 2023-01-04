public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double g = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        return Math.sqrt((this.xxPos - p.xxPos)*(this.xxPos - p.xxPos) + (this.yyPos - p.yyPos)*(this.yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p){
        return g * this.mass * p.mass / (this.calcDistance(p) * this.calcDistance(p));
    }

    public double calcForceExertedByX(Planet p){
        return this.calcForceExertedBy(p) * (p.xxPos-this.xxPos) / this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p){
        return this.calcForceExertedBy(p) * (p.yyPos-this.yyPos) / this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet allPlanets[]){
        double netX = 0;
        for(int i = 0; i<allPlanets.length; i++){
            if(this.equals(allPlanets[i])){
                continue;
            }
            netX = netX + this.calcForceExertedByX(allPlanets[i]);
        }
        return netX;
    }

    public double calcNetForceExertedByY(Planet allPlanets[]){
        double netY = 0;
        for(int i = 1; i<allPlanets.length; i++){
            if(this.equals(allPlanets[i])){
                continue;
            }
            netY = netY + this.calcForceExertedByY(allPlanets[i]);
        }
        return netY;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX / this.mass;
        double aY = fY / this.mass;

        xxVel = this.xxVel + dt * aX;
        yyVel = this.yyVel + dt * aY;

        xxPos = this.xxPos + dt * xxVel;
        yyPos = this.yyPos + dt * yyVel;

    }
    
    public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);

		/* Shows the drawing to the screen, and waits 2000 milliseconds. */
		StdDraw.show();
		StdDraw.pause(1000);		
    }
}
