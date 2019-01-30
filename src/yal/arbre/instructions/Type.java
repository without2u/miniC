package yal.arbre.instructions;


    public enum Type {


        ENTIER("ENTIER"),BOOLEAN("BOOLEAN");


        private String type;

        private Type(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }


    }

