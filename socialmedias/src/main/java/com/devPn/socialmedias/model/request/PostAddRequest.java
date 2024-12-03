package com.devPn.socialmedias.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class PostAddRequest {
        private int userId;
        private String caption;
    }

