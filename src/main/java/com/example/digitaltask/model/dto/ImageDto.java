package com.example.digitaltask.model.dto;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDto {


    private String name;
    private String type;
    private byte[] imageData;
}