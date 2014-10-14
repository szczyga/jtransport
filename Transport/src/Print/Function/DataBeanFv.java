package Print.Function;

public class DataBeanFv {
	
        private String name;
        private String nr_rej;
        private String date;
        private String what_do;
        private double hours;
        private double hours50;
        private double hours100;
        private double hours200;
        private double km;
        private double idle;
        private String comment;

        
//      Name
        public void setName(String name) {
                this.name = name;
        }
       
        public String getName() {
                return name;
        }
//        *************************
        
//      Nr rej
        public void setNr_rej(String nr_rej) {
            this.nr_rej = nr_rej;
	    }
	   
	    public String getNr_rej() {
	            return nr_rej;
	    }
//	    *****************************
	    
//	    Data
        public void setDate(String date) {
            this.date = date;
	    }
	   
	    public String getDate() {
	            return date;
	    }	    
//	    ************************
	    
//	    what_do
        public void setWhat_do(String what_do) {
            this.what_do = what_do;
	    }
	   
	    public String getWhat_do() {
	            return what_do;
	    }	    
//	    ************************
	    
//	    hours
        public void setHours(double hours) {
            this.hours = hours;
	    }
	   
	    public double getHours() {
	            return hours;
	    }	    
//	    ************************
	    
//	    hours50
        public void setHours50(double hours50) {
            this.hours50 = hours50;
	    }
	   
	    public double getHours50() {
	            return hours50;
	    }	    
//	    ************************
	    
//	    hours100
        public void setHours100(double hours100) {
            this.hours100 = hours100;
	    }
	   
	    public double getHours100() {
	            return hours100;
	    }	    
//	    ************************
	    
//	    hours200
        public void setHours200(double hours200) {
            this.hours200 = hours200;
	    }
	   
	    public double getHours200() {
	            return hours200;
	    }	    
//	    ************************
	    
//	    km
        public void setKm(double km) {
            this.km = km;
	    }
	   
	    public double getKm() {
	            return km;
	    }	    
//	    ************************
	    
//	    idle
        public void setIdle(double idle) {
            this.idle = idle;
	    }
	   
	    public double getIdle() {
	            return idle;
	    }	    

//	    comment
        public void setComment(String comment) {
            this.comment = comment;
	    }
	   
	    public String getComment() {
	            return comment;
	    }	    
//	    ************************
}