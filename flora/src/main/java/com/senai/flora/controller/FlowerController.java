package com.senai.flora.controller;

import com.senai.flora.serviceImpl.FlowerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/flowers")
public class FlowerController {
    @Autowired
    FlowerServiceImpl service;

    //TODO
}
