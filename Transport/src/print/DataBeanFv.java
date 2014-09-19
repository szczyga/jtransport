package print;

public class DataBeanFv {
	
        private String name;
        private String nr_rej;
        private String date;
        private String what_do;
        private int hours;
        private int hours50;
        private int hours100;
        private int hours200;
        private int km;
        private int idle;
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
        public void setHours(int hours) {
            this.hours = hours;
	    }
	   
	    public int getHours() {
	            return hours;
	    }	    
//	    ************************
	    
//	    hours50
        public void setHours50(int hours50) {
            this.hours50 = hours50;
	    }
	   
	    public int getHours50() {
	            return hours50;
	    }	    
//	    ************************
	    
//	    hours100
        public void setHours100(int hours100) {
            this.hours100 = hours100;
	    }
	   
	    public int getHours100() {
	            return hours100;
	    }	    
//	    ************************
	    
//	    hours200
        public void setHours200(int hours200) {
            this.hours200 = hours200;
	    }
	   
	    public int getHours200() {
	            return hours200;
	    }	    
//	    ************************
	    
//	    km
        public void setKm(int km) {
            this.km = km;
	    }
	   
	    public int getKm() {
	            return km;
	    }	    
//	    ************************
	    
//	    idle
        public void setIdle(int idle) {
            this.idle = idle;
	    }
	   
	    public int getIdle() {
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