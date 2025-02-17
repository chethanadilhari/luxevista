package com.chethana.luxevista;

public class ServiceReservation {


        private String serviceId;
        private String serviceName;
        private String serviceImageUrl;
        private String serviceDuration;
        private long servicePrice;
        private String date;
        private String time;


        // Constructor to initialize the ServiceReservation object
        public ServiceReservation(String serviceId, String serviceName, String serviceImageUrl,
                                  String serviceDuration, long servicePrice, String date,
                                  String time ) {
            this.serviceId = serviceId;
            this.serviceName = serviceName;
            this.serviceImageUrl = serviceImageUrl;
            this.serviceDuration = serviceDuration;
            this.servicePrice = servicePrice;
            this.date = date;
            this.time = time;

        }

        // Getters and Setters for each field
        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getServiceImageUrl() {
            return serviceImageUrl;
        }

        public void setServiceImageUrl(String serviceImageUrl) {
            this.serviceImageUrl = serviceImageUrl;
        }

        public String getServiceDuration() {
            return serviceDuration;
        }

        public void setServiceDuration(String serviceDuration) {
            this.serviceDuration = serviceDuration;
        }

        public long getServicePrice() {
            return servicePrice;
        }

        public void setServicePrice(long servicePrice) {
            this.servicePrice = servicePrice;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }




    }


