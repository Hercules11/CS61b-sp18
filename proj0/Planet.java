public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double delta_x = this.xxPos - p.xxPos;
        double delta_y = this.yyPos - p.yyPos;
        return Math.sqrt(delta_x*delta_x + delta_y*delta_y);
    }

    public double calcForceExertedBy(Planet p) {
        double product_mass = p.mass * this.mass;
        double square_dis = (this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) +
                (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos);
        return G * product_mass / square_dis;
    }

    public double calcForceExertedByX(Planet p) {
        double cos_value = (p.xxPos -this.xxPos) / calcDistance(p); // pay attention to direction of force.
        double total_force = calcForceExertedBy(p);
        return cos_value * total_force;
    }

    public double calcForceExertedByY(Planet p) {
        double sin_value = (p.yyPos - this.yyPos) / calcDistance(p);
        double total_force = calcForceExertedBy(p);
        return sin_value * total_force;
    }

    public double calcNetForceExertedByX(Planet[] p) {
        double xForces = 0;
        for(Planet planet:p) {
            if(!this.equals(planet)) {
                xForces += calcForceExertedByX(planet);
            }
        }
        return xForces;
    }

    public double calcNetForceExertedByY(Planet[] p) {
        double yForces = 0;
        for(Planet planet:p) {
            if(!this.equals(planet)) {
                yForces += calcForceExertedByY(planet);
            }
        }
        return yForces;
    }

    public void update(double delta_t, double delta_fx, double delta_fy) {
        double a_x = delta_fx / this.mass;
        double a_y = delta_fy / this.mass;
        this.xxVel += a_x * delta_t;
        this.yyVel += a_y * delta_t;
        this.xxPos += delta_t * this.xxVel;
        this.yyPos += delta_t * this.yyVel;
    }

    public void draw() {
        String file_name = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, file_name);
    }
}