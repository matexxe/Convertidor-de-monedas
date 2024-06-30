package com.aluracursos.convert.model;

public class Monedas {

    public class Divisa {
        private String code;
        private double rate;

        public Divisa(String code, double rate) {
            this.code = code;
            this.rate = rate;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        @Override
        public String toString() {
            return "Moneda{" +
                    "code='" + code + '\'' +
                    ", rate=" + rate +
                    '}';
        }
    }


}
