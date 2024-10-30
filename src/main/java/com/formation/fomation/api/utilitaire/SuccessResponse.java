package com.formation.fomation.api.utilitaire;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse {
    private int status;
    private String message;
    private String details;
}